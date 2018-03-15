package com.spring.statemachine.sample.example;

import com.spring.statemachine.sample.Events;
import com.spring.statemachine.sample.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


@Configuration
@EnableStateMachine(name = "3")
public class ForkJoinStateMachine extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI)
                .fork(States.S1)
                .join(States.S5)
                .state(States.S2)
                .state(States.S3)
                .state(States.S4);

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.SI)
                .target(States.S1)
                .event(Events.E1)
                .and()
                .withExternal()
                .source(States.S3)
                .target(States.S4)
                .event(Events.E4)
                .and()
                .withFork()
                .source(States.S1)
                .target(States.S2)
                .target(States.S3)
                .and()
                .withJoin()
                .source(States.S2)
                .source(States.S4)
                .target(States.S5);
    }


}
