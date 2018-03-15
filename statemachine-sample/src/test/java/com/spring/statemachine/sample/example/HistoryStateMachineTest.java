package com.spring.statemachine.sample.example;

import com.spring.statemachine.sample.Events;
import com.spring.statemachine.sample.StateMachineCommands;
import com.spring.statemachine.sample.States;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.ObjectStateMachine;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineSystemConstants;

public class HistoryStateMachineTest {

    private AnnotationConfigApplicationContext context;
    private StateMachine<States,Events> machine;


    @Test
    public void test(){

    }



    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext();
        context.register(HistoryStateMachine.class, StateMachineCommands.class);
        context.refresh();
        machine = context.getBean(StateMachineSystemConstants.DEFAULT_ID_STATEMACHINE, ObjectStateMachine.class);
        machine.start();
    }


    @After
    public void clean() {
        machine.stop();
        context.close();
        context = null;
        machine = null;
    }
}