package com.spring.statemachine.data.redis;


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

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisPersistConfigTest {

    @Autowired
    StateMachineAdapter stateMachineAdapter;

    @Test
    public void test() throws Exception {
        StateMachine<StateEnum, EventEnum> stateMachine = stateMachineAdapter.create();
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S1);
        stateMachine.sendEvent(EventEnum.E1);
        stateMachineAdapter.persist(stateMachine, "1");
        stateMachine = stateMachineAdapter.restore("1");
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S2);
        StateMachine<StateEnum, EventEnum> stateMachine2 = stateMachineAdapter.create();
        stateMachineAdapter.persist(stateMachine2, "2");
        stateMachine = stateMachineAdapter.restore("2");
        Assert.assertEquals(stateMachine.getState().getId(), StateEnum.S1);
    }

}