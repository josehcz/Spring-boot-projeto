package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controler.View;


@Entity
@Table(name = "prod_produtos")
public class Produto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;

    @JsonView(View.ProdutoResumo.class)
    @Column(name = "prod_nome", unique=true, length= 20, nullable= false)
    private String produto;

    @JsonView(View.ProdutoResumo.class)
    @Column(name = "prod_valor", nullable= false)
    private Long valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

}
