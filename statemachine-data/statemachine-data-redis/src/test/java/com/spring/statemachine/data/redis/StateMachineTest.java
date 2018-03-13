package com.spring.statemachine.data.redis;

import com.spring.statemachine.EventEnum;
import com.spring.statemachine.StateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StateMachineTest {

    @Autowired
    StateMachineFactory<StateEnum, EventEnum> stateMachineFactory;

    @Test
    public void test() {
        StateMachine<StateEnum, EventEnum> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.start();
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S1);
        stateMachine.sendEvent(EventEnum.E1);
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S1);
        stateMachine.sendEvent(EventEnum.E2);
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S1);
    }

}