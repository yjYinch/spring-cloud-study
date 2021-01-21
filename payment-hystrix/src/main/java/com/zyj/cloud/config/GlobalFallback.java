package com.zyj.cloud.config;

import com.zyj.cloud.beans.Result;

/**
 * @author : zhang yijun
 * @date : 2021/1/21 14:36
 * @description : TODO
 */

public class GlobalFallback {
    public Result paymentGlobalFallbackMethod(){
        return Result.error("全局异常处理....");
    }
}
