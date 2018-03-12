# spring statemachine
本教程基于spring-statemachine 2.0.0.RELEASE版本。

- [官方文档](https://docs.spring.io/spring-statemachine/docs/2.0.0.RELEASE/reference/htmlsingle)
- [使用教程](/使用教程.md)
- [简单入门例子](/简单入门.md)
- [使用Eclipse-Papyrus建立state-machine模型](/statemachineWithEclipse-papyrus.md)


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
- 有简单的教程以供快速入门。
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




