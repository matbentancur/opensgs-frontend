package opensgs.web.actions.solicitudes;

import static com.opensymphony.xwork2.Action.INPUT;
import java.util.List;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtAnuncio;
import opensgs.web.webservices.DtArchivo;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtPaginaAplicacion;
import opensgs.web.webservices.DtPaginaSolicitud;
import opensgs.web.webservices.DtPreguntaFrecuente;
import opensgs.web.webservices.DtSolicitud;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PaginaSolicitudAction"
)
@Results({
    @Result(
            name = "Entrega",
            type = "redirectAction",
            params = {
                "actionName", "MisSolicitudesAction",
                "namespace", "/solicitudes",
                "method", "paginaEntrega",
                "solicitudId", "${solicitudId}",
                "textListName", "${textListName}",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "DatosPersonalesCEP",
            type = "redirectAction",
            params = {
                "actionName", "DatosPersonalesCEPAction",
                "method", "${metodo}",
                "namespace", "/solicitudes/paginas/cep",
                "solicitudId", "${solicitudId}",
                "id", "${id}",
                "paginaAplicacionId", "${paginaAplicacionId}",
                "pagina", "${pagina}",
                "comando", "${comando}",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "EducacionCEP",
            type = "redirectAction",
            params = {
                "actionName", "EducacionCEPAction",
                "method", "${metodo}",
                "namespace", "/solicitudes/paginas/cep",
                "solicitudId", "${solicitudId}",
                "id", "${id}",
                "paginaAplicacionId", "${paginaAplicacionId}",
                "pagina", "${pagina}",
                "comando", "${comando}",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "SituacionLaboralCEP",
            type = "redirectAction",
            params = {
                "actionName", "SituacionLaboralCEPAction",
                "method", "${metodo}",
                "namespace", "/solicitudes/paginas/cep",
                "solicitudId", "${solicitudId}",
                "id", "${id}",
                "paginaAplicacionId", "${paginaAplicacionId}",
                "pagina", "${pagina}",
                "comando", "${comando}",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "DifusionCEP",
            type = "redirectAction",
            params = {
                "actionName", "DifusionCEPAction",
                "method", "${metodo}",
                "namespace", "/solicitudes/paginas/cep",
                "solicitudId", "${solicitudId}",
                "id", "${id}",
                "paginaAplicacionId", "${paginaAplicacionId}",
                "pagina", "${pagina}",
                "comando", "${comando}",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    )

})
public class PaginaSolicitudAction extends OpenSGSManagedBeanAction {

    private DtPaginaSolicitud dtPaginaSolicitud;
    private DtPaginaAplicacion dtPaginaAplicacion;
    private Long solicitudId;
    private Long paginaAplicacionId;
    private Integer pagina;
    private List<DtPaginaAplicacion> listaDtPaginasAplicacion;
    private List<DtAnuncio> listaDtAnuncios;
    private List<DtArchivo> listaDtArchivos;
    private List<DtPreguntaFrecuente> listaDtPreguntasFrecuentes;

    public DtPaginaSolicitud getDtPaginaSolicitud() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtPaginaSolicitud) this.getDtOpenSGSManagedBean();
        } else {
            return dtPaginaSolicitud;
        }
    }

    public void setDtPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud) {
        this.dtPaginaSolicitud = dtPaginaSolicitud;
    }

    public DtPaginaAplicacion getDtPaginaAplicacion() {
        return dtPaginaAplicacion;
    }

    public void setDtPaginaAplicacion(DtPaginaAplicacion dtPaginaAplicacion) {
        this.dtPaginaAplicacion = dtPaginaAplicacion;
    }

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Long getPaginaAplicacionId() {
        return paginaAplicacionId;
    }

    public void setPaginaAplicacionId(Long paginaAplicacionId) {
        this.paginaAplicacionId = paginaAplicacionId;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public List<DtPaginaAplicacion> getListaDtPaginasAplicacion() {
        return listaDtPaginasAplicacion;
    }

    public void setListaDtPaginasAplicacion(List<DtPaginaAplicacion> listaDtPaginasAplicacion) {
        this.listaDtPaginasAplicacion = listaDtPaginasAplicacion;
    }

    public List<DtAnuncio> getListaDtAnuncios() {
        return listaDtAnuncios;
    }

    public void setListaDtAnuncios(List<DtAnuncio> listaDtAnuncios) {
        this.listaDtAnuncios = listaDtAnuncios;
    }

    public List<DtArchivo> getListaDtArchivos() {
        return listaDtArchivos;
    }

    public void setListaDtArchivos(List<DtArchivo> listaDtArchivos) {
        this.listaDtArchivos = listaDtArchivos;
    }

    public List<DtPreguntaFrecuente> getListaDtPreguntasFrecuentes() {
        return listaDtPreguntasFrecuentes;
    }

    public void setListaDtPreguntasFrecuentes(List<DtPreguntaFrecuente> listaDtPreguntasFrecuentes) {
        this.listaDtPreguntasFrecuentes = listaDtPreguntasFrecuentes;
    }

    public DtPaginaSolicitud obtenerDtPaginaSolicitud(String className, Long id) throws Exception {
        DtPaginaSolicitud dtPs = this.getOpensgsWsPort().obtenerDtPaginaSolicitud(className, id, this.getDtSesion());
        return dtPs;
    }

    @Override
    public String execute() throws Exception {
        return this.evaluarPagina();
//        switch (this.getComando()) {
//            case "crear":
//                this.setMetodo("input");
//                return this.evaluarPagina();
//            case "modificar":
//                this.setMetodo("form");
//                return this.evaluarPagina();
//            default:
//                addActionError(this.getText("Error.command.notFound") + ": " + this.getComando());
//                return ERROR;
//        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtPaginaSolicitud";
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

    private void setCommonTextsValues() {
        this.setClassName("PaginaSolicitud");
        this.setTextClassName(this.getText("OpenSGSBean.class.request"));
        this.setTextListName(this.getText("OpenSGSBean.list.requests"));
    }

    public String input() throws Exception {
        this.setComando("crear");
        return INPUT;
    }

    @Override
    public String ver() throws Exception {
        this.setDtOpenSGSManagedBean(this.obtenerDtPaginaSolicitud(this.getClassName(), this.getId()));
        this.setTitle(
                this.getTextClassName()
                + ": "
        );
        return "view";
    }

    public String form() throws Exception {
        this.setDtOpenSGSManagedBean(this.obtenerDtPaginaSolicitud(this.getClassName(), this.getId()));
        this.setComando("modificar");
        return INPUT;
    }

    @Override
    public String crear() throws Exception {
        DtMensaje dtMensaje = this.getOpensgsWsPort().crearPaginaSolicitud(this.getSolicitudId(), this.getPaginaAplicacionId(), this.getDtPaginaSolicitud(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.paginaSiguiente();
        }
        return INPUT;
    }

    @Override
    public String modificar() throws Exception {
        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarPaginaSolicitud(this.getDtPaginaSolicitud(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.paginaSiguiente();
        }
        return INPUT;
    }

    @Override
    public String parseComando() throws Exception {
        if (this.getComando() == null) {
            addActionError(this.getText("Error.command.notFound"));
            return ERROR;
        }

        switch (this.getComando()) {
            case "crear":
                return this.crear();
            case "modificar":
                return this.modificar();
            default:
                addActionError(this.getText("Error.command.notFound") + ": " + this.getComando());
                return ERROR;
        }
    }

    public String evaluarPagina() throws Exception {
        if (this.getPagina() == null) {
            this.setPagina(1);
        }

        DtSolicitud dtSolicitud = (DtSolicitud) this.obtenerDtOpenSGSBean("Solicitud", this.getSolicitudId());

        if (this.paginaEntrega(dtSolicitud)) {
            return "Entrega";
        }

        List<DtPaginaSolicitud> listaPaginasSolicitudes = dtSolicitud.getDtPaginasSolicitud();

        if (listaPaginasSolicitudes != null && !listaPaginasSolicitudes.isEmpty()) {
            for (DtPaginaSolicitud dtPaginaSolicitud : listaPaginasSolicitudes) {
                if (dtPaginaSolicitud.getDtPaginaAplicacion().getPagina().equals(this.getPagina())) {
                    this.setId(dtPaginaSolicitud.getId());
                    this.setMetodo("form");
                    this.setComando("modificar");
                    this.setPaginaAplicacionId(dtPaginaSolicitud.getDtPaginaAplicacion().getId());
                    return this.retornarPagina(dtPaginaSolicitud.getDtPaginaAplicacion().getEsctructuraDatos());
                }
            }
        }

//        List<DtPaginaAplicacion> listaPaginasAplicacion = dtSolicitud.getDtAplicacion().getDtPlantillaAplicacion().getDtPaginasAplicacion();
        for (DtPaginaAplicacion dtPaginaAplicacion : this.getListaDtPaginasAplicacion()) {
            if (dtPaginaAplicacion.getPagina().equals(this.getPagina())) {
                this.setId(null);
                this.setMetodo("input");
                this.setComando("crear");
                this.setPaginaAplicacionId(dtPaginaAplicacion.getId());
                return this.retornarPagina(dtPaginaAplicacion.getEsctructuraDatos());
            }
        }

        return null;

    }

    public Boolean paginaEntrega(DtSolicitud dtSolicitud) throws Exception {
        this.listarPaginasAplicacionActivas(dtSolicitud.getDtAplicacion().getDtPlantillaAplicacion().getId());
        if (this.getPagina() > this.getListaDtPaginasAplicacion().size()) {
            return true;
        }
        return false;
    }

    public String paginaSiguiente() throws Exception {
        this.setPagina(this.getPagina() + 1);
        return this.evaluarPagina();
    }

    public String paginaAnterior() throws Exception {
        if (this.getPagina() > 1) {
            this.setPagina(this.getPagina() - 1);
        }
        return this.evaluarPagina();
    }

    public void cargarDatosPagina() throws Exception {
        DtSolicitud dtSolicitud = (DtSolicitud) this.obtenerDtOpenSGSBean("Solicitud", this.getSolicitudId());

        //TITULO DE APLICACION
        this.setTextListName(dtSolicitud.getDtAplicacion().getTitulo());

        //NAVEGACION DE PAGINAS
        this.listarPaginasAplicacionActivas(dtSolicitud.getDtAplicacion().getDtPlantillaAplicacion().getId());

        //ANUNCIOS
        this.listarAnunciosAplicacion(dtSolicitud.getDtAplicacion().getId());

        //ARCHIVOS
        this.listarArchivosAplicacion(dtSolicitud.getDtAplicacion().getId());

        //PREGUNTAS FRECUENTES
        this.listarPreguntasAplicacion(dtSolicitud.getDtAplicacion().getId());

        //TEXTOS DE PAGINA DE APLICACION
        DtPaginaAplicacion dtpa = (DtPaginaAplicacion) this.obtenerDtOpenSGSManagedBean("PaginaAplicacion", this.getPaginaAplicacionId());
        this.setDtPaginaAplicacion(dtpa);
    }

    private void listarAnunciosAplicacion(Long aplicacionId) throws Exception {
        List<DtAnuncio> lista = null;
        lista = this.getOpensgsWsPort().listarAnunciosAplicacionVigentes(aplicacionId, this.getDtSesion());
        this.setListaDtAnuncios(lista);
    }

    private void listarArchivosAplicacion(Long aplicacionId) throws Exception {
        List<DtArchivo> lista = null;
        lista = this.getOpensgsWsPort().listarArchivosAplicacionActivos(aplicacionId, this.getDtSesion());
        this.setListaDtArchivos(lista);
    }

    private void listarPreguntasAplicacion(Long aplicacionId) throws Exception {
        List<DtPreguntaFrecuente> lista = null;
        lista = this.getOpensgsWsPort().listarPreguntasAplicacionActivas(aplicacionId, this.getDtSesion());
        this.setListaDtPreguntasFrecuentes(lista);
    }

    private void listarPaginasAplicacionActivas(Long plantillaAplicacionId) throws Exception {
        List<DtPaginaAplicacion> lista = null;
        lista = this.getOpensgsWsPort().listarPaginasAplicacionPlantillaAplicacionActivas(plantillaAplicacionId, this.getDtSesion());
        this.setListaDtPaginasAplicacion(lista);
    }

    public String retornarPagina(String esctructuraDatos) throws Exception {
        switch (esctructuraDatos) {
            case "DatosPersonalesCEP":
                return "DatosPersonalesCEP";
            case "DifusionCEP":
                return "DifusionCEP";
            case "EducacionCEP":
                return "EducacionCEP";
            case "SituacionLaboralCEP":
                return "SituacionLaboralCEP";
            default:
                break;
        }
        return null;
    }

}
