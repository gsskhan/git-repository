package com.example.ssm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.state.State;

import com.example.ssm.constants.Events;
import com.example.ssm.constants.States;

@Configuration
public class StateMachineConfig {
	
	private static final Logger log = LoggerFactory.getLogger(StateMachineConfig.class);
	
	@Bean
	public StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister(
			JpaStateMachineRepository jpaStateMachineRepository) {
		return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
	}

	@Bean
	public StateMachineService<States, Events> shoppingMachineService(
			StateMachineFactory<States, Events> shoppingStateMachineFactory,
			StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister) {
		return new DefaultStateMachineService<States, Events>(shoppingStateMachineFactory,
				stateMachineRuntimePersister);
	}

	@Bean
	public StateMachineListener<States, Events> listener() {
		return new StateMachineListenerAdapter<States, Events>() {
			@Override
			public void stateChanged(State<States, Events> from, State<States, Events> to) {
				if (from == null) {
					log.info("State machine initialised in state [{}].", to.getId());
				} else {
					log.info("State changed from [{}] to [{}].", from.getId(), to.getId());
				}
			}
		};
	}	
	
	@Configuration
	@EnableStateMachineFactory(name = "shoppingStateMachineFactory")
	public static class ShoppingMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
		
		@Autowired
		private StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister;
		
		@Autowired
		private StateMachineListener<States, Events> listener;
		
		@Override
		public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {		
			config
			.withConfiguration()
				.listener(listener)
			.and()
			.withPersistence()
				.runtimePersister(stateMachineRuntimePersister);	
		}
		
		@Override
	    public void configure(StateMachineStateConfigurer<States, Events> states)
	            throws Exception {
			states
				.withStates()
				.initial(States.CHOOSE_ITEMS)
				.state(States.PAYMENT_COUNTER)
				.state(States.PACKAGE_HANDOVER);
		}
		
		@Override
	    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
	            throws Exception {
			transitions
				.withExternal().source(States.CHOOSE_ITEMS)
							   .target(States.PAYMENT_COUNTER)
							   .event(Events.BUY)
				.and()
				.withExternal().source(States.PAYMENT_COUNTER)
							   .target(States.PACKAGE_HANDOVER)
							   .event(Events.PAYMENT);
		}
		
	}

}
