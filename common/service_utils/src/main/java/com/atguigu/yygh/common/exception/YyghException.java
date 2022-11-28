package com.atguigu.yygh.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常：集成运行时异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YyghException extends RuntimeException{

    private String msg; //前端错误消息
    private Integer code; //响应码

    public YyghException(Integer code, String msg, Exception e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

   /* private YyghException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }*/
}
