package opensgs.web.actions.sistema;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtReporte;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ReporteAction",
        results = {
            @Result(name = "input", location = "reporte/reporteForm.jsp"),
            @Result(name = "list", location = "reporte/reporteList.jsp"),
            @Result(name = "view", location = "reporte/reporteView.jsp"),
            @Result(
                    name = "descargar",
                    type = "stream",
                    params = {
                        "contentType", "text/csv",
                        "inputName", "fileInputStream",
                        "contentDisposition", "attachment; filename=\"${dtReporte.nombre}.csv\""
                    })
        }
)
@AllowedMethods({
    "descargar"
})
public class ReporteAction extends AplicacionHijoAction {

    private DtReporte dtReporte;
    private List<DtReporte> listaDtReportes;
    
    private String contentType;
    private String filename;
    private InputStream fileInputStream;
    

    public DtReporte getDtReporte() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtReporte) this.getDtOpenSGSManagedBean();
        } else {
            return dtReporte;
        }
    }

    public void setDtReporte(DtReporte dtReporte) {
        this.dtReporte = dtReporte;
    }

    public List<DtReporte> getListaDtReportes() {
        return listaDtReportes;
    }

    public void setListaDtReportes(List<DtReporte> listaDtReportes) {
        this.listaDtReportes = listaDtReportes;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFilename() {
        return filename;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    @Validations(
            requiredFields = {
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.cabezal",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.sentenciaSQL",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.alcance",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.cabezal",
                        minLength = "1",
                        maxLength = "1000",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.sentenciaSQL",
                        minLength = "1",
                        maxLength = "10000",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtReporte.alcance",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtReporte.alcance",
                        expression = "dtReporte.alcance in {'APLICACION','APLICACIONES','SISTEMA','GLOBAL'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtReporte != null) {
            this.setDtOpenSGSBean(dtReporte);
            this.setDtOpenSGSManagedBean(dtReporte);
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
            DtMensaje dtMensaje = this.getOpensgsWsPort().crearReporteAplicacion(this.getDtReporte(), this.getAplicacionId(), this.getDtSesion());
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
        List<DtReporte> lista = null;

        if (this.getAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarReportesAplicacion(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());
        } else {
            lista = this.getOpensgsWsPort().listarReportesSistema(this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtReportes(lista);
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
    public String descargar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.setDtReporte((DtReporte) this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId()));
        DataHandler dataHandler = this.getOpensgsWsPort().descargarReporteCSV(this.getId(), this.getDtSesion());
        if (dataHandler != null) {
            this.setFileInputStream(dataHandler.getInputStream());
            return "descargar";
        }
        DtMensaje dtMensaje = new DtMensaje();
        this.addActionError(this.getText("Error.file.download"));
        return this.listar();
    }

    @SkipValidation
    public String getDtAttributeName() {
        return "dtReporte";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtReporte.nombre";
            case "head":
                return "dtReporte.cabezal";
            case "sqlStatement":
                return "dtReporte.sentenciaSQL";
            case "scope":
                return "dtReporte.alcance";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Reporte");
        this.setTextClassName(this.getText("OpenSGSBean.class.report"));
        this.setTextListName(this.getText("OpenSGSBean.list.reports"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

}
