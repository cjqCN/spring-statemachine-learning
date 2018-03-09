# 使用Eclipse-Papyrus建立state-machine模型
 
点击[官方教程](https://docs.spring.io/spring-statemachine/docs/2.0.0.RELEASE/reference/htmlsingle/#sm-papyrus)   
**spring-statemachine不仅限于使用硬编码的方式构建模型，还支持使用导入uml文件的形式构建模型。以下是入门教程：**

## [papyrus](https://www.eclipse.org/papyrus/) 
Papyrus是一个基于Eclipse平台UML2建模工具，需要在Eclipse上以插件的形式安装。  
安装地址：`https://www.eclipse.org/papyrus/download.html`


## 创建项目
创建一个maven项目来进入该教程

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