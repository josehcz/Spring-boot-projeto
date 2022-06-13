package br.gov.sp.fatec.springbootapp.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springbootapp.entity.Cliente;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value= "/pedido")
@CrossOrigin
public class PedidoControler {

    @Autowired
    private SegurancaService segurancaService;
    
    @PostMapping
    public ResponseEntity<Cliente> cadastrarNovoUsuario(@RequestBody Cliente cliente,
            UriComponentsBuilder uriComponentsBuilder){
                cliente = segurancaService.geraPedido(cliente.getNome() ,cliente.getPedido(), cliente.getGasto());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                  "/pedido/" + cliente.getId()).build().toUri());
        return new ResponseEntity<Cliente>(cliente, responseHeaders, HttpStatus.CREATED);
    }
}
