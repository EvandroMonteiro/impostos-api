/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.gov.financas.impostosApi.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import st.gov.financas.impostosApi.repository.filter.PessoaFilter;

/**
 *
 * @author barro
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}
