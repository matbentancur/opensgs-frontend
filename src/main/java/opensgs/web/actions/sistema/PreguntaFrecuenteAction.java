package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtPreguntaFrecuente;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PreguntaFrecuenteAction",
        results = {
            @Result(name = "input", location = "preguntaFrecuente/preguntaFrecuenteForm.jsp"),
            @Result(name = "list", location = "preguntaFrecuente/preguntaFrecuenteList.jsp"),
            @Result(name = "view", location = "preguntaFrecuente/preguntaFrecuenteView.jsp"),
            @Result(name = "preguntasSistema", location = "preguntaFrecuente/preguntasSistema.jsp"),
            @Result(name = "preguntasAplicacion", location = "preguntaFrecuente/preguntasAplicacion.jsp")
        }
)
@AllowedMethods({
    "preguntasAplicacion",
    "preguntasSistema"
})
public class PreguntaFrecuenteAction extends AplicacionHijoAction {

    private DtPreguntaFrecuente dtPreguntaFrecuente;
    private List<DtPreguntaFrecuente> listaDtPreguntaFrecuentes;

    public DtPreguntaFrecuente getDtPreguntaFrecuente() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtPreguntaFrecuente) this.getDtOpenSGSManagedBean();
        } else {
            return dtPreguntaFrecuente;
        }
    }

    public void setDtPreguntaFrecuente(DtPreguntaFrecuente dtPreguntaFrecuente) {
        this.dtPreguntaFrecuente = dtPreguntaFrecuente;
    }

    public List<DtPreguntaFrecuente> getListaDtPreguntaFrecuentes() {
        return listaDtPreguntaFrecuentes;
    }

    public void setListaDtPreguntaFrecuentes(List<DtPreguntaFrecuente> listaDtPreguntaFrecuentes) {
        this.listaDtPreguntaFrecuentes = listaDtPreguntaFrecuentes;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.orden",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.pregunta",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.respuesta",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.alcance",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.pregunta",
                        minLength = "1",
                        maxLength = "512",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.respuesta",
                        minLength = "1",
                        maxLength = "1024",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.alcance",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtPreguntaFrecuente.orden",
                        min = "1",
                        max = "1000",
                        key = "OpenSGSBean.validator.intRange"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtPreguntaFrecuente.alcance",
                        expression = "dtPreguntaFrecuente.alcance in {'APLICACION','APLICACIONES','SISTEMA','GLOBAL'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtPreguntaFrecuente != null) {
            this.setDtOpenSGSBean(dtPreguntaFrecuente);
            this.setDtOpenSGSManagedBean(dtPreguntaFrecuente);
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
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().crearPreguntaFrecuenteAplicacion(this.getDtPreguntaFrecuente(), this.getAplicacionId(), this.getDtSesion());
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
        List<DtPreguntaFrecuente> lista = null;

        if (this.getAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarPreguntasAplicacion(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());
        } else {
            lista = this.getOpensgsWsPort().listarPreguntasSistema(this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtPreguntaFrecuentes(lista);
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
            Logger.getLogger(AnuncioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String preguntasSistema() throws Exception {
        this.setCommonTextsValues();
        this.setTitle(this.getText("OpenSGSBean.list.faqs"));
        List<DtPreguntaFrecuente> lista = null;
        lista = (List<DtPreguntaFrecuente>) this.getOpensgsWsPort().listarPreguntasSistemaActivas();
        this.setListaDtPreguntaFrecuentes(lista);
        return "preguntasSistema";
    }

    @SkipValidation
    public String preguntasAplicacion() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtAplicacion dtAplicacion = (DtAplicacion) this.obtenerDtOpenSGSManagedBean("Aplicacion", this.getAplicacionId());

        this.setCommonTextsValues();
        this.setTitle(this.getText("OpenSGSBean.list.faqs") + ": " + dtAplicacion.getTitulo());
        List<DtPreguntaFrecuente> lista = null;
        lista = this.getOpensgsWsPort().listarPreguntasAplicacionActivas(this.getAplicacionId(), this.getDtSesion());
        this.setListaDtPreguntaFrecuentes(lista);
        return "preguntasAplicacion";
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtPreguntaFrecuente";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtPreguntaFrecuente.nombre";
            case "order":
                return "dtPreguntaFrecuente.orden";
            case "question":
                return "dtPreguntaFrecuente.pregunta";
            case "answer":
                return "dtPreguntaFrecuente.respuesta";
            case "scope":
                return "dtPreguntaFrecuente.alcance";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("PreguntaFrecuente");
        this.setTextClassName(this.getText("OpenSGSBean.class.faq"));
        this.setTextListName(this.getText("OpenSGSBean.list.faqs"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

}
