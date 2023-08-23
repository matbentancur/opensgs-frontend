package opensgs.web.actions;

import static com.opensymphony.xwork2.Action.INPUT;
import java.util.List;
import opensgs.web.webservices.DtActividad;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtOpenSGSBean;
import opensgs.web.webservices.DtOpenSGSManagedBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Results({
    @Result(
            name = "actividad",
            location = "actividad/actividad.jsp"
    ),
    @Result(
            name = "actividadVer",
            location = "actividad/actividadVer.jsp"
    )
})
@AllowedMethods({
    "form",
    "activar",
    "desactivar",
    "borrar",
    "restaurar",
    "actividad",
    "actividadVer"
})
public abstract class OpenSGSManagedBeanAction extends OpenSGSBeanAction {

    private boolean activo;
    private boolean borrado;
    private boolean admistrable;
    private DtOpenSGSManagedBean dtOpenSGSManagedBean;
    private List<DtOpenSGSManagedBean> listaDtOpenSGSManagedBeans;
    private DtOpenSGSBean dtOpenSGSBeanChild;

    private Long parentId;
    private String parentParam;
    private String parentClassName;
    private String textParentClassName;
    private String textParentListName;

    private Long childId;
    private String childClassName;
    private String textChildClassName;
    private String textChildListName;

    private Long actividadId;
    private List<DtActividad> listaActividad;
    private String textActivityNavigation;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isAdmistrable() {
        return admistrable;
    }

    public void setAdmistrable(boolean admistrable) {
        this.admistrable = admistrable;
    }

    public DtOpenSGSManagedBean getDtOpenSGSManagedBean() {
        return dtOpenSGSManagedBean;
    }

    public void setDtOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        this.dtOpenSGSManagedBean = dtOpenSGSManagedBean;
    }

    public List<DtOpenSGSManagedBean> getListaDtOpenSGSManagedBeans() {
        return listaDtOpenSGSManagedBeans;
    }

    public void setListaDtOpenSGSManagedBeans(List<DtOpenSGSManagedBean> listaDtOpenSGSManagedBeans) {
        this.listaDtOpenSGSManagedBeans = listaDtOpenSGSManagedBeans;
    }

    public DtOpenSGSBean getDtOpenSGSBeanChild() {
        return dtOpenSGSBeanChild;
    }

    public void setDtOpenSGSBeanChild(DtOpenSGSBean dtOpenSGSBeanChild) {
        this.dtOpenSGSBeanChild = dtOpenSGSBeanChild;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentParam() {
        return parentParam;
    }

    public void setParentParam(String parentParam) {
        this.parentParam = parentParam;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public String getTextParentClassName() {
        return textParentClassName;
    }

    public void setTextParentClassName(String textParentClassName) {
        this.textParentClassName = textParentClassName;
    }

    public String getTextParentListName() {
        return textParentListName;
    }

    public void setTextParentListName(String textParentListName) {
        this.textParentListName = textParentListName;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getChildClassName() {
        return childClassName;
    }

    public void setChildClassName(String childClassName) {
        this.childClassName = childClassName;
    }

    public String getTextChildClassName() {
        return textChildClassName;
    }

    public void setTextChildClassName(String textChildClassName) {
        this.textChildClassName = textChildClassName;
    }

    public String getTextChildListName() {
        return textChildListName;
    }

    public void setTextChildListName(String textChildListName) {
        this.textChildListName = textChildListName;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public List<DtActividad> getListaActividad() {
        return listaActividad;
    }

    public void setListaActividad(List<DtActividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public String getTextActivityNavigation() {
        return textActivityNavigation;
    }

    public void setTextActivityNavigation(String textActivityNavigation) {
        this.textActivityNavigation = textActivityNavigation;
    }

    public DtOpenSGSManagedBean obtenerDtOpenSGSManagedBean(String className, Long id) throws Exception {
        DtOpenSGSManagedBean dtOMB = (DtOpenSGSManagedBean) this.getOpensgsWsPort().obtenerOpenSGSBean(className, id, this.getDtSesion());
        return dtOMB;
    }

    public String input() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setTitleForm(
                this.getText("OpenSGSBean.text.create")
                + " "
                + this.getTextClassName()
        );
        this.setComando("crear");
        return INPUT;
    }

    @Override
    public String ver() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtOpenSGSBean dtOSB = this.obtenerDtOpenSGSBean(this.getClassName(), this.getId());
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

    public String form() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setDtOpenSGSManagedBean(this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId()));
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

    @Override
    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.cargarLista();
        return "list";
    }

    public void cargarTextosLista() {
        if (this.isBorrado()) {
            this.setTitleList(
                    this.getTitleList()
                    + " "
                    + this.getText("OpenSGSBean.text.deleted")
                    + ":"
            );
        } else {
            this.setTitleList(this.getTitleList() + ":");
        }
    }

    public void cargarTextosActividad() {
        this.setTitleList(
                this.getText("OpenSGSBean.text.activity")
                + " "
                + this.getTextClassName()
                + " "
                + this.getId()
                + ":"
        );
        this.setTextActivityNavigation(
                this.getText("OpenSGSBean.text.activity")
                + " "
                + this.getTextClassName()
                + " "
                + this.getId()
        );
    }

    public void cargarLista() throws Exception {
        this.cargarTextosLista();
        List<DtOpenSGSManagedBean> lista = null;

        if (!this.isEmptyFilterParam()) {
            if (this.isLongFilterParam()) {
                Long longValue = Long.parseLong(this.getFilterValue());
                lista = this.getOpensgsWsPort().listarOpenSGSManagedBeanParametroValorLong(this.getClassName(), this.getFilterParam(), longValue, this.isBorrado(), this.getDtSesion());
            } else {
                lista = this.getOpensgsWsPort().listarOpenSGSManagedBeanParametroValor(this.getClassName(), this.getFilterParam(), this.getFilterValue(), this.isBorrado(), this.getDtSesion());
            }
        } else if (!this.isEmptyParentParam()) {
            lista = this.getOpensgsWsPort().listarOpenSGSManagedBeanParametroValorLong(this.getClassName(), this.getParentParam(), this.getParentId(), this.isBorrado(), this.getDtSesion());
        } else {
            lista = this.getOpensgsWsPort().listarOpenSGSManagedBean(this.getClassName(), this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtOpenSGSManagedBeans(lista);
    }

    public void listChild() throws Exception {
        this.setDtOpenSGSManagedBean(this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getParentId()));
        String identifierValue = BeanUtilsBean.getInstance().getProperty(this.getDtOpenSGSManagedBean(), this.getTextObjectIdentifierName());
        this.setTitleForm(
                this.getText("OpenSGSBean.text.add")
                + " "
                + this.getTextChildClassName()
                + " "
                + this.getText("OpenSGSBean.text.addTo")
                + " "
                + identifierValue
        );
        this.setTitleList(
                this.getTextChildListName()
                + " "
                + this.getText("OpenSGSBean.text.elementFrom")
                + " "
                + identifierValue
        );
    }

    public String crear() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtMensaje dtMensaje = this.getOpensgsWsPort().crearOpenSGSManagedBean(this.getDtOpenSGSManagedBean(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.listar();
        }
        return INPUT;
    }

    public String modificar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarOpenSGSManagedBean(this.getDtOpenSGSManagedBean(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.listar();
        }
        return INPUT;
    }

    public String borrar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtOMB != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().borrarOpenSGSManagedBean(dtOMB, this.getDtSesion());
            this.setDtMensaje(dtMensaje);
        }

        return this.listar();
    }

    public String restaurar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtOMB != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().restaurarOpenSGSManagedBean(dtOMB, this.getDtSesion());
            this.setDtMensaje(dtMensaje);
        }

        return this.listar();
    }

    public String activar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtOMB != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().activarOpenSGSManagedBean(dtOMB, this.getDtSesion());
            this.setDtMensaje(dtMensaje);
        }

        return this.listar();
    }

    public String desactivar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtOMB != null) {
            DtMensaje dtMensaje = this.getOpensgsWsPort().desactivarOpenSGSManagedBean(dtOMB, this.getDtSesion());
            this.setDtMensaje(dtMensaje);
        }

        return this.listar();
    }

    public void addChild() throws Exception {
        DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        DtOpenSGSBean childE = this.obtenerDtOpenSGSBean(this.getChildClassName(), this.getChildId());
        DtMensaje dtMensaje = this.getOpensgsWsPort().agregarOpenSGSBean(dtOMB, childE, this.getDtSesion());
        this.setDtMensaje(dtMensaje);
    }

    public void removeChild() throws Exception {
        DtOpenSGSManagedBean dtOMB = this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getParentId());
        DtOpenSGSBean childE = this.obtenerDtOpenSGSBean(this.getChildClassName(), this.getChildId());
        DtMensaje dtMensaje = this.getOpensgsWsPort().quitarOpenSGSBean(dtOMB, childE, this.getDtSesion());
        this.setDtMensaje(dtMensaje);
    }

    public List<DtOpenSGSBean> listarOpenSGSBean(String className) throws Exception {
        List<DtOpenSGSBean> lista = null;
        lista = this.getOpensgsWsPort().listarOpenSGSBean(className, this.getDtSesion());
        return lista;
    }

    public String actividad() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        if (this.getParentId() == null) {
            this.setParentId(this.getId());
        }
//        if (this.getChildId() == null) {
//            this.setChildId(this.getId());
//        } else {
//            this.setId(this.getChildId());
//        }
        this.cargarTextosActividad();
        List<DtActividad> lista = null;
        lista = this.getOpensgsWsPort().listarActividadElemento(this.getClassName(), this.getId(), this.getDtSesion());
        this.setListaActividad(lista);
        return "actividad";
    }

    @SkipValidation
    public String actividadVer() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DtOpenSGSBean dtOSB = this.obtenerDtOpenSGSBean("Actividad", this.getActividadId());
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

    public List<DtOpenSGSManagedBean> listOpenSGSManagedBeanFormElement(String className) throws Exception {
        List<DtOpenSGSManagedBean> lista = null;
        lista = this.getOpensgsWsPort().listarOpenSGSManagedBeanActivos(className, this.getDtSesion());
        return lista;
    }

    public List<String> listEnumFormElement(String enumName) throws Exception {
        List<String> lista = null;
        lista = this.getOpensgsWsPort().listarEnum(enumName, this.getDtSesion());
        return lista;
    }

    public String parseComando() throws Exception {
        if (this.getComando() == null) {
            addActionError(this.getText("Error.command.notFound"));
            return ERROR;
        }

        switch (this.getComando()) {
            case "crear":
                return this.crear();
            case "modificar":
                return this.modificar();
            default:
                addActionError(this.getText("Error.command.notFound") + ": " + this.getComando());
                return ERROR;
        }
    }

    public boolean isEmptyParentParam() {
        if (this.getParentId() != null && this.getParentParam() != null) {
            if (!this.getParentParam().trim().isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public String getFieldLabel(String field) {
        return this.getText("OpenSGSBean.attribute." + field);
    }

    public abstract String getFieldNameValue(String field);

}
