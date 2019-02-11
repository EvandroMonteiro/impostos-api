/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.gov.financas.impostosApi.model.Lancamento;
import st.gov.financas.impostosApi.repository.lancamento.LancamentoRepositoryQuery;

/**
 *
 * @author Impostos
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{
    
}
