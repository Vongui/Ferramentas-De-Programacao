package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.LoginController;
import br.edu.ifsp.pep.util.Mensagem;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;
import org.primefaces.PrimeFaces;

/**
 *
 * @author aluno
 */
public class LifeCycleListener implements PhaseListener {

    @Inject
    private LoginController loginController;

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext ctx = event.getFacesContext();
        String pagina = ctx.getViewRoot().getViewId();

        if (pagina.equals("/pessoa/ListPessoa.xhtml")) {
            if (loginController.getPessoaAutenticada() == null) {
//                Mensagem.atencao("TEste");
//                
//                ctx.getExternalContext().getFlash().setKeepMessages(true);

                //Redirecionar para página de erro ou login.
                redirecionar(ctx, "/erro.xhtml");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private void redirecionar(FacesContext ctx, String pagina) {
        try {
            //Nome do projeto
            String projeto = ctx.getExternalContext()
                    .getRequestContextPath();

            //Encaminhar/Redirecionar
            ctx.getExternalContext().redirect(projeto + pagina);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
