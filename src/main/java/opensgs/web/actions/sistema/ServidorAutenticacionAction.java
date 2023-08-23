package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtServidorAutenticacion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ServidorAutenticacionAction",
        results = {
            @Result(name = "input", location = "servidorAutenticacion/servidorAutenticacionForm.jsp"),
            @Result(name = "list", location = "servidorAutenticacion/servidorAutenticacionList.jsp"),
            @Result(name = "view", location = "servidorAutenticacion/servidorAutenticacionView.jsp")
        }
)
public class ServidorAutenticacionAction extends OpenSGSManagedBeanAction {

    private DtServidorAutenticacion dtServidorAutenticacion;

    public DtServidorAutenticacion getDtServidorAutenticacion() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtServidorAutenticacion) this.getDtOpenSGSManagedBean();
        } else {
            return dtServidorAutenticacion;
        }
    }

    public void setDtServidorAutenticacion(DtServidorAutenticacion dtServidorAutenticacion) {
        this.dtServidorAutenticacion = dtServidorAutenticacion;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.servidor",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.puerto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.usuario",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.password",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.tipo",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.servidor",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.usuario",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.password",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.tipo",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAutenticacion.puerto",
                        min = "1",
                        max = "65535",
                        key = "OpenSGSBean.validator.intRange"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtServidorAutenticacion.tipo",
                        expression = "dtServidorAutenticacion.tipo in {'LOCAL','LDAP','RADIUS','KERBEROS'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtServidorAutenticacion != null) {
            this.setDtOpenSGSBean(dtServidorAutenticacion);
            this.setDtOpenSGSManagedBean(dtServidorAutenticacion);
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
        return "dtServidorAutenticacion";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtServidorAutenticacion.nombre";
            case "server":
                return "dtServidorAutenticacion.servidor";
            case "port":
                return "dtServidorAutenticacion.puerto";
            case "username":
                return "dtServidorAutenticacion.usuario";
            case "password":
                return "dtServidorAutenticacion.password";
            case "authenticationType":
                return "dtServidorAutenticacion.tipo";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("ServidorAutenticacion");
        this.setTextClassName(this.getText("OpenSGSBean.class.authenticationServer"));
        this.setTextListName(this.getText("OpenSGSBean.list.authenticationServers"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

}
