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


import br.gov.sp.fatec.springbootapp.entity.Produto;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value= "/venda")
@CrossOrigin
public class VendaControler {

    @Autowired
    private SegurancaService segurancaService;

    @PostMapping
    public ResponseEntity<Produto> cadastrarNovoUsuario(@RequestBody Produto produto,
            UriComponentsBuilder uriComponentsBuilder){
                produto = segurancaService.cadastraProduto(produto.getProduto(), produto.getValor());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                  "/venda/" + produto.getId()).build().toUri());
        return new ResponseEntity<Produto>(produto, responseHeaders, HttpStatus.CREATED);
    }
}
