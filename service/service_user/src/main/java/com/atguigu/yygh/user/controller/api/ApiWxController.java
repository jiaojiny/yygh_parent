package com.atguigu.yygh.user.controller.api;

import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.user.utils.ConstantProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Api("微信扫码登录")
@Controller
@RequestMapping("/api/user/wx")
@Slf4j
public class ApiWxController {
    @Autowired
    private ConstantProperties constantProperties;

    // ~~~~~~~~~~~~方式1：在新的页码显示二维码~~~~~~~~~~~~

    @ApiOperation("扫码")
    @GetMapping("getQRCodeUrl")
    public String getQRCodeUrl(HttpSession session) {
        //请求重定向     微信

        //https://open.weixin.qq.com/connect/qrconnect?
        // appid=wx827225356b689e24
        // &state=D03A7F2E7B31381BE95A750BB31104F0826961A85DA2BF965EC836F387B2F7E2C6ACEDEE5F9D17F017BB73289254AA59
        // &redirect_uri=https%3A%2F%2Fqq.jd.com%2Fnew%2Fwx%2Fcallback.action%3Fview%3Dnull%26uuid%3De5ab860cdd124274820f647425ec5fbd
        // &response_type=code
        // &scope=snsapi_login#wechat_redirect

        try {
            String redirectUri = URLEncoder.encode(constantProperties.getRedirectUri(), "UTF-8");

            //要把这个state 存入session 微信要通过这个状态进行用户认证  分为两种情况，一个是页码直接打开weixin 一个是嵌套进 iframe
            String state = UUID.randomUUID().toString();
            session.setAttribute("wx_open_state", state);

            String url = "https://open.weixin.qq.com/connect/qrconnect?" +
                    "appid=" + constantProperties.getAppId() +
                    "&state=" + state +
                    "&redirect_uri=" + redirectUri +
                    "&response_type=code" +
                    "&scope=snsapi_login#wechat_redirect";

            return "redirect:" + url;
        } catch (Exception e) {
            throw new YyghException("生成二维码失败", 20001);
        }


    }

    //```````````方式2````````````
    @GetMapping("getQRCodeParams")
    @ResponseBody  //这个方法是为了让前台把微信内嵌到页面上，前台需要一下参数 scope appid redirectUri state
    public R getQRCodeParams(HttpSession session) {


        try {
            String redirectUri = URLEncoder.encode(constantProperties.getRedirectUri(), "UTF-8");


            //处理state：生成随机数，存入session
            String state = UUID.randomUUID().toString();
            log.info("生成 state = " + state);
            session.setAttribute("wx_open_state", state);


            //组装前端需要的参数
            Map<String, Object> map = new HashMap<>();
            map.put("scope", "snsapi_login");
            map.put("appid", constantProperties.getAppId());
            map.put("redirectUri", redirectUri);
            map.put("state", state);
            return R.ok().data(map);
        } catch (UnsupportedEncodingException e) {
            throw new YyghException("",20001);
        }
    }


@GetMapping("callback")
    public String callback(String code,String state,HttpSession session){
        //


    //https://api.weixin.qq.com/sns/oauth2/access_token?
    // appid=APPID
    // &secret=SECRET
    // &code=CODE
    // &grant_type=authorization_code

        return  "redirect:";

}

}
