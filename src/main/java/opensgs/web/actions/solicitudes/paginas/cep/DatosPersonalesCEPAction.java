package opensgs.web.actions.solicitudes.paginas.cep;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.solicitudes.PaginaSolicitudAction;
import opensgs.web.webservices.DtDatosPersonalesCEP;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "DatosPersonalesCEPAction",
        results = {
            @Result(name = "input", location = "datosPersonalesCEP/datosPersonalesCEPForm.jsp"),
            @Result(name = "view", location = "datosPersonalesCEP/datosPersonalesCEPView.jsp")
        }
)
public class DatosPersonalesCEPAction extends PaginaSolicitudAction {

    private DtDatosPersonalesCEP dtDatosPersonalesCEP;

    public DtDatosPersonalesCEP getDtDatosPersonalesCEP() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtDatosPersonalesCEP) this.getDtOpenSGSManagedBean();
        } else {
            return dtDatosPersonalesCEP;
        }
    }

    public void setDtDatosPersonalesCEP(DtDatosPersonalesCEP dtDatosPersonalesCEP) {
        this.dtDatosPersonalesCEP = dtDatosPersonalesCEP;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.apellidos",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.nombres",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.documento",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.email",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.genero",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.nacionalidad",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.celular",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.departamentoPaisResidencia",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.apellidos",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.nombres",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.documento",
                        minLength = "7",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.email",
                        minLength = "5",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.genero",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.fechaNacimiento",
                        maxLength = "20",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.nacionalidad",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.celular",
                        minLength = "8",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtDatosPersonalesCEP.departamentoPaisResidencia",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtDatosPersonalesCEP != null) {
            this.setDtOpenSGSBean(dtDatosPersonalesCEP);
            this.setDtOpenSGSManagedBean(dtDatosPersonalesCEP);
            this.setDtPaginaSolicitud(dtDatosPersonalesCEP);
        }
        this.setCommonTextsValues();
        return this.parseComando();
    }

    @SkipValidation
    @Override
    public String input() throws Exception {
        this.setCommonTextsValues();
        this.cargarDatosPagina();
        return super.input();
    }

    @SkipValidation
    @Override
    public String ver() throws Exception {
        this.setCommonTextsValues();
        return super.ver();
    }

    @SkipValidation
    @Override
    public String form() throws Exception {
        this.setCommonTextsValues();
        this.cargarDatosPagina();
        return super.form();
    }

    @Override
    public void validate() {
        this.setCommonTextsValues();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtDatosPersonalesCEP";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "lastNames":
                return this.getDtAttributeName() + ".apellidos";
            case "names":
                return this.getDtAttributeName() + ".nombres";
            case "document":
                return this.getDtAttributeName() + ".documento";
            case "email":
                return this.getDtAttributeName() + ".email";
            case "genero":
                return this.getDtAttributeName() + ".genero";
            case "fechaNacimiento":
                return this.getDtAttributeName() + ".fechaNacimiento";
            case "nacionalidad":
                return this.getDtAttributeName() + ".nacionalidad";
            case "phone":
                return this.getDtAttributeName() + ".celular";
            case "departamentoPaisResidencia":
                return this.getDtAttributeName() + ".departamentoPaisResidencia";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("DatosPersonalesCEP");
        this.setTextClassName(this.getText("Pagina.class.datosPersonalesCEP"));
        this.setTitleForm(this.getTextClassName());
    }

}
