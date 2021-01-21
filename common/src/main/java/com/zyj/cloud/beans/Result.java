package com.zyj.cloud.beans;

import lombok.Data;

/**
 * @author: zhangyijun
 * @date: created in 15:00 2021/1/10
 * @description
 */
@Data
public class Result {
    private Integer code;

    private String message;

    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static Result error(String message){
        if (message == null){
            return error();
        }
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(-1);
        result.setMessage("error");
        result.setData(null);
        return result;
    }
}
