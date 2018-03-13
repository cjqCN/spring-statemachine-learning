package com.spring.statemachine.internal;

import com.spring.statemachine.EventEnum;
import com.spring.statemachine.StateEnum;
import com.spring.statemachine.StateMachineAdapter;
import com.spring.statemachine.StateMachineConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Import(StateMachineConfig.class)
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    class Config {
        @Bean
        public StateMachineAdapter<StateEnum, EventEnum, StateMachineContextEntity<StateEnum, EventEnum>> stateMachineAdapter(
                StateMachineFactory<StateEnum, EventEnum> stateMachineFactory,
                StateMachinePersister<StateEnum, EventEnum, StateMachineContextEntity<StateEnum, EventEnum>> stateMachinePersister) {
            return new StateMachineAdapter(stateMachineFactory, stateMachinePersister);
        }

        @Bean
        public InEntityStateMachinePersist<StateEnum, EventEnum> stateMachinePersist() {
            return new InEntityStateMachinePersist<StateEnum, EventEnum>();
        }

        @Bean
        public StateMachinePersister<StateEnum, EventEnum, StateMachineContextEntity<StateEnum, EventEnum>> stateMachinePersister(
                InEntityStateMachinePersist<StateEnum, EventEnum> inEntityStateMachinePersist) {
            return new DefaultStateMachinePersister<StateEnum, EventEnum, StateMachineContextEntity<StateEnum, EventEnum>>(inEntityStateMachinePersist) {
            };
        }
    }
}
