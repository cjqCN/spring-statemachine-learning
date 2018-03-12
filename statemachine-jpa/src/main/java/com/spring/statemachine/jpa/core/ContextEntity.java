package com.spring.statemachine.jpa.core;

import com.sun.corba.se.spi.ior.Identifiable;
import org.springframework.statemachine.StateMachineContext;

import java.io.Serializable;

public interface ContextEntity<S, E, ID extends Serializable> extends Identifiable {

    StateMachineContext<S, E> getStateMachineContext();

    void setStateMachineContext(StateMachineContext<S, E> context);

}
