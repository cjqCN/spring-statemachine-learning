package com.spring.statemachine.jpa.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class PersistConfig {

    @Bean
    public StateMachineAdapter<State, Event, User> stateMachineAdapter(
            StateMachineFactory<State, Event> stateMachineFactory,
            StateMachinePersister<State, Event, User> persister) {
        return new StateMachineAdapter(stateMachineFactory, persister);
    }

    @Bean
    public StateMachinePersister<State, Event, User> persister(
            StateMachinePersist<State, Event, User> persist) {
        return new DefaultStateMachinePersister(persist);
    }

    @Bean
    public StateMachinePersist<State, Event, User> persist() {
        return new StateMachinePersist<State, Event, User>() {

            @Override
            public StateMachineContext<State, Event> read(
                    User user) throws Exception {
                return user.getStateMachineContext();
            }

            @Override
            public void write(StateMachineContext<State, Event> context,
                              User user) throws Exception {
                user.setStateMachineContext(context);
            }
        };
    }

}
