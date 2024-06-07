package com.tlias;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasApplicationTests {

    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "tlias")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("tlias")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxNzY5ODUzNH0.nc5-plc3Qxe85NovYcL0Ng-dNxcij_ZUvSPmwF3uoFU")
                .getBody();
        System.out.println(claims);
    }

}
