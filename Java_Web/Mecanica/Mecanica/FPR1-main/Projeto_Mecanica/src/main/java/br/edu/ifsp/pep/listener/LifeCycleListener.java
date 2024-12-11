package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.LoginController;
import br.edu.ifsp.pep.entity.NivelAcesso;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;

/**
 *
 * @author aluno
 */
public class LifeCycleListener implements PhaseListener {

    @Inject
    private LoginController loginController;
    private NivelAcesso nivelAcesso;
    
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("Após a fase " + event.getPhaseId());
        
        FacesContext ctx = event.getFacesContext();
        String pagina = ctx.getViewRoot().getViewId();
        System.out.println("Pagina " + pagina);
        
        if (pagina.equals("/pessoa/ListPessoa.xhtml")) {
            if (loginController.getPessoaAutenticada() == null) {
                //Redirecionar para página de erro ou login
                redirecionar(ctx, "/erro.xhtml");
            }
        }
        if (pagina.equals("/financeiro/list.xhtml")) {
            if (loginController.getPessoaAutenticada().getNivelAcesso() != nivelAcesso.Financeiro) {
                
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("Antes da fase " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
    private void redirecionar(FacesContext ctx, String pagina){
        try {
            String projeto = ctx.getExternalContext().getRequestContextPath();
            
            //encaminhar/redirecionar
            ctx.getExternalContext().redirect(projeto + pagina);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
