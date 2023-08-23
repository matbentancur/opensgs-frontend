package opensgs.web.actions.solicitudes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.actions.sistema.AplicacionHijoAction;
import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtConstancia;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtPaginaSolicitud;
import opensgs.web.webservices.DtSolicitud;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "MisSolicitudesAction",
        results = {
            @Result(name = "input", location = "solicitud/solicitudForm.jsp"),
            @Result(name = "list", location = "misSolicitudes/misSolicitudesList.jsp"),
            @Result(name = "view", location = "misSolicitudes/misSolicitudesView.jsp"),
            @Result(name = "constancias", location = "misSolicitudes/constancias.jsp"),
            @Result(name = "agreement", location = "misSolicitudes/agreement.jsp"),
            @Result(name = "paginaEntrega", location = "solicitud/paginaEntrega.jsp"),
            @Result(name = "paginaEntregado", location = "solicitud/paginaEntregado.jsp"),
            @Result(
                    name = "paginaSolicitud",
                    type = "redirectAction",
                    params = {
                        "actionName", "PaginaSolicitudAction",
                        "namespace", "/solicitudes",
                        "actionErrors", "${actionErrors}",
                        "actionMessages", "${actionMessages}",
                        "solicitudId", "${solicitudId}"
                    }
            )
        }
)
@AllowedMethods({
    "agreement",
    "paginaEntrega",
    "entregar",
    "constancias"
})
public class MisSolicitudesAction extends AplicacionHijoAction {

    private DtSolicitud dtSolicitud;
    private Long solicitudId;
    private String textoAcuerdo;
    private String textoEntrega;
    private List<DtSolicitud> listaDtSolicitudes;
    private List<DtPaginaSolicitud> listaDtPaginasSolicitud;
    private List<DtConstancia> listaDtConstancias;
    private String textSolicitudNavigation;

    public DtSolicitud getDtSolicitud() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtSolicitud) this.getDtOpenSGSManagedBean();
        } else {
            return dtSolicitud;
        }
    }

    public void setDtSolicitud(DtSolicitud dtSolicitud) {
        this.dtSolicitud = dtSolicitud;
    }

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public String getTextoAcuerdo() {
        return textoAcuerdo;
    }

    public void setTextoAcuerdo(String textoAcuerdo) {
        this.textoAcuerdo = textoAcuerdo;
    }

    public String getTextoEntrega() {
        return textoEntrega;
    }

    public void setTextoEntrega(String textoEntrega) {
        this.textoEntrega = textoEntrega;
    }

    public List<DtSolicitud> getListaDtSolicitudes() {
        return listaDtSolicitudes;
    }

    public void setListaDtSolicitudes(List<DtSolicitud> listaDtSolicitudes) {
        this.listaDtSolicitudes = listaDtSolicitudes;
    }

    public List<DtPaginaSolicitud> getListaDtPaginasSolicitud() {
        return listaDtPaginasSolicitud;
    }

    public void setListaDtPaginasSolicitud(List<DtPaginaSolicitud> listaDtPaginasSolicitud) {
        this.listaDtPaginasSolicitud = listaDtPaginasSolicitud;
    }

    public List<DtConstancia> getListaDtConstancias() {
        return listaDtConstancias;
    }

    public void setListaDtConstancias(List<DtConstancia> listaDtConstancias) {
        this.listaDtConstancias = listaDtConstancias;
    }

    public String getTextSolicitudNavigation() {
        return textSolicitudNavigation;
    }

    public void setTextSolicitudNavigation(String textSolicitudNavigation) {
        this.textSolicitudNavigation = textSolicitudNavigation;
    }

    @Override
    public String execute() throws Exception {
        if (dtSolicitud != null) {
            this.setDtOpenSGSBean(dtSolicitud);
            this.setDtOpenSGSManagedBean(dtSolicitud);
        }
        this.setCommonTextsValues();
        if (this.getComando().equals("crear")) {
            return this.crear();
        }
        if (this.getComando().equals("modificar")) {
            return this.modificar();
        }
        if (this.getComando().equals("agreement")) {
            return this.agreement();
        }
        if (this.getComando().equals("entregar")) {
            return this.entregar();
        }
        return this.parseComando();
    }

    @SkipValidation
    @Override
    public String input() throws Exception {
        this.setCommonTextsValues();
        super.input();
        this.setComando("agreement");
        return INPUT;
    }

    @SkipValidation
    @Override
    public String ver() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("id");

        DtSolicitud dtSolicitud = (DtSolicitud) this.getOpensgsWsPort().obtenerMiSolicitud(this.getId(), this.getDtSesion());
        if (dtSolicitud != null) {
            this.setListaDtPaginasSolicitud(dtSolicitud.getDtPaginasSolicitud());
        }

        return super.ver();
    }

    @SkipValidation
    @Override
    public String form() throws Exception {
        return this.modificar();
    }

    @SkipValidation
    @Override
    public String listar() throws Exception {
        this.setCommonTextsValues();
        this.cargarTextosLista();
        List<DtSolicitud> lista = null;

        lista = (List<DtSolicitud>) this.getOpensgsWsPort().listarMisSolicitudes(this.getDtSesion());

        this.setListaDtSolicitudes(lista);
        return "list";
    }

    @SkipValidation
    @Override
    public String crear() throws Exception {
        DtMensaje dtMensaje = this.getOpensgsWsPort().crearSolicitud(this.getAplicacionId(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            this.setSolicitudId(dtMensaje.getOpenSGSBeanId());
//            this.setComando("crear");
            return "paginaSolicitud";
        }
        return this.listar();
    }

    @SkipValidation
    @Override
    public String modificar() throws Exception {
        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarMiSolicitud(this.getId(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            this.setSolicitudId(this.getId());
//            this.setComando("modificar");
            return "paginaSolicitud";
        }
        return this.listar();
    }

    @SkipValidation
    public String entregar() throws Exception {
        DtMensaje dtMensaje = this.getOpensgsWsPort().entregarMiSolicitud(this.getSolicitudId(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return "paginaEntregado";
        }
        return INPUT;
    }

    @SkipValidation
    public String agreement() throws Exception {
        DtAplicacion dtAplicacion = (DtAplicacion) this.obtenerDatosBasicosAplicacion(this.getAplicacionId());
        if (dtAplicacion != null) {
            this.setTextoAcuerdo(dtAplicacion.getDtPlantillaAplicacion().getTextoAcuerdo());
        }
        this.setTitleForm(this.getText("OpenSGSBean.text.agreement"));
        this.setTextListName(dtAplicacion.getTitulo());
        this.setComando("crear");
        return "agreement";
    }

    @SkipValidation
    public String paginaEntrega() throws Exception {
        DtSolicitud dtSolicitud = (DtSolicitud) this.getOpensgsWsPort().obtenerOpenSGSBean("Solicitud", this.getSolicitudId(), this.getDtSesion());
        if (dtSolicitud != null) {
            this.setTextoEntrega(dtSolicitud.getDtAplicacion().getDtPlantillaAplicacion().getTextoEntrega());
        }
        this.setTitleForm(this.getText("OpenSGSBean.text.deliver"));
        this.setComando("entregar");
        return "paginaEntrega";
    }

    @SkipValidation
    public String constancias() throws Exception {
        this.setCommonTextsValues();

        this.setTextSolicitudNavigation(
                this.getText("OpenSGSBean.list.certificates")
                + " "
                + this.getTextClassName()
                + " "
                + this.getId()
        );
        this.setTitleList(this.getTextSolicitudNavigation() + ":");
        
        List<DtConstancia> lista = null;
        lista = (List<DtConstancia>) this.getOpensgsWsPort().listarConstanciasSolicitud(this.getId(), this.getDtSesion());
        this.setListaDtConstancias(lista);
        return "constancias";
    }

    @Override
    public void validate() {
        try {
            this.setCommonTextsValues();
        } catch (Exception ex) {
            Logger.getLogger(MisSolicitudesAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtSolicitud";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "application":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Solicitud");
        this.setTextClassName(this.getText("OpenSGSBean.class.request"));
        this.setTextListName(this.getText("OpenSGSBean.title.myRequests"));
        this.setParentParam("aplicacion_id");

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

    @SkipValidation
    public List<DtAplicacion> listarAplicacionesAbiertas() throws Exception {
        List<DtAplicacion> lista = null;
        lista = this.getOpensgsWsPort().listarAplicacionesAbiertas();
        return lista;
    }

}
