/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.resource;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import st.gov.financas.impostosApi.event.RecursoCriadoEvent;
import st.gov.financas.impostosApi.model.Pessoa;
import st.gov.financas.impostosApi.repository.PessoaRepository;
import st.gov.financas.impostosApi.service.PessoaService;

/**
 *
 * @author barro
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
        Pessoa pessoaEncotrada = pessoaRepository.findOne(codigo);

//        if (pessoaEncotrada == null) {
//            return new ResponseEntity(ResponseEntity.ok(), HttpStatus.OK);
//        } else {
        return pessoaEncotrada != null ? ResponseEntity.ok(pessoaEncotrada) : ResponseEntity.notFound().build();
    }

    //HttpStatus.NO_CONTENT codigo 204 dizendo que teve sucesso mas eu não tenho nada para retornar
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarPessoa(@PathVariable Long codigo) {
        pessoaRepository.delete(codigo);

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> actualizarPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.actualizarPessoa(codigo, pessoa);
        return ResponseEntity.ok(pessoaSalva);

    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizarPropriedadeAtivo(@PathVariable Long codigo,  @RequestBody Boolean ativo) {
        pessoaService.actualizarPropriedadeAtivo(codigo,ativo);
    }
}
