package com.spring.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 *        +-----------+        E1       +-----------+
 *        |           | --------------> |           |
 *   ---> |     S1    |                 |     S2    |
 *        |           |       E2        |           |
 *        |           | <-------------- |           |
 *        +-----------+                 +-----------+
 * */
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> states) throws Exception {
        states
                .withStates()
                .initial(StateEnum.S1)
                .states(EnumSet.allOf(StateEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(StateEnum.S1)
                .target(StateEnum.S2)
                .event(EventEnum.E1)
                .and()
                .withExternal()
                .source(StateEnum.S2)
                .target(StateEnum.S1)
                .event(EventEnum.E2);
    }


}
