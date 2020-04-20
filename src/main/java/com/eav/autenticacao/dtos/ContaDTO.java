/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.autenticacao.dtos;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ContaDTO {
   
   @NotEmpty(message = "Usuário não pode ser vazio. Verifique!")
   private String  username;
   
   @NotEmpty(message = "Senha não pode ser vazia. Verifique!")
   private String password;
}
