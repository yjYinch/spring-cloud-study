package cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: zhangyijun
 * @date: created in 20:18 2021/1/10
 * @description
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain02 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain02.class, args);
    }
}
