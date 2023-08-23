package opensgs.web.actions.usuarios;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtPerfil;
import opensgs.web.webservices.DtUsuario;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "UsuarioAction",
        results = {
            @Result(name = "input", location = "usuario/usuarioForm.jsp"),
            @Result(name = "list", location = "usuario/usuarioList.jsp"),
            @Result(name = "view", location = "usuario/usuarioView.jsp"),
            @Result(name = "perfilesAplicacion", location = "usuario/perfilesAplicacion.jsp"),
            @Result(name = "perfilAplicacionView", location = "usuario/perfilAplicacionView.jsp")
        }
)
@AllowedMethods({
    "perfilesAplicacion",
    "perfilAplicacionVer",
    "agregar",
    "quitar"
})
public class UsuarioAction extends OpenSGSManagedBeanAction {

    private DtUsuario dtUsuario;
    private Long perfilId;
    private Long aplicacionId;

    public DtUsuario getDtUsuario() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtUsuario) this.getDtOpenSGSManagedBean();
        } else {
            return dtUsuario;
        }
    }

    public void setDtUsuario(DtUsuario dtUsuario) {
        this.dtUsuario = dtUsuario;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public Long getAplicacionId() {
        return aplicacionId;
    }

    public void setAplicacionId(Long aplicacionId) {
        this.aplicacionId = aplicacionId;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.email",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.documentoTipo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.documento",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.nombres",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.apellidos",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.telefono",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.email",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.documentoTipo",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.documento",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.nombres",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.apellidos",
                        minLength = "2",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.telefono",
                        minLength = "8",
                        maxLength = "16",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            emails = {
                @EmailValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtUsuario.email",
                        key = "OpenSGSBean.validator.email")
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtUsuario.documentoTipo",
                        expression = "dtUsuario.documentoTipo in {'DocumentoUruguayo','Pasaporte','DocumentoExtranjero'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtUsuario != null) {
            this.setDtOpenSGSBean(dtUsuario);
            this.setDtOpenSGSManagedBean(dtUsuario);
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
        this.setTextObjectIdentifierName("email");
        return super.ver();
    }

    @SkipValidation
    @Override
    public String form() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("email");
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
        return "dtUsuario";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "email":
                return this.getDtAttributeName() + ".email";
            case "documentType":
                return this.getDtAttributeName() + ".documentoTipo";
            case "document":
                return this.getDtAttributeName() + ".documento";
            case "names":
                return this.getDtAttributeName() + ".nombres";
            case "lastNames":
                return this.getDtAttributeName() + ".apellidos";
            case "phone":
                return this.getDtAttributeName() + ".telefono";
            case "profile":
                return this.getDtAttributeName() + ".dtPerfil.nombre";
            case "applicationProfile":
                return "perfilId";
            case "activeApplications":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("Usuario");
        this.setTextClassName(this.getText("OpenSGSBean.class.user"));
        this.setTextListName(this.getText("OpenSGSBean.list.users"));

        this.setChildClassName("PerfilAplicacion");
        this.setTextChildClassName(this.getText("OpenSGSBean.class.profile"));
        this.setTextObjectIdentifierName("email");
        this.setTextChildListName(this.getText("OpenSGSBean.list.profiles"));

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

    @SkipValidation
    public List<DtPerfil> listarPerfilesGlobales() throws Exception {
        List<DtPerfil> lista = null;
        lista = this.getOpensgsWsPort().listarPerfilesGlobales(this.getDtSesion());
        return lista;
    }

    @SkipValidation
    public List<DtPerfil> listarPerfilesAplicacion() throws Exception {
        List<DtPerfil> lista = null;
        lista = this.getOpensgsWsPort().listarPerfilesAplicacion(this.getDtSesion());
        return lista;
    }

    @SkipValidation
    public List<DtAplicacion> listarAplicacionesActivas() throws Exception {
        List<DtAplicacion> lista = null;
        lista = this.getOpensgsWsPort().listarAplicacionesActivas();
        return lista;
    }

    @SkipValidation
    public String perfilesAplicacion() throws Exception {
        if (this.getParentId() == null) {
            this.setParentId(this.getId());
        }
        this.setCommonTextsValues();
        this.setDtUsuario((DtUsuario) this.getDtOpenSGSManagedBean());
        super.listChild();
        return "perfilesAplicacion";
    }

    @SkipValidation
    public String perfilAplicacionVer() throws Exception {
        this.setDtOpenSGSBean(this.obtenerDtOpenSGSBean("PerfilAplicacion", this.getId()));
        this.setTitle(
                this.getText("OpenSGSBean.class.profile")
                + ": "
                + this.getDtOpenSGSBean().getId()
        );
        return "perfilAplicacionView";
    }

    @SkipValidation
    public String agregar() throws Exception {
        this.setCommonTextsValues();
        DtMensaje dtMensaje = this.getOpensgsWsPort().agregarPerfilAplicacion(this.getDtUsuario(), this.getPerfilId(), this.getAplicacionId(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        this.setAplicacionId(null);
        return this.perfilesAplicacion();
    }

    @SkipValidation
    public String quitar() throws Exception {
        this.setCommonTextsValues();
        if (this.getChildClassName().equals("PerfilAplicacion")) {
            this.setChildClassName("PerfilAplicacion");
            super.removeChild();
        }
        return this.perfilesAplicacion();
    }

}
