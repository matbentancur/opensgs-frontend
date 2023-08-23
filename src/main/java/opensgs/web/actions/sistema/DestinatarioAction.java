package opensgs.web.actions.sistema;

import opensgs.web.actions.OpenSGSBeanAction;
import opensgs.web.webservices.DtDestinatario;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "DestinatarioAction",
        results = {
            @Result(name = "view", location = "destinatario/destinatarioView.jsp")
        }
)
public class DestinatarioAction extends OpenSGSBeanAction {

    private DtDestinatario dtDestinatario;

    public DtDestinatario getDtDestinatario() {
        if (this.getDtOpenSGSBean() != null) {
            return (DtDestinatario) this.getDtOpenSGSBean();
        } else {
            return dtDestinatario;
        }
    }

    public void setDtDestinatario(DtDestinatario dtDestinatario) {
        this.dtDestinatario = dtDestinatario;
    }

    @SkipValidation
    @Override
    public String ver() throws Exception {
        this.setCommonTextsValues();
        this.setTextObjectIdentifierName("id");
        return super.ver();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtDestinatario";
    }

    private void setCommonTextsValues() {
        this.setClassName("Destinatario");
        this.setTextClassName(this.getText("OpenSGSBean.class.recipient"));
    }

}
