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

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value= "/funcionario")
@CrossOrigin
public class FuncionarioControler {

    @Autowired
    private SegurancaService segurancaService;
    
    @JsonView(View.UsuarioResumo.class)
    @PostMapping
    public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody Usuario usuario,
            UriComponentsBuilder uriComponentsBuilder){
        usuario = segurancaService.criarUsuario(usuario.getNome(), usuario.getSenha(), "ROLE_FUNCIONARIO");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                  "/funcionario/" + usuario.getId()).build().toUri());
        return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
    }
}
