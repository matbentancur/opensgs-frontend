package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtPlantillaCorreo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PlantillaCorreoAction",
        results = {
            @Result(name = "input", location = "plantillaCorreo/plantillaCorreoForm.jsp"),
            @Result(name = "list", location = "plantillaCorreo/plantillaCorreoList.jsp"),
            @Result(name = "view", location = "plantillaCorreo/plantillaCorreoView.jsp")
        }
)
public class PlantillaCorreoAction extends OpenSGSManagedBeanAction {

    private DtPlantillaCorreo dtPlantillaCorreo;

    public DtPlantillaCorreo getDtPlantillaCorreo() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtPlantillaCorreo) this.getDtOpenSGSManagedBean();
        } else {
            return dtPlantillaCorreo;
        }
    }

    public void setDtPlantillaCorreo(DtPlantillaCorreo dtPlantillaCorreo) {
        this.dtPlantillaCorreo = dtPlantillaCorreo;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.asunto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.texto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.adjunto",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.asunto",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.texto",
                        minLength = "1",
                        maxLength = "1024",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaCorreo.adjunto",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtPlantillaCorreo.adjunto",
                        expression = "dtPlantillaCorreo.adjunto in {'NINGUNO','SOLICITUD_PDF'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtPlantillaCorreo != null) {
            this.setDtOpenSGSBean(dtPlantillaCorreo);
            this.setDtOpenSGSManagedBean(dtPlantillaCorreo);
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
    public String actividadVer() throws Exception {
        this.setCommonTextsValues();
        return super.actividadVer();
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

    @Override
    public void validate() {
        this.setCommonTextsValues();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtPlantillaCorreo";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtPlantillaCorreo.nombre";
            case "subject":
                return "dtPlantillaCorreo.asunto";
            case "text":
                return "dtPlantillaCorreo.texto";
            case "attachment":
                return "dtPlantillaCorreo.adjunto";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("PlantillaCorreo");
        this.setTextClassName(this.getText("OpenSGSBean.class.mailTemplate"));
        this.setTextListName(this.getText("OpenSGSBean.list.mailTemplates"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

}
