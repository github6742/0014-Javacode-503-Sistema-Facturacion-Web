package sys.clasesClasesAuxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import sys.model.Usuario;

public class filtroUrl implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        
        //Capturamos el nombre de la pagina actual
        String currentPage = facesContext.getViewRoot().getViewId();
        
        //Creamos una variable boolean para ver si es la pagina de login la que se capturo
        boolean isPageLogin = currentPage.lastIndexOf("login.xhtml") > -1 ? true : false;
        
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true) ;
        /* recuperamos un object del string que se guardo, para ello se toma de la session al usuario que se
        definio en el loginBean
        */
        
        Object usuario = session.getAttribute("usuario");
        
        if(!isPageLogin && usuario==null){
            NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null, "/login.xhtml");
        }
        
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
