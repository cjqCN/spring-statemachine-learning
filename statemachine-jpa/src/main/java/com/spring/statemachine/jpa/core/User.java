package com.spring.statemachine.jpa.core;

import lombok.Data;
import org.springframework.statemachine.StateMachineContext;


@Data
public class User {

    Long userId;
    State currentState;
    StateMachineContext<State, Event> stateMachineContext;

    public void setStateMachineContext(StateMachineContext context) {
        this.stateMachineContext = context;
        this.currentState = stateMachineContext.getState();
    }


}
