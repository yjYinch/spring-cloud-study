package cloud.controller;

import cloud.service.PaymentService;
import com.zyj.cloud.beans.PaymentBean;
import com.zyj.cloud.beans.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangyijun
 * @date: created in 16:58 2021/1/9
 * @description
 */

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/get/{id}")
    public Result getPayment(@PathVariable("id") Integer id){
        log.info("得到id为{}", id);
        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setId(id);
        List<PaymentBean> paymentBeanList = paymentService.getPaymentByCondition(paymentBean);
        log.info("获取成功，端口号：{}", serverPort);
        return Result.success(paymentBeanList);
    }

    @PostMapping("/payment/insert")
    public Result insertPayment(@RequestBody PaymentBean paymentBean){
        Integer id = paymentService.insert(paymentBean);
        if (id == null || id ==0){
            return Result.error();
        }
        log.info("插入成功，端口号：{}", serverPort);
        return Result.success(null);
    }

    @GetMapping("/payment/getPort")
    public String getServerPort(){
        return serverPort;
    }

    @GetMapping("/payment/timeout/getPort")
    public String getServerPortWhenTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.info("超时异常");
        }
        return serverPort;
    }
}
