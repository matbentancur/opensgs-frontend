package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.webservices.DtAnuncio;
import opensgs.web.webservices.DtMensaje;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "AnuncioAction",
        results = {
            @Result(name = "input", location = "anuncio/anuncioForm.jsp"),
            @Result(name = "list", location = "anuncio/anuncioList.jsp"),
            @Result(name = "view", location = "anuncio/anuncioView.jsp")
        }
)
public class AnuncioAction extends AplicacionHijoAction {

    private DtAnuncio dtAnuncio;
    private List<DtAnuncio> listaDtAnuncios;

    public DtAnuncio getDtAnuncio() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtAnuncio) this.getDtOpenSGSManagedBean();
        } else {
            return dtAnuncio;
        }
    }

    public void setDtAnuncio(DtAnuncio dtAnuncio) {
        this.dtAnuncio = dtAnuncio;
    }

    public List<DtAnuncio> getListaDtAnuncios() {
        return listaDtAnuncios;
    }

    public void setListaDtAnuncios(List<DtAnuncio> listaDtAnuncios) {
        this.listaDtAnuncios = listaDtAnuncios;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.texto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.inicio",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.fin",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.alcance",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.texto",
                        minLength = "1",
                        maxLength = "512",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.inicio",
                        minLength = "1",
                        maxLength = "20",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.fin",
                        minLength = "1",
                        maxLength = "20",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtAnuncio.alcance",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtAnuncio.alcance",
                        expression = "dtAnuncio.alcance in {'APLICACION','APLICACIONES','SISTEMA','GLOBAL'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtAnuncio != null) {
            this.setDtOpenSGSBean(dtAnuncio);
            this.setDtOpenSGSManagedBean(dtAnuncio);
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
    public String crear() throws Exception {
        if (this.getAplicacionId() != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().crearAnuncioAplicacion(this.getDtAnuncio(), this.getAplicacionId(), this.getDtSesion());
            this.setDtMensaje(dtMensaje);
            if (dtMensaje.isExito()) {
                return this.listar();
            }
            return INPUT;
        }
        return super.crear();
    }

    @SkipValidation
    @Override
    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.cargarTextosLista();
        List<DtAnuncio> lista = null;

        if (this.getAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarAnunciosAplicacion(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());
        } else {
            lista = this.getOpensgsWsPort().listarAnunciosSistema(this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtAnuncios(lista);
        return "list";
    }

    @SkipValidation
    @Override
    public String modificar() throws Exception {
        this.setCommonTextsValues();
        return super.modificar();
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
            Logger.getLogger(AnuncioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtAnuncio";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "text":
                return this.getDtAttributeName() + ".texto";
            case "start":
                return this.getDtAttributeName() + ".inicio";
            case "finish":
                return this.getDtAttributeName() + ".fin";
            case "scope":
                return this.getDtAttributeName() + ".alcance";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Anuncio");
        this.setTextClassName(this.getText("OpenSGSBean.class.announcement"));
        this.setTextListName(this.getText("OpenSGSBean.list.announcements"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

}
