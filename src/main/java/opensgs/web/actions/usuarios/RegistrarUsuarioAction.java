package opensgs.web.actions.usuarios;

import cn.apiclub.captcha.Captcha;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.Map;
import opensgs.web.actions.BaseAction;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtRegistrarUsuario;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "RegistrarUsuarioAction",
        results = {
            @Result(location = "registrarUsuario.jsp"),
            @Result(name = "input", location = "registrarUsuario.jsp")
        }
)
public class RegistrarUsuarioAction extends BaseAction implements SessionAware {

    private DtRegistrarUsuario dtRegistrarUsuario;
    private String passwordNuevoRepetir;
    private Map session;
    private String captchaAnswer;

    public DtRegistrarUsuario getDtRegistrarUsuario() {
        return dtRegistrarUsuario;
    }

    public void setDtRegistrarUsuario(DtRegistrarUsuario dtRegistrarUsuario) {
        this.dtRegistrarUsuario = dtRegistrarUsuario;
    }

    public String getPasswordNuevoRepetir() {
        return passwordNuevoRepetir;
    }

    public void setPasswordNuevoRepetir(String passwordNuevoRepetir) {
        this.passwordNuevoRepetir = passwordNuevoRepetir;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getCaptchaAnswer() {
        return captchaAnswer;
    }

    public void setCaptchaAnswer(String captchaAnswer) {
        this.captchaAnswer = captchaAnswer;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.email",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.clave",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordNuevoRepetir",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.documentoTipo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.documento",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.nombres",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.apellidos",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.telefono",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "captchaAnswer",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.email",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.clave",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "passwordNuevoRepetir",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.documentoTipo",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.documento",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.nombres",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.apellidos",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.telefono",
                        minLength = "8",
                        maxLength = "16",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "captchaAnswer",
                        minLength = "5",
                        maxLength = "10",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            emails = {
                @EmailValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtRegistrarUsuario.email",
                        key = "OpenSGSBean.validator.email")
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtRegistrarUsuario.clave",
                        expression = "!dtRegistrarUsuario.getEmail().equalsIgnoreCase(dtRegistrarUsuario.getClave())",
                        key = "OpenSGSBean.validator.expression.userEqualPassword"
                ),
                @FieldExpressionValidator(
                        fieldName = "passwordNuevoRepetir",
                        expression = "dtRegistrarUsuario.getClave().equals(passwordNuevoRepetir)",
                        key = "OpenSGSBean.validator.expression.passwordRepeatPassword"
                ),
                @FieldExpressionValidator(
                        fieldName = "dtRegistrarUsuario.documentoTipo",
                        expression = "dtRegistrarUsuario.documentoTipo in {'DocumentoUruguayo','Pasaporte','DocumentoExtranjero'}", key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        Captcha captcha = (Captcha) session.get(Captcha.NAME);
        session.remove(Captcha.NAME);
        if (captcha != null && captchaAnswer != null && captcha.isCorrect(captchaAnswer)) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().registrarUsuario(this.getDtRegistrarUsuario());
            this.setDtMensaje(dtMensaje);

            if (!dtMensaje.isExito()) {
                captchaAnswer = null;
                return this.input();
            }

            return SUCCESS;
        } else {
            addFieldError("captchaAnswer", "Código inválido, intente nuevamente.");
            captchaAnswer = null;
            return INPUT;
        }

    }

    @SkipValidation
    @Override
    public String input() throws Exception {
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
            case "email":
                return "dtRegistrarUsuario.email";
            case "password":
                return "dtRegistrarUsuario.clave";
            case "passwordRepeat":
                return "passwordNuevoRepetir";
            case "documentType":
                return "dtRegistrarUsuario.documentoTipo";
            case "document":
                return "dtRegistrarUsuario.documento";
            case "names":
                return "dtRegistrarUsuario.nombres";
            case "lastNames":
                return "dtRegistrarUsuario.apellidos";
            case "phone":
                return "dtRegistrarUsuario.telefono";
            case "captchaAnswer":
                return "captchaAnswer";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setTitleForm(this.getText("OpenSGSBean.title.register"));
    }

}
