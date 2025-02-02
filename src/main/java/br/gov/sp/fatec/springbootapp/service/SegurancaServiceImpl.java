package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Cliente;
import br.gov.sp.fatec.springbootapp.entity.Compra;
import br.gov.sp.fatec.springbootapp.entity.Produto;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.exception.RegistroNaoEncontradoExcption;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.ClienteRepository;
import br.gov.sp.fatec.springbootapp.repository.CompraRepository;
import br.gov.sp.fatec.springbootapp.repository.ProdutoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService{

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private CompraRepository compraRepo;

    @Autowired
    private PasswordEncoder passEncoder;

    @Transactional
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if (aut == null){
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(passEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Transactional
    public Cliente criarCliente(String nome, String pet) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setPet(pet);
        cliente.setPedidocount((long) 0);
        clienteRepo.save(cliente);
        return cliente;
    }

    @Transactional
    public Cliente geraPedido(String nome, String pedido, Long gasto) {
        Cliente cliente = clienteRepo.findByNome(nome);
        System.out.println(cliente);
        if(cliente != null){
            cliente.setPedido(pedido);
            cliente.setGasto(gasto);
            cliente.setPedidocount(cliente.getPedidocount() + 1);
            clienteRepo.save(cliente);
            return cliente;
        }
        throw new RegistroNaoEncontradoExcption("Cliente não encontrado");
    }

    @Transactional
    public Produto cadastraProduto(String nome, Long valor) {
        
        Produto produto = new Produto();
        produto.setProduto(nome);
        produto.setValor(valor);
        produtoRepo.save(produto);
        return produto;
    }

    @Transactional
    public Compra realizarCompra(String cliente, String produto,Long valor) {
        
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setItem(produto);
        compra.setValor(valor);
        compraRepo.save(compra);
        return compra;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Cliente> buscarTodosClientes(){
        return clienteRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Produto> buscarTodosProdutos(){
        return produtoRepo.findAll();
    }
    
    @Override
    @PreAuthorize("isAuthenticated()")
        public Usuario buscarUsuarioId(Long id){
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isPresent()){
            return usuarioOp.get();
        }
        throw new RegistroNaoEncontradoExcption("Usuario não encontrado");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
        public Cliente buscarClienteId(Long id){
        Optional<Cliente> clienteOp = clienteRepo.findById(id);
        if(clienteOp.isPresent()){
            return clienteOp.get();
        }
        throw new RegistroNaoEncontradoExcption("Cliente não encontrado");
    }

    @Override
    public Usuario buscarUsuarioPorNome(String nome){
        Usuario usuario = usuarioRepo.BuscaPorNome(nome);
        if(usuario != null){
            return usuario;
        }
        throw new RegistroNaoEncontradoExcption("Usuario não encontrado");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarAutorizacaoPorNome(String nome){
        Autorizacao autorizacao = autRepo.findByNome(nome);
        if(autorizacao != null){
            return autorizacao;
        }
        throw new RegistroNaoEncontradoExcption("Autorização não encontrada");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario" + usuario + "não encontrado");
        }
        return User.builder().username(username).password(usuario.getSenha())
            .authorities(usuario.getAutorizacoes().stream()
                .map(Autorizacao::getNome).collect(Collectors.toList())
                .toArray(new String[usuario.getAutorizacoes().size()]))
            .build();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Cliente buscarClientePorNome(String nome){
        Cliente cliente = clienteRepo.findByNome(nome);
        if(cliente != null){
            return cliente;
        }
        throw new RegistroNaoEncontradoExcption("Cliente não encontrado");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Cliente buscarClientePorPet(String pet) {
        Cliente cliente = clienteRepo.findByPet(pet);
        if(cliente != null){
            return cliente;
        }
        throw new RegistroNaoEncontradoExcption("Cliente não encontrado");
    }

    
}
