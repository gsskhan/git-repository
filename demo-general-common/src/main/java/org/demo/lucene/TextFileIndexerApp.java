package org.demo.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;

/**
 * This terminal application creates an Apache Lucene index in a folder and adds files into this index
 */

public class TextFileIndexerApp {
	
	private static StandardAnalyzer analyzer = new StandardAnalyzer();	
	
	private static Logger log = Logger.getLogger(TextFileIndexerApp.class);

	public static void main(String[] args) throws IOException, ParseException {
		log.info("program started ...");
		String filesLocation = "/tmp/toread";
		String indexLocation = "/tmp/index";
		
		// create indexer		
		FSDirectory directory = FSDirectory.open( new File(indexLocation).toPath());
		IndexWriterConfig irw = new IndexWriterConfig(analyzer);
		IndexWriter iw = new IndexWriter(directory, irw);
		
		// optional - to delete all existing docs in index
		log.info("Existing total docs in index: "+ iw.numDocs());
		if (iw.numDocs() > 0) {
			iw.deleteAll();
			log.info("all docs deleted from index ...");
		}
		
		// optional - And create input directory if it not exists
		File inputFilesDirectory = new File(filesLocation);
		if (inputFilesDirectory.isDirectory() == false ){
			inputFilesDirectory.mkdir();
			log.info(filesLocation + " directory created...");
		}
		
		// add files to index	
		for (File f : inputFilesDirectory.listFiles()) {
			Document doc = new Document();
			FileReader fr = new FileReader(f);
			doc.add(new TextField("contents", fr));
			doc.add(new StringField("filename", f.getName(), Field.Store.YES));
			doc.add(new StringField("path", f.getPath(), Field.Store.YES));
			iw.addDocument(doc);
			fr.close();
			log.info(f.getName() +" added to index...");
		}		
		log.info("Final total docs in index: "+ iw.numDocs());
		
		// close index
		iw.close();
		
		
		// search the string in file from lucene index.
		IndexReader ir = DirectoryReader.open(FSDirectory.open(new File(indexLocation).toPath()));
		IndexSearcher searcher = new IndexSearcher(ir);
		TopScoreDocCollector collector = TopScoreDocCollector.create(5);
		
		Query q = new QueryParser("contents", analyzer).parse("i am");
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		
		// Display results
        log.info("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            log.info((i + 1) + ". " + d.get("path"));
        }
        
        //close reader
        ir.close();
		
		
		
		
	}

}
