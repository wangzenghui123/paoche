package com.example.paoche;

import com.example.paoche.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenUtilTest {

    @Test
    public void testGetAccessToken() throws NoSuchFieldException {
        String accessToken = JwtTokenUtil.getAccessToken("111", new HashMap<>());
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
        System.out.println("Subject "+claimsFromToken.getSubject());
        System.out.println(" £”‡ ±º‰"+JwtTokenUtil.getRemainingTime(accessToken));
    }
}
