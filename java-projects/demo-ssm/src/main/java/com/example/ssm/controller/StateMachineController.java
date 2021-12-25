package com.example.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
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
	private StateMachine<States, Events> stateMachine;
	
	@GetMapping("/status/now/{id}")
	public String getCurrentStatus(@PathVariable(name = "id") String machineId) {
		log.info("Current status of machine id [{}] is .", machineId);
		return null;		
	}
	
	@GetMapping(path = "/test")
	public String test( String machineId) {
		stateMachine.sendEvent(Events.E1);
		stateMachine.sendEvent(Events.E2);
		return "success";
		
	}
	
	

}
