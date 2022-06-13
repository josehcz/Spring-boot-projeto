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

import br.gov.sp.fatec.springbootapp.entity.Compra;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value= "/compra")
@CrossOrigin
public class CompraControler {

    @Autowired
    private SegurancaService segurancaService;
    
    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody Compra compra,
            UriComponentsBuilder uriComponentsBuilder){
                compra = segurancaService.realizarCompra(compra.getCliente(), compra.getItem(), compra.getValor());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                  "/compra/" + compra.getId()).build().toUri());
        return new ResponseEntity<Compra>(compra, responseHeaders, HttpStatus.CREATED);
    }
}
