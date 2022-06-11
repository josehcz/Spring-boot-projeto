package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import br.gov.sp.fatec.springbootapp.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    public Produto findByid(Long id);

    public Produto findByProduto(String produto);

    public List<Produto> findByValorGreaterThan(Long valor);

    public List<Produto> findByValorLessThan(Long valor);
    
}
