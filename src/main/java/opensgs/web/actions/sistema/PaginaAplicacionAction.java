package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtPaginaAplicacion;
import opensgs.web.webservices.DtPlantillaAplicacion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PaginaAplicacionAction",
        results = {
            @Result(name = "input", location = "paginaAplicacion/paginaAplicacionForm.jsp"),
            @Result(name = "list", location = "paginaAplicacion/paginaAplicacionList.jsp"),
            @Result(name = "view", location = "paginaAplicacion/paginaAplicacionView.jsp")
        }
)
public class PaginaAplicacionAction extends OpenSGSManagedBeanAction {

    private DtPaginaAplicacion dtPaginaAplicacion;
    private List<DtPaginaAplicacion> listaDtPaginasAplicacion;
    private Long plantillaAplicacionId;

    public DtPaginaAplicacion getDtPaginaAplicacion() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtPaginaAplicacion) this.getDtOpenSGSManagedBean();
        } else {
            return dtPaginaAplicacion;
        }
    }

    public void setDtPaginaAplicacion(DtPaginaAplicacion dtPaginaAplicacion) {
        this.dtPaginaAplicacion = dtPaginaAplicacion;
    }

    public List<DtPaginaAplicacion> getListaDtPaginasAplicacion() {
        return listaDtPaginasAplicacion;
    }

    public void setListaDtPaginasAplicacion(List<DtPaginaAplicacion> listaDtPaginasAplicacion) {
        this.listaDtPaginasAplicacion = listaDtPaginasAplicacion;
    }

    public Long getPlantillaAplicacionId() {
        return plantillaAplicacionId;
    }

    public void setPlantillaAplicacionId(Long plantillaAplicacionId) {
        this.plantillaAplicacionId = plantillaAplicacionId;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.titulo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.esctructuraDatos",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.titulo",
                        minLength = "1",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.textoInicial",
                        minLength = "0",
                        maxLength = "1000",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.textoFinal",
                        minLength = "0",
                        maxLength = "1000",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPaginaAplicacion.esctructuraDatos",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtPaginaAplicacion != null) {
            this.setDtOpenSGSBean(dtPaginaAplicacion);
            this.setDtOpenSGSManagedBean(dtPaginaAplicacion);
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
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.cargarTextosLista();

        List<DtPaginaAplicacion> lista = null;

        if (this.getPlantillaAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarPaginasAplicacionPlantillaAplicacion(this.getPlantillaAplicacionId(), this.isBorrado(), this.getDtSesion());
            this.setListaDtPaginasAplicacion(lista);
        } else {
            super.listar();
        }

        return "list";
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
        try {
            this.setCommonTextsValues();
        } catch (Exception ex) {
            Logger.getLogger(PaginaAplicacionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtPaginaAplicacion";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtPaginaAplicacion.nombre";
            case "title":
                return "dtPaginaAplicacion.titulo";
            case "initialText":
                return "dtPaginaAplicacion.textoInicial";
            case "finalText":
                return "dtPaginaAplicacion.textoFinal";
            case "page":
                return "dtPaginaAplicacion.pagina";
            case "dataStructure":
                return "dtPaginaAplicacion.esctructuraDatos";
            case "applicationTemplateId":
                return "dtPaginaAplicacion.dtPlantillaAplicacion.id";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("PaginaAplicacion");
        this.setTextClassName(this.getText("OpenSGSBean.class.applicationPage"));
        this.setTextListName(this.getText("OpenSGSBean.list.applicationPages"));

        this.setParentClassName("PlantillaAplicacion");
        this.setTextParentClassName(this.getText("OpenSGSBean.class.applicationTemplate"));
        this.setTextParentListName(this.getText("OpenSGSBean.list.applicationTemplates"));
        this.setParentParam("plantillaaplicacion_id");

        if (this.getPlantillaAplicacionId() != null) {
            DtPlantillaAplicacion dtPlantillaAplicacion = (DtPlantillaAplicacion) this.obtenerDtOpenSGSManagedBean("PlantillaAplicacion", this.getPlantillaAplicacionId());
            this.setTextListName(this.getText("OpenSGSBean.list.applicationPages") + " " + dtPlantillaAplicacion.getNombre());
            this.setTitleForm(" " + dtPlantillaAplicacion.getNombre());
        } else {
            this.setTextListName(this.getText("OpenSGSBean.list.applicationPages"));
        }

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

}
