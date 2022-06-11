package br.gov.sp.fatec.springbootapp.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.entity.Cliente;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value= "/cliente")
@CrossOrigin
public class ClienteControler {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.ClienteResumo.class)
    @GetMapping()
    public List<Cliente> buscarTodos(){
        return segurancaService.buscarTodosClientes();
    }

    @JsonView(View.ClientePetResumo.class)
    @GetMapping(value = "/{id}")
    public Cliente buscarPorId(@PathVariable("id") Long id){
        return segurancaService.buscarClienteId(id);
    }

    @JsonView(View.ClienteResumo.class)
    @GetMapping(value = "/nome")
    public Cliente buscarClientePorNome(@RequestParam(value="nome")String nome){
        return segurancaService.buscarClientePorNome(nome);
    }
    
    @JsonView(View.ClienteResumo.class)
    @PostMapping
    public ResponseEntity<Cliente> cadastrarNovoCliente(@RequestBody Cliente cliente,
            UriComponentsBuilder uriComponentsBuilder){
        cliente = segurancaService.criarCliente(cliente.getNome(), cliente.getPet());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                  "/cliente/" + cliente.getId()).build().toUri());
        return new ResponseEntity<Cliente>(cliente, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.ClienteResumo.class)
    @PostMapping(value = "/nome"+"/pedido"+"/valor")
    public Cliente geraNovoPedido(@RequestParam(value="nome")String nome,@RequestParam(value="pedido")String pedido,@RequestParam(value="valor")Long valor){
        return segurancaService.geraPedido(nome,pedido,valor);
    }
}
