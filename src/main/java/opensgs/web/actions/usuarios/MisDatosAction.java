package opensgs.web.actions.usuarios;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.BaseAction;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtUsuario;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "MisDatosAction",
        results = {
            @Result(location = "misDatos.jsp"),
            @Result(name = "input", location = "misDatos.jsp")
        }
)
public class MisDatosAction extends BaseAction {

    private DtUsuario dtUsuario;

    public DtUsuario getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DtUsuario dtUsuario) {
        this.dtUsuario = dtUsuario;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.email",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.nombres",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.apellidos",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.telefono",
                        key = "OpenSGSBean.validator.required")
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.email",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.nombres",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.apellidos",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.telefono",
                        minLength = "8",
                        maxLength = "16",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            emails = {
                @EmailValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.email",
                        key = "OpenSGSBean.validator.email"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtUsuario dtUsuarioExistente = this.getOpensgsWsPort().obtenerMisDatos(this.getDtSesion());

        dtUsuarioExistente.setEmail(this.getDtUsuario().getEmail());
        dtUsuarioExistente.setNombres(this.getDtUsuario().getNombres());
        dtUsuarioExistente.setApellidos(this.getDtUsuario().getApellidos());
        dtUsuarioExistente.setTelefono(this.getDtUsuario().getTelefono());

        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarMisDatos(dtUsuarioExistente, this.getDtSesion());
        this.setDtMensaje(dtMensaje);

        if (!dtMensaje.isExito()) {
            return this.input();
        }
        this.agregarMensajeOK(dtMensaje);

        return "menu";
    }

    @Override
    public String input() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        DtUsuario dtU = this.getOpensgsWsPort().obtenerMisDatos(this.getDtSesion());
        this.setDtUsuario(dtU);
        return INPUT;
    }

    @Override
    public void validate() {
        this.setCommonTextsValues();
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "email":
                return "dtUsuario.email";
            case "names":
                return "dtUsuario.nombres";
            case "lastNames":
                return "dtUsuario.apellidos";
            case "phone":
                return "dtUsuario.telefono";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setTitleForm(this.getText("OpenSGSBean.title.myData"));
    }

}
