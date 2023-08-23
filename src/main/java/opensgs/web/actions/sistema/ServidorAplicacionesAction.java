package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtServidorAplicaciones;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ServidorAplicacionesAction",
        results = {
            @Result(name = "input", location = "servidorAplicaciones/servidorAplicacionesForm.jsp"),
            @Result(name = "list", location = "servidorAplicaciones/servidorAplicacionesList.jsp"),
            @Result(name = "view", location = "servidorAplicaciones/servidorAplicacionesView.jsp")
        }
)
public class ServidorAplicacionesAction extends OpenSGSManagedBeanAction {

    private DtServidorAplicaciones dtServidorAplicaciones;

    public DtServidorAplicaciones getDtServidorAplicaciones() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtServidorAplicaciones) this.getDtOpenSGSManagedBean();
        } else {
            return dtServidorAplicaciones;
        }
    }

    public void setDtServidorAplicaciones(DtServidorAplicaciones dtServidorAplicaciones) {
        this.dtServidorAplicaciones = dtServidorAplicaciones;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.servidor",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.puerto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.usuario",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.password",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.logPath",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.servidor",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.usuario",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.password",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.logPath",
                        minLength = "1",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtServidorAplicaciones.puerto",
                        min = "1",
                        max = "65535",
                        key = "OpenSGSBean.validator.intRange"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtServidorAplicaciones != null) {
            this.setDtOpenSGSBean(dtServidorAplicaciones);
            this.setDtOpenSGSManagedBean(dtServidorAplicaciones);
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
        return "dtServidorAplicaciones";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtServidorAplicaciones.nombre";
            case "server":
                return "dtServidorAplicaciones.servidor";
            case "port":
                return "dtServidorAplicaciones.puerto";
            case "username":
                return "dtServidorAplicaciones.usuario";
            case "password":
                return "dtServidorAplicaciones.password";
            case "logPath":
                return "dtServidorAplicaciones.logPath";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("ServidorAplicaciones");
        this.setTextClassName(this.getText("OpenSGSBean.class.aplicationServer"));
        this.setTextListName(this.getText("OpenSGSBean.list.aplicationServers"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

}
