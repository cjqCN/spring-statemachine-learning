# 使用Eclipse-Papyrus建立state-machine模型
 
点击**[官方教程](https://docs.spring.io/spring-statemachine/docs/2.0.0.RELEASE/reference/htmlsingle/#sm-papyrus)**  
**spring-statemachine不仅限于使用硬编码的方式构建模型，还支持使用导入uml文件的形式构建模型。下面是使用教程：**

## [papyrus](https://www.eclipse.org/papyrus/) 
Papyrus是一个基于Eclipse平台UML2建模工具，需要在Eclipse上以插件的形式安装。  
安装地址：`https://www.eclipse.org/papyrus/download.html`




## 导入依赖
除了`spring-statemachine-core`模块，还需要`spring-statemachine-uml`的支持。  

**使用maven:**  
pom.xml
 		
		<dependency>
			<groupId>org.springframework.statemachine</groupId>
			<artifactId>spring-statemachine-core</artifactId>
			<version>2.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.statemachine</groupId>
			<artifactId>spring-statemachine-uml</artifactId>
			<version>2.0.0.RELEASE</version>
		</dependency>


