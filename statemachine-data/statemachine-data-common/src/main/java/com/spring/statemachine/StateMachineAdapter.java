package com.spring.statemachine;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

@RequiredArgsConstructor
public class StateMachineAdapter<S, E, T> {

    final StateMachineFactory<S, E> stateMachineFactory;

    final StateMachinePersister<S, E, T> persister;

    @SneakyThrows
    public StateMachine<S, E> restore(T contextObject) {
        StateMachine<S, E> stateMachine = stateMachineFactory.getStateMachine();
        return persister.restore(stateMachine, contextObject);
    }

    @SneakyThrows
    public void persist(StateMachine<S, E> stateMachine, T order) {
        persister.persist(stateMachine, order);
    }

    public StateMachine<S, E> create() {
        StateMachine<S, E> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.start();
        return stateMachine;
    }

}
