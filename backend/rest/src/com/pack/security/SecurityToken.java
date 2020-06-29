package com.pack.security;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

import com.pack.interceptors.AppRequest;
import com.pack.pojo.SpUsuarioPojo;
import com.pack.utils.Application;
import com.pack.utils.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityToken {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final Key SIGNING_KEY = new SecretKeySpec(Application.TOKEN_SECRET_KEY.getBytes(),
            SIGNATURE_ALGORITHM.getJcaName());

    public static final String AUTHORIZATION_PROPERTY = "Authorization";
    public static final String AUTHENTICATION_SCHEME = "Bearer ";
    public static final String USUARIO = "usuario";
    public static final String TOKEN = "token";

    private SecurityToken() {
    }

    /**
     * Cria um novo token
     * 
     * @param appRequest
     *            objeto wrapper da requisição
     * @return Token criado
     */
    public static String createToken(AppRequest appRequest) {
        SpUsuarioPojo usuario = appRequest.getUsuario();
        return Jwts.builder().setIssuer(appRequest.getIp()).setIssuedAt(new Date()).setSubject(usuario.getUsuario())
                .claim(USUARIO, usuario)
                .setExpiration(DateUtils.convert(LocalDateTime.now().plusMinutes(Application.ACCESS_TOKEN_EXPIRATION_TIME)))
                .signWith(SIGNATURE_ALGORITHM, SIGNING_KEY).compact();
    }

    public static Claims verifyToken(String authToken) {
        String token = authToken.replace(SecurityToken.AUTHENTICATION_SCHEME, "");
        return Jwts.parser().setSigningKey(SecurityToken.SIGNING_KEY).parseClaimsJws(token).getBody();
    }

}