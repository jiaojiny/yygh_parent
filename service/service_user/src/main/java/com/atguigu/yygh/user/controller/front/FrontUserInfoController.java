package com.atguigu.yygh.user.controller.front;



import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.vo.user.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@Api("登录接口")
@RestController
@RequestMapping("/front/user/userInfo")
public class FrontUserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        Map<String, Object> userInfo = userInfoService.login(loginVo);

        return R.ok().data(userInfo);
    }


}
