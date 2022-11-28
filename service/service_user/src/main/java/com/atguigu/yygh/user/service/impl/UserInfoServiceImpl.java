package com.atguigu.yygh.user.service.impl;


import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.utils.JwtHelper;
import com.atguigu.yygh.enums.AuthStatusEnum;
import com.atguigu.yygh.model.user.UserInfo;
import com.atguigu.yygh.user.mapper.UserInfoMapper;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.vo.user.LoginVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author JIAO
 * @since 2022-11-25
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Map<String, Object> login(LoginVo loginVo) {
        //解析数据
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();

        //健壮性检验
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)){
        throw new YyghException("参数不能为空",20001);
        }

        //判断验证码是否正确
        //从 redis 中根据用户手机号获取验证码 和 请求的验证码code 进行比较
        String codeNum = (String) redisTemplate.opsForValue().get("code:" + phone);
        if(!code.equals(codeNum)){
                throw new YyghException("验证码不正确",20001);
            }
        //根据手机号查找用户
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

      //存在 被锁定
        if (userInfo!=null&&userInfo.getStatus()==0){
            throw new YyghException("该账号已被锁定",20001);

        }

        //不存在，去注册
        if (userInfo==null){

            //怎么去注册呢，就是新增一条数据，新增什么呢，注册什么就新增什么，
            userInfo = new UserInfo();
            userInfo.setNickName(phone);
            userInfo.setPhone(phone);
            userInfo.setName(phone);
            userInfo.setStatus(1);
            userInfo.setAuthStatus(AuthStatusEnum.NO_AUTH.getStatus());
            baseMapper.insert(userInfo);
        }

        //登录  ( 登录  就是  生成令牌并返回给前端  )
        Map<String, Object> map = new HashMap<>();

        String name = userInfo.getName();
        String token = JwtHelper.createToken(userInfo.getId(), name);

        map.put("name",name);//name
        map.put("token",token);//token

        return map ;
    }
}
