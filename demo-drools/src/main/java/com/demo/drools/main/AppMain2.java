package com.demo.drools.main;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.drools.conf.SpringConf;
import com.demo.drools.model.Message;

public class AppMain2 {

	private static Logger log = LoggerFactory.getLogger("AppMain2");
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConf.class);
		
		KieContainer kieContainer = ctx.getBean(KieContainer.class);
		
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
