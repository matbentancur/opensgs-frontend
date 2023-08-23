package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.UrlValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtSistema;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "SistemaAction",
        results = {
            @Result(
                    name = "input",
                    location = "sistema/sistemaForm.jsp"
            ),
            @Result(
                    name = "list",
                    type = "redirectAction",
                    params = {
                        "actionName", "MenuAction",
                        "namespace", "/usuarios",
                        "actionErrors", "${actionErrors}",
                        "actionMessages", "${actionMessages}"
                    }
            )
        }
)
public class SistemaAction extends OpenSGSManagedBeanAction {

    private DtSistema dtSistema;

    public DtSistema getDtSistema() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtSistema) this.getDtOpenSGSManagedBean();
        } else {
            return dtSistema;
        }
    }

    public void setDtSistema(DtSistema dtSistema) {
        this.dtSistema = dtSistema;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.url",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.mantenimiento",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.cantidadMaximaUsuarios",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.fileUploadMaxSize",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.correoSoporte",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.filesPath",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.dtServidorCorreo.id",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.dtServidorAplicaciones.id",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.dtServidorAutenticacion.id",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.url",
                        minLength = "1",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.correoSoporte",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.filesPath",
                        minLength = "1",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            urls = {
                @UrlValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.url",
                        key = "OpenSGSBean.validator.url"
                )
            },
            emails = {
                @EmailValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.correoSoporte",
                        key = "OpenSGSBean.validator.email"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.cantidadMaximaUsuarios",
                        min = "1",
                        max = "100000",
                        key = "OpenSGSBean.validator.intRange"
                ),
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSistema.fileUploadMaxSize",
                        min = "1",
                        max = "500",
                        key = "OpenSGSBean.validator.intRange"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtSistema != null) {
            this.setDtOpenSGSBean(dtSistema);
            this.setDtOpenSGSManagedBean(dtSistema);
        }
        this.setCommonTextsValues();
        return this.parseComando();
    }

    @SkipValidation
    @Override
    public String form() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("nombre");
        this.setId(1L);
        return super.form();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtSistema";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "url":
                return this.getDtAttributeName() + ".url";
            case "maintenance":
                return this.getDtAttributeName() + ".mantenimiento";
            case "maxUsers":
                return this.getDtAttributeName() + ".cantidadMaximaUsuarios";
            case "fileUploadMaxSize":
                return this.getDtAttributeName() + ".fileUploadMaxSize";
            case "supportEmail":
                return this.getDtAttributeName() + ".correoSoporte";
            case "filesPath":
                return this.getDtAttributeName() + ".filesPath";
            case "mailServer":
                return this.getDtAttributeName() + ".dtServidorCorreo.id";
            case "aplicationServer":
                return this.getDtAttributeName() + ".dtServidorAplicaciones.id";
            case "authenticationServer":
                return this.getDtAttributeName() + ".dtServidorAutenticacion.id";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("Sistema");
        this.setTextClassName(this.getText("OpenSGSBean.class.system"));
        this.setTextListName(this.getText("OpenSGSBean.class.system"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

}
