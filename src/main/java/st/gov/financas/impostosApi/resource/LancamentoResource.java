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
import st.gov.financas.impostosApi.model.Lancamento;
import st.gov.financas.impostosApi.repository.LancamentoRepository;
import st.gov.financas.impostosApi.service.LancamentoService;

/**
 *
 * @author Impostos
 */
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<Lancamento> listar() {

        return lancamentoRepository.findAll();

    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        Lancamento LancamentoSalvo = lancamentoRepository.findOne(codigo);

        return LancamentoSalvo != null ? ResponseEntity.ok(LancamentoSalvo) : ResponseEntity.notFound().build();

    }

    //HttpStatus.NO_CONTENT codigo 204 dizendo que teve sucesso mas eu não tenho nada para retornar
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarLancamento(@PathVariable Long codigo) {
        lancamentoRepository.delete(codigo);

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Lancamento> actualizarLancamento(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
        Lancamento lancamentoSalvo = lancamentoService.actualizarLancamento(codigo, lancamento);
        return ResponseEntity.ok(lancamentoSalvo);

    }

//    @PutMapping("/{codigo}/ativo")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void actualizarPropriedadeAtivo(@PathVariable Long codigo,  @RequestBody Boolean ativo) {
//        lancamentoService.actualizarPropriedadeAtivo(codigo,ativo);
//    }
}
