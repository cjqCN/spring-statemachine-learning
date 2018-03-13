package com.spring.statemachine.internal;

import com.spring.statemachine.EventEnum;
import com.spring.statemachine.StateEnum;
import org.springframework.statemachine.StateMachineContext;

public class EnityWithSateMachine implements StateMachineContextEntity<StateEnum, EventEnum> {

    Long userId;

    StateMachineContext<StateEnum, EventEnum> context;

    @Override
    public StateMachineContext getStateMachineContext() {
        return context;
    }


    @Override
    public void setStateMachineContext(StateMachineContext context) {
        this.context = context;
    }
}
