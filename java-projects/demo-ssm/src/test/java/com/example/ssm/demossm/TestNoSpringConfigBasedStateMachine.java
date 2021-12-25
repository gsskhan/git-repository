package com.example.ssm.demossm;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

public class TestNoSpringConfigBasedStateMachine {
			
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		try {			
			// Configure State Machine
			StateMachineBuilder.Builder<String, String> builder 
			  = StateMachineBuilder.builder();
			
			builder.configureStates().withStates()
			  .initial("SI")
			  .state("S1")
			  .end("SF")
			  ;

			builder.configureTransitions()
			  .withExternal()
			  .source("SI").target("S1").event("E1")
			  .and().withExternal()
			  .source("S1").target("SF").event("E2");
			
			StateMachineListener<String, String> listner = new StateMachineListenerAdapter<String, String>() {
	            @Override
	            public void stateChanged(State<String, String> from, State<String, String> to) {
	            	if (from != null) {
		            	System.out.println("State changed from " + from.getId() + " to " + to.getId());	
					} else {
						System.out.println("State changed from " + from + " to " + to.getId());	
					}
	            }
	        };

			// State Machine init.
			StateMachine<String, String> machine1 = builder.build();
			machine1.addStateListener(listner);
			
			// Business logic
			System.out.println("Starting machine - " + machine1.getUuid());
			machine1.start();			
			machine1.sendEvent("E1");
			machine1.sendEvent("E2");
			System.out.println("finished sending events to machine - " + machine1.getUuid());
			
		} catch (Exception e) {
			System.err.println("Program failed.");
			e.printStackTrace();
		}

	}

}
