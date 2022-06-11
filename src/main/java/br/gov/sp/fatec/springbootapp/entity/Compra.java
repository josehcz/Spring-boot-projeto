package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "reg_compras")
public class Compra {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "regc_id")
    private Long id;

    @Column(name = "regc_compra", length= 100, nullable= false)
    private String itens;

    @Column(name = "regc_valor", nullable= false)
    private Long valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItens() {
        return itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    
    
}
