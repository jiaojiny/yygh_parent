package com.atguigu.yygh.model.user;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 就诊人表
 * </p>
 *
 * @author JIAO
 * @since 2022-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Patient implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件类型
     */
    private String certificatesType;

    /**
     * 证件编号
     */
    private String certificatesNo;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生年月
     */
    private LocalDate birthdate;

    /**
     * 手机
     */
    private String phone;

    /**
     * 是否结婚
     */
    private Integer isMarry;

    /**
     * 省code
     */
    private String provinceCode;

    /**
     * 市code
     */
    private String cityCode;

    /**
     * 区code
     */
    private String districtCode;

    /**
     * 详情地址
     */
    private String address;

    /**
     * 联系人姓名
     */
    private String contactsName;

    /**
     * 联系人证件类型
     */
    private String contactsCertificatesType;

    /**
     * 联系人证件号
     */
    private String contactsCertificatesNo;

    /**
     * 联系人手机
     */
    private String contactsPhone;

    /**
     * 就诊卡号
     */
    private String cardNo;

    /**
     * 是否有医保
     */
    private Integer isInsure;

    /**
     * 状态（0：默认 1：已认证）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    @TableLogic
    private Integer isDeleted;


}
