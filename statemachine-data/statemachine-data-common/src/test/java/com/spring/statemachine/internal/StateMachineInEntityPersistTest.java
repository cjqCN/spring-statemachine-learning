package com.spring.statemachine.internal;

import com.spring.statemachine.EventEnum;
import com.spring.statemachine.StateEnum;
import com.spring.statemachine.StateMachineAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StateMachineInEntityPersistTest {

    @Autowired
    StateMachineAdapter<StateEnum, EventEnum, StateMachineContextEntity<StateEnum, EventEnum>> stateMachineAdapter;

    @Test
    public void test() {
        StateMachine<StateEnum, EventEnum> stateMachine = stateMachineAdapter.create();
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S1);
        EnityWithSateMachine enityWithSateMachine = new EnityWithSateMachine();
        stateMachine.sendEvent(EventEnum.E1);
        stateMachineAdapter.persist(stateMachine, enityWithSateMachine);
        StateMachine<StateEnum, EventEnum> stateMachine1 = stateMachineAdapter.restore(enityWithSateMachine);
        Assert.assertEquals(stateMachine1.getState().getId(), StateEnum.S2);
    }


}