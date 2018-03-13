package com.spring.statemachine.data.redis;

import com.spring.statemachine.StateMachineConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(StateMachineConfig.class)
@SpringBootApplication
public class RedisStateMachineAppliction {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RedisStateMachineAppliction.class, args);
    }
}
