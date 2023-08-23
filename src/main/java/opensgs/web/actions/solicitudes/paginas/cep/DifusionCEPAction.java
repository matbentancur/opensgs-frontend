package opensgs.web.actions.solicitudes.paginas.cep;

import java.util.ArrayList;
import java.util.List;
import opensgs.web.actions.solicitudes.PaginaSolicitudAction;
import opensgs.web.webservices.DtDifusionCEP;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "DifusionCEPAction",
        results = {
            @Result(name = "input", location = "difusionCEP/difusionCEPForm.jsp"),
            @Result(name = "view", location = "difusionCEP/difusionCEPView.jsp")
        }
)
public class DifusionCEPAction extends PaginaSolicitudAction {

    private DtDifusionCEP dtDifusionCEP;
    private List<String> medios;

    public DifusionCEPAction() {
        dtDifusionCEP = new DtDifusionCEP();
        medios = new ArrayList<String>();
    }

    public DtDifusionCEP getDtDifusionCEP() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtDifusionCEP) this.getDtOpenSGSManagedBean();
        } else {
            return dtDifusionCEP;
        }
    }

    public void setDtDifusionCEP(DtDifusionCEP dtDifusionCEP) {
        this.dtDifusionCEP = dtDifusionCEP;
    }

    public List<String> getMedios() {
        return medios;
    }

    public void setMedios(List<String> medios) {
        this.medios = medios;
    }

    @Override
    public String execute() throws Exception {
        if (dtDifusionCEP != null) {
            this.setDtOpenSGSBean(dtDifusionCEP);
            this.setDtOpenSGSManagedBean(dtDifusionCEP);
            this.setDtPaginaSolicitud(dtDifusionCEP);
        }
        if (!this.getMedios().isEmpty()) {
            List<String> listaMedios = this.getDtDifusionCEP().getMedios();
            for (String medio : this.getMedios()) {
                listaMedios.add(medio);
            }
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
        this.setDtOpenSGSManagedBean(this.obtenerDtPaginaSolicitud(this.getClassName(), this.getId()));
        this.setComando("modificar");

        if (!this.getDtDifusionCEP().getMedios().isEmpty()) {
            List<String> listaMedios = this.getMedios();
            for (String medio : this.getDtDifusionCEP().getMedios()) {
                listaMedios.add(medio);
            }
        }

        return INPUT;
    }

    @Override
    public void validate() {
        this.setCommonTextsValues();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtDifusionCEP";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "medios":
//                return this.getDtAttributeName() + ".medios";
                return "medios";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("DifusionCEP");
        this.setTextClassName(this.getText("Pagina.class.difusionCEP"));
        this.setTitleForm(this.getTextClassName());
    }

}
