package uz.master.warehouse.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {
    public static int expire=600000;
    public static String secret="gfhg4561967$%#%$%^?>?>:gjhgsdfsd?>jahdqpdsdhgf";
    public static Date getExpireDate(){
        return new Date(expire+System.currentTimeMillis());
    }
    public static Date getExpireDateForRefreshToken(){
        return new Date(expire+System.currentTimeMillis()+2000000);
    }
    public static Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
    }

    public static JWTVerifier verifier(){
        return JWT.require(getAlgorithm()).build();
    }
}
