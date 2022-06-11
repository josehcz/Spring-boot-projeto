package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Cliente;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface SegurancaService extends UserDetailsService{
    
    //Usuario

    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);

    public Autorizacao buscarAutorizacaoPorNome(String nome);

    //Clientes

    public Cliente criarCliente(String nome, String pet);

    public List<Cliente> buscarTodosClientes();

    public Cliente buscarClienteId(Long id);

    public Cliente buscarClientePorNome(String nome);

    public Cliente buscarClientePorPet(String pet);

    public Cliente geraPedido(String nome, String pedido, Long gasto);

    //pedido
}
