package com.dcd.finance.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dcd.finance.bean.Login;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
 * @Title:
 * @author MRC
 * @date
 * @version V1.0
 */
@Service("TokenService")
public class TokenService {

    public String getToken(Login login) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(login.getLoginId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(login.getLoginPass()));
        return token;
    }
}
