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
@Table(name = "cli_cliente")
public class Cliente {

    @JsonView(View.ClienteCompleto.class)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long id;
    
    @JsonView({View.ClienteResumo.class, View.ClientePetResumo.class})
    @Column(name = "cli_nome", unique=true, length= 20, nullable= false)
    private String nome;

    @JsonView({View.ClienteResumo.class, View.ClientePetResumo.class})
    @Column(name = "cli_pet", length= 20, nullable= false)
    private String pet;

    @Column(name = "cli_pedido", length= 20, nullable= true)
    private String pedido;

    @Column(name = "cli_pedidocount", nullable= true)
    private Long pedidocount;

    @Column(name = "cli_gasto", nullable= true)
    private Long gasto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Long getPedidocount() {
        return pedidocount;
    }

    public void setPedidocount(Long pedidocount) {
        this.pedidocount = pedidocount;
    }

    public Long getGasto() {
        return gasto;
    }

    public void setGasto(Long gasto) {
        this.gasto = gasto;
    }

    
}
