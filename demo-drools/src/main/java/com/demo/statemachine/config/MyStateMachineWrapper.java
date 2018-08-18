package com.demo.statemachine.config;

import com.demo.statemachine.vo.Events;
import com.demo.statemachine.vo.States;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
public class MyStateMachineWrapper {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    public void setStateMachine( StateMachine<States, Events> stateMachine ) {
        this.stateMachine = stateMachine;
    }

    public StateMachine<States, Events> getStateMachine() {
        return this.stateMachine;
    }

}
