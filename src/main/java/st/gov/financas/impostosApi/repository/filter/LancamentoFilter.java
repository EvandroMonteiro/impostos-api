/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author barro
 */
public class LancamentoFilter {

    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoAte;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimentoDe() {
        return dataVencimentoDe;
    }

    public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }

    public LocalDate getDataVencimentoAte() {
        return dataVencimentoAte;
    }

    public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
        this.dataVencimentoAte = dataVencimentoAte;
    }

}
