package com.atguigu.yygh.sms.controller.front;

import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.sms.service.SmsService;
import com.atguigu.yygh.sms.utils.RandomUtil;
import com.atguigu.yygh.sms.utils.SmsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Api("短信接口")
@RestController
@RequestMapping("/front/sms")
public class FrontSmsController {
    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("发短信")
    @PostMapping("send/{phone}")
    public R sms(@PathVariable String phone){

        //生成随机验证码
        String code = RandomUtil.getFourBitRandom();
        //发送短信

            smsService.send(phone,code);

        //将验证码存到 redis 设置过期时间
        redisTemplate.opsForValue().set("code:"+phone,code,5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }


}
