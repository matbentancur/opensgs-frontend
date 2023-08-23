package opensgs.web.actions;

import java.util.List;
import opensgs.web.webservices.DtOpenSGSBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.AllowedMethods;

@AllowedMethods({
    "listar",
    "ver"
})
public abstract class OpenSGSBeanAction extends BaseAction {

    private Long id;
    private String className;
    private DtOpenSGSBean dtOpenSGSBean;
    private List<DtOpenSGSBean> listaDtOpenSGSBeans;
    private String textClassName;
    private String textListName;
    private String textObjectIdentifierName;
    private String namespaceBefore;
    private String filterParam;
    private String filterValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public DtOpenSGSBean getDtOpenSGSBean() {
        return dtOpenSGSBean;
    }

    public void setDtOpenSGSBean(DtOpenSGSBean dtOpenSGSBean) {
        this.dtOpenSGSBean = dtOpenSGSBean;
    }

    public List<DtOpenSGSBean> getListaDtOpenSGSBeans() {
        return listaDtOpenSGSBeans;
    }

    public void setListaDtOpenSGSBeans(List<DtOpenSGSBean> listaDtOpenSGSBeans) {
        this.listaDtOpenSGSBeans = listaDtOpenSGSBeans;
    }

    public String getTextClassName() {
        return textClassName;
    }

    public void setTextClassName(String textClassName) {
        this.textClassName = textClassName;
    }

    public String getTextListName() {
        return textListName;
    }

    public void setTextListName(String textListName) {
        this.textListName = textListName;
    }

    public String getTextObjectIdentifierName() {
        return textObjectIdentifierName;
    }

    public void setTextObjectIdentifierName(String textObjectIdentifierName) {
        this.textObjectIdentifierName = textObjectIdentifierName;
    }

    public String getNamespaceBefore() {
        return namespaceBefore;
    }

    public void setNamespaceBefore(String namespaceBefore) {
        this.namespaceBefore = namespaceBefore;
    }

    public String getFilterParam() {
        return filterParam;
    }

    public void setFilterParam(String filterParam) {
        this.filterParam = filterParam;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public DtOpenSGSBean obtenerDtOpenSGSBean(String className, Long id) throws Exception {
        DtOpenSGSBean dtOB = (DtOpenSGSBean) this.getOpensgsWsPort().obtenerOpenSGSBean(className, id, this.getDtSesion());
        return dtOB;
    }

    public String ver() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setDtOpenSGSBean(this.obtenerDtOpenSGSBean(this.getClassName(), this.getId()));
        String identifierValue = BeanUtilsBean.getInstance().getProperty(this.getDtOpenSGSBean(), this.getTextObjectIdentifierName());
        this.setTitle(
                this.getTextClassName()
                + ": "
                + identifierValue
        );
        return "view";
    }

    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setTitle(this.getTextListName() + ":");

        List<DtOpenSGSBean> lista = null;

        if (!this.isEmptyFilterParam()) {
            if (this.isLongFilterParam()) {
                Long longValue = Long.parseLong(this.getFilterValue());
                lista = (List<DtOpenSGSBean>) this.getOpensgsWsPort().listarOpenSGSBean(this.getClassName(), this.getDtSesion());
            } else {
                lista = (List<DtOpenSGSBean>) this.getOpensgsWsPort().listarOpenSGSBean(this.getClassName(), this.getDtSesion());
            }
        } else {
            lista = (List<DtOpenSGSBean>) this.getOpensgsWsPort().listarOpenSGSBean(this.getClassName(), this.getDtSesion());
        }

        this.setListaDtOpenSGSBeans(lista);
        return "list";
    }

    public boolean isEmptyFilterParam() {
        if (this.getFilterParam() != null && this.getFilterValue() != null) {
            if (!this.getFilterParam().trim().isEmpty() && !this.getFilterValue().trim().isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean isLongFilterParam() {
        return NumberUtils.isCreatable(this.getFilterValue());
    }

    public abstract String getDtAttributeName();

}
