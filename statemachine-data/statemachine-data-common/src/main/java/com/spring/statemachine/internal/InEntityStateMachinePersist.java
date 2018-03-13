package com.spring.statemachine.internal;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

public class InEntityStateMachinePersist<S, E> implements StateMachinePersist<S, E, StateMachineContextEntity<S, E>> {

    @Override
    public void write(StateMachineContext<S, E> context, StateMachineContextEntity<S, E> contextObj) throws Exception {
        contextObj.setStateMachineContext(context);
    }

    @Override
    public StateMachineContext<S, E> read(StateMachineContextEntity<S, E> contextObj) throws Exception {
        return contextObj.getStateMachineContext();
    }
}
