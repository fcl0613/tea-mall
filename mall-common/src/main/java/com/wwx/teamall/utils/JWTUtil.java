package com.wwx.teamall.utils;


import cn.hutool.core.util.StrUtil;
import com.wwx.teamall.exception.BadRequestException;
import com.wwx.teamall.model.UserInfo;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JWTUtil {
    private final String secretKey = "wwxteamall";
    private final long expiration = 15 * 24 * 60 * 60 * 1000;

    public String createToken(Integer userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public Integer getUserId(String token) {
        try {
            if (StrUtil.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUsername(String token) {
        try {
            if (StrUtil.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userInfo 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserInfo userInfo) {
        String username = getUsername(token);
        return username.equals(userInfo.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
            throw new BadRequestException("无效认证信息，请重新登录");
        }
        return claims;
    }

    public static void main(String[] args) {
        JWTUtil jwtUtil = new JWTUtil();
        String token = jwtUtil.createToken(1, "admin");
        System.out.println(token);
        System.out.println(jwtUtil.isTokenExpired(token));

    }
}
