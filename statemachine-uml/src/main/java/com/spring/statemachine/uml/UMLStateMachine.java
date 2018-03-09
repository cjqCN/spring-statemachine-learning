package com.spring.statemachine.uml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;
import org.springframework.stereotype.Component;

@Component
public class UMLStateMachine {

	@Configuration
	@EnableStateMachine
	public static class Config1 extends StateMachineConfigurerAdapter<String, String> {

		/**
		 * 配置状态机属性
		 */
		@Override
		public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
			model.withModel().factory(modelFactory());
		}

		/**
		 * 生成UML状态机ModelFactory
		 * 
		 * @return StateMachineModelFactory
		 */
		@Bean
		public StateMachineModelFactory<String, String> modelFactory() {
			// 导入UML文件
			return new UmlStateMachineModelFactory("/uml/statemachine-uml.uml");
		}

	}
}
