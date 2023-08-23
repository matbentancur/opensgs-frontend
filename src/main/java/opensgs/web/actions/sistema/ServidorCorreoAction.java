package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtServidorCorreo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ServidorCorreoAction",
        results = {
            @Result(name = "input", location = "servidorCorreo/servidorCorreoForm.jsp"),
            @Result(name = "list", location = "servidorCorreo/servidorCorreoList.jsp"),
            @Result(name = "view", location = "servidorCorreo/servidorCorreoView.jsp"),
            @Result(name = "pruebaEnvioCorreo", location = "servidorCorreo/pruebaEnvioCorreo.jsp")
        }
)
@AllowedMethods({
    "pruebaEnvioCorreo",
    "enviarCorreo"
})
public class ServidorCorreoAction extends OpenSGSManagedBeanAction {

    private DtServidorCorreo dtServidorCorreo;
    private String email;

    public DtServidorCorreo getDtServidorCorreo() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtServidorCorreo) this.getDtOpenSGSManagedBean();
        } else {
            return dtServidorCorreo;
        }
    }

    public void setDtServidorCorreo(DtServidorCorreo dtServidorCorreo) {
        this.dtServidorCorreo = dtServidorCorreo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.servidor",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.puerto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.usuario",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.password",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.seguridad",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.identificacion",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.desdeCorreo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.desdeNombre",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.servidor",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.usuario",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.password",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.seguridad",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.identificacion",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.desdeCorreo",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.desdeNombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.puerto",
                        min = "1",
                        max = "65535",
                        key = "OpenSGSBean.validator.intRange"
                )
            },
            emails = {
                @EmailValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorCorreo.desdeCorreo",
                        key = "OpenSGSBean.validator.email"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtServidorCorreo.seguridad",
                        expression = "dtServidorCorreo.seguridad in {'NINGUNA','SSL_TLS','STARTTLS'}",
                        key = "OpenSGSBean.validator.expression"
                ),
                @FieldExpressionValidator(
                        fieldName = "dtServidorCorreo.identificacion",
                        expression = "dtServidorCorreo.identificacion in {'LOGIN','PLAIN','DIGEST_MD5','NTLM'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtServidorCorreo != null) {
            this.setDtOpenSGSBean(dtServidorCorreo);
            this.setDtOpenSGSManagedBean(dtServidorCorreo);
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
        return "dtServidorCorreo";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtServidorCorreo.nombre";
            case "server":
                return "dtServidorCorreo.servidor";
            case "port":
                return "dtServidorCorreo.puerto";
            case "username":
                return "dtServidorCorreo.usuario";
            case "password":
                return "dtServidorCorreo.password";
            case "mailServerSecurity":
                return "dtServidorCorreo.seguridad";
            case "mailServerIdentification":
                return "dtServidorCorreo.identificacion";
            case "fromEmail":
                return "dtServidorCorreo.desdeCorreo";
            case "fromName":
                return "dtServidorCorreo.desdeNombre";
            case "email":
                return "email";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("ServidorCorreo");
        this.setTextClassName(this.getText("OpenSGSBean.class.mailServer"));
        this.setTextListName(this.getText("OpenSGSBean.list.mailServers"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

    @SkipValidation
    public String pruebaEnvioCorreo() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setDtOpenSGSManagedBean(this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId()));
        this.setDtServidorCorreo((DtServidorCorreo) this.getDtOpenSGSManagedBean());
        this.setTitleForm(""
                + this.getText("OpenSGSBean.title.mailServer.test")
                + ": "
                + this.getDtServidorCorreo().getNombre()
        );
        this.setTextChildListName(this.getText("OpenSGSBean.title.mailServer.test"));
        return "pruebaEnvioCorreo";
    }

    @SkipValidation
    public String enviarCorreo() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtMensaje dtMensaje = this.getOpensgsWsPort().probarEnvioCorreo(this.getDtServidorCorreo().getId(), this.getEmail(), this.getDtSesion());
        this.agregarMensaje(dtMensaje);
        return this.listar();
    }

}
