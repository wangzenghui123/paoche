package com.example.paoche.util;

import com.example.paoche.constants.Constant;
import com.example.paoche.shiro.MyUsernamePasswordToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

public class JwtTokenUtil {

    public   static  String secretKey;

    public static Duration accessTokenExpireTime;

    public static  Duration refreshTokenExpireTime;

    public static Duration refreshTokenExpireAppTime;

    public static String issuer;

    public static void setJwtTokenUtil(TokenSettings tokenSettings){
        secretKey = tokenSettings.getSecretKey();
        accessTokenExpireTime = tokenSettings.getAccessTokenExpireTime();
        refreshTokenExpireTime = tokenSettings.getRefreshTokenExpireTime();
        refreshTokenExpireAppTime = tokenSettings.getRefreshTokenExpireAppTime();
        issuer = tokenSettings.getIssuer();
    }

    public static String getAccessToken(String subject, Map<String, Object> claims){
        return generateToken(subject,claims,accessTokenExpireTime.toMillis(),issuer,secretKey);
    }
    public static String getRefreshToken(String subject,Map<String,Object> claims){
        return generateToken(subject,claims,refreshTokenExpireTime.toMillis(),issuer,secretKey);
    }
    public static String getRefreshAppToken(String subject,Map<String,Object> claims){
        return generateToken(subject,claims,refreshTokenExpireAppTime.toMillis(),issuer,secretKey);
    }
    public static Claims getClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
    }
    public static String getUserId(String token){
        return getClaimsFromToken(token).getSubject();
    }
    public static String getUserName(String token){
        return (String) getClaimsFromToken(token).get(Constant.JWT_USER_NAME);
    }

    public static Boolean isTokenExpired(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        Date expiration = claimsFromToken.getExpiration();
        return expiration.before(new Date());
    }
    public static Boolean validateToken(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        return (null !=  claimsFromToken && !isTokenExpired(token) );
    }
    public static String refreshToken(String refreshToken,Map<String,Object> claims){
        Claims claimsFromToken = getClaimsFromToken(refreshToken);
        if(null == claims){
            claims = claimsFromToken;
        }
        return generateToken(claimsFromToken.getSubject(),claims,accessTokenExpireTime.toMillis(), claimsFromToken.getIssuer(), secretKey);
    }
    public static long getRemainingTime(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        long nowMills = System.currentTimeMillis();
        return claimsFromToken.getExpiration().getTime() - nowMills;
    }

    public static String generateToken(String subject,Map<String,Object> claims,long accessTokenExpireTime,String issuer,String secretKey){
       JwtBuilder jwtBuilder = Jwts.builder();
        if(subject != null){
            jwtBuilder.setSubject(subject);
        }
        if(claims != null && !claims.isEmpty()){
            jwtBuilder.setClaims(claims);
        }
        if(accessTokenExpireTime >= 0){
            jwtBuilder.setExpiration(new Date(accessTokenExpireTime+System.currentTimeMillis()));
        }
        if(issuer != null){
            jwtBuilder.setIssuer(issuer);
        }
        jwtBuilder.setIssuedAt(new Date(System.currentTimeMillis()));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes =  DatatypeConverter.parseBase64Binary(secretKey);
        jwtBuilder.signWith(signatureAlgorithm,bytes);
        return  jwtBuilder.compact();
    }
}
