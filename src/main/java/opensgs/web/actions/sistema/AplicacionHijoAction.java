package opensgs.web.actions.sistema;

import java.util.List;
import opensgs.web.actions.OpenSGSManagedBeanAction;
import opensgs.web.webservices.DtActividad;
import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtOpenSGSBean;
import opensgs.web.webservices.DtOpenSGSManagedBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public abstract class AplicacionHijoAction extends OpenSGSManagedBeanAction {

    private DtAplicacion dtAplicacion;
    private Long aplicacionId;
    private String textApplicationClassName;
    private String textApplicationListName;
    private String textApplicationNavigation;

    public DtAplicacion getDtAplicacion() {
        return dtAplicacion;
    }

    public void setDtAplicacion(DtAplicacion dtAplicacion) {
        this.dtAplicacion = dtAplicacion;
    }

    public Long getAplicacionId() {
        return aplicacionId;
    }

    public void setAplicacionId(Long aplicacionId) {
        this.aplicacionId = aplicacionId;
    }

    public String getTextApplicationClassName() {
        return textApplicationClassName;
    }

    public void setTextApplicationClassName(String textApplicationClassName) {
        this.textApplicationClassName = textApplicationClassName;
    }

    public String getTextApplicationListName() {
        return textApplicationListName;
    }

    public void setTextApplicationListName(String textApplicationListName) {
        this.textApplicationListName = textApplicationListName;
    }

    public String getTextApplicationNavigation() {
        return textApplicationNavigation;
    }

    public void setTextApplicationNavigation(String textApplicationNavigation) {
        this.textApplicationNavigation = textApplicationNavigation;
    }

    public void setApplicationTextsValues() throws Exception {
        if (this.getAplicacionId() != null) {
//            this.setParentClassName("Aplicacion");
//            this.setTextParentClassName(this.getText("OpenSGSBean.class.application"));
//            this.setTextParentListName(this.getText("OpenSGSBean.list.applications"));
            this.setTextApplicationClassName(this.getText("OpenSGSBean.class.application"));
            this.setTextApplicationListName(this.getText("OpenSGSBean.list.applications"));

            DtAplicacion dtA = this.obtenerDatosBasicosAplicacion(this.getAplicacionId());
            this.setDtAplicacion(dtA);
            this.setTextApplicationNavigation(this.getTextApplicationListName());
            this.setTextNavigation(this.getTextListName() + " " + this.getDtAplicacion().getNombre());
            this.setTitleForm(" " + this.getDtAplicacion().getNombre());
            this.setTitleList(this.getTitleList() + " " + this.getDtAplicacion().getNombre());
        }
    }

    public DtAplicacion obtenerDatosBasicosAplicacion(Long aplicacionId) throws Exception {
        DtAplicacion dtA = this.getOpensgsWsPort().obtenerDatosBasicosAplicacion(aplicacionId, this.getDtSesion());
        return dtA;
    }

    public DtOpenSGSBean obtenerDtOpenSGSBeanAplicacion(String className, Long id, Long aplicacionId) throws Exception {
        DtOpenSGSBean dtOB = (DtOpenSGSBean) this.getOpensgsWsPort().obtenerOpenSGSBeanAplicacion(className, id, aplicacionId, this.getDtSesion());
        return dtOB;
    }

    public DtOpenSGSManagedBean obtenerDtOpenSGSManagedBeanAplicacion(String className, Long id, Long aplicacionId) throws Exception {
        DtOpenSGSManagedBean dtOMB = (DtOpenSGSManagedBean) this.obtenerDtOpenSGSBeanAplicacion(className, id, aplicacionId);
        return dtOMB;
    }

    @Override
    public String ver() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSBean dtOSB = this.obtenerDtOpenSGSBeanAplicacion(this.getClassName(), this.getId(), this.getAplicacionId());
            if (dtOSB != null) {
                this.setDtOpenSGSBean(dtOSB);
                this.setDtOpenSGSManagedBean((DtOpenSGSManagedBean) this.getDtOpenSGSBean());
                String identifierValue = BeanUtilsBean.getInstance().getProperty(this.getDtOpenSGSManagedBean(), this.getTextObjectIdentifierName());
                this.setTitle(
                        this.getTextClassName()
                        + ": "
                        + identifierValue
                );
            }
            return "view";
        }
        return super.ver();
    }

    @Override
    public String form() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBeanAplicacion(this.getClassName(), this.getId(), this.getAplicacionId());
            this.setDtOpenSGSManagedBean(dtOMB);
            String identifierValue = BeanUtilsBean.getInstance().getProperty(this.getDtOpenSGSManagedBean(), this.getTextObjectIdentifierName());
            this.setTitleForm(
                    this.getText("OpenSGSBean.text.modify")
                    + " "
                    + this.getTextClassName()
                    + ": "
                    + identifierValue
            );
            this.setComando("modificar");
            return INPUT;
        }
        return super.form();
    }

    public String modificar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().modificarOpenSGSManagedBeanAplicacion(this.getDtOpenSGSManagedBean(), this.getAplicacionId(), this.getDtSesion());
            this.setDtMensaje(dtMensaje);
            if (dtMensaje.isExito()) {
                return this.listar();
            }
            return INPUT;
        }
        return super.modificar();
    }

    @Override
    public String borrar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBeanAplicacion(this.getClassName(), this.getId(), this.getAplicacionId());
            if (dtOMB != null) {
                DtMensaje dtMensaje = this.getOpensgsWsPort().borrarOpenSGSManagedBeanAplicacion(dtOMB, this.getAplicacionId(), this.getDtSesion());
                this.setDtMensaje(dtMensaje);
            }
            return this.listar();
        }
        return super.borrar();
    }

    @Override
    public String restaurar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBeanAplicacion(this.getClassName(), this.getId(), this.getAplicacionId());
            if (dtOMB != null) {
                DtMensaje dtMensaje = this.getOpensgsWsPort().restaurarOpenSGSManagedBeanAplicacion(dtOMB, this.getAplicacionId(), this.getDtSesion());
                this.setDtMensaje(dtMensaje);
            }

            return this.listar();
        }
        return super.restaurar();
    }

    @Override
    public String activar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBeanAplicacion(this.getClassName(), this.getId(), this.getAplicacionId());
            if (dtOMB != null) {
                DtMensaje dtMensaje = this.getOpensgsWsPort().activarOpenSGSManagedBeanAplicacion(dtOMB, this.getAplicacionId(), this.getDtSesion());
                this.setDtMensaje(dtMensaje);
            }
            return this.listar();
        }
        return super.activar();
    }

    @Override
    public String desactivar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBeanAplicacion(this.getClassName(), this.getId(), this.getAplicacionId());
            if (dtOMB != null) {
                DtMensaje dtMensaje = this.getOpensgsWsPort().desactivarOpenSGSManagedBeanAplicacion(dtOMB, this.getAplicacionId(), this.getDtSesion());
                this.setDtMensaje(dtMensaje);
            }
            return this.listar();
        }
        return super.desactivar();
    }

    @Override
    public String actividad() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            if (this.getParentId() == null) {
                this.setParentId(this.getId());
            }
            this.cargarTextosActividad();
            List<DtActividad> lista = null;
            lista = this.getOpensgsWsPort().listarActividadElementoAplicacion(this.getClassName(), this.getId(), this.getAplicacionId(), this.getDtSesion());
            this.setListaActividad(lista);
            return "actividad";
        }
        return super.actividad();
    }

    @Override
    public String actividadVer() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getAplicacionId() != null) {
            DtOpenSGSBean dtOSB = this.obtenerDtOpenSGSBeanAplicacion("Actividad", this.getActividadId(), this.getAplicacionId());
            if (dtOSB != null) {
                this.setDtOpenSGSBean(dtOSB);
                this.setTitle(
                        this.getText("OpenSGSBean.class.activity")
                        + ": "
                        + this.getDtOpenSGSBean().getId()
                );
                this.setTextActivityNavigation(
                        this.getText("OpenSGSBean.text.activity")
                        + " "
                        + this.getTextClassName()
                        + " "
                        + this.getParentId()
                );
            }

            return "actividadVer";
        }
        return super.actividadVer();
    }

}
