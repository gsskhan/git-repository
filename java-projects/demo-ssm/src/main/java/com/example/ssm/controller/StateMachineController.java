package com.example.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssm.constants.Events;
import com.example.ssm.constants.States;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {
	
	private static final Logger log = LoggerFactory.getLogger(StateMachineController.class);
	
	@Autowired
	private StateMachineFactory<States, Events> stateMachineFactory;
	
	@GetMapping("/status/now/{id}")
	public String getCurrentStatus(@PathVariable(name = "id") String machineId) {
		log.info("Current status of machine id [{}] is .", machineId);
		return null;		
	}
	
	@GetMapping(path = "/test")
	public String test( String machineId) {
		StateMachine<States, Events> stateMachine1 = stateMachineFactory.getStateMachine();
		StateMachine<States, Events> stateMachine2 = stateMachineFactory.getStateMachine();
		log.info("StateMachine 1 Id before start: {}", stateMachine1.getUuid());
		log.info("StateMachine 2 Id before start: {}", stateMachine2.getUuid());
		stateMachine1.start();
		stateMachine2.start();
		stateMachine1.sendEvent(Events.E1);
		stateMachine2.sendEvent(Events.E1);
		stateMachine1.sendEvent(Events.E2);
		log.info("StateMachine 1 Id after: {}", stateMachine1.getUuid());
		log.info("StateMachine 2 Id after: {}", stateMachine2.getUuid());
		return "success";		
	}
	
	

}
