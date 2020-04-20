/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.autenticacao.business;

import com.eav.autenticacao.dtos.ContaDTO;
import com.eav.autenticacao.dtos.TokenDTO;
import com.eav.autenticacao.entities.Conta;
import com.eav.autenticacao.entities.Response;
import com.eav.autenticacao.repositories.ContaRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ivaai
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AutenticacaoBusiness {

    @Autowired
    private ContaRepository contaRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Date gerarDataExpiracao() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private TokenDTO GeraToken(Conta conta) {
        String token;
        
        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        
        token = Jwts.builder()
                .setSubject(conta.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(gerarDataExpiracao())
                .signWith(secretKey)
                .compact();
                                                  
        return new TokenDTO.TokenBuilder(token).build();
    }

    public Response<TokenDTO> autenticaConta(ContaDTO contaDTO) {
        Optional<Conta> conta = contaRepository.findByUsername(contaDTO.getUsername());

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        Response<TokenDTO> response = new Response<>();

        if (conta.isPresent() && bCrypt.matches(contaDTO.getPassword(), conta.get().getPassword())) {
            response.setData(GeraToken(conta.get()));
            response.setAuthentic(Boolean.TRUE);
        } else {
            response.getErrors().add("Usuário ou Senha errado.");
        }

        return response;
    }

    private Claims getClaimsFromToken(String token) {
        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        
        Claims claims;
        try {
            claims = Jwts.parserBuilder().setSigningKey(secretKey)
                     .build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private boolean tokenExpirado(String token) {
        Date dataExpiracao = this.getExpirationDateFromToken(token);
        if (dataExpiracao == null) {
            return false;
        }
        return dataExpiracao.before(new Date());
    }

    public Response<TokenDTO> autenticaJWT(String token) {
        Response<TokenDTO> response = new Response<>();
        
        if(!tokenExpirado(token)){
            response.setAuthentic(Boolean.TRUE);
        }
        
        return response;
    }

}
