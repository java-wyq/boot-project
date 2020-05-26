package com.lw.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;

/**
 * @author wangyanqiang
 * @title: JwtTokenUtils
 * @date 2020/5/616:45
 */
@Component
@SuppressWarnings("all")
public class JwtTokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    private static Long EXPIRE;
    private static String ISSUER;
    private static String SECRET;

    @Value("${jwt.expire}")
    public void setEXPIRE(Long expire) {
        JwtTokenUtils.EXPIRE = expire;
    }
    @Value("${jwt.issuer}")
    public void setISSUER(String issuer) {
        JwtTokenUtils.ISSUER = issuer;
    }
    @Value("${jwt.secret}")
    public void setSECRET(String secret) {
        JwtTokenUtils.SECRET = secret;
    }

    public static String create(Map<String, Object> claimsMap) {
        return Jwts.builder()
                   .setClaims(claimsMap)
                   .setIssuedAt(new Date())
                   .setIssuer(ISSUER)
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                   .signWith(SignatureAlgorithm.HS384, SECRET).compact();

    }

    public static Claims verify(String token){
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            logger.info("jwt exception info :" + e.getMessage() + " cause: " + e.getCause());
            throw e;
        }
    }
}
