/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.autenticacao.repositories;
import com.eav.autenticacao.entities.Conta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ivaai
 */
public interface ContaRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> findByUsername(String username);
}
