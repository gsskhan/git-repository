package org.demo.lucene;

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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class HelloLucene {
	
	private static Logger log = Logger.getLogger(HelloLucene.class);
	
	public static void main(String[] args) throws IOException, ParseException {
		log.info("program started ...");
		
		// specify analyzer for tokenizing text
		StandardAnalyzer analyzer = new StandardAnalyzer();
		
		// 1. create the index
		Directory index = new RAMDirectory();
		IndexWriterConfig irConfig = new IndexWriterConfig(analyzer);
		IndexWriter iw = new IndexWriter(index, irConfig); 
		
		addDoc(iw, "Spring in action", "AB1234l");
		addDoc(iw, "Struts in action", "AB1235l");
		addDoc(iw, "lucene in action", "AB1236l");
		addDoc(iw, "Dummy guide for spring", "AB1237l");
		iw.close();
		
		// 2. query
		String stringToSearch = "Spring";
		
	    // the "title" arg specifies the default field to use
	    // when no field is explicitly specified in the query.
	    Query q = new QueryParser("title", analyzer).parse(stringToSearch);
	    
	    // 3. search
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        // 4. display results
        log.info("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            log.info((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close();				
		
	}
	
	private static void addDoc(IndexWriter w, String titleOfBook, String isbnOfBook) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", titleOfBook, Field.Store.YES ));		
		// use a string field for isbn because we don't want it tokenized
		doc.add(new StringField("isbn", isbnOfBook, Field.Store.YES ));
		w.addDocument(doc);
	}
        

}
