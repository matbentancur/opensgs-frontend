package opensgs.web.actions.usuarios;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtPerfil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PerfilAction",
        results = {
            @Result(name = "input", location = "perfil/perfilForm.jsp"),
            @Result(name = "list", location = "perfil/perfilList.jsp"),
            @Result(name = "view", location = "perfil/perfilView.jsp"),
            @Result(name = "permisos", location = "perfil/permisos.jsp"),
            @Result(name = "permisoView", location = "perfil/permisoView.jsp")
        }
)
@AllowedMethods({
    "permisos",
    "permisoVer",
    "agregar",
    "quitar"
})
public class PerfilAction extends OpenSGSManagedBeanAction {

    private DtPerfil dtPerfil;

    public DtPerfil getDtPerfil() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtPerfil) this.getDtOpenSGSManagedBean();
        } else {
            return dtPerfil;
        }
    }

    public void setDtPerfil(DtPerfil dtPerfil) {
        this.dtPerfil = dtPerfil;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPerfil.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPerfil.alcancePerfil",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPerfil.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (dtPerfil != null) {
            this.setDtOpenSGSBean(dtPerfil);
            this.setDtOpenSGSManagedBean(dtPerfil);
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
        return "dtPerfil";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "profileScope":
                return this.getDtAttributeName() + ".alcancePerfil";
            case "permission":
                return "childId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("Perfil");
        this.setTextClassName(this.getText("OpenSGSBean.class.profile"));
        this.setTextListName(this.getText("OpenSGSBean.list.profiles"));

        this.setChildClassName("Permiso");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.permission"));
        this.setTextObjectIdentifierName("nombre");
        this.setTextChildListName(this.getText("OpenSGSBean.list.permissions"));

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

    @SkipValidation
    public String permisos() throws Exception {
        if (this.getParentId() == null) {
            this.setParentId(this.getId());
        }
        this.setCommonTextsValues();
        this.setDtPerfil((DtPerfil) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "permisos";
    }

    @SkipValidation
    public String permisoVer() throws Exception {
        this.setDtOpenSGSBean(this.obtenerDtOpenSGSBean("Permiso", this.getId()));
        this.setTitle(
                this.getText("OpenSGSBean.class.permission")
                + ": "
                + this.getDtOpenSGSBean().getId()
        );
        return "permisoView";
    }

    @SkipValidation
    public String agregar() throws Exception {
        this.setCommonTextsValues();
        if (this.getChildClassName().equals("Permiso")) {
            this.setChildClassName("Permiso");
            this.setId(this.getDtPerfil().getId());
            super.addChild();
        }
        return this.permisos();
    }

    @SkipValidation
    public String quitar() throws Exception {
        this.setCommonTextsValues();
        if (this.getChildClassName().equals("Permiso")) {
            this.setChildClassName("Permiso");
            super.removeChild();
        }
        return this.permisos();
    }

}
