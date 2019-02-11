/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;
import st.gov.financas.impostosApi.model.Lancamento;
import st.gov.financas.impostosApi.repository.filter.LancamentoFilter;

/**
 *
 * @author barro
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        //Criar as Restrições
        Predicate[] predicates = CriarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);

        return null;
    }

    private Predicate[] CriarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
            predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)), "%"+lancamentoFilter.getDescricao().toLowerCase()+"%"));
        }
        if (lancamentoFilter.getDataVencimentoDe() != null) {
//            predicates.add(e);

        }
        if (lancamentoFilter.getDataVencimentoAte() != null) {
//            predicates.add(e);

        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }

}
