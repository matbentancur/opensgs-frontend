package opensgs.web.actions.usuarios;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.BaseAction;
import opensgs.web.webservices.DtMensaje;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "MiClaveAction",
        results = {
            @Result(name = "input", location = "miClave.jsp"),
            @Result(name = "success", location = "miClave.jsp")
        }
)
public class MiClaveAction extends BaseAction {

    private String passwordActual;
    private String passwordNuevo;
    private String passwordNuevoRepetir;

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }

    public String getPasswordNuevo() {
        return passwordNuevo;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

    public String getPasswordNuevoRepetir() {
        return passwordNuevoRepetir;
    }

    public void setPasswordNuevoRepetir(String passwordNuevoRepetir) {
        this.passwordNuevoRepetir = passwordNuevoRepetir;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordActual",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordNuevo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordNuevoRepetir",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordActual",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordNuevo",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordNuevoRepetir",
                        minLength = "100",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "passwordNuevoRepetir",
                        expression = "passwordNuevo.equals(passwordNuevoRepetir)",
                        key = "OpenSGSBean.validator.expression.passwordRepeatPassword"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarMiClave(this.getPasswordActual(), this.getPasswordNuevo(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);

        if (!dtMensaje.isExito()) {
            return this.input();
        }
        this.agregarMensajeOK(dtMensaje);

        return "menu";
    }

    @SkipValidation
    @Override
    public String input() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        return INPUT;
    }

    @Override
    public void validate() {
        this.setCommonTextsValues();
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "passwordCurrent":
                return "passwordActual";
            case "passwordNew":
                return "passwordNuevo";
            case "passwordRepeat":
                return "passwordNuevoRepetir";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setTitleForm(this.getText("OpenSGSBean.title.changePassword"));
    }
}
