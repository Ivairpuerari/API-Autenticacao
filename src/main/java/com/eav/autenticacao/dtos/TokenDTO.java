/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.autenticacao.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 *
 * @author Ivaai
 */

@Getter
@AllArgsConstructor
public class TokenDTO {
   
    private final String token;
    
    @Getter
    @AllArgsConstructor
    public static class TokenBuilder {
        private final String token;
        
        public TokenDTO build() {            
            return new TokenDTO(this.getToken());
        } 
    }
}
