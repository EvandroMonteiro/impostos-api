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
import st.gov.financas.impostosApi.repository.LancamentoRepository;

/**
 *
 * @author Impostos
 */
@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento actualizarLancamento(Long codigo, Lancamento lancamento) {
        Lancamento LancamentoSalvo = buscarLancamentoPeloCodigo(codigo);

        BeanUtils.copyProperties(lancamento, LancamentoSalvo, "codigo");
        return lancamentoRepository.save(LancamentoSalvo);
    }

    private Lancamento buscarLancamentoPeloCodigo(Long codigo) throws EmptyResultDataAccessException {
        Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
        if (lancamentoSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return lancamentoSalvo;
    }

}
