/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.gov.financas.impostosApi.model.Categoria;
import st.gov.financas.impostosApi.repository.CategoriaRepository;

/**
 *
 * @author barro
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
        
    }
    
}
