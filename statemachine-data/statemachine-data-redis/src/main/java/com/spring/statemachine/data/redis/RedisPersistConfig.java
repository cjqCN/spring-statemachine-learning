package com.spring.statemachine.data.redis;

import com.spring.statemachine.EventEnum;
import com.spring.statemachine.StateEnum;
import com.spring.statemachine.StateMachineAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class RedisPersistConfig {

    @Bean
    public StateMachineAdapter<StateEnum, EventEnum, String> stateMachineAdapter(
            StateMachineFactory<StateEnum, EventEnum> stateMachineFactory,
            StateMachinePersister<StateEnum, EventEnum, String> stateMachinePersister) {
        return new StateMachineAdapter(stateMachineFactory, stateMachinePersister);
    }

    @Bean
    public StateMachinePersist<StateEnum, EventEnum, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<StateEnum, EventEnum> repository =
                new RedisStateMachineContextRepository<StateEnum, EventEnum>(connectionFactory);
        return new RepositoryStateMachinePersist<StateEnum, EventEnum>(repository);
    }

    @Bean
    public StateMachinePersister<StateEnum, EventEnum, String> stateMachinePersister(
            StateMachinePersist<StateEnum, EventEnum, String> stateMachinePersist) {
        return new RedisStateMachinePersister<StateEnum, EventEnum>(stateMachinePersist);
    }
}
