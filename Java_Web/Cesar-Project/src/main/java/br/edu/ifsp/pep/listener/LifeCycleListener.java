package br.edu.ifsp.pep.listener;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;


public class LifeCycleListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("FINALIZANDO FASE: "+ event.getPhaseId());
        
        FacesContext ctx = event.getFacesContext();
        String pagina = ctx.getViewRoot().getViewId();
        System.out.println("Pagina: "+ pagina);
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("INICIANDO FASE: "+ event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
}
