package opensgs.web.actions.usuarios;

import cn.apiclub.captcha.Captcha;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.BaseAction;
import java.util.Map;
import opensgs.web.webservices.DtMensaje;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(value = "RecuperarMiClaveAction", results = {
    @Result(location = "recuperarMiClave.jsp"),
    @Result(name = "input", location = "recuperarMiClave.jsp")
}
)
public class RecuperarMiClaveAction extends BaseAction {

    private String email;
    private Map session;
    private String captchaAnswer;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                        fieldName = "email",
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
                        fieldName = "email",
                        minLength = "1",
                        maxLength = "128",
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
                        fieldName = "email",
                        key = "OpenSGSBean.validator.email"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        Captcha captcha = (Captcha) session.get(Captcha.NAME);
        session.remove(Captcha.NAME);
        if (captcha != null && captchaAnswer != null && captcha.isCorrect(captchaAnswer)) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().recuperarMiClave(this.getEmail());
            this.setDtMensaje(dtMensaje);

            if (!dtMensaje.isExito()) {
                captchaAnswer = null;
                return this.input();
            }
            this.agregarMensajeOK(dtMensaje);
            return "login";
        } else {
            addFieldError("captchaAnswer", "Código inválido, intente nuevamente.");
            captchaAnswer = null;
            return this.input();
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
                return "email";
            case "captchaAnswer":
                return "captchaAnswer";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setTitleForm(this.getText("OpenSGSBean.title.restorePassword"));
    }
}
