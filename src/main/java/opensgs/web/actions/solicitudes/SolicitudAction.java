package opensgs.web.actions.solicitudes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.actions.sistema.AplicacionHijoAction;
import opensgs.web.webservices.DtConstancia;
import opensgs.web.webservices.DtEstadoSolicitud;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtPaginaSolicitud;
import opensgs.web.webservices.DtSolicitud;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "SolicitudAction",
        results = {
            @Result(name = "input", location = "solicitud/solicitudForm.jsp"),
            @Result(name = "list", location = "solicitud/solicitudList.jsp"),
            @Result(name = "view", location = "solicitud/solicitudView.jsp"),
            @Result(name = "estadoInput", location = "solicitud/solicitudEstadoForm.jsp"),
            @Result(name = "paginaEntrega", location = "solicitud/paginaEntrega.jsp"),
            @Result(name = "paginaEntregado", location = "solicitud/paginaEntregado.jsp"),
            @Result(
                    name = "paginaSolicitud",
                    type = "redirectAction",
                    params = {
                        "actionName", "PaginaSolicitudAction",
                        "namespace", "/solicitudes",
                        //                        "comando", "${comando}",
                        "actionErrors", "${actionErrors}",
                        "actionMessages", "${actionMessages}",
                        "solicitudId", "${solicitudId}"
                    }
            )
        }
)
@AllowedMethods({
    "estadoForm",
    "estadoModificar",
    "paginaEntrega",
    "entregar"
})
public class SolicitudAction extends AplicacionHijoAction {

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
        if (this.getComando().equals("estadoModificar")) {
            return this.estadoModificar();
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

        DtSolicitud dtSolicitud = (DtSolicitud) this.obtenerDtOpenSGSBean("Solicitud", this.getId());
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

        if (this.getAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarSolicitudesAplicacion(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());
        }

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
        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarSolicitud(this.getId(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            this.setSolicitudId(this.getId());
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
    public String estadoForm() throws Exception {
        this.setCommonTextsValues();
        this.setTextSolicitudNavigation(
                this.getText("OpenSGSBean.text.status")
                + " "
                + this.getTextClassName()
                + " "
                + this.getId()
        );
        this.setTitleForm(
                this.getText("OpenSGSBean.text.modify")
                + " "
                + this.getTextSolicitudNavigation()
        );

        this.setDtOpenSGSManagedBean(this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId()));
        this.setComando("estadoModificar");
        return "estadoInput";
    }

    public String estadoModificar() throws Exception {
        DtEstadoSolicitud dtEstadoSolicitud = new DtEstadoSolicitud();
        dtEstadoSolicitud.setId(this.getDtSolicitud().getId());
        dtEstadoSolicitud.setEntregado(this.getDtSolicitud().isEntregado());
        dtEstadoSolicitud.setFinanciado(this.getDtSolicitud().isFinanciado());
        dtEstadoSolicitud.setCursado(this.getDtSolicitud().isCursado());
        dtEstadoSolicitud.setAprobado(this.getDtSolicitud().isAprobado());

        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarEstadoSolicitud(dtEstadoSolicitud, this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.listar();
        }
        return "estadoInput";
    }

//    @SkipValidation
//    public String constancias() throws Exception {
//        this.setCommonTextsValues();
//        List<DtConstancia> lista = null;
//        lista = (List<DtConstancia>) this.getOpensgsWsPort().listarMisSolicitudes(this.isBorrado(), this.getDtSesion());
//        this.setListaDtSolicitudes(lista);
//        return "constancias";
//    }
    @SkipValidation
    @Override
    public String borrar() throws Exception {
        this.setCommonTextsValues();
        return super.borrar();
    }

    @SkipValidation
    @Override
    public String restaurar() throws Exception {
        this.setCommonTextsValues();
        return super.restaurar();
    }

    @SkipValidation
    @Override
    public String activar() throws Exception {
        this.setCommonTextsValues();
        return super.activar();
    }

    @SkipValidation
    @Override
    public String desactivar() throws Exception {
        this.setCommonTextsValues();
        return super.desactivar();
    }

    @SkipValidation
    @Override
    public String actividad() throws Exception {
        this.setCommonTextsValues();
        return super.actividad();
    }

    @SkipValidation
    @Override
    public String actividadVer() throws Exception {
        this.setCommonTextsValues();
        return super.actividadVer();
    }

    @Override
    public void validate() {
        try {
            this.setCommonTextsValues();
        } catch (Exception ex) {
            Logger.getLogger(SolicitudAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtSolicitud";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "delivered":
                return this.getDtAttributeName() + ".entregado";
            case "financed":
                return this.getDtAttributeName() + ".financiado";
            case "completed":
                return this.getDtAttributeName() + ".cursado";
            case "approved":
                return this.getDtAttributeName() + ".aprobado";
            case "application":
                return "aplicacionId";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Solicitud");
        this.setTextClassName(this.getText("OpenSGSBean.class.request"));
        this.setTextListName(this.getText("OpenSGSBean.list.requests"));
        this.setParentParam("aplicacion_id");

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

}
