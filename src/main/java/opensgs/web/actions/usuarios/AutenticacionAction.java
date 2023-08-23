package opensgs.web.actions.usuarios;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.ComunicationAction;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtSesion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Actions({
    @Action(
            value = "AutenticacionAction",
            results = {
                @Result(location = "login.jsp"),
                @Result(name = "input", location = "login.jsp"),
                @Result(name = "logout", location = "login.jsp")
            }
    ),
    @Action(
            value = "Login",
            results = {
                @Result(location = "login.jsp"),
                @Result(name = "input", location = "login.jsp")
            }
    )
})
@AllowedMethods({
    "login",
    "logout"
})
public class AutenticacionAction extends ComunicationAction {

    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "usuario",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "password",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "usuario",
                        minLength = "5",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "password",
                        minLength = "8",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            emails = {
                @EmailValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "usuario",
                        key = "OpenSGSBean.validator.email"
                )
            }
    )
    public String login() throws Exception {
        DtMensaje dtMensaje = this.getOpensgsWsPort().autenticarUsuario(this.getUsuario(), this.getPassword());
        System.out.println(dtMensaje.getMensajes().toString());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            DtSesion dtSesion = this.getOpensgsWsPort().iniciarSesion(this.getUsuario(), this.getPassword());
            if (dtSesion != null) {
                this.getSession().put("dtSesion", dtSesion);
                return "menu";
            }
        }
        return INPUT;
    }

    @SkipValidation
    public String logout() throws Exception {
        if (this.getSession() != null) {
            DtSesion dtSesion = (DtSesion) this.getSession().get("dtSesion");
            DtMensaje dtMensaje = this.getOpensgsWsPort().cerrarSesion(dtSesion);
            this.setDtMensaje(dtMensaje);
            this.getSession().clear();
        }
        return "logout";
    }
    
    @SkipValidation
    @Override
    public String input() throws Exception {
        this.listarAnunciosSistema();
        return INPUT;
    }

}
