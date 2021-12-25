package com.example.ssm.config;

import java.util.EnumSet;

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
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events>{
	
	private static final Logger log = LoggerFactory.getLogger(StateMachineConfig.class);
	
	@Autowired
	private JpaStateMachineRepository jpaStateMachineRepository;
	
	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {		
		config
			.withPersistence()
			.runtimePersister(stateMachineRuntimePersister());	
	}
	
	@Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
		states
			.withStates()
			.initial(States.SI)
			.states(EnumSet.allOf(States.class));
	}
	
	@Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
		transitions
			.withExternal().source(States.SI)
						   .target(States.S1)
						   .event(Events.E1)
			.and()
			.withExternal().source(States.S1)
						   .target(States.S2)
						   .event(Events.E2);
	}
	
	@Bean
	public StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister() {
		return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
	}
    
    @Bean
	public StateMachineService<States, Events> stateMachineService(StateMachineFactory<States, Events> stateMachineFactory,
			StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister) {
		return new DefaultStateMachineService<States, Events>(stateMachineFactory, stateMachineRuntimePersister);
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


}
