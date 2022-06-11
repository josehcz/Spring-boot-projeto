package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import br.gov.sp.fatec.springbootapp.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
        
    public List<Cliente> findByNomeContainsIgnoreCase(String nome);

    public Cliente findByNome(String nome);

    public Cliente findByPet(String pet);

}