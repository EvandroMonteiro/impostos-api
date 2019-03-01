/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository.pessoa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import st.gov.financas.impostosApi.model.Pessoa;
import st.gov.financas.impostosApi.model.Pessoa_;
import st.gov.financas.impostosApi.repository.filter.PessoaFilter;

/**
 *
 * @author Impostos
 */
public class PessoaRepositoryImpl implements PessoaRepositoryQuery{
    
     @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);

        //Criar as Restrições
        Predicate[] predicates = CriarRestricoes(pessoaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Pessoa> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(pessoaFilter));
    }

//    @Override
//    public Page<ResumoPessoa> resumir(PessoaFilter lancamentoFilter, Pageable pageable) {
//        CriteriaBuilder builder = manager.getCriteriaBuilder();
//        CriteriaQuery<ResumoPessoa> criteriaQuery = builder.createQuery(ResumoPessoa.class);
//        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
//
//        // fazer a selecção do resumo de lançamento
//        criteriaQuery.select(builder.construct(ResumoPessoa.class,
//                root.get(Pessoa_.codigo), root.get(Pessoa_.descricao),
//                root.get(Pessoa_.dataVencimento), root.get(Pessoa_.dataPagamento),
//                root.get(Pessoa_.valor), root.get(Pessoa_.tipo),
//                root.get(Pessoa_.categoria).get(Categoria_.nome),
//                root.get(Pessoa_.pessoa).get(Pessoa_.nome)));
//
//        //Criar as Restrições
//        Predicate[] predicates = CriarRestricoes(lancamentoFilter, builder, root);
//        criteriaQuery.where(predicates);
//
//        TypedQuery<ResumoPessoa> query = manager.createQuery(criteriaQuery);
//        adicionarRestricoesDePaginacao(query, pageable);
//        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
//    }
//
    private Predicate[] CriarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(pessoaFilter.getNome())) {
            predicates.add(builder.like(builder.lower(root.get(Pessoa_.nome)), "%" + pessoaFilter.getNome().toLowerCase() + "%"));
        }
        if (pessoaFilter.getAtivo()!= null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(Pessoa_.ativo), pessoaFilter.getAtivo()));

        }
        
        return predicates.toArray(new Predicate[predicates.size()]);

    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(PessoaFilter pessoaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);

        Predicate[] predicates = CriarRestricoes(pessoaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
    
    
}
