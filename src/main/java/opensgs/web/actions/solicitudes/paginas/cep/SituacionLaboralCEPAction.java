package opensgs.web.actions.solicitudes.paginas.cep;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.solicitudes.PaginaSolicitudAction;
import opensgs.web.webservices.DtSituacionLaboralCEP;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "SituacionLaboralCEPAction",
        results = {
            @Result(name = "input", location = "situacionLaboralCEP/situacionLaboralCEPForm.jsp"),
            @Result(name = "view", location = "situacionLaboralCEP/situacionLaboralCEPView.jsp")
        }
)
public class SituacionLaboralCEPAction extends PaginaSolicitudAction {

    private DtSituacionLaboralCEP dtSituacionLaboralCEP;

    public DtSituacionLaboralCEP getDtSituacionLaboralCEP() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtSituacionLaboralCEP) this.getDtOpenSGSManagedBean();
        } else {
            return dtSituacionLaboralCEP;
        }
    }

    public void setDtSituacionLaboralCEP(DtSituacionLaboralCEP dtSituacionLaboralCEP) {
        this.dtSituacionLaboralCEP = dtSituacionLaboralCEP;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSituacionLaboralCEP.situacion",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSituacionLaboralCEP.situacion",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSituacionLaboralCEP.departamentoTrabaja",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtSituacionLaboralCEP.lugarTrabajo",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtSituacionLaboralCEP != null) {
            this.setDtOpenSGSBean(dtSituacionLaboralCEP);
            this.setDtOpenSGSManagedBean(dtSituacionLaboralCEP);
            this.setDtPaginaSolicitud(dtSituacionLaboralCEP);
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
        return "dtSituacionLaboralCEP";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "situacion":
                return this.getDtAttributeName() + ".situacion";
            case "departamentoTrabaja":
                return this.getDtAttributeName() + ".departamentoTrabaja";
            case "lugarTrabajo":
                return this.getDtAttributeName() + ".lugarTrabajo";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("SituacionLaboralCEP");
        this.setTextClassName(this.getText("Pagina.class.situacionLaboralCEP"));
        this.setTitleForm(this.getTextClassName());
    }

}
