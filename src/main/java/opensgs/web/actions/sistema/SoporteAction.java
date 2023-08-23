package opensgs.web.actions.sistema;

import cn.apiclub.captcha.Captcha;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.Map;
import opensgs.web.actions.BaseAction;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtSoporte;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "SoporteAction",
        results = {
            @Result(location = "soporte.jsp"),
            @Result(name = "input", location = "soporte/soporte.jsp")
        }
)
public class SoporteAction extends BaseAction {

    private DtSoporte dtSoporte;
    private Map session;
    private String captchaAnswer;

    public DtSoporte getDtSoporte() {
        return dtSoporte;
    }

    public void setDtSoporte(DtSoporte dtSoporte) {
        this.dtSoporte = dtSoporte;
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
                        fieldName = "dtSoporte.email",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.nombres",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.apellidos",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.telefono",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.descripcion",
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
                        fieldName = "dtSoporte.email",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.nombres",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.apellidos",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.telefono",
                        minLength = "8",
                        maxLength = "16",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSoporte.descripcion",
                        minLength = "1",
                        maxLength = "1024",
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
                        fieldName = "dtSoporte.email",
                        key = "OpenSGSBean.validator.email"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        Captcha captcha = (Captcha) session.get(Captcha.NAME);
        session.remove(Captcha.NAME);
        if (captcha != null && captchaAnswer != null && captcha.isCorrect(captchaAnswer)) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().enviarSoporte(dtSoporte);
            this.setDtMensaje(dtMensaje);

            if (!dtMensaje.isExito()) {
                return this.input();
            }
            this.agregarMensajeOK(dtMensaje);

            return "inicio";
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

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "email":
                return "dtSoporte.email";
            case "names":
                return "dtSoporte.nombres";
            case "lastNames":
                return "dtSoporte.apellidos";
            case "phone":
                return "dtSoporte.telefono";
            case "description":
                return "dtSoporte.descripcion";
            case "captchaAnswer":
                return "captchaAnswer";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setTitleForm(this.getText("OpenSGSBean.title.support"));
    }

}
