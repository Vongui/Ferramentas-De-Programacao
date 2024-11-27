package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.LoginController;
import br.edu.ifsp.pep.entity.NivelAcesso;
import br.edu.ifsp.pep.util.Mensagem;
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
        ctx.getExternalContext().getFlash().setKeepMessages(true);
        if (pagina.equals("/pessoa/ListPessoa.xhtml")) {
            if (loginController.getPessoaAutenticada() == null) {
                
                //Mantem uma mensagem mesmo utilizando redirect
                ctx.getExternalContext().getFlash().setKeepMessages(true);
                
                Mensagem.erro("Não possui nivel Acesso para acessar!!!!");
                //Redirecionar para página de erro ou login
                redirecionar(ctx, "/index.xhtml");
            }
        }
        if (pagina.equals("/financeiro/list.xhtml")) {
            if (loginController.getPessoaAutenticada() == null || 
                    (loginController.getPessoaAutenticada().getNivelAcesso() != nivelAcesso.Financeiro && 
                    loginController.getPessoaAutenticada().getNivelAcesso() != nivelAcesso.Administrador) ) {
                ctx.getExternalContext().getFlash().setKeepMessages(true);
                Mensagem.erro("Não possui nivel Acesso para acessar!!!!");
                redirecionar(ctx, "/index.xhtml");
            }
        }
        if (pagina.equals("/financeiro/create.xhtml")) {
            if (loginController.getPessoaAutenticada() == null || 
                    loginController.getPessoaAutenticada().getNivelAcesso() != nivelAcesso.Administrador) {
                ctx.getExternalContext().getFlash().setKeepMessages(true);
                Mensagem.erro("Não possui nivel Acesso para acessar!!!!");
                redirecionar(ctx, "/index.xhtml");
            }
        }
        if (pagina.equals("/comum.xhtml")) {
            if (loginController.getPessoaAutenticada() == null || (loginController.getPessoaAutenticada().getNivelAcesso() != nivelAcesso.Comum && loginController.getPessoaAutenticada().getNivelAcesso() != NivelAcesso.Administrador) ) {
                ctx.getExternalContext().getFlash().setKeepMessages(true);
                Mensagem.erro("Não possui nivel Acesso para acessar!!!!");
                redirecionar(ctx, "/index.xhtml");
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
