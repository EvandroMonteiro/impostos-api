/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.resource;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import st.gov.financas.impostosApi.event.RecursoCriadoEvent;
import st.gov.financas.impostosApi.exceptionhandler.Exceptionhandler;
import st.gov.financas.impostosApi.model.Lancamento;
import st.gov.financas.impostosApi.repository.LancamentoRepository;
import st.gov.financas.impostosApi.repository.filter.LancamentoFilter;
import st.gov.financas.impostosApi.repository.projection.ResumoLancamento;
import st.gov.financas.impostosApi.service.LancamentoService;
import st.gov.financas.impostosApi.service.exception.PessoaInexistenteOuInativaException;

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

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {

        return lancamentoRepository.filtrar(lancamentoFilter, pageable);

    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {

        return lancamentoRepository.resumir(lancamentoFilter, pageable);

    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);

    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        Lancamento LancamentoSalvo = lancamentoRepository.findOne(codigo);

        return LancamentoSalvo != null ? ResponseEntity.ok(LancamentoSalvo) : ResponseEntity.notFound().build();

    }

    //HttpStatus.NO_CONTENT codigo 204 dizendo que teve sucesso mas eu não tenho nada para retornar
    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarLancamento(@PathVariable Long codigo) {
        lancamentoRepository.delete(codigo);

    }

//    @PutMapping("/{codigo}")
//    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
//    public ResponseEntity<Lancamento> actualizarLancamento(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
//        Lancamento lancamentoSalvo = lancamentoService.actualizarLancamento(codigo, lancamento);
//        return ResponseEntity.ok(lancamentoSalvo);
//
//    }
    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
    public ResponseEntity<Lancamento> actualizarLancamento(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
        try {
            Lancamento lancamentoSalvo = lancamentoService.actualizarLancamento(codigo, lancamento);
            return ResponseEntity.ok(lancamentoSalvo);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Exceptionhandler.Erro> erros = Arrays.asList(new Exceptionhandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);

//                return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }
}
