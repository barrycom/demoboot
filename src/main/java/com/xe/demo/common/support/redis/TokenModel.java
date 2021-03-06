package com.xe.demo.common.support.redis;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-10-16.
 */
public class TokenModel  implements Serializable {
    private static final long serialVersionUID = -8366929034564774130L;
    //用户id
    private String userId;

    //随机生成的uuid
    private String token;

    public TokenModel(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}