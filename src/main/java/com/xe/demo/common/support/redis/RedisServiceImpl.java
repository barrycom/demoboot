/*
package com.xe.demo.common.support.redis;

import com.xe.demo.common.conf.Constants;
import com.xe.demo.common.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

*/
/**
 * Created by Administrator on 2017-10-16.
 *//*

@Service
public class RedisServiceImpl implements IRedisService{
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

  */
/*  @Autowired
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }*//*


    public TokenModel createToken(String  userId) {
        //使用uuid作为源token

        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, userId+"_"+token);

        String tokens=userId+"_"+token.trim();
        redisTemplate.boundValueOps(userId).set(userId+"_"+token.trim(),Constants.TOKEN_EXPIRES_HOUR,TimeUnit.HOURS);
        //存储到redis并设置过期时间
   //    redisTemplate.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String  userId =param[0];
        String token = param[1];
        return new TokenModel(userId, token);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        if(redisTemplate.boundValueOps(model.getUserId())==null)
        {
            return  false;
        }
        else {

                String token=redisTemplate.boundValueOps(model.getUserId()).get();
                if(token==null)
                {
                    return false;
                }
        //    String token = redisTemplate.boundValueOps(model.getUserId()).get().toString();
               token=token.toString().trim();
            if (token == null || !token.equals(model.getUserId()+"_"+model.getToken())) {
                return false;
            }
            else
            {
                redisTemplate.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
                return true;
            }
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间

    }

    public void deleteToken(String userId) {
        redisTemplate.delete(userId);
    }
}*/
