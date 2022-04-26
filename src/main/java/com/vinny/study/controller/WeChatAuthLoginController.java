package com.vinny.study.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Vinny
 * @version $Id: WeChatAuthLoginController, v1.0 2022/4/19 10:14 Vinny Exp $
 */
@RestController
@RequestMapping("wechat/wx")
public class WeChatAuthLoginController {

    @RequestMapping(value = "/authLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String authLogin(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        if (!CollectionUtils.isEmpty(paramsMap)) {
            paramsMap.forEach((key , value) -> {
                System.out.println(key + ":" + JSON.toJSONString(value));
            });
        }

        String getAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc4debb736d6a5140&secret=4bd12763225d656029f1df07708e6d3e&grant_type=authorization_code&code=" + paramsMap.get("code")[0];
        RestTemplate restTemplate = new RestTemplate(Collections.singletonList(new StringHttpMessageConverter(StandardCharsets.UTF_8)));
        String getAccessTokenResult = restTemplate.getForObject(getAccessTokenUrl, String.class);
        System.out.println("getAccessTokenResult:" + getAccessTokenResult);

        JSONObject jsonObject = JSON.parseObject(getAccessTokenResult);

        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonObject.getString("access_token") + "&openid=" + jsonObject.getString("openid") + "&lang=zh_CN";
        String getUserInfoResult = restTemplate.getForObject(getUserInfoUrl, String.class);
        System.out.println("getUserInfoResult:" + getUserInfoResult);
        JSONObject userInfoJsonObject = JSON.parseObject(getUserInfoResult);
        System.out.println(JSON.toJSONString(userInfoJsonObject, SerializerFeature.PrettyFormat));

        return "success";
    }

    @RequestMapping(value = "/handleMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public String handleMessage(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        if (!CollectionUtils.isEmpty(paramsMap)) {
            paramsMap.forEach((key , value) -> {
                System.out.println(key + ":" + JSON.toJSONString(value));
            });
        }
        return paramsMap.get("echostr")[0];
    }

}
