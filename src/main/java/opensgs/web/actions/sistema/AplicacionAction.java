package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtAnuncio;
import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtArchivo;
import opensgs.web.webservices.DtNotificacion;
import opensgs.web.webservices.DtPreguntaFrecuente;
import opensgs.web.webservices.DtReporte;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "AplicacionAction",
        results = {
            @Result(name = "input", location = "aplicacion/aplicacionForm.jsp"),
            @Result(name = "list", location = "aplicacion/aplicacionList.jsp"),
            @Result(name = "view", location = "aplicacion/aplicacionView.jsp"),
            @Result(name = "anuncios", location = "aplicacion/anuncios.jsp"),
            @Result(name = "notificaciones", location = "aplicacion/notificaciones.jsp"),
            @Result(name = "preguntasFrecuentes", location = "aplicacion/preguntasFrecuentes.jsp"),
            @Result(name = "reportes", location = "aplicacion/reportes.jsp"),
            @Result(name = "archivos", location = "aplicacion/archivos.jsp"),
            @Result(name = "constancias", location = "aplicacion/constancias.jsp")
        }
)
@AllowedMethods({
    "anuncios",
    "notificaciones",
    "preguntasFrecuentes",
    "reportes",
    "archivos",
    "constancias",
    "agregar",
    "quitar"
})
public class AplicacionAction extends OpenSGSManagedBeanAction {

    private DtAplicacion dtAplicacion;

    public DtAplicacion getDtAplicacion() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtAplicacion) this.getDtOpenSGSManagedBean();
        } else {
            return dtAplicacion;
        }
    }

    public void setDtAplicacion(DtAplicacion dtAplicacion) {
        this.dtAplicacion = dtAplicacion;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.titulo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.apertura",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.cierre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.contacto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.solicitudesPorUsuario",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.solicitudesTotal",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.titulo",
                        minLength = "1",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.apertura",
                        minLength = "1",
                        maxLength = "20",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.cierre",
                        minLength = "1",
                        maxLength = "20",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.contacto",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.solicitudesPorUsuario",
                        min = "1",
                        max = "100",
                        key = "OpenSGSBean.validator.intRange"
                ),
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAplicacion.solicitudesTotal",
                        min = "1",
                        max = "10000",
                        key = "OpenSGSBean.validator.intRange"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtAplicacion != null) {
            this.setDtOpenSGSBean(dtAplicacion);
            this.setDtOpenSGSManagedBean(dtAplicacion);
        }
        this.setCommonTextsValues();
        return this.parseComando();
    }

    @SkipValidation
    @Override
    public String input() throws Exception {
        this.setCommonTextsValues();
        return super.input();
    }

    @SkipValidation
    @Override
    public String ver() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("nombre");
        return super.ver();
    }

    @SkipValidation
    @Override
    public String form() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("nombre");
        return super.form();
    }

    @SkipValidation
    @Override
    public String listar() throws Exception {
        this.setCommonTextsValues();
        return super.listar();
    }

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
        this.setCommonTextsValues();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtAplicacion";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "title":
                return this.getDtAttributeName() + ".titulo";
            case "opening":
                return this.getDtAttributeName() + ".apertura";
            case "closing":
                return this.getDtAttributeName() + ".cierre";
            case "contact":
                return this.getDtAttributeName() + ".contacto";
            case "requestPerUser":
                return this.getDtAttributeName() + ".solicitudesPorUsuario";
            case "totalRequests":
                return this.getDtAttributeName() + ".solicitudesTotal";
            case "applicationTemplate":
                return this.getDtAttributeName() + ".dtPlantillaAplicacion.id";
            case "announcement":
                return "childId";
            case "notification":
                return "childId";
            case "faq":
                return "childId";
            case "report":
                return "childId";
            case "file":
                return "childId";
            case "certificate":
                return "childId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("Aplicacion");
        this.setTextClassName(this.getText("OpenSGSBean.class.application"));
        this.setTextListName(this.getText("OpenSGSBean.list.applications"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

    @SkipValidation
    public String anuncios() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setChildClassName("Anuncio");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.announcement"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.announcements"));
        this.setDtAplicacion((DtAplicacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "anuncios";
    }

    @SkipValidation
    public String notificaciones() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setChildClassName("Notificacion");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.notification"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.notifications"));
        this.setDtAplicacion((DtAplicacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "notificaciones";
    }

    @SkipValidation
    public String preguntasFrecuentes() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setChildClassName("PreguntaFrecuente");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.faq"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.faqs"));
        this.setDtAplicacion((DtAplicacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "preguntasFrecuentes";
    }

    @SkipValidation
    public String reportes() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setChildClassName("Reporte");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.report"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.reports"));
        this.setDtAplicacion((DtAplicacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "reportes";
    }

    @SkipValidation
    public String archivos() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setChildClassName("Archivo");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.file"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.files"));
        this.setDtAplicacion((DtAplicacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "archivos";
    }

    @SkipValidation
    public String constancias() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setChildClassName("Constancia");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.certificate"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.certificates"));
        this.setDtAplicacion((DtAplicacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "constancias";
    }

    @SkipValidation
    public String agregar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setId(this.getDtAplicacion().getId());
        super.addChild();
        if (this.getChildClassName().equals("Anuncio")) {
            return this.anuncios();
        } else if (this.getChildClassName().equals("Notificacion")) {
            return this.notificaciones();
        } else if (this.getChildClassName().equals("PreguntaFrecuente")) {
            return this.preguntasFrecuentes();
        } else if (this.getChildClassName().equals("Reporte")) {
            return this.reportes();
        } else if (this.getChildClassName().equals("Archivo")) {
            return this.archivos();
        } else if (this.getChildClassName().equals("Constancia")) {
            return this.constancias();
        }
        return this.listar();

    }

    @SkipValidation
    public String quitar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        super.removeChild();
        if (this.getChildClassName().equals("Anuncio")) {
            return this.anuncios();
        } else if (this.getChildClassName().equals("Notificacion")) {
            return this.notificaciones();
        } else if (this.getChildClassName().equals("PreguntaFrecuente")) {
            return this.preguntasFrecuentes();
        } else if (this.getChildClassName().equals("Reporte")) {
            return this.reportes();
        } else if (this.getChildClassName().equals("Archivo")) {
            return this.archivos();
        } else if (this.getChildClassName().equals("Constancia")) {
            return this.constancias();
        }
        return this.listar();
    }

    @SkipValidation
    public List<DtAnuncio> listarAnunciosFormField() throws Exception {
        List<DtAnuncio> lista = null;
        lista = this.getOpensgsWsPort().listarAnunciosActivos(this.getDtSesion());
        return lista;
    }

    @SkipValidation
    public List<DtNotificacion> listarNotificacionesFormField() throws Exception {
        List<DtNotificacion> lista = null;
        lista = this.getOpensgsWsPort().listarNotificacionesActivas(this.getDtSesion());
        return lista;
    }

    @SkipValidation
    public List<DtPreguntaFrecuente> listarPreguntasFrecuentesFormField() throws Exception {
        List<DtPreguntaFrecuente> lista = null;
        lista = this.getOpensgsWsPort().listarPreguntasFrecuentesActivas(this.getDtSesion());
        return lista;
    }

    @SkipValidation
    public List<DtReporte> listarReportesFormField() throws Exception {
        List<DtReporte> lista = null;
        lista = this.getOpensgsWsPort().listarReportesActivos(this.getDtSesion());
        return lista;
    }

    @SkipValidation
    public List<DtArchivo> listarArchivosFormField() throws Exception {
        List<DtArchivo> lista = null;
        lista = this.getOpensgsWsPort().listarArchivosActivos(this.getDtSesion());
        return lista;
    }

}
