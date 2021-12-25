package com.example.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssm.constants.Events;
import com.example.ssm.constants.States;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {

	private static final Logger log = LoggerFactory.getLogger(StateMachineController.class);
	
	private StateMachine<States, Events> stateMachine;

	@Autowired
	private StateMachineService<States, Events> stateMachineService;
	
	@Autowired
	private StateMachineListener<States, Events> stateMachineListener;

	@GetMapping(path = "/start/{id}")
	public String init(@PathVariable(name = "id") String machineId) {
		log.info("StateMachineController... init method... started.");
		
		try {
			stateMachine = getStateMachine(machineId);
		} catch (Exception e) {
			log.error("StateMachineController... init method... error occured.", e);
			return "error";
		}
		
		log.info("StateMachineController... init method... state machine [{}:{}] initialized to state [{}].",
				stateMachine.getId(), stateMachine.getUuid(), stateMachine.getState().getId());	
		return "success";
	}

	@GetMapping(path = "/proceed/{id}/{event}")
	public String proceed(@PathVariable(name = "id") String machineId, @PathVariable(name = "event") String eventName) {
		log.info("StateMachineController... proceed method... started.");	
		
		try {
			stateMachine = getStateMachine(machineId);
			log.info("StateMachineController... proceed method... state machine [{}:{}] started at state [{}].", 
					stateMachine.getId(), stateMachine.getUuid(), stateMachine.getState().getId());
			
			// Sending Event
			stateMachine.sendEvent(Mono.just(MessageBuilder
							.withPayload(Events.valueOf(eventName))
							.build()))
						.blockLast();
		} catch (Exception e) {
			log.error("StateMachineController... proceed method... error occured.", e);
			return "error";
		}
		
		log.info("StateMachineController... proceed method... state machine [{}:{}] proceeded to state [{}].", 
				stateMachine.getId(), stateMachine.getUuid(), stateMachine.getState().getId());
		return "success";
	}

	// Synchronized method for fetching persisted State Machine from repository.
	private synchronized StateMachine<States, Events> getStateMachine(String machineId) throws Exception {
		if (stateMachine == null) {
			stateMachine = stateMachineService.acquireStateMachine(machineId);
			stateMachine.addStateListener(stateMachineListener);
			stateMachine.startReactively().block();
		} else if (!ObjectUtils.nullSafeEquals(stateMachine.getId(), machineId)) {
			stateMachineService.releaseStateMachine(stateMachine.getId());
			stateMachine.stopReactively().block();
			stateMachine = stateMachineService.acquireStateMachine(machineId);
			stateMachine.addStateListener(stateMachineListener);
			stateMachine.startReactively().block();
		}
		return stateMachine;
	}

}
