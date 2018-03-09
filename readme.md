# spring statemachine
[官方文档](https://docs.spring.io/spring-statemachine/docs/1.2.8.RELEASE/reference/htmlsingle)

## 什么是spring statemachine
Spring Statemachine 是使用 Spring框架下的[状态机](https://zh.wikipedia.org/wiki/%E6%9C%89%E9%99%90%E7%8A%B6%E6%80%81%E6%9C%BA "状态机")概念创建的一种应用程序开发框架。它使得状态机结构层次化，简化了配置状态机的过程。

### [模块](https://docs.spring.io/spring-statemachine/docs/2.0.0.RELEASE/reference/htmlsingle/#modules "模块")
- spring-statemachine-core
- spring-statemachine-recipes-common
- spring-statemachine-kryo
- spring-statemachine-data-common
- spring-statemachine-data-jpa
- spring-statemachine-data-redis
- spring-statemachine-data-mongodb
- spring-statemachine-zookeeper
- spring-statemachine-test
- spring-statemachine-cluster
- spring-statemachine-uml
- spring-statemachine-autoconfigure
- spring-statemachine-bom
- spring-statemachine-starter

### 优点
- 有非常简单的例子以供快速开发一个一级状态机。
- 具有简便的方式配置多层次结构复杂的状态机。
- 可以灵活地配置状态区。
- 提供触发器、状态转移器、保护机制、状态转移动作和状态机事件监听器。
- 类型安全的配置适配器。
- 在spring应用中具有简便的创建模式。
- 多种通用的用例。
- 可基于Zookeeper实现分布式状态机。
- 与spring ioc整合。


### 适用情况
 
下列情况适合使用状态机：   

- 应用或者其一部分可以用状态表示。
- 希望将复杂的逻辑拆分，获得更清晰更简单的逻辑。
- 应用遇到并发问题，即异步导致的问题。

初步尝试适用状态机：

- 使用布尔标志和枚举建立状态模型
- 仅使用在应用生命周期内具有意义的状态。
- 使用有限的状态，每种状态或状态的组合都有意义和相应的处理方法。

## 名词解释

- State Machine  
  状态机，将状态结合、转移、事件整合到一起的机器

- State    
   一个不变的状态模型  
   主要由一个事件修改实体的状态

- Extended State  
  扩展状态是一组特殊的变量保存在一个状态机中，可以保存业务数据。

- Transition  
  源状态和目标状态之间的关系，它可能是复合过度的一部分，它规定在一个特定的事件发生时某个状态转移的情况。

- Event  
  事件，驱动状态转移的实体。
 
- Initial State  
  初始状态

- End State  
  终态

- History State  
  记录状态机最后的活动状态，存在两种模式

- Choice State  
  状态转移选择，相当于if/else

- Fork State  
  状态分支

- Join State  
  状态合并

- Region  
  区域，一个父状态与数个子状态组成的状态区

- guard  
  状态保护机制，对一个状态转移进行评估，评估值为true允许状态转移，评估值为false禁止转移

- Action  
  状态转移动作，在转移进行的时候触发的动作


## 使用教程
### 状态机配置
利用spring ioc容器配置state machine

#### 使用Enable注解
应用中存在下列两个注解会启动状态机
  
- @EnableStateMachine
- @EnableStateMachineFactory

#### 状态机配置属性
继承下列两个类并覆盖其方法可以快速地配置状态机的属性。

- EnumStateMachineConfigurerAdapter（状态与事件类型为枚举，继承于StateMachineConfigurerAdapter）
- StateMachineConfigurerAdapter

可配置Api：

    /**
	 * Callback for {@link StateMachineModelConfigurer}.
	 *
	 * @param model the {@link StateMachineModelConfigurer}
	 * @throws Exception if configuration error happens
	 */
	void configure(StateMachineModelConfigurer<S, E> model) throws Exception;

	/**
	 * Callback for {@link StateMachineConfigurationConfigurer}.
	 *
	 * @param config the {@link StateMachineConfigurationConfigurer}
	 * @throws Exception if configuration error happens
	 */
	void configure(StateMachineConfigurationConfigurer<S, E> config) throws Exception;

	/**
	 * Callback for {@link StateMachineStateConfigurer}.
	 *
	 * @param states the {@link StateMachineStateConfigurer}
	 * @throws Exception if configuration error happens
	 */
	void configure(StateMachineStateConfigurer<S, E> states) throws Exception;

	/**
	 * Callback for {@link StateMachineTransitionConfigurer}.
	 *
	 * @param transitions the {@link StateMachineTransitionConfigurer}
	 * @throws Exception if configuration error happens
	 */
	void configure(StateMachineTransitionConfigurer<S, E> transitions) throws Exception;


#### 配置状态机状态
覆盖 `void configure(StateMachineStateConfigurer<S, E> states) throws Exception` 进行状态属性的配置

##### 一级状态机
1、StateMachineConfigurerAdapter
	
	@Configuration
	@EnableStateMachine
	public class Config1Strings
	        extends StateMachineConfigurerAdapter<String, String> {
	
	    @Override
	    public void configure(StateMachineStateConfigurer<String, String> states)
	            throws Exception {
	        states
	            .withStates()
	                .initial("S1")
	                .end("SF")
	                .states(new HashSet<String>(Arrays.asList("S1","S2","S3","S4")));
	    }
	
	}
2、EnumStateMachineConfigurerAdapter

    @Configuration
	@EnableStateMachine
	public class Config1Enums
	        extends EnumStateMachineConfigurerAdapter<States, Events> {
	
	    @Override
	    public void configure(StateMachineStateConfigurer<States, Events> states)
	            throws Exception {
	        states
	            .withStates()
	                .initial(States.S1)
	                .end(States.SF)
	                .states(EnumSet.allOf(States.class));
	    }

	}



##### 多级状态机

    @Configuration
	@EnableStateMachine
	public class Config2
	        extends EnumStateMachineConfigurerAdapter<States, Events> {
	
	    @Override
	    public void configure(StateMachineStateConfigurer<States, Events> states)
	            throws Exception {
	        states
	            .withStates()                   
	                .initial(States.S1)
	                .state(States.S1)
	                .and()
	                .withStates()
	                    .parent(States.S1)
	                    .initial(States.S2)
	                    .state(States.S2);
	    }
	
	}


#### 配置状态转移
覆盖 `void configure(StateMachineTransitionConfigurer<S, E> transitions) throws Exception` 进行状态转移的配置。
 
支持三种不同类型的转换，包括外部，内部和本地。转换是由一个信号触发的，该信号可以是外部发给状态机或者由定时触发器触发。   

- **external**      外部转换，有状态转移  
- **internal**      内部转换，无状态转移
- **local**         本地转换...
	
	    @Override
	    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
	            throws Exception {
	        transitions
	            .withExternal()                               
	                .source(States.S1).target(States.S2)
	                .event(Events.E1)
	                .and()
	            .withInternal()
	                .source(States.S2)
	                .event(Events.E2)
	                .and()
	            .withLocal()
	                .source(States.S2).target(States.S3)
	                .event(Events.E3);
	    }


	
	
#### 配置guard安全机制
 状态保护机制，对一个状态转移进行评估，评估值为true允许状态转移，评估值为false禁止转移。
    
    @Configuration
	@EnableStateMachine
	public class Config4
	        extends EnumStateMachineConfigurerAdapter<States, Events> {
	
	    @Override
	    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
	            throws Exception {
	        transitions
	            .withExternal()
	                .source(States.S1).target(States.S2)
	                .event(Events.E1)
	                .guard(guard())   // guard
	                .and()
	            .withExternal()
	                .source(States.S2).target(States.S3)
	                .event(Events.E2)
	                .guardExpression("true");
	
	    }
	
	    @Bean
	    public Guard<States, Events> guard() {
	        return new Guard<States, Events>() {
	
	            @Override
	            public boolean evaluate(StateContext<States, Events> context) {
	                return true;
	            }
	        };
	    }
	
	}


#### 配置action转移动作
    
    @Configuration
	@EnableStateMachine
	public class Config51
	        extends EnumStateMachineConfigurerAdapter<States, Events> {
	
	    @Override
	    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
	            throws Exception {
	        transitions
	            .withExternal()
	                .source(States.S1)
	                .target(States.S2)
	                .event(Events.E1)
	                .action(action());
	    }
	
	    @Bean
	    public Action<States, Events> action() {
	        return new Action<States, Events>() {
	
	            @Override
	            public void execute(StateContext<States, Events> context) {
	                // do something
	            }
	        };
	    }
	
	}






## 简单入门

### 简单状态机（状态无层次结构，不存在子状态）
Turnstile是一个简单的设备，如果付款完成，您可以访问该设备，并且使用状态机进行建模非常简单。 最简单的形式是只有两个状态，LOCKED和UNLOCKED。 如果您尝试通过它或您付款，可能会发生两个事件，COIN和PUSH。

![Turnstile](https://docs.spring.io/spring-statemachine/docs/1.2.8.RELEASE/reference/htmlsingle/images/statechart1.png)

**状态**
    
    public enum States {
    	LOCKED, UNLOCKED
	}



**事件** 

    public enum Events {
    	COIN, PUSH
	}


**状态机配置**

    @Configuration
	@EnableStateMachine
	static class StateMachineConfig
	        extends EnumStateMachineConfigurerAdapter<States, Events> {
	
	    @Override
	    public void configure(StateMachineStateConfigurer<States, Events> states)
	            throws Exception {
	        states
	            .withStates()
	                .initial(States.LOCKED)   //配置初始状态
	                .states(EnumSet.allOf(States.class)); //导入所有状态
	    }
	
	    @Override
	    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
	            throws Exception {
	        transitions    		//配置transition
	            .withExternal()   
	                .source(States.LOCKED)    //当接收到COIN事件
	                .target(States.UNLOCKED)  //LOCKED--> UNLOCKED
	                .event(Events.COIN)
	                .and()
	            .withExternal()
	                .source(States.UNLOCKED) //当接收到PUSH事件
	                .target(States.LOCKED)   //UNLOCKED--> LOCKED
	                .event(Events.PUSH);
	    	}

	}


### 多层次状态机
Showcase是一个复杂的状态机，显示所有可能的转换拓扑结构，最多可达四级状态嵌套。
![Showcase](https://docs.spring.io/spring-statemachine/docs/1.2.8.RELEASE/reference/htmlsingle/images/statechart2.png)

**状态** 

    public enum States {
    	S0, S1, S11, S12, S2, S21, S211, S212
	}

**事件** 

    public enum Events {
    	A, B, C, D, E, F, G, H, I
	}

**状态机配置**  
状态关系

    @Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
	        throws Exception {
	    states
	        .withStates()
	            .initial(States.S0, fooAction())   //初始化状态，初始化动作
	            .state(States.S0)				   //导入状态
	            .and()
	            .withStates()                      //配置S0的子状态S1  
	                .parent(States.S0)         
	                .initial(States.S1)
	                .state(States.S1)
	                .and()
	                .withStates()              	   //配置S1的子状态S11、S12
	                    .parent(States.S1)       
	                    .initial(States.S11)
	                    .state(States.S11)
	                    .state(States.S12)
	                    .and()
	            .withStates()                      //配置S0的子状态S2  
	                .parent(States.S0)
	                .state(States.S2)
	                .and()
	                .withStates()                  //配置S2的子状态S21、S22
	                    .parent(States.S2)
	                    .initial(States.S21)
	                    .state(States.S21)
	                    .and()
	                    .withStates()              //配置S21的子状态S211、S212
	                        .parent(States.S21)
	                        .initial(States.S211)
	                        .state(States.S211)
	                        .state(States.S212);
		}


状态转移关系


    @Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
        throws Exception {
		    transitions
		        .withExternal()            
		            .source(States.S1).target(States.S1).event(Events.A)    //A：S1->S1
		            .guard(foo1Guard())                                     //配置guard
		            .and()
		        .withExternal()
		            .source(States.S1).target(States.S11).event(Events.B)   //B:S1->S11
		            .and()
		        .withExternal()
		            .source(States.S21).target(States.S211).event(Events.B) //B:S21->S211
		            .and()
		        .withExternal()
		            .source(States.S1).target(States.S2).event(Events.C)    //C:S1->S2
		            .and()
		        .withExternal()
		            .source(States.S2).target(States.S1).event(Events.C)    //C:S2->S1
		            .and()
		        .withExternal()
		            .source(States.S1).target(States.S0).event(Events.D)    //D:S1->S0
		            .and()
		        .withExternal()
		            .source(States.S211).target(States.S21).event(Events.D) //D:S211->S21
		            .and()
		        .withExternal()
		            .source(States.S0).target(States.S211).event(Events.E)  //E:S0->S211
		            .and()
		        .withExternal()
		            .source(States.S1).target(States.S211).event(Events.F)  //F:S1->S211
		            .and()
		        .withExternal()
		            .source(States.S2).target(States.S11).event(Events.F)   //F:S2->S11
		            .and()
		        .withExternal()
		            .source(States.S11).target(States.S211).event(Events.G) //G:S11->S211
		            .and()
		        .withExternal()
		            .source(States.S211).target(States.S0).event(Events.G)  //G:S211->S0
		            .and()
		        .withInternal()                    							//内部转移，无状态变化 
		            .source(States.S0).event(Events.H)
		            .guard(foo0Guard())
		            .action(fooAction())
		            .and()	
		        .withInternal()                    							//内部转移，无状态变化 
		            .source(States.S2).event(Events.H)
		            .guard(foo1Guard())
		            .action(fooAction())
		            .and()
		        .withInternal()                                             //内部转移，无状态变化    
		            .source(States.S1).event(Events.H)
		            .and()
		        .withExternal()                                             //I:S11->S12 
		            .source(States.S11).target(States.S12).event(Events.I)
		            .and()
		        .withExternal()                                             //I:S211->S212
		            .source(States.S211).target(States.S212).event(Events.I)
		            .and()
		        .withExternal()                                             //I:S12->S212
		            .source(States.S12).target(States.S212).event(Events.I);
		}

**action&guard**

		@Bean
		public FooGuard foo0Guard() {
		    return new FooGuard(0);
		}
		
		@Bean
		public FooGuard foo1Guard() {
		    return new FooGuard(1);
		}
		
		@Bean
		public FooAction fooAction() {
		    return new FooAction();
		}

		private static class FooAction implements Action<States, Events> {
		
		    @Override
		    public void execute(StateContext<States, Events> context) {
		        Map<Object, Object> variables = context.getExtendedState().getVariables();
		        Integer foo = context.getExtendedState().get("foo", Integer.class);
		        if (foo == null) {
		            log.info("Init foo to 0");
		            variables.put("foo", 0);
		        } else if (foo == 0) {
		            log.info("Switch foo to 1");
		            variables.put("foo", 1);
		        } else if (foo == 1) {
		            log.info("Switch foo to 0");
		            variables.put("foo", 0);
		        }
		    }
		}


		private static class FooGuard implements Guard<States, Events> {
		
		    private final int match;
		
		    public FooGuard(int match) {
		        this.match = match;
		    }
		
		    @Override
		    public boolean evaluate(StateContext<States, Events> context) {
		        Object foo = context.getExtendedState().getVariables().get("foo");
		        return !(foo == null || !foo.equals(match));
		    }
		}





