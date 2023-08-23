package opensgs.web.actions.usuarios;

import com.opensymphony.xwork2.inject.Container;
import java.util.List;
import opensgs.web.actions.BaseAction;
import opensgs.web.webservices.DtPerfilAplicacion;
import opensgs.web.webservices.DtPermiso;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

@Action(
        value = "MenuAction",
        results = {
            @Result(location = "menu.jsp"),
            @Result(name = "success", location = "menu.jsp"
            )
        }
)
public class MenuAction extends BaseAction {

    private boolean menuMisSolicitudes;
    private boolean menuMisDatos;
    private boolean menuMiClave;
    private boolean menuReporte;
    private boolean menuPlantillaCorreo;
    private boolean menuNotificacion;
    private boolean menuAplicacion;
    private boolean menuPlantillaAplicacion;
    private boolean menuAnuncio;
    private boolean menuArchivo;
    private boolean menuPreguntaFrecuente;
    private boolean menuUsuario;
    private boolean menuPerfil;
    private boolean menuActividad;
    private boolean menuSistema;
    private boolean menuServidorAplicaciones;
    private boolean menuServidorAutenticacion;
    private boolean menuServidorCorreo;

    public boolean isMenuMisSolicitudes() {
        return menuMisSolicitudes;
    }

    public void setMenuMisSolicitudes(boolean menuMisSolicitudes) {
        this.menuMisSolicitudes = menuMisSolicitudes;
    }

    public boolean isMenuMisDatos() {
        return menuMisDatos;
    }

    public void setMenuMisDatos(boolean menuMisDatos) {
        this.menuMisDatos = menuMisDatos;
    }

    public boolean isMenuMiClave() {
        return menuMiClave;
    }

    public void setMenuMiClave(boolean menuMiClave) {
        this.menuMiClave = menuMiClave;
    }

    public boolean isMenuReporte() {
        return menuReporte;
    }

    public void setMenuReporte(boolean menuReporte) {
        this.menuReporte = menuReporte;
    }

    public boolean isMenuPlantillaCorreo() {
        return menuPlantillaCorreo;
    }

    public void setMenuPlantillaCorreo(boolean menuPlantillaCorreo) {
        this.menuPlantillaCorreo = menuPlantillaCorreo;
    }

    public boolean isMenuNotificacion() {
        return menuNotificacion;
    }

    public void setMenuNotificacion(boolean menuNotificacion) {
        this.menuNotificacion = menuNotificacion;
    }

    public boolean isMenuAplicacion() {
        return menuAplicacion;
    }

    public void setMenuAplicacion(boolean menuAplicacion) {
        this.menuAplicacion = menuAplicacion;
    }

    public boolean isMenuPlantillaAplicacion() {
        return menuPlantillaAplicacion;
    }

    public void setMenuPlantillaAplicacion(boolean menuPlantillaAplicacion) {
        this.menuPlantillaAplicacion = menuPlantillaAplicacion;
    }

    public boolean isMenuAnuncio() {
        return menuAnuncio;
    }

    public void setMenuAnuncio(boolean menuAnuncio) {
        this.menuAnuncio = menuAnuncio;
    }

    public boolean isMenuArchivo() {
        return menuArchivo;
    }

    public void setMenuArchivo(boolean menuArchivo) {
        this.menuArchivo = menuArchivo;
    }

    public boolean isMenuPreguntaFrecuente() {
        return menuPreguntaFrecuente;
    }

    public void setMenuPreguntaFrecuente(boolean menuPreguntaFrecuente) {
        this.menuPreguntaFrecuente = menuPreguntaFrecuente;
    }

    public boolean isMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(boolean menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    public boolean isMenuPerfil() {
        return menuPerfil;
    }

    public void setMenuPerfil(boolean menuPerfil) {
        this.menuPerfil = menuPerfil;
    }

    public boolean isMenuActividad() {
        return menuActividad;
    }

    public void setMenuActividad(boolean menuActividad) {
        this.menuActividad = menuActividad;
    }

    public boolean isMenuSistema() {
        return menuSistema;
    }

    public void setMenuSistema(boolean menuSistema) {
        this.menuSistema = menuSistema;
    }

    public boolean isMenuServidorAplicaciones() {
        return menuServidorAplicaciones;
    }

    public void setMenuServidorAplicaciones(boolean menuServidorAplicaciones) {
        this.menuServidorAplicaciones = menuServidorAplicaciones;
    }

    public boolean isMenuServidorAutenticacion() {
        return menuServidorAutenticacion;
    }

    public void setMenuServidorAutenticacion(boolean menuServidorAutenticacion) {
        this.menuServidorAutenticacion = menuServidorAutenticacion;
    }

    public boolean isMenuServidorCorreo() {
        return menuServidorCorreo;
    }

    public void setMenuServidorCorreo(boolean menuServidorCorreo) {
        this.menuServidorCorreo = menuServidorCorreo;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String execute() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.inicializarMenu();
        this.activarMenu();
        return SUCCESS;
    }

    private void inicializarMenu() {
        this.setMenuMisSolicitudes(false);
        this.setMenuMisDatos(false);
        this.setMenuMiClave(false);
        this.setMenuReporte(false);
        this.setMenuPlantillaCorreo(false);
        this.setMenuNotificacion(false);
        this.setMenuAplicacion(false);
        this.setMenuPlantillaAplicacion(false);
        this.setMenuAnuncio(false);
        this.setMenuPreguntaFrecuente(false);
        this.setMenuUsuario(false);
        this.setMenuPerfil(false);
        this.setMenuActividad(false);
        this.setMenuSistema(false);
        this.setMenuServidorAplicaciones(false);
        this.setMenuServidorAutenticacion(false);
        this.setMenuServidorCorreo(false);
    }

    private void activarMenu() {
        List<DtPermiso> listaPermisosGlobales = this.getDtSesion().getDtUsuario().getDtPerfil().getDtPermisos();
        for (DtPermiso dtPermiso : listaPermisosGlobales) {
            this.activarTarjetaMenu(dtPermiso);
        }
        
         List<DtPerfilAplicacion> listaPermisosAplicaciones = this.getDtSesion().getDtUsuario().getDtPerfilesAplicacion();
         if (!listaPermisosAplicaciones.isEmpty()) {
             this.setMenuAplicacion(true);
         }
    }

    private void activarTarjetaMenu(DtPermiso dtPermiso) {
        if (dtPermiso.getElemento().equalsIgnoreCase("Actividad")) {
            this.setMenuActividad(evaluarOperacion(dtPermiso.getOperacion()));
        } 
        else if (dtPermiso.getElemento().equalsIgnoreCase("Anuncio")) {
            this.setMenuAnuncio(evaluarOperacion(dtPermiso.getOperacion()));
        } 
        else if (dtPermiso.getElemento().equalsIgnoreCase("Aplicacion")) {
            this.setMenuAplicacion(evaluarOperacion(dtPermiso.getOperacion()));
        } 
        else if (dtPermiso.getElemento().equalsIgnoreCase("Archivo")) {
            this.setMenuArchivo(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("Notificacion")) {
            this.setMenuNotificacion(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("Perfil")) {
            this.setMenuPerfil(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("PlantillaAplicacion")) {
            this.setMenuPlantillaAplicacion(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("PlantillaCorreo")) {
            this.setMenuPlantillaCorreo(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("PreguntaFrecuente")) {
            this.setMenuPreguntaFrecuente(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("Reporte")) {
            this.setMenuReporte(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("ServidorAplicaciones")) {
            this.setMenuServidorAplicaciones(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("ServidorAutenticacion")) {
            this.setMenuServidorAutenticacion(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("ServidorCorreo")) {
            this.setMenuServidorCorreo(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("Sistema")) {
            this.setMenuSistema(evaluarOperacion(dtPermiso.getOperacion()));
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("Solicitud")) {
            if(dtPermiso.getOperacion().equalsIgnoreCase("VerMisSolicitudes")){
                this.setMenuMisSolicitudes(true);
            }
            else if(dtPermiso.getOperacion().equalsIgnoreCase("Todas")){
                this.setMenuMisSolicitudes(true);
            }
        }
        else if (dtPermiso.getElemento().equalsIgnoreCase("Usuario")) {
            this.setMenuUsuario(evaluarOperacion(dtPermiso.getOperacion()));
            if(dtPermiso.getOperacion().equalsIgnoreCase("ModificarMisDatos")){
                this.setMenuMisDatos(true);
            }
            else if(dtPermiso.getOperacion().equalsIgnoreCase("ModificarMiContraseña")){
                this.setMenuMiClave(true);
            }
            else if(dtPermiso.getOperacion().equalsIgnoreCase("Todas")){
                this.setMenuMisDatos(true);
                this.setMenuMiClave(true);
            }
        }
    }

    private boolean evaluarOperacion(String operacion) {
        switch (operacion) {
            case "Crear":
                return true;
            case "Modificar":
                return true;
            case "Ver":
                return true;
            case "Borrar":
                return true;
            case "Todas":
                return true;
            default:
                return false;
        }
    }
    
}
