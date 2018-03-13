package com.spring.statemachine.internal;

import org.springframework.statemachine.StateMachineContext;

import java.io.Serializable;

public interface StateMachineContextEntity<S, E> extends Serializable {

    StateMachineContext<S, E> getStateMachineContext();

    void setStateMachineContext(StateMachineContext<S, E> context);

}
