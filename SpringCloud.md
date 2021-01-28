# SpringCloud

Sp

ringCloud + SpringCloud alibaba

技术要求：

* java8
* maven
* git
* Nginx
* RabbitMQ
* SpringBoot



**约定 > 配置 > 编码**

搭建一个微服务模块，主要包含一下几步：

1. **建立module**
2. **改pom**
3. **写yml**
4. **主启动**
5. **业务类**



## 一、微服务

### 1.1 什么是微服务

​		微服务是一种架构模式，它将单一应用程序划分为一组小的服务，各个服务之间互相协调配合，为用户体现价值。每个服务运行在其独立的进程中，服务与服务之间采用轻量级的通信机制互相协作（通常是基于HTTP协议的RESTful API）。每个服务都围绕其具体业务进行构建，并且能够被独立的部署到生产环境、类生产环境等。通俗而言，微服务就是每个SpringBoot开发的一个一个功能单元。

### 1.2 分布式微服务

SpringCloud是分布式微服务的其中一种，微服务全家桶。

SpringCloud至少包含以下功能：

![image-20200824233131721](C:\Users\10538\AppData\Roaming\Typora\typora-user-images\image-20200824233131721.png)



1. 一个通用的微服务框架如下：

![image-20200824233507256](C:\Users\10538\AppData\Roaming\Typora\typora-user-images\image-20200824233507256.png)



2. 所用到的主要技术有：

![image-20200824233731460](C:\Users\10538\AppData\Roaming\Typora\typora-user-images\image-20200824233731460.png)



3. 最终，一个SpringCloud微服务的整个流程如下：

![image-20200824233914534](C:\Users\10538\AppData\Roaming\Typora\typora-user-images\image-20200824233914534.png)



4. Cloud升级

   ![image-20200901104753097](C:\Users\10538\AppData\Roaming\Typora\typora-user-images\image-20200901104753097.png)



## 二、项目搭建

项目搭建分为两步，分别是：

1. **父工程project**
2. **project下面创建多个module**,  比如**订单模块**、**支付模块**。





## 三、结构分析

SpringCloud主要分为：

* **服务注册中心**
* **服务调用**
* **服务降级**
* **服务网管**
* **服务配置**
* **服务总线**



### 3.1  服务注册中心

![image-20210110211150822](C:\Users\10538\AppData\Roaming\Typora\typora-user-images\image-20210110211150822.png)

```markdown
# 1. 服务注册
	将服务信息注册到注册中心
# 2. 服务发现
	从注册中心上获取服务信息
# 3. 结论：
	是一个Map结构。
	​ key：服务名
	​ value：调用地址
```



**问题：微服务RPC远程服务调用最核心的是声明？**

答：高可用。如果注册中心只有一个，并且其出故障，将会导致整个服务环境不可用。

解决办法：搭建Eureka注册中心集群，实现负载均衡+故障容错



### 3.2 Eureka集群原理

**核心：互相注册，相互守望**





### 3.3 服务发现Discovery

对于注册进eureka里面的微服务，可以通过微服务来发现来获得该服务的信息。



### 3.4 自我保护

```markdown
# 原理 
	如果Eureka在服务端Server一定时间内没有收到Eureka Client发送心跳包，便会直接从服务注册列表中剔除该服务，但是在短时间内丢失了大量的服务实例心跳，这时候Eureka Server会开启自我保护机制，不会剔除该服务。
	在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。
```

关闭自我保护：

```yaml
Eureka.server.enable.
```





## Ribbon





## Feign

Feign是Spring Cloud组建中的一个轻量级RESTful的HTTP服务客户端，Feign内置了Ribbon，用来做客户端的负载均衡，去调用服务注册中心的服务。

```markdown
# 1. 使用Feign的注解定义注解，调用这个接口，就可以调用服务注册中心的服务

# 2. 创建一个接口，并在接口上添加注解说明
```

服务接口绑定器，实现方式：接口+注解，可以替换Ribbon+RestTemplate。

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```



## 服务调用OpenFeign

**1. 特点**

`OpenFeign`是Spring Cloud在Feign的基础上支持SpringMVC的注解。

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

```markdown
# 1. OpenFeign特点
	集成了Ribbon, 自带负载均衡
# 2. 调用方式
	服务1Controller --> OpenFrign jar包Service --> 被调用房Contoller
```

2. **超时控制**

```markdown
# 1. Feign超时时间
	Feign默认的客户端只等待1秒钟，但是服务端处理需要超过1秒，导致客户端调用超时，为了避免这种情况，需要设置Feign客户端的超时控制。
# 2. 如何配置
	在yml文件中配置超时情况。由于OpenFeign默认支持ribbon，所以添加Ribbon的超时时间即可。
```



## 服务降级HyStrix

1. **服务雪崩**

   ```markdown
   # 1. 服务雪崩
   	A -> B -> C...
   # 2. Hystrix解决方案
   	能够保证在一个依赖出现问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。
   ```

2. 服务降级

   ```markdown
   # 1. 服务降级策略
   	 服务器忙，请稍后重试，不应让客户端等待而是应该返回一个友好提示， 使用的策略是fallback
   # 2. 哪些情况会导致服务降级
   	a. 程序运行异常
   	b. 超时
   	c. 服务熔断引发服务降级
   	d. 线程满
   ```

   

3. 服务熔断

   ```markdown
   # 1. 类比保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的策略返回友好提示
   # 2. 服务降级 --> 进而熔断 --> 恢复调用链路
   ```

4. 注解

   ```java
   // 兜底方案
   @HystrixCommand(fallbackMethod ="指定错误方法名", commandProperties = {
       @HystrixProperty(name="execution.isolation.thread.timeoutMilliseconds", value="3000")
   })
   
   // 2. 主启动类上添加@EnableCircuitBreaker
   ```

5. 全局服务降级

   ```markdown
   # 这样做的目的是为了简化不用在每个方法上都配置，可以全局配置
   	
   
   ```

   

6. `@HystrixCommand`注解中的参数作用

   ```java
   @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000") //接口超时设置
   
   ```

   

7. 熔断

   

8. Resolving eureka endpoints via configuration日志打印的原因





```java
2021-01-21 13:56:53.405  INFO 2200 --- [trap-executor-0] c.n.d.s.r.aws.ConfigClusterResolver      : Resolving eureka endpoints via configuration
```

此日志打印的原因是：springcloud的注册中心客户端会每隔一定时间向注册中心服务端发送心跳，用此来判断注册中心服务端是否运行正常。



## 服务网关GateWay

<span style='color:red'>为什么有负载均衡了，还需要使用网关呢？</span>

```markdown
# 执行过程
	先根据断言条件找到指定的微服务，再路由转发，过滤可以在转发前或转发后加一些自定义的东西。
```



三大核心：

1. Route路由

   

2. Predicate断言

   ![image-20210126142500173](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210126142500173.png)

   

3. Filter过滤器

Unmapped Spring configuration files found.  Please configure Spring facet or use 'Create Default Context' to add one including all unmapped files