# 使用Eclipse-Papyrus建立state-machine模型
 
点击[官方教程](https://docs.spring.io/spring-statemachine/docs/2.0.0.RELEASE/reference/htmlsingle/#sm-papyrus)   
**spring-statemachine不仅限于使用硬编码的方式构建模型，还支持使用导入uml文件的形式构建模型。以下是入门教程：**

## [papyrus](https://www.eclipse.org/papyrus/) 
Papyrus是一个基于Eclipse平台UML2建模工具，需要在Eclipse上以插件的形式安装。  
安装地址：`https://www.eclipse.org/papyrus/download.html`


## 简单入门
创建一个maven项目来进入该教程   
[项目地址](https://github.com/cjqCN/spring-statemachine-learning/tree/master/statemachine-uml) `https://github.com/cjqCN/spring-statemachine-learning/tree/master/statemachine-uml`

### 导入依赖
- 除了`spring-statemachine-core`模块，还需要`spring-statemachine-uml`的支持。  

- 为了方便构建和使用statemachine，还导入了`spring-boot-starter`(快速构建状态机)和`org.springframework.shell`(使用命令行操作状态机)。

**pom.xml**

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>
	
		<groupId>com.spring.statemachine</groupId>
		<artifactId>statemachine-uml</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<packaging>jar</packaging>
	
		<name>statemachine-uml</name>
		<url>http://maven.apache.org</url>
	
		<parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
			<version>1.5.10.RELEASE</version>
		</parent>
		
		<properties>
			<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		</properties>
	
		<dependencies>
	
			<!-- https://mvnrepository.com/artifact/org.springframework.statemachine/spring-statemachine-core -->
			<dependency>
				<groupId>org.springframework.statemachine</groupId>
				<artifactId>spring-statemachine-core</artifactId>
				<version>2.0.0.RELEASE</version>
			</dependency>
			<!-- https://mvnrepository.com/artifact/org.springframework.statemachine/spring-statemachine-uml -->
			<dependency>
				<groupId>org.springframework.statemachine</groupId>
				<artifactId>spring-statemachine-uml</artifactId>
				<version>2.0.0.RELEASE</version>
			</dependency>
	
			<dependency>
				<groupId>org.springframework.shell</groupId>
				<artifactId>spring-shell</artifactId>
				<version>1.1.0.RELEASE</version>
			</dependency>

		</dependencies>
	</project>



### 创建papyrus-model

- 选择创建Papyrus Model  
![创建papyrus-model1](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/create-papyrus-model1.png?raw=true)  
- 选择UML  
![创建papyrus-model2](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/create-papyrus-model2.png?raw=true)  
- 选择文件目录，在resources目录中  
![创建papyrus-model3](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/create-papyrus-model3.png?raw=true)  
- 创建state machine视图  
![创建papyrus-model4](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/create-papyrus-model4.png?raw=true)  
- 文件结构
 - statemachine-uml.di  状态机视图
 - statemachine-uml.notation 记录视图中的UI布局
 - statemachine-uml.uml UML模型文件  
![创建papyrus-model1](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/file-struct.png?raw=true) 

- 完成创建  
![创建papyrus-model完成](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/create-papyrus-finsh.png?raw=true) 


### 开始建模
- 通过从palette板拖拽组件，建立以下模型：  
![statemachine-build1](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/statemachine-build1.png?raw=true)

- 在根元素下创建两个Signal以及两个SignalEvent，操作：RootElement→New Child→Signal & RootElement→New Child→SignalEvent.  
![statemachine-build2](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/statemachine-build2.png?raw=true)

- 分别设置SignalEvent1的Signal为Signal1，SignalEvent2的Signal为Signal2  
![statemachine-build3](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/statemachine-build3.png?raw=true)   

- 在状态S1与S2的两个Transition分别添加基于SignalEvent1、SignalEvent2的触发器  
![statemachine-build4](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/statemachine-build4.png?raw=true)   

- 完成建模   
![statemachine-build5](https://github.com/cjqCN/spring-statemachine-learning/blob/master/pic/statemachine-build5.png?raw=true)   

### 生成UML数据
完成建模后，点击保存，自动生成UML数据   
statemachine-uml.uml:  
  
	<?xml version="1.0" encoding="UTF-8"?>
	<uml:Model xmi:version="20131001" xmlns:xmi="http://www.omg.org/spec/XMI/20131001" xmlns:uml="http://www.eclipse.org/uml2/5.0.0/UML" xmi:id="_cfujcCN2EeizONWQ1SRsZg" name="statemachine-uml">
	  <packageImport xmi:type="uml:PackageImport" xmi:id="_cjr2YCN2EeizONWQ1SRsZg">
	    <importedPackage xmi:type="uml:Model" href="pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#_0"/>
	  </packageImport>
	  <packagedElement xmi:type="uml:StateMachine" xmi:id="_cf0DACN2EeizONWQ1SRsZg" name="StateMachine1" visibility="public">
	    <region xmi:type="uml:Region" xmi:id="_cf1RICN2EeizONWQ1SRsZg" name="Region1">
	      <transition xmi:type="uml:Transition" xmi:id="_hQcrACN3EeizONWQ1SRsZg" source="_aTbOgCN3EeizONWQ1SRsZg" target="_bpozgCN3EeizONWQ1SRsZg"/>
	      <transition xmi:type="uml:Transition" xmi:id="_k2b6QCN3EeizONWQ1SRsZg" source="_bpozgCN3EeizONWQ1SRsZg" target="_czGV8CN3EeizONWQ1SRsZg">
	        <trigger xmi:type="uml:Trigger" xmi:id="_X-K_oCN9EeizONWQ1SRsZg" event="_G8YkgCN7EeizONWQ1SRsZg"/>
	      </transition>
	      <transition xmi:type="uml:Transition" xmi:id="_UyW9MCN4EeizONWQ1SRsZg" source="_czGV8CN3EeizONWQ1SRsZg" target="_bpozgCN3EeizONWQ1SRsZg">
	        <trigger xmi:type="uml:Trigger" xmi:id="_3yGO0CN-EeizONWQ1SRsZg"/>
	      </transition>
	      <subvertex xmi:type="uml:Pseudostate" xmi:id="_aTbOgCN3EeizONWQ1SRsZg" name="Initial"/>
	      <subvertex xmi:type="uml:State" xmi:id="_bpozgCN3EeizONWQ1SRsZg" name="S1"/>
	      <subvertex xmi:type="uml:State" xmi:id="_czGV8CN3EeizONWQ1SRsZg" name="S2"/>
	    </region>
	  </packagedElement>
	  <packagedElement xmi:type="uml:Signal" xmi:id="_-hnXoCN6EeizONWQ1SRsZg" name="Signal1"/>
	  <packagedElement xmi:type="uml:SignalEvent" xmi:id="_AQq6cCN7EeizONWQ1SRsZg" name="SignalEvent1" signal="_-hnXoCN6EeizONWQ1SRsZg"/>
	  <packagedElement xmi:type="uml:Signal" xmi:id="_F2ukACN7EeizONWQ1SRsZg" name="Signal2"/>
	  <packagedElement xmi:type="uml:SignalEvent" xmi:id="_G8YkgCN7EeizONWQ1SRsZg" name="SignalEvent2" signal="_F2ukACN7EeizONWQ1SRsZg"/>
	</uml:Model>


### 使用UML生成状态机实例

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
				return new UmlStateMachineModelFactory("/uml/state-machine.uml");
			}
	
		}
	}


### 测试
输入命令：  

- 开启状态机
- 查看状态
- 发送信号Signal2
- 查看状态
- 发送信号Signal1
- 查看状态

得到结果正确。





	     _____            _               _____ _          _ _ 
	/  ___|          (_)             /  ___| |        | | |
	\ `--. _ __  _ __ _ _ __   __ _  \ `--.| |__   ___| | |
	 `--. \ '_ \| '__| | '_ \ / _` |  `--. \ '_ \ / _ \ | |
	/\__/ / |_) | |  | | | | | (_| | /\__/ / | | |  __/ | |
	\____/| .__/|_|  |_|_| |_|\__, | \____/|_| |_|\___|_|_|
	      | |                  __/ |                       
	      |_|                 |___/                        
	1.1.0.RELEASE
	
	
	Welcome to Spring Shell. For assistance press or type "hint" then hit ENTER.
	spring> sm start
	State machine started
	spring> sm state
	S1
	spring> sm event Signal2
	Event Signal2 send
	spring> sm state
	S2
	spring> sm event Signal1
	Event Signal1 send
	spring> sm state
	S1
	spring> 



## action&guard使用

待续......
