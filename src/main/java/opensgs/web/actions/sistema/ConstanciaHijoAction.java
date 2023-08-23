package opensgs.web.actions.sistema;

import opensgs.web.webservices.DtAplicacion;
import opensgs.web.webservices.DtConstancia;

public abstract class ConstanciaHijoAction extends AplicacionHijoAction {

    private DtConstancia dtConstancia;
    private Long constanciaId;
    private String textCertificateClassName;
    private String textCertificateListName;
    private String textCertificateNavigation;

    public DtConstancia getDtConstancia() {
        return dtConstancia;
    }

    public void setDtConstancia(DtConstancia dtConstancia) {
        this.dtConstancia = dtConstancia;
    }

    public Long getConstanciaId() {
        return constanciaId;
    }

    public void setConstanciaId(Long constanciaId) {
        this.constanciaId = constanciaId;
    }

    public String getTextCertificateClassName() {
        return textCertificateClassName;
    }

    public void setTextCertificateClassName(String textCertificateClassName) {
        this.textCertificateClassName = textCertificateClassName;
    }

    public String getTextCertificateListName() {
        return textCertificateListName;
    }

    public void setTextCertificateListName(String textCertificateListName) {
        this.textCertificateListName = textCertificateListName;
    }

    public String getTextCertificateNavigation() {
        return textCertificateNavigation;
    }

    public void setTextCertificateNavigation(String textCertificateNavigation) {
        this.textCertificateNavigation = textCertificateNavigation;
    }

    public void setCertificateTextsValues() throws Exception {

        if (this.getConstanciaId() != null) {

            this.setTextCertificateClassName(this.getText("OpenSGSBean.class.certificate"));
            this.setTextCertificateListName(this.getText("OpenSGSBean.list.certificates"));

            DtConstancia dtC = (DtConstancia) this.obtenerDtOpenSGSManagedBean("Constancia", this.getConstanciaId());
            this.setDtConstancia(dtC);

            if (this.getAplicacionId() != null) {
                DtAplicacion dtA = (DtAplicacion) this.obtenerDtOpenSGSManagedBean("Aplicacion", this.getAplicacionId());
                this.setDtAplicacion(dtA);
                this.setTextApplicationListName(this.getText("OpenSGSBean.list.applications"));
                this.setTextApplicationNavigation(this.getTextApplicationListName());
                this.setTextCertificateNavigation(this.getTextCertificateListName() + " " + this.getDtAplicacion().getNombre());

            }

            this.setTextNavigation(this.getTextListName() + " " + this.getDtConstancia().getNombre());
            this.setTitleForm(" " + dtC.getNombre());
            this.setTitleList(this.getTitleList() + " " + dtC.getNombre());
        }
    }
}
