/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st.gov.financas.impostosApi.event;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author barro
 */
public class RecursoCriadoEvent extends ApplicationEvent{

    private static final long serialVersionUID =1L;
    
    private HttpServletResponse response;
    private Long codigo;
    
//    Toda vez que eu quizer add o header location eu disparo o evento.
    public RecursoCriadoEvent(Object source,HttpServletResponse response,Long Codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getCodigo() {
        return codigo;
    }
    
    
    
}
