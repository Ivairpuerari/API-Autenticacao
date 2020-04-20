package com.eav.autenticacao.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivaai
 */

@Getter
@Setter
@NoArgsConstructor
public class Response<T>{
    
    private T data;
    private List<String> errors;
    private Boolean authentic = false;
    
    public List<String> getErrors(){
        if( this.errors == null){
            this.errors = new ArrayList<>();
        }
        return errors;
    }
}
