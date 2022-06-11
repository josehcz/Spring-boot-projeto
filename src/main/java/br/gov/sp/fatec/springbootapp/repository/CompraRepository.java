package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

    public Compra findByid(Long id);

    public List<Compra> findByValorGreaterThan(Long valor);

    public List<Compra> findByValorLessThan(Long valor);
}
