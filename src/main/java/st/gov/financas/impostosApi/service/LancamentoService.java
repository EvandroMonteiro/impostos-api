/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import st.gov.financas.impostosApi.model.Lancamento;
import st.gov.financas.impostosApi.model.Pessoa;
import st.gov.financas.impostosApi.repository.LancamentoRepository;
import st.gov.financas.impostosApi.repository.PessoaRepository;
import st.gov.financas.impostosApi.service.exception.PessoaInexistenteOuInativaException;

/**
 *
 * @author Impostos
 */
@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

//    public Lancamento actualizarLancamento(Long codigo, Lancamento lancamento) {
//        Lancamento LancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
//        
//        BeanUtils.copyProperties(lancamento, LancamentoSalvo, "codigo");
//        return lancamentoRepository.save(LancamentoSalvo);
//    }
    public Lancamento actualizarLancamento(Long codigo, Lancamento lancamento) {
        Lancamento LancamentoSalvo = buscarLancamentoExistente(codigo);

        if (!lancamento.getPessoa().equals(LancamentoSalvo.getPessoa())) {
            validarPessoa(lancamento);
        }

        BeanUtils.copyProperties(lancamento, LancamentoSalvo, "codigo");
        return lancamentoRepository.save(LancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getCodigo() != null) {
            pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());

        }
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();

        }
    }

//    private Lancamento buscarLancamentoPeloCodigo(Long codigo) throws EmptyResultDataAccessException {
//        Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
//        if (lancamentoSalvo == null) {
//            throw new EmptyResultDataAccessException(1);
//        }
//        return lancamentoSalvo;
//    }
    private Lancamento buscarLancamentoExistente(Long codigo) {
        Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
        if (lancamentoSalvo == null) {
            throw new IllegalArgumentException();
        }
        return lancamentoSalvo;
    }

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);

    }

}
