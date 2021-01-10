package com.zyj.cloud.service;

import com.zyj.cloud.beans.PaymentBean;

import java.util.List;

/**
 * @author: zhangyijun
 * @date: created in 16:59 2021/1/9
 * @description
 */
public interface PaymentService {
    /**
     * 新增支付订单
     * @param paymentBean
     * @return id
     */
    Integer insert(PaymentBean paymentBean);

    /**
     * 查询支付订单
     * @param paymentBean
     * @return
     */
    List<PaymentBean> getPaymentByCondition(PaymentBean paymentBean);

}
