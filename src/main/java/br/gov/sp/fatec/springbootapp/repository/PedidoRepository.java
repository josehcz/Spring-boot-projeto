package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
    public Pedido findByid(Long id);

    public List<Pedido> findByValorGreaterThan(Long valor);

    public List<Pedido> findByValorLessThan(Long valor);

}
