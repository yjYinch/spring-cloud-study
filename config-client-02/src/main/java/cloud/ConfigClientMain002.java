package cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : zhang yijun
 * @date : 2021/1/27 10:58
 * @description : TODO
 */

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain002 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain002.class, args);
    }
}
