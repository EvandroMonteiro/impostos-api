/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import st.gov.financas.impostosApi.model.Usuario;

/**
 *
 * @author Impostos
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

}
