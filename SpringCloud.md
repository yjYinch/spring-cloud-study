# SpringCloud

在学习SpringCloud之前，我们先来看看以下几个问题

**分布式与集群的区别是什么？**

​	集群：同一个业务部署在多个服务器上

​	分布式： 一个业务拆分成多个子业务，部署在不同服务器上，一般来说一个子业务就表示一个结点

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521105117.jpeg" alt="img" style="zoom:50%;" />

**微服务中的CAP理论是什么？**

* C：数据一致性 (Consistency) 

  所有节点拥有数据的最新版本

* A：可用性(Availability)

  数据具备高可用性

* P：分区容错性(Partition-tolerance)

  容忍网络出现分区，分区之间不可达

为了更加清晰地认识CAP理论，将通过案例来介绍其特性。

下面有三个节点(它们是集群的)，此时三个节点都能够相互通信：

![image-20210521170117528](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521170117.png)

由于我们的系统是分布式的，节点之间的通信是通过网络来进行的。**只要是分布式系统**，那很有可能会出现一种情况：因为一些**故障**，使得有些**节点之间不连通**了，整个网络就分成了**几块区域**。数据散落在这些不连通的区域中，这就叫**分区**。

现在出现了网络分区后，此时有一个请求过来了，想要注册一个账户。

![image-20210521170503574](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521170503.png)

此时我们**节点一和节点三是不可通信的**，这就有了抉择：

* 如果**允许**当前用户注册一个账户，此时注册的记录数据只会在节点一和节点二，或者节点二和节点三**同步**，因为节点一和节点三的记录不能同步的。

  这种情况其实就是**选择了可用性(availability)，抛弃了数据一致性(consistency)**

* 如果**不允许**当前用户注册一个账户(就是要**等到**节点一和节点三恢复通信)。节点一和节点三一旦恢复通信，我们就可以**保证节点拥有的数据是最新版本**。

  这种情况其实就是**抛弃了可用性(availability)，选择了数据一致性(consistency)**



一般我们说的分布式系统，P：分区容错性(partition-tolerance)这个是**必需**的，这是客观存在的。

在实际分布式系统中，**CAP理论是无法三个全满足的，要么是AP，要么是AP**。

在CAP理论中，**C所表示的一致性是强一致性**(每个节点的数据都是最新版本)，其实一致性还有其他级别的：

* 弱一致性：弱一致性是相对于强一致性而言，它不保证总能得到最新的值
* 最终一致性(eventual consistency)：放宽对时间的要求，在被调完成操作响应后的某个时间点，被调多个节点的数据最终达成一致。

可用性的值域可以定义成**0到100%的连续区间**。

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521111527.jpeg" alt="img" style="zoom: 80%;" />

所以，**CAP理论定义的其实是在容忍网络分区的条件下，“强一致性”和“极致可用性”无法同时达到**。



## 一、SpringCloud版本选择

上面已经对分布式微服务有了初步的概念，简而言之，分布式微服务就是将一个大的项目分成多个小的模块，这些小的模块组合起来完成功能。当然，拆分出多个模块以后，就会出现**各种各样**的问题，而SpringCloud提供了**一整套**的解决方案！

**SpringCloud是什么？**

先看看[SpringCloud官网](https://spring.io/projects/spring-cloud#learn)是如何介绍的

![image-20210521111958658](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521111958.png)

* SpringCloud是一个微服务框架， **Spring Cloud提供的全套的分布式系统解决方案**

* Spring Cloud对微服务基础框架Netflix的多个开源组件进行了封装，同时又实现了和云端平台以及和Spring Boot开发框架的集成
* Spring Cloud为微服务架构开发涉及的**配置管理，服务治理，熔断机制，智能路由，微代理，控制总线，一次性token，全局一致性锁，leader选举，分布式session，集群状态**管理等操作提供了一种简单的开发方式。



在2020版本之前，SpringCloud版本一直以伦敦地铁站名的顺序（A-H）命名，到2020年之后，SpringCloud采用日历化版本方式命名。

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210519171948.png" alt="image-20210519171948134" style="zoom:67%;" />

**重大变化**

自从SpringCloud2020.0.0正式版本发布以来，以下模块已从spring-cloud-netflix中删除。

- spring-cloud-netflix-archaius
- spring-cloud-netflix-concurrency-limits
- spring-cloud-netflix-core
- spring-cloud-netflix-dependencies
- spring-cloud-netflix-hystrix
- spring-cloud-netflix-hystrix-contract
- spring-cloud-netflix-hystrix-dashboard
- spring-cloud-netflix-hystrix-stream
- spring-cloud-netflix-ribbon
- spring-cloud-netflix-sidecar
- spring-cloud-netflix-turbine
- spring-cloud-netflix-turbine-stream
- spring-cloud-netflix-zuul
- spring-cloud-starter-netflix-archaius
- spring-cloud-starter-netflix-hystrix
- spring-cloud-starter-netflix-hystrix-dashboard
- spring-cloud-starter-netflix-ribbon
- spring-cloud-starter-netflix-turbine
- spring-cloud-starter-netflix-turbine-stream
- spring-cloud-starter-netflix-zuul

可以看出在已发布的版本中移除了对ribbon、hystrix和zuul的支持。总之，新版本删除了除Eureka外的所有组件。可选的替代方案有以下：

- Spring Cloud Azure
- **Spring Cloud Alibaba**
- Spring Cloud for Amazon Web Services
- Spring Cloud GCP。




[SpringCloud中文文档](https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md)

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210519172155.png" alt="image-20210519172155650" style="zoom: 80%;" />

[SpringBoot官网](https://spring.io/projects/spring-boot/#learn)

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210519171906.png" alt="image-20210519171859115" style="zoom: 67%;" />



[SpringCloud与SpringBoot版本对应关系](https://start.spring.io/actuator/info)

JSON格式化之后：可以看出SpringCloud对应的版本号需要对SpringBoot的版本号有要求。

```json
{
    "bom-ranges": {
        "spring-cloud": {
            "Hoxton.SR11": "Spring Boot >=2.2.0.RELEASE and <2.3.11.BUILD-SNAPSHOT", 
            "Hoxton.BUILD-SNAPSHOT": "Spring Boot >=2.3.11.BUILD-SNAPSHOT and <2.4.0.M1", 
            "2020.0.0-M3": "Spring Boot >=2.4.0.M1 and <=2.4.0.M1", 
            "2020.0.0-M4": "Spring Boot >=2.4.0.M2 and <=2.4.0-M3", 
            "2020.0.0": "Spring Boot >=2.4.0.M4 and <=2.4.0", 
            "2020.0.2": "Spring Boot >=2.4.1 and <2.5.0-M1", 
            "2020.0.3-SNAPSHOT": "Spring Boot >=2.4.6-SNAPSHOT"
        }, 
        "spring-cloud-alibaba": {
            "2.2.1.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1"
        }
    }
}
```



**SpringCloud集成组件**

![image-20210521101824274](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521101824.png)

特别说明：

服务注册：Eureka2.0已经停更了，1.0版本仍在继续维护，建议使用Nacos

![image-20210521103829946](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521103830.png)

## 二、服务注册与发现

### 2.1 Spring Cloud Eureka

**服务端**

1. 导入依赖

   ```xml
   	<dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
           </dependency>
       </dependencies>
   ```

   

2. 向配置文件添加配置

   ```yaml
   server:
     port: 7001
   
   eureka:
     instance:
       hostname: eureka7001 # 主机名称
     client:
       # false表示不向注册中心注册自己
       register-with-eureka: false
       # false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
       fetch-registry: false
       service-url:
       # 设置与Eureka server交互的地址查询服务和注册服务都需要依赖这个地址
         defaultZone: http://eureka7002.com:7002/eureka/
   ```

   

3. 启动类上加上注解`@EnableEurekaServer`

   ```java
   @SpringBootApplication
   @EnableEurekaServer
   public class EurekaMain {
       public static void main(String[] args) {
           SpringApplication.run(EurekaMain.class, args);
       }
   }
   
   ```

4. 服务界面

   ![image-20210520095154800](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520095201.png)

**客户端**

1. 导入依赖

   ```xml
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
           </dependency>
   ```

   

2. 向配置文件添加配置

   ```yaml
   eureka:
     client:
       # 向注册中心注册自己
       register-with-eureka: true
       fetch-registry: true
       service-url:
         defaultZone: http://eureka7001.com:7001/eureka, http://eureka7002.com:7002/eureka
   ```

3. 服务启动类上添加注解`@EnableEurekaClient`

   ```java
   @SpringBootApplication
   @EnableEurekaClient
   public class PaymentMain {
   
       public static void main(String[] args) {
           SpringApplication.run(PaymentMain.class, args);
       }
   }
   ```

4. 注册到Eureka服务器

   ![image-20210520095632278](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520095632.png)



### 2.2 Spring Cloud Alibaba Nacos

**Nacos服务端启动**

1. 下载Nacos[最新稳定版本](https://github.com/alibaba/nacos/releases)

2. 启动Nacos

   Linux/Unix/Mac，启动命令(standalone代表着单机模式运行，非集群模式):

   ```bash
   sh startup.sh -m standalone
   ```

   如果您使用的是ubuntu系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：

   ```bash
   bash startup.sh -m standalone
   ```

   Windows：

   启动命令(standalone代表着单机模式运行，非集群模式):

   ```bash
   startup.cmd -m standalone
   ```

3. 启动后的服务端页面

   ![image-20210520114252256](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520114252.png)



**Nacos服务注册与发现**

官网提供了服务发现的案例[Nacos Discovery Example](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/nacos-example/nacos-discovery-example/readme.md)

1. pom文件添加依赖

   ```xml
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
   ```

   

2. 向配置文件添加配置

   ```yaml
   spring:
     application:
       name: nacos-order-consumer
     cloud:
       nacos:
         discovery:
           server-addr: localhost:8848 # nacos服务端名称
   ```

   

3. 启动类上添加`@EnableDiscobveryClient`将该服务注册到server-addr中

   ```java
   @SpringBootApplication
   @EnableDiscoveryClient
   public class OrderMain8001 {
   
       public static void main(String[] args) {
           SpringApplication.run(OrderMain8001.class, args);
       }
   }
   ```

4. 启动服务

   ![image-20210520114409859](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520114410.png)

   服务端web页面可以看出服务也被注册进来

   ![image-20210520114511700](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520114511.png)



5. 负载均衡

   由于Nacos集成了Ribbon，负载均衡的调用通过@LoadBalance和RestTemplate结合实现客户端的负载均衡。

   ```java
   @RestController
   public class OrderController {
   
       @Autowired
       private RestTemplate restTemplate;
   
       @Value("${provider.server}")
       private String providerServer;
   
       @GetMapping("/get/port")
       public String get(){
   
           return restTemplate.getForObject(providerServer + "/payment/getPort", String.class);
       }
   }
   
   ```

   

### 2.3 Spring Cloud Consul

* [Consul官方网站](https://www.consul.io/intro/index.html)

* [Consul下载地址](https://www.consul.io/downloads.html)

* [Spring Cloud Consul](https://www.springcloud.cc/spring-cloud-consul.html)

功能：

* 服务发现
* 健康检测
* KV存储
* 多数据中心
* 可视化web界面



**运行Consul**

1. 下载Consul，以windows为例，解压之后，得到consul.exe文件，然后双击consul.exe文件

   ![image-20210520142126101](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520142126.png)

2. 在该文件目录下，打开cmd窗口，Start the Consul agent in development mode.

   ```bash
   consul agent -dev
   ```

   ![image-20210520142320624](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520142320.png)

3. 打开web界面，http://localhost:8500

   ![image-20210520142545635](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520142545.png)



**项目使用**

1. 导入pom依赖

   ```xml
   <dependency>
   	<groupId>org.springframework.cloud</groupId>
   	<artifactId>spring-cloud-starter-consul-discovery</artifactId>
   </dependency>
   ```

   

2. yaml配置

   ```yaml
   spring:
     cloud:
       consul:
         host: localhost
         port: 8500
         discovery: 
         	service-name: consul-service
   ```

   

3. 启动类

   ```java
   @SpringBootApplication
   @EnableDiscoveryClient
   public class ConsulServiceMain {
       public static void main(String[] args) {
           SpringApplication.run(ConsulServiceMain.class, args);
       }
   }
   ```

   启动完后，web界面可以看出该服务已经被注册进服务中心

   ![image-20210520143755836](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210520143755.png)

### 2.4 Spring Cloud Zookeeper

1. 引入依赖

   ```xml
   <dependency>
   	<groupId>org.springframework.cloud</groupId>
   	<artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
   </dependency>
   ```

   

2. yaml配置

   ```yaml
   spring:
     application:
       name: zookeeper-discovery-service
     cloud:
       zookeeper:
         connect-string: localhost:2181
   ```

3. 主启动类上注解配置

   ```java
   @SpringBootApplication
   @EnableDiscoveryClient
   public class ZookeeperDiscovertServiceMain {
       public static void main(String[] args) {
           SpringApplication.run(PaymentHystrixMain.class, args);
       }
   }
   ```



### 2.5 总结

|                 | Nacos                      | Eureka      | Consul            | Zookeeper  |
| --------------- | -------------------------- | ----------- | ----------------- | ---------- |
| 一致性协议      | CP+AP                      | AP          | CP                | CP         |
| 健康检查        | TCP/HTTP/MYSQL/Client Beat | Client Beat | TCP/HTTP/gRPC/Cmd | Keep Alive |
| 负载均衡策略    | 权重/metadata/Selector     | Ribbon      | Fabio             | ——         |
| 雪崩保护        | 有                         | 有          | 无                | 无         |
| 自动注销实例    | 支持                       | 支持        | 支持              | 支持       |
| 访问协议        | HTTP/DNS                   | HTTP        | HTTP/DNS          | TCP        |
| 监听支持        | 支持                       | 支持        | 不支持            | 支持       |
| 多数据中心      | 支持                       | 支持        | 支持              | 不支持     |
| 跨注册中心同步  | 支持                       | 不支持      | 支持              | 不支持     |
| SpringCloud集成 | 支持                       | 支持        | 支持              | 支持       |
| Dubbo集成       | 支持                       | 不支持      | 支持              | 支持       |
| K8S集成         | 支持                       | 不支持      | 支持              | 不支持     |



## 三、服务负载与调用

服务之间的调用要考虑两方面，一是如何调用，另一个则是如何均衡的调用。

### 3.1 负载均衡

负载均衡

* 客户端负载均衡（Ribbon）

  客户端从注册中心获取注册服务清单，然后通过负载均衡算法选择一个服务实例。

* 服务端负载均衡（Nginx）

  服务器负载均衡，客户端所有请求都交到Nginx，然后由Nginx进行转发。服务实例的**清单在服务端**，服务器进行负载均衡算法分配



### 3.2 服务调用——RestTemplate

在SpringCloud中，服务之间的调用则是通过http请求的方式来调用，我们不需要自己创建HttpClient，而是直接使用Spring封装好的RestTemplate工具类。

根据服务发现，直接通过服务名来获取具体的服务实例的位置了(IP)，至于它走哪个端口号，这就是负载均衡所做的事了

示例1：

Controller层调用

```java
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${provider.server}")
    private String providerServer;

    @GetMapping("/get/port")
    public String get(){

        return restTemplate.getForObject(providerServer + "/payment/getPort", String.class);
    }
}
```

配置文件：

```yaml
server:
  port: 8001

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

provider:
  server: http://payment-provider
```



SpringCloud基于Ribbon实现了客户端的负载均衡此时，只需在注入RestTemplate类的时候加上@LoadBalanced注解即可。

```java
@Configuration
public class GlobalConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```





### 3.3 服务调用——Spring Cloud OpenFeign

> Feign是一种声明式、模板化的HTTP客户端。在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

Feign与OpenFeign的区别

|             | Feign                                                        | OpenFeign                                                    |
| ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 作用        | Feign是Spring Cloud组件中一个轻量级RESTful的HTTP服务客户端，Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。 | OpenFeign是Spring Cloud在Feign的基础上支持了SpringMVC的注解，如@RequestMapping等等。OpenFeign的@FeignClient可以解析SpringMVC的@RequestMapping注解下的接口，并通过动态代理的方式产生实现类 |
| 依赖        | spring-cloud-starter-feign                                   | spring-cloud-starter-openfeign                               |
| 说明        | Spring Cloud已不再对feign进行支持，转而升级了openfeign       | openfeign在feign的基础上进行了升级                           |
| Ribbon集成  | 是                                                           | 是                                                           |
| Hystrix集成 | 是                                                           | 是                                                           |



**使用方式**

1. 引入依赖

   ```xml
    <dependency>
   	<groupId>org.springframework.cloud</groupId>
   	<artifactId>spring-cloud-starter-openfeign</artifactId>
   </dependency>
   ```

2. 主启动上加上`@EnableFeignClient`注解

   ```java
   @SpringBootApplication
   @EnableFeignClients
   public class OpenFeignOrder {
   
       public static void main(String[] args) {
           SpringApplication.run(OpenFeignOrder.class, args);
       }
   }
   ```

   

3. 将被调用方微服务的Controller接口封装成接口，这样不需要使用restemplate的方式调用

   ```java
   @Component
   @FeignClient(value = "PAYMENT-HYSTRIX-SERVICE", fallbackFactory = MyFallbackFactory.class)
   public interface PaymentFeign {
   
       /**
        * normal
        * @return
        */
       @GetMapping("/payment/normal")
       String normal();
   
       /**
        * timeout
        * @return
        */
       @GetMapping("/payment/timeout")
       String timeout();
   
       @GetMapping("/payment/circuit/{id}")
       String paymentCircuitBreaker(@PathVariable("id") Integer id);
   }
   ```

4. 熔断器

   ```java
   /**
    * 这里主要是处理异常出错的情况(降级/熔断时服务不可用，fallback就会找到这里来)
    */
   @Component
   public class MyFallbackFactory implements FallbackFactory<PaymentFeign> {
   
       @Override
       public PaymentFeign create(Throwable throwable) {
           return new PaymentFeign() {
               @Override
               public String normal() {
                   return null;
               }
   
               @Override
               public String timeout() {
                   return null;
               }
           };
       }
   }
   ```

   

5. controller层调用时使用

   ```java
   @RestController
   @Slf4j
   public class OrderOpenFeignController {
   
       @Autowired
       private PaymentFeign paymentFeign;
   
       @GetMapping("/order/payment/get")
       public Result getPayment(@RequestParam(value = "id") Integer id){
           log.info("接收到序列号id: {}", id);
           return paymentFeign.getPayment(id);
       }
   
       @GetMapping("/order/payment/timeout/get")
       public String getPaymentTimeout(@RequestParam(value = "id") Integer id){
           log.info("接收到序列号id: {}", id);
           // openfeign-ribbon, 客户端默认等待1s
           return paymentFeign.getServerPortWhenTimeout();
       }
   }
   ```

总结： OpenFeign集成了Ribbon和Hystrix，使得服务调用更加简单优雅，并且提供服务降级策略，使用起来更方便。



## 四、服务熔断降级

前面主要讲述了服务注册与调用，在实际项目中，出现服务之间的调用失败或者超时的情况是正常不过的，这时候需要对服务的调用进行熔断降级处理，比如让其返回异常情况。常见的熔断降级组件有以下几种

* **Hystrix**
* **Resilience4j**
* **Sentinel**

下图展示了熔断的概念，来自Martin Flower的[CircuitBreaker](https://martinfowler.com/bliki/CircuitBreaker.html)

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521172121.png" alt="img" style="zoom: 67%;" />

### 4.1 Hystrix

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统中，许多依赖不可避免的会调用失败，超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，提高分布式系统的弹性。

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521151634.png" alt="image-20210521151631323" style="zoom:67%;" />

但是！！！由官网可知，现在Hytrix已经停更了。不过，其熔断降级的思想还是得学习了解，因为后面官方推荐的替代品resilience4j和alibaba的sentinel都是借鉴了Hytrix的思想来进行开发。



**Hystrix设计原则**

1. 防止单个服务的故障，耗尽整个系统服务的容器（比如tomcat）的线程资源，避免分布式环境里大量级联失败。通过第三方客户端访问（通常是通过网络）依赖服务出现失败、拒绝、超时或短路时执行回退逻辑
2. 用快速失败代替排队(每个依赖服务维护一个小的线程池或信号量，当线程池满或信号量满，会立即拒绝服务而不会排队等待)和优雅的服务降级；当依赖服务失效后又恢复正常，快速恢复
3. 提供接近实时的监控和警报，从而能够快速发现故障和修复。监控信息包括请求成功，失败（客户端抛出的异常），超时和线程拒绝。如果访问依赖服务的错误百分比超过阈值，断路器会跳闸，此时服务会在一段时间内停止对特定服务的所有请求
4. 将所有请求外部系统（或请求依赖服务）封装到HystrixCommand或HystrixObservableCommand对象中，然后这些请求在一个独立的线程中执行。使用隔离技术来限制任何一个依赖的失败对系统的影响。每个依赖服务维护一个小的线程池（或信号量），当线程池满或信号量满，会立即拒绝服务而不会排队等待



**Hystrix特性**

1. 请求熔断： 当Hystrix Command请求后端服务失败数量超过一定比例(默认50%), 断路器会切换到开路状态(Open)。这时所有请求会直接失败而不会发送到后端服务。断路器保持在开路状态一段时间后(默认5秒)，自动切换到半开路状态(HALF-OPEN)。 这时会判断下一次请求的返回情况，如果请求成功，断路器切回闭路状态(CLOSED)，否则重新切换到开路状态(OPEN)。Hystrix的断路器就像我们家庭电路中的保险丝， 一旦后端服务不可用，断路器会直接切断请求链，避免发送大量无效请求影响系统吞吐量，并且断路器有自我检测并恢复的能力。
2. 服务降级：Fallback相当于是降级操作。 对于查询操作，我们可以实现一个fallback方法，当请求后端服务出现异常的时候，可以使用fallback方法返回的值。fallback方法的返回值一般是设置的默认值或者来自缓存，告知后面的请求服务不可用了，不要再来了。





**下图显示了通过Hystrix向服务依赖项发出请求时发生的情况：**

![hystrix-command-flow-chart](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521154635.png)

1. 每次调用创建一个新的HystrixCommand,把依赖调用封装在run()方法中

2. 执行execute()/queue做同步或异步调用

3. 是否在缓存中

4. 判断熔断器(circuit-breaker)是否打开,如果打开跳到步骤8，进行降级策略，如果关闭进入步骤5

5. 判断线程池/队列/信号量是否跑满，如果跑满进入降级步骤8，否则继续后续步骤6

6. 调用HystrixCommand的run方法.运行依赖逻辑

   1. 依赖逻辑调用超时,进入步骤8.

7. 判断逻辑是否调用成功
   　7a:返回成功调用结果
   　7b:调用出错，进入步骤8.

8. 计算熔断器状态,所有的运行状态(成功, 失败, 拒绝,超时)上报给熔断器，用于统计从而判断熔断器状态

9. getFallback()降级逻辑.以下四种情况将触发getFallback调用：
   　　     (1):run()方法抛出非HystrixBadRequestException异常。
   　　     (2):run()方法调用超时
   　　     (3):熔断器开启拦截调用
   　　     (4):线程池/队列/信号量是否跑满

   9a:没有实现getFallback的Command将直接抛出异常
   9b:fallback降级逻辑调用成功直接返回
   9c:降级逻辑调用失败抛出异常

10. 返回执行成功结果



下图显示了HystrixCommand或@HystrixObservableCommand如何与HystrixCircuitBreaker交互以及其逻辑和决策流程，包括计数器在断路器中的行为。

<img src="https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521154744.png" alt="circuit-breaker-1280" style="zoom:80%;" />





**什么是服务熔断？**

​		熔断机制是应对雪崩效应的一种微服务链路保护机制，当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的相应信息。当检测当该节点微服务调用响应正常后恢复调用链路。

```markdown
举个例子解释，生活中每家每户都在用电，小明家的电线因为故障导致了小明家停电了。而小李、小张家的电是正常使用的。电力公司没有因为小明家有故障线路而停掉其他人家的电，同时小明家没有使用有故障的电路的电。这时即为熔断。熔断的目的是当A服务模块中的某块程序出现故障后为了不影响其他客户端的请求而做出的及时回应。
```
**什么是服务降级？**

​		降级一般是从整体负荷考虑，当某个服务熔断之后，服务器将不再被调用，客户端可自己准备一个本地的fallback回调，返回一个缺省值，虽然服务水平下降，但是还能用，比直接挂掉要强。

```markdown
markdown举个例子解释，我们去银行排队办理业务，大部分的银行分为普通窗口、特殊窗口（VIP窗口，老年窗口）。某一天银行大厅排普通窗口的人巨多。这时特殊窗口贴出告示说某时刻之后再开放。那么这时特殊窗口的工作人员就可以空出来去帮其他窗口办理业务，提高办事效率，已达到解决普通窗口排队的人过的目的。这时即为降级，降级的目的是为了解决整体项目的压力，而牺牲掉某一服务模块而采取的措施。
```



**两者的目的性很一致**：都是从可用性可靠性着想，为防止系统的整体缓慢甚至崩溃，采用的技术手段；

**两者触发原因不一样**：服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑



**什么是服务雪崩？**

​		当A调用微服务B，B调C，和其他微服务，这是扇出，当扇出链路上某个微服务调用响应时间过长或者不可用，对微服务的A的调用就会占用越来越多的系统资源，导致系统崩溃，所谓的雪崩效应。



**使用说明**

导入Hystrix依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

启动类配置注解

```java
package com.lin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//启动类注解
@SpringBootApplication
@EnableDiscoveryClient //服务发现
@EnableCircuitBreaker  //添加对熔断的支持
public class UserProvider_hystrix {
    public static void main(String[] args) {
        SpringApplication.run(UserProvider_hystrix.class,args);
    }
}
```

接口熔断方法：

使用@HystrixCommand方法注解在方法上

```java
package com.lin.springcloud.controller;


import com.lin.springcloud.pojo.User;
import com.lin.springcloud.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/add")
    public boolean addUser(User user){
        return userService.addUser(user);
    }

    //使用注解方式来调用熔断方法
    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/user/get/{id}")
    public User queryUserById(@PathVariable(value = "id") int id){
        User user = userService.queryUserById(id);
        if(user == null) throw new RuntimeException("该id"+id+"查找不到用户");
        return userService.queryUserById(id);
    }

    @GetMapping("/user/list")
    public List<User> queryListUser(){
        return userService.queryListUser();
    }


    //熔断处理的备选方法
    public User hystrixGet(@PathVariable("id") int id){
        return new User()
                .setId(id)
                .setName("该id"+id+"查找不到用户！")
                .setDb_source("无该数据库");
    }

}
```



**Hystrix——服务降级**

```java
package com.lin.springcloud.service;

import com.lin.springcloud.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
//调用我们的实现的fallbackFactory的实现类
@FeignClient(value = "SPRINGCLOUD-PROVIDER",fallbackFactory = UserFeignServiceFallbackFactory.class)
public interface UserFeignService {

    @PostMapping("/user/add")
    public boolean addUser(User user);

    @GetMapping("/user/get/{id}")
    public User queryUserById(@PathVariable(value = "id") int id);

    @GetMapping("/user/list")
    public List<User> queryListUser();

}
```

自定义降级策略UserFeignServiceFallbackFactory：

```java
package com.lin.springcloud.service;

import com.lin.springcloud.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFeignServiceFallbackFactory implements FallbackFactory {
    @Override
    public Object create(Throwable throwable) {
        return new UserFeignService() {
            @Override
            public boolean addUser(User user) {
                return false;
            }

            @Override
            public User queryUserById(int id) {
                return new User()
                        .setId(id)
                        .setName("目前服务不可用！服务降级演示")
                        .setDb_source("no data_source");
            }

            @Override
            public List<User> queryListUser() {
                return null;
            }
        };
    }
}
```



### 4.2 Resilience4j



### 4.3 Sentinel



## 五、服务网关

> 服务网关的作用：限流、过滤、鉴权。

有了网关之后，微服务的框架可以像这样

![image-20210521172004237](https://gitee.com/zhangyijun0229/picture-for-pic-go/raw/master/img/20210521172004.png)

* **Zuul**
  * Zuul 1.0版本：阻塞的I/O，基于Servlet 2.5的阻塞架构
  * Zuul 2.0版本：基于Netty异步非阻塞
* **Gateway**

### 5.1 Spring Cloud Gateway

**Gateway三大核心**

* Route(路由)

  网关配置的基本组成模块，一个**Route模块**由一个 ID，一个目标 URI，一组断言和一组过滤器定义。如果断言为真，则路由匹配，目标URI会被访问。

* Predicate(断言)

  这是一个 Java 8 的 Predicate，可以使用它来匹配来自 HTTP 请求的任何内容。

* Filter(过滤)

  可以使用它拦截和修改请求，并且对上游的响应，进行二次处理

Spring Cloud Gateway使用的是webflux中的reactor-netty响应式编程，底层使用的框架是netty。



## 六、服务分布式配置

> 服务配置：

**技术选型：**

* **Config**
* **Nacos**



## 七、服务总线

> 服务总线：

**技术选型：**

* **Bus**
* **Nacos**



## 技术选型

**1 服务框架：**

> 若是采用SpringCloud的一套组件：Nacos(服务注册、服务配置、服务总线) + Ribbon(服务调用) + Gateway(服务网关) 。

**2 缓存**

>  Redis

**3 消息中间件**

> RocketMQ 、RabbitMQ、Kafka

性能对比：

|               | RocketMQ            | Kafka                   | RabbitMQ             |
| ------------- | ------------------- | ----------------------- | -------------------- |
| **性能对比**  | 吞吐量在11.6 w/s    | 吞吐量在17.3 w/s        | 吞吐量在5.95 w/s     |
| **消息存储**  | 支持大量堆积        | 支持大量堆积            | 支持少量堆积         |
| **负载均衡**  | 支持                | 支持                    | 对负载均衡的支持不好 |
| **集群方式**  | 支持                | 支持                    | 支持                 |
| **消息确认**  | 支持                | 支持                    | 支持                 |
| **消息重试**  | 支持                | 不支持                  | 不支持               |
| **Topic数量** | 支持几百几千的Topic | Topic几十上百个性能下降 |                      |



## 项目实战

> 基于SpringCloud搭建一个秒杀系统。

**秒杀系统需要解决的问题**

**1 如何防止超卖**

> 1. 悲观锁 synchronized
> 2. 乐观锁 CAS + version



**2 redis库存如何与数据库库存保持数据一致性**

> 



**3 接口限流**

> 当并发量很高时，需要做接口限流。

**4 高并发情况下保护系统的方式**

* 缓存：提升系统访问访问速度和增大系统处理容量
* 降级：当服务器压力巨大时，根据当前业务情况进行一定的降级策略，以减缓服务器压力从而保证服务器的核心业务正常运行
* 限流：对并发访问/请求进行限速，或者对同一个时间窗口内的请求进行限速从而保护系统。4.1 限流的方式

**限流算法：**

* 令牌桶算法
* 漏斗算法



### 秒杀项目

使用消息队列碰到的问题

```java
java.lang.IllegalArgumentException: SimpleMessageConverter only supports String, byte[] and Serializable payloads, received:
```



jmeter压测响应结果集乱码：

添加Bean Shell 后置处理器

```
prev.setDataEncoding("UTF-8");
```


