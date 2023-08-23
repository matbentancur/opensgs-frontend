package opensgs.web.actions.sistema;

import java.util.List;
import opensgs.web.actions.OpenSGSBeanAction;
import opensgs.web.webservices.DtActividad;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ActividadAction",
        results = {
            @Result(name = "list", location = "actividad/actividadList.jsp"),
            @Result(name = "view", location = "actividad/actividadView.jsp"),
            @Result(name = "actividad", location = "actividad/actividad.jsp")
        }
)
public class ActividadAction extends OpenSGSBeanAction {

    private DtActividad dtActividad;
    private List<DtActividad> listaActividad;
    private String inicioString;
    private String finString;

    public DtActividad getDtActividad() {
        if (this.getDtOpenSGSBean() != null) {
            return (DtActividad) this.getDtOpenSGSBean();
        } else {
            return dtActividad;
        }
    }

    public void setDtActividad(DtActividad dtActividad) {
        this.dtActividad = dtActividad;
    }

    public List<DtActividad> getListaActividad() {
        return listaActividad;
    }

    public void setListaActividad(List<DtActividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public String getInicioString() {
        return inicioString;
    }

    public void setInicioString(String inicioString) {
        this.inicioString = inicioString;
    }

    public String getFinString() {
        return finString;
    }

    public void setFinString(String finString) {
        this.finString = finString;
    }

    @SkipValidation
    @Override
    public String ver() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("id");
        return super.ver();
    }

    @SkipValidation
    @Override
    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setTitleForm(this.getText("OpenSGSBean.title.searchActivity"));
        this.setTitle(this.getTextListName() + ":");

        List<DtActividad> lista = null;

        if (this.getInicioString() != null && this.getFinString() != null) {
            lista = this.getOpensgsWsPort().listarActividadTemporal(this.getInicioString(), this.getFinString(), this.getDtSesion());
            System.out.println("Lista: " + lista);
        }

        this.setListaActividad(lista);
        return "list";
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtActividad";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "start":
                return "inicioString";
            case "finish":
                return "finString";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() {
        this.setClassName("Actividad");
        this.setTextClassName(this.getText("OpenSGSBean.class.activity"));
        this.setTextListName(this.getText("OpenSGSBean.list.activitys"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
    }

}
