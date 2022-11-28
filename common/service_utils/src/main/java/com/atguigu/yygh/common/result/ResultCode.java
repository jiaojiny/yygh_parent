package com.atguigu.yygh.common.result;

public interface ResultCode {

    Integer SUCCESS = 20000; //成功响应码
    Integer ERROR = 20001; //默认失败响应码
    Integer ERROR_DELETE = 20101; //删除失败
    Integer ERROR_ARITHMETIC = 20102; //计算错误
    Integer ERROR_BADSQL = 20103; //SQL语法错误
    Integer ERROR_DUPLICATE_DATA = 20104; //数据重复

    Integer ERROR_NO_DATA = 20105; //数据不存在


}
