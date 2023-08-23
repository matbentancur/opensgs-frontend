package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtNotificacion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "NotificacionAction",
        results = {
            @Result(name = "input", location = "notificacion/notificacionForm.jsp"),
            @Result(name = "list", location = "notificacion/notificacionList.jsp"),
            @Result(name = "view", location = "notificacion/notificacionView.jsp"),
            @Result(name = "destinatarios", location = "notificacion/destinatarios.jsp"),
            @Result(name = "destinatarioView", location = "notificacion/destinatarioView.jsp")
        }
)
@AllowedMethods({
    "destinatarios",
    "destinatarioVer",
    "agregar",
    "quitar"
})
public class NotificacionAction extends AplicacionHijoAction {

    private DtNotificacion dtNotificacion;
    private String email;
    private List<DtNotificacion> listaDtNotificaciones;

    public DtNotificacion getDtNotificacion() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtNotificacion) this.getDtOpenSGSManagedBean();
        } else {
            return dtNotificacion;
        }
    }

    public void setDtNotificacion(DtNotificacion dtNotificacion) {
        this.dtNotificacion = dtNotificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DtNotificacion> getListaDtNotificaciones() {
        return listaDtNotificaciones;
    }

    public void setListaDtNotificaciones(List<DtNotificacion> listaDtNotificaciones) {
        this.listaDtNotificaciones = listaDtNotificaciones;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.operacion",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.elemento",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.destino",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.dtPlantillaCorreo.id",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.alcance",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.operacion",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.elemento",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.destino",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtNotificacion.alcance",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtNotificacion.alcance",
                        expression = "dtNotificacion.alcance in {'APLICACION','APLICACIONES','SISTEMA','GLOBAL'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtNotificacion != null) {
            this.setDtOpenSGSBean(dtNotificacion);
            this.setDtOpenSGSManagedBean(dtNotificacion);
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
    public String crear() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().crearNotificacionAplicacion(this.getDtNotificacion(), this.getAplicacionId(), this.getDtSesion());
            this.setDtMensaje(dtMensaje);
            if (dtMensaje.isExito()) {
                return this.listar();
            }
            return INPUT;
        }
        return super.crear();
    }

    @SkipValidation
    @Override
    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.cargarTextosLista();
        List<DtNotificacion> lista = null;

        if (this.getAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarNotificacionesAplicacion(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());
        } else {
            lista = this.getOpensgsWsPort().listarNotificacionesSistema(this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtNotificaciones(lista);
        return "list";
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
        try {
            this.setCommonTextsValues();
        } catch (Exception ex) {
            Logger.getLogger(NotificacionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtNotificacion";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "operation":
                return this.getDtAttributeName() + ".operacion";
            case "element":
                return this.getDtAttributeName() + ".elemento";
            case "notificationDestination":
                return this.getDtAttributeName() + ".destino";
            case "mailTemplate":
                return this.getDtAttributeName() + ".dtPlantillaCorreo.id";
            case "scope":
                return this.getDtAttributeName() + ".alcance";
            case "email":
                return "email";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Notificacion");
        this.setTextClassName(this.getText("OpenSGSBean.class.notification"));
        this.setTextListName(this.getText("OpenSGSBean.list.notifications"));

        this.setChildClassName("Destinatario");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.recipient"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.recipients"));

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

    @SkipValidation
    public String destinatarios() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getParentId() == null) {
            this.setParentId(this.getId());
        }
        this.setCommonTextsValues();
        this.setDtNotificacion((DtNotificacion) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "destinatarios";
    }

    @SkipValidation
    public String destinatarioVer() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setDtOpenSGSBean(this.obtenerDtOpenSGSBean("Destinatario", this.getId()));
        this.setTitle(
                this.getText("OpenSGSBean.class.recipient")
                + ": "
                + this.getDtOpenSGSBean().getId()
        );
        return "destinatarioView";
    }

//    @Validations(
//            requiredFields = {
//                @RequiredFieldValidator(
//                        type = ValidatorType.SIMPLE,
//                        fieldName = "email",
//                        key = "OpenSGSBean.validator.required"
//                )
//            },
//            stringLengthFields = {
//                @StringLengthFieldValidator(
//                        type = ValidatorType.SIMPLE,
//                        fieldName = "email",
//                        minLength = "1",
//                        maxLength = "128",
//                        key = "OpenSGSBean.validator.stringLength"
//                )
//            },
//            emails = {
//                @EmailValidator(
//                        type = ValidatorType.SIMPLE,
//                        fieldName = "email",
//                        key = "OpenSGSBean.validator.email"
//                )
//            }
//    )
    @SkipValidation
    public String agregar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        if (this.getChildClassName().equals("Destinatario")) {
            this.setChildClassName("Destinatario");
            this.setId(this.getDtNotificacion().getId());
            DtNotificacion dn = (DtNotificacion) this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
            DtMensaje dtMensaje = this.getOpensgsWsPort().agregarDestinatario(dn, this.getEmail(), this.getDtSesion());
            this.agregarMensaje(dtMensaje);
        }
        return this.destinatarios();
    }

    @SkipValidation
    public String quitar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        if (this.getChildClassName().equals("Destinatario")) {
            this.setChildClassName("Destinatario");
            super.removeChild();
        }
        return this.destinatarios();
    }

}
