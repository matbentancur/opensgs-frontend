package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.web.webservices.DtConstanciaElementoTexto;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ConstanciaElementoTextoAction",
        results = {
            @Result(name = "input", location = "constanciaElementoTexto/constanciaElementoTextoForm.jsp"),
            @Result(name = "list", location = "constanciaElementoTexto/constanciaElementoTextoList.jsp"),
            @Result(name = "view", location = "constanciaElementoTexto/constanciaElementoTextoView.jsp")}
)
public class ConstanciaElementoTextoAction extends ConstanciaHijoAction {

    private DtConstanciaElementoTexto dtConstanciaElementoTexto;
    private List<DtConstanciaElementoTexto> listaDtConstanciaElementoTexto;

    public DtConstanciaElementoTexto getDtConstanciaElementoTexto() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtConstanciaElementoTexto) this.getDtOpenSGSManagedBean();
        } else {
            return dtConstanciaElementoTexto;
        }
    }

    public void setDtConstanciaElementoTexto(DtConstanciaElementoTexto dtConstanciaElementoTexto) {
        this.dtConstanciaElementoTexto = dtConstanciaElementoTexto;
    }

    public List<DtConstanciaElementoTexto> getListaDtConstanciaElementoTexto() {
        return listaDtConstanciaElementoTexto;
    }

    public void setListaDtConstanciaElementoTexto(List<DtConstanciaElementoTexto> listaDtConstanciaElementoTexto) {
        this.listaDtConstanciaElementoTexto = listaDtConstanciaElementoTexto;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.posicionX",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.posicionY",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.texto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.fuenteTamanio",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.fuenteTipo",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.texto",
                        minLength = "1",
                        maxLength = "1024",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtConstanciaElementoTexto != null) {
            this.setDtOpenSGSBean(dtConstanciaElementoTexto);
            this.setDtOpenSGSManagedBean(dtConstanciaElementoTexto);
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
        List<DtConstanciaElementoTexto> lista = null;

        if (this.getConstanciaId() != null) {
            lista = this.getOpensgsWsPort().listarConstanciaElementoTexto(this.getConstanciaId(), this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtConstanciaElementoTexto(lista);
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
            Logger.getLogger(ConstanciaElementoTextoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtConstanciaElementoTexto";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "positionX":
                return this.getDtAttributeName() + ".posicionX";
            case "positionY":
                return this.getDtAttributeName() + ".posicionY";
            case "text":
                return this.getDtAttributeName() + ".texto";
            case "fontSize":
                return this.getDtAttributeName() + ".fuenteTamanio";
            case "fontType":
                return this.getDtAttributeName() + ".fuenteTipo";
            case "certificateId":
                return this.getDtAttributeName() + ".dtConstancia.id";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("ConstanciaElementoTexto");
        this.setTextClassName(this.getText("OpenSGSBean.class.certificateTextElement"));
        this.setTextListName(this.getText("OpenSGSBean.list.certificateTextElements"));

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());

        super.setCertificateTextsValues();
    }

}
