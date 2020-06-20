package com.systemmanage.security.handler;

import com.systemmanage.common.config.JWTConfig;
import com.systemmanage.common.util.JWTTokenUtil;
import com.systemmanage.common.util.ResultUtil;
import com.systemmanage.redis.util.RedisClient;
import com.systemmanage.redis.util.RedisUtil;
import com.systemmanage.security.entity.SelfUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * 登录成功返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        Long time= (long) (7 * 60 * 60 * 24);
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity);
        //redisClient.set(selfUserEntity.getUsername(),token,time);
        token = JWTConfig.tokenPrefix + token;
        RedisUtil.set("admin",token);
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        ResultUtil.responseJson(response,resultData);
    }
}