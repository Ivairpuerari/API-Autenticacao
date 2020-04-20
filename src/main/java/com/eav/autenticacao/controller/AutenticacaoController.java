/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.autenticacao.controller;


import com.eav.autenticacao.entities.Response;
import com.eav.autenticacao.business.AutenticacaoBusiness;
import com.eav.autenticacao.dtos.ContaDTO;
import com.eav.autenticacao.dtos.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api-autenticacao")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AutenticacaoController {
    
    @Autowired
    private AutenticacaoBusiness autenticacaoBusiness;
    
    
    @PostMapping
    public ResponseEntity<Response<TokenDTO>> autenticaConta(@RequestBody ContaDTO contaDTO){
       return new ResponseEntity<>(autenticacaoBusiness.autenticaConta(contaDTO), HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/autenticado", method = RequestMethod.POST)
    public ResponseEntity<Response<TokenDTO>> autenticaJWT(@RequestBody String token ){
       return new ResponseEntity<>(autenticacaoBusiness.autenticaJWT(token), HttpStatus.OK);
    } 
    
}
