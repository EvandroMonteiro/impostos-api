/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository.lancamento;

import java.util.List;
import st.gov.financas.impostosApi.model.Lancamento;
import st.gov.financas.impostosApi.repository.filter.LancamentoFilter;

/**
 *
 * @author barro
 */
public interface LancamentoRepositoryQuery {
    
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
    
}
