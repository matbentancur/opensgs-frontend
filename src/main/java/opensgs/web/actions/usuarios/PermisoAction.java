package opensgs.web.actions.usuarios;

import opensgs.web.actions.OpenSGSBeanAction;
import opensgs.web.webservices.DtPermiso;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "PermisoAction",
        results = {
            @Result(name = "view", location = "permiso/permisoView.jsp")
        }
)
public class PermisoAction extends OpenSGSBeanAction {

    private DtPermiso dtPermiso;

    public DtPermiso getDtPermiso() {
        if (this.getDtOpenSGSBean() != null) {
            return (DtPermiso) this.getDtOpenSGSBean();
        } else {
            return dtPermiso;
        }
    }

    public void setDtPermiso(DtPermiso dtPermiso) {
        this.dtPermiso = dtPermiso;
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
        return "dtPermiso";
    }

    private void setCommonTextsValues() {
        this.setClassName("Permiso");
        this.setTextClassName(this.getText("OpenSGSBean.class.permission"));
    }

}
