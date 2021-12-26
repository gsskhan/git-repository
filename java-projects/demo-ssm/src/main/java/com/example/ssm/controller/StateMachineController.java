package com.example.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
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

	private StateMachine<States, Events> currentShoppingStateMachine;

	private StateMachine<States, Events> currentDocumentStateMachine;

	@Autowired
	private StateMachineService<States, Events> shoppingMachineService;

	@Autowired
	private StateMachineService<States, Events> documentMachineService;

	/**
	 * To begin shopping flow
	 * 
	 * @param machineId
	 * @return
	 */
	@GetMapping(path = "/shopping/start/{id}")
	public String startShopping(@PathVariable(name = "id") String machineId) {
		log.info("StateMachineController... startShopping method... started.");

		try {
			currentShoppingStateMachine = getShoppingStateMachine(machineId);
		} catch (Exception e) {
			log.error("StateMachineController... startShopping method... error occured.", e);
			return "error";
		}

		log.info("StateMachineController... startShopping method... state machine [{}:{}] initialized to state [{}].",
				currentShoppingStateMachine.getId(), currentShoppingStateMachine.getUuid(),
				currentShoppingStateMachine.getState().getId());
		return "success";
	}

	/**
	 * To proceed in shopping flow
	 * 
	 * @param machineId
	 * @param eventName
	 * @return
	 */
	@GetMapping(path = "/shopping/proceed/{id}/{event}")
	public String proceedShopping(@PathVariable(name = "id") String machineId,
			@PathVariable(name = "event") String eventName) {
		log.info("StateMachineController... proceedShopping method... started.");

		try {
			currentShoppingStateMachine = getShoppingStateMachine(machineId);
			log.info("StateMachineController... proceedShopping method... state machine [{}:{}] started at state [{}].",
					currentShoppingStateMachine.getId(), currentShoppingStateMachine.getUuid(),
					currentShoppingStateMachine.getState().getId());

			// Sending Event
			currentShoppingStateMachine
					.sendEvent(Mono
							.just(MessageBuilder.withPayload(Events.valueOf(eventName.toUpperCase().trim())).build()))
					.blockLast();
		} catch (Exception e) {
			log.error("StateMachineController... proceedShopping method... error occured.", e);
			return "error";
		}

		log.info("StateMachineController... proceedShopping method... state machine [{}:{}] proceeded to state [{}].",
				currentShoppingStateMachine.getId(), currentShoppingStateMachine.getUuid(),
				currentShoppingStateMachine.getState().getId());
		return "success";
	}

	/**
	 * Synchronized method for shopping state machine
	 * 
	 * @param machineId
	 * @return
	 * @throws Exception
	 */
	private synchronized StateMachine<States, Events> getShoppingStateMachine(String machineId) throws Exception {
		if (currentShoppingStateMachine == null) {
			currentShoppingStateMachine = shoppingMachineService.acquireStateMachine(machineId);
			currentShoppingStateMachine.startReactively().block();
		} else if (!ObjectUtils.nullSafeEquals(currentShoppingStateMachine.getId(), machineId)) {
			shoppingMachineService.releaseStateMachine(currentShoppingStateMachine.getId());
			currentShoppingStateMachine.stopReactively().block();
			currentShoppingStateMachine = shoppingMachineService.acquireStateMachine(machineId);
			currentShoppingStateMachine.startReactively().block();
		}
		return currentShoppingStateMachine;
	}

	/**
	 * To begin document flow
	 * 
	 * @param machineId
	 * @return
	 */
	@GetMapping(path = "/document/start/{id}")
	public String startDocumentFlow(@PathVariable(name = "id") String machineId) {
		log.info("StateMachineController... startDocumentFlow method... started.");

		try {
			currentDocumentStateMachine = getDocumentStateMachine(machineId);
		} catch (Exception e) {
			log.error("StateMachineController... startDocumentFlow method... error occured.", e);
			return "error";
		}

		log.info(
				"StateMachineController... startDocumentFlow method... state machine [{}:{}] initialized to state [{}].",
				currentDocumentStateMachine.getId(), currentDocumentStateMachine.getUuid(),
				currentDocumentStateMachine.getState().getId());
		return "success";
	}

	/**
	 * To proceed in document flow
	 * 
	 * @param machineId
	 * @param eventName
	 * @return
	 */
	@GetMapping(path = "/document/proceed/{id}/{event}")
	public String proceedDocumentFlow(@PathVariable(name = "id") String machineId,
			@PathVariable(name = "event") String eventName) {
		log.info("StateMachineController... proceedDocumentFlow method... started.");

		try {
			currentDocumentStateMachine = getDocumentStateMachine(machineId);
			log.info(
					"StateMachineController... proceedDocumentFlow method... state machine [{}:{}] started at state [{}].",
					currentDocumentStateMachine.getId(), currentDocumentStateMachine.getUuid(),
					currentDocumentStateMachine.getState().getId());

			// Sending Event
			currentDocumentStateMachine
					.sendEvent(Mono
							.just(MessageBuilder.withPayload(Events.valueOf(eventName.toUpperCase().trim())).build()))
					.blockLast();
		} catch (Exception e) {
			log.error("StateMachineController... proceedDocumentFlow method... error occured.", e);
			return "error";
		}

		log.info(
				"StateMachineController... proceedDocumentFlow method... state machine [{}:{}] proceeded to state [{}].",
				currentDocumentStateMachine.getId(), currentDocumentStateMachine.getUuid(),
				currentDocumentStateMachine.getState().getId());
		return "success";
	}

	/**
	 * Synchronized method for document state machine
	 * 
	 * @param machineId
	 * @return
	 * @throws Exception
	 */
	private synchronized StateMachine<States, Events> getDocumentStateMachine(String machineId) throws Exception {
		if (currentDocumentStateMachine == null) {
			currentDocumentStateMachine = documentMachineService.acquireStateMachine(machineId);
			currentDocumentStateMachine.startReactively().block();
		} else if (!ObjectUtils.nullSafeEquals(currentDocumentStateMachine.getId(), machineId)) {
			documentMachineService.releaseStateMachine(currentDocumentStateMachine.getId());
			currentDocumentStateMachine.stopReactively().block();
			currentDocumentStateMachine = documentMachineService.acquireStateMachine(machineId);
			currentDocumentStateMachine.startReactively().block();
		}
		return currentDocumentStateMachine;
	}

}
