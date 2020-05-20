package com.demo.statemachine.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.StateMachine;

import com.demo.statemachine.config.MyStateMachineWrapper;
import com.demo.statemachine.config.StateMachineConfig;
import com.demo.statemachine.vo.Events;
import com.demo.statemachine.vo.States;

public class AppMain3{

    private static Logger log = LoggerFactory.getLogger("AppMain3");

    private static AnnotationConfigApplicationContext ctx;

    public static void main(String[] args) {
        log.info("State machine Demo program started...");
        
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(StateMachineConfig.class);
        ctx.refresh();
        
        MyStateMachineWrapper myStateMachine = ctx.getBean(MyStateMachineWrapper.class);
        StateMachine<States, Events> stateMachine = myStateMachine.getStateMachine();                
        runMyStateMachine(stateMachine);
        
        log.info("State machine Demo program completed...");
    }
    
    private static void runMyStateMachine(StateMachine<States, Events> stateMachine) {
    	stateMachine.start();
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);    	
        stateMachine.stop();
    }

}