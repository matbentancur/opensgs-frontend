package opensgs.web.actions;

import java.util.List;
import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtArchivo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "IndexAction",
        results = {
            @Result(location = "inicio.jsp"),
            @Result(name = "inicio", location = "inicio.jsp"),
            @Result(name = "contacto", location = "contacto.jsp"),
            @Result(name = "cerradas", location = "cerradas.jsp"),
            @Result(name = "proximas", location = "proximas.jsp")
        }
)
@AllowedMethods({
    "contacto",
    "cerradas",
    "proximas"
})
public class IndexAction extends ComunicationAction {

    private String tableTitle;
    private List<DtAplicacion> listaDtAplicaciones;
    private List<DtArchivo> listaDtArchivos;

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public List<DtAplicacion> getListaDtAplicaciones() {
        return listaDtAplicaciones;
    }

    public void setListaDtAplicaciones(List<DtAplicacion> listaDtAplicaciones) {
        this.listaDtAplicaciones = listaDtAplicaciones;
    }

    public List<DtArchivo> getListaDtArchivos() {
        return listaDtArchivos;
    }

    public void setListaDtArchivos(List<DtArchivo> listaDtArchivos) {
        this.listaDtArchivos = listaDtArchivos;
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.setTableTitle(this.getText("OpenSGSBean.text.opened"));
        this.listarAplicacionesAbiertas();
        this.cargarDatosSistema();
        return "inicio";
    }

    @SkipValidation
    public String contacto() throws Exception {
        return "contacto";
    }

    @SkipValidation
    public String cerradas() throws Exception {
        this.setTableTitle(this.getText("OpenSGSBean.text.closed"));
        this.listarAplicacionesCerradas();
        this.cargarDatosSistema();
        return "cerradas";
    }

    @SkipValidation
    public String proximas() throws Exception {
        this.setTableTitle(this.getText("OpenSGSBean.text.coming"));
        this.listarAplicacionesProximas();
        this.cargarDatosSistema();
        return "proximas";
    }
    
    private void cargarDatosSistema() throws Exception{
        this.listarAnunciosSistema();
        this.listarArchivosSistema();
    }

    private void listarAplicacionesAbiertas() throws Exception {
        List<DtAplicacion> lista = null;
        lista = this.getOpensgsWsPort().listarAplicacionesAbiertas();
        this.setListaDtAplicaciones(lista);
    }

    private void listarAplicacionesCerradas() throws Exception {
        List<DtAplicacion> lista = null;
        lista = this.getOpensgsWsPort().listarAplicacionesCerradas();
        this.setListaDtAplicaciones(lista);
    }

    private void listarAplicacionesProximas() throws Exception {
        List<DtAplicacion> lista = null;
        lista = this.getOpensgsWsPort().listarAplicacionesProximas();
        this.setListaDtAplicaciones(lista);
    }

    private void listarArchivosSistema() throws Exception {
        List<DtArchivo> lista = null;
        lista = this.getOpensgsWsPort().listarArchivosSistemaActivos();
        this.setListaDtArchivos(lista);
    }

}
