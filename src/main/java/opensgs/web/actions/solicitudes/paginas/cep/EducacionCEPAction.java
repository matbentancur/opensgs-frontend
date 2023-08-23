package opensgs.web.actions.solicitudes.paginas.cep;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.solicitudes.PaginaSolicitudAction;
import opensgs.web.webservices.DtEducacionCEP;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "EducacionCEPAction",
        results = {
            @Result(name = "input", location = "educacionCEP/educacionCEPForm.jsp"),
            @Result(name = "view", location = "educacionCEP/educacionCEPView.jsp")
        }
)
public class EducacionCEPAction extends PaginaSolicitudAction {

    private DtEducacionCEP dtEducacionCEP;

    public DtEducacionCEP getDtEducacionCEP() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtEducacionCEP) this.getDtOpenSGSManagedBean();
        } else {
            return dtEducacionCEP;
        }
    }

    public void setDtEducacionCEP(DtEducacionCEP dtEducacionCEP) {
        this.dtEducacionCEP = dtEducacionCEP;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.ultimoNivelEducativo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.tituloGrado",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.anioRecibio",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.ultimoNivelEducativo",
                        minLength = "2",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.tituloGrado",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.anioRecibio",
                        minLength = "1",
                        maxLength = "20",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtEducacionCEP.institucionTitulo",
                        minLength = "0",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtEducacionCEP != null) {
            this.setDtOpenSGSBean(dtEducacionCEP);
            this.setDtOpenSGSManagedBean(dtEducacionCEP);
            this.setDtPaginaSolicitud(dtEducacionCEP);
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
        return "dtEducacionCEP";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "ultimoNivelEducativo":
                return this.getDtAttributeName() + ".ultimoNivelEducativo";
            case "tituloGrado":
                return this.getDtAttributeName() + ".tituloGrado";
            case "anioRecibio":
                return this.getDtAttributeName() + ".anioRecibio";
            case "institucionTitulo":
                return this.getDtAttributeName() + ".institucionTitulo";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("EducacionCEP");
        this.setTextClassName(this.getText("Pagina.class.educacionCEP"));
        this.setTitleForm(this.getTextClassName());
    }

}
