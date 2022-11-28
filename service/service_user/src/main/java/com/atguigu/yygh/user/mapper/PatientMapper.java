package com.atguigu.yygh.user.mapper;


import com.atguigu.yygh.model.user.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 就诊人表 Mapper 接口
 * </p>
 *
 * @author JIAO
 * @since 2022-11-25
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

}
