package com.spring.statemachine.sample.example;

import com.spring.statemachine.sample.Events;
import com.spring.statemachine.sample.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;


import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "2")
public class ChoiceStateMachine extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI)
                .choice(States.S1)
                .end(States.SF)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withChoice()
                .source(States.S1)
                .first(States.S2, s2Guard())
                .then(States.S3, s3Guard())
                .last(States.S4)
                .and()
                .withExternal()
                .source(States.SI)
                .target(States.S1)
                .event(Events.E1);
    }

    @Bean
    public Guard<States, Events> s2Guard() {
        return new Guard<States, Events>() {
            @Override
            public boolean evaluate(StateContext<States, Events> context) {
                return false;
            }
        };
    }

    @Bean
    public Guard<States, Events> s3Guard() {
        return new Guard<States, Events>() {
            @Override
            public boolean evaluate(StateContext<States, Events> context) {
                return true;
            }
        };
    }

}



