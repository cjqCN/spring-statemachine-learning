package com.spring.statemachine.sample.example;

import com.spring.statemachine.sample.Events;
import com.spring.statemachine.sample.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.StateConfigurer;

@Configuration
@EnableStateMachine(name = "4")
public class JunctionStateMachine extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.S1)
                .state(States.S2)
                .and()
                .withStates()
                .parent(States.S2)
                .initial(States.S2I)
                .state(States.S21)
                .state(States.S22)
                .history(States.S2H, StateConfigurer.History.SHALLOW).
                and()
                .withStates()
                .parent(States.S22)
                .initial(States.S22I)
                .state(States.S221)
                .state(States.S222);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withHistory()
                .source(States.S2H)
                .target(States.S22)
                .and()
                .withExternal()
                .source(States.S1)
                .target(States.S2)
                .event(Events.E2)
                .and()
                .withExternal()
                .source(States.S2)
                .target(States.S1)
                .event(Events.E1)
                .and()
                .withExternal()
                .source(States.S2)
                .target(States.S21)
                .event(Events.E21)
                .and()
                .withExternal()
                .source(States.S21)
                .target(States.S22)
                .event(Events.E22)
                .and()
                .withExternal()
                .source(States.S2)
                .target(States.S2H)
                .event(Events.E2H)
                .and()
                .withExternal()
                .source(States.S22)
                .target(States.S221)
                .event(Events.E221);
    }

}







