package com.demo.drools.main;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.drools.model.Message;

public class AppMain1 {
	
	private static Logger log = LoggerFactory.getLogger("AppMain1");
	
	public static void main(String[] args) {
		
		KieServices kieServices = KieServices.Factory.get();
		 
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/demo-message.drl"));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
 
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        
        KieSession kieSession = kieContainer.newKieSession();
        log.info("Kie session created...");
        
        Message msg = new Message();
        msg.setText("Hello");
        
        kieSession.setGlobal("log", log);
        
        kieSession.insert(msg);
        kieSession.fireAllRules();
        kieSession.dispose();
        
        log.info("Program completed ...");
		
	}	

}
