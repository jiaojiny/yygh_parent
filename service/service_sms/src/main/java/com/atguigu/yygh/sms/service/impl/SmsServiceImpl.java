package com.atguigu.yygh.sms.service.impl;


import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.sms.service.SmsService;

import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public void send(String phone, String code) {
        try {
            Config config = new Config()
                    // 必填，您的 AccessKey ID
                    .setAccessKeyId("LTAI5tKgN2BCR141CTxCN9qt")
                    // 必填，您的 AccessKey Secret
                    .setAccessKeySecret("3DSwXtwbcWakgTAj9fdADTDcHcVBYS");
            config.endpoint = "dysmsapi.aliyuncs.com";
            Client client = new Client(config);

            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName("谷粒")
                    .setTemplateCode("SMS_96695065")
                    .setTemplateParam("{\"code\":"     + JSON.toJSONString(code+"")  +  "}");

            RuntimeOptions runtime = new RuntimeOptions();


            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
          //  TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
          //  com.aliyun.teautil.Common.assertAsString(error.message);
            throw new YyghException("验证失败",20001);


        }
    }
}


















