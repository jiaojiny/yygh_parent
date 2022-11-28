package com.atguigu.yygh.common.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class R {
//    "success": 布尔, //响应是否成功
    private Boolean success;
//    "code": 数字, //响应码
    private Integer code;
//    "message": 字符串, //返回消息
    private String message;
//    "data": HashMap //返回数据，放在键值对中
    private Map<String, Object> data = new HashMap<>();

    private R(){};

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> data){
        this.data = data;
        return this;
    }

    public R message(String message){
        this.message = message;
        return this;
    }

    public R code(Integer code){
        this.code = code;
        return this;
    }
}
