package com.atguigu.yygh.common.exception;

import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("计算错误").code(ResultCode.ERROR_ARITHMETIC);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        e.printStackTrace();
        return R.error().message("SQL语法错误").code(ResultCode.ERROR_BADSQL);
    }

    /**
     * 捕获自定义异常，处理程序中各种错误，为前端返回个性化的错误结果，同时在后端能够跟踪到异常原因
     * @param e
     * @return
     */
    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public R error(YyghException e){
        log.error("YyghException日志处理");
//        log.error(e.getMessage());
        log.error(ExceptionUtils.getStackTrace(e));
        return R.error().message(e.getMsg()).code(e.getCode());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public R error(RuntimeException e){
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }

    /**
     * 特定异常
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public R error(DuplicateKeyException e){
        e.printStackTrace();
        return R.error().message("编号重复").code(ResultCode.ERROR_DUPLICATE_DATA);
    }
}
