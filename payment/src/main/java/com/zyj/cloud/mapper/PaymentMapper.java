package com.zyj.cloud.mapper;

import com.zyj.cloud.beans.PaymentBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zhangyijun
 * @date: created in 14:16 2021/1/10
 * @description
 */

@Mapper
public interface PaymentMapper {

    /**
     * 新增支付订单
     * @param paymentBean
     * @return 主键id
     */
    Integer insert(PaymentBean paymentBean);

    /**
     * 查询支付订单
     * @param paymentBean
     * @return
     */
    List<PaymentBean> getPaymentByCondition(PaymentBean paymentBean);
}
