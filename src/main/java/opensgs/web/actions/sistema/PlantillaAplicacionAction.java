package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtPlantillaAplicacion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PlantillaAplicacionAction",
        results = {
            @Result(name = "input", location = "plantillaAplicacion/plantillaAplicacionForm.jsp"),
            @Result(name = "list", location = "plantillaAplicacion/plantillaAplicacionList.jsp"),
            @Result(name = "view", location = "plantillaAplicacion/plantillaAplicacionView.jsp"),
            @Result(name = "paginasAplicacion", location = "plantillaAplicacion/paginasAplicacion.jsp")
        }
)
@AllowedMethods({
    "paginasAplicacion",
    "agregar",
    "quitar"
})
public class PlantillaAplicacionAction extends OpenSGSManagedBeanAction {

    private DtPlantillaAplicacion dtPlantillaAplicacion;

    public DtPlantillaAplicacion getDtPlantillaAplicacion() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtPlantillaAplicacion) this.getDtOpenSGSManagedBean();
        } else {
            return dtPlantillaAplicacion;
        }
    }

    public void setDtPlantillaAplicacion(DtPlantillaAplicacion dtPlantillaAplicacion) {
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaAplicacion.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaAplicacion.textoAcuerdo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaAplicacion.textoEntrega",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaAplicacion.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaAplicacion.textoAcuerdo",
                        minLength = "1",
                        maxLength = "1024",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPlantillaAplicacion.textoEntrega",
                        minLength = "1",
                        maxLength = "1024",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtPlantillaAplicacion != null) {
            this.setDtOpenSGSBean(dtPlantillaAplicacion);
            this.setDtOpenSGSManagedBean(dtPlantillaAplicacion);
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
        return "dtPlantillaAplicacion";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtPlantillaAplicacion.nombre";
            case "agreementText":
                return "dtPlantillaAplicacion.textoAcuerdo";
            case "deliveredText":
                return "dtPlantillaAplicacion.textoEntrega";
            case "applicationPage":
                return "childId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("PlantillaAplicacion");
        this.setTextClassName(this.getText("OpenSGSBean.class.applicationTemplate"));
        this.setTextListName(this.getText("OpenSGSBean.list.applicationTemplates"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

//    @SkipValidation
//    public String paginasAplicacion() throws Exception {
//        this.setCommonTextsValues();
//        this.setChildClassName("PaginaAplicacion");
//        this.setTextChildClassName(this.getText("OpenSGSBean.class.applicationPage"));
//        this.setTextObjectIdentifierName("nombre");
//        this.setTextChildListName(this.getText("OpenSGSBean.list.applicationPages"));
//        this.setDtPlantillaAplicacion((DtPlantillaAplicacion) this.getDtOpenSGSManagedBean());
//        super.listChild();
//        return "paginasAplicacion";
//    }
//    @SkipValidation
//    public String agregar() throws Exception {
//        this.setCommonTextsValues();
//        if (this.getChildClassName().equals("PaginaAplicacion")) {
//            this.setChildClassName("PaginaAplicacion");
//            this.setId(this.getDtPlantillaAplicacion().getId());
//            super.addChild();
//        }
//        return this.paginasAplicacion();
//    }
//
//    @SkipValidation
//    public String quitar() throws Exception {
//        this.setCommonTextsValues();
//        if (this.getChildClassName().equals("PaginaAplicacion")) {
//            this.setChildClassName("PaginaAplicacion");
//            super.removeChild();
//        }
//        return this.paginasAplicacion();
//    }
//    @SkipValidation
//    public List<DtPaginaAplicacion> listarPaginasAplicacionFormField() throws Exception {
//        List<DtPaginaAplicacion> lista = null;
//        lista = this.getOpensgsWsPort().listarPaginasAplicacionFormField(this.getDtSesion());
//        return lista;
//    }
}
