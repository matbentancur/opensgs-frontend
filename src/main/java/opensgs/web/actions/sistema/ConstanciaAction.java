package opensgs.web.actions.sistema;

import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import opensgs.web.webservices.DtConstancia;
import opensgs.web.webservices.DtMensaje;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ConstanciaAction",
        results = {
            @Result(name = "input", location = "constancia/constanciaForm.jsp"),
            @Result(name = "list", location = "constancia/constanciaList.jsp"),
            @Result(name = "view", location = "constancia/constanciaView.jsp"),
            @Result(
                    name = "descargar",
                    type = "stream",
                    params = {
                        "contentType", "${dtConstancia.dtArchivo.mime}",
                        "inputName", "fileInputStream",
                        "contentDisposition", "attachment; filename=\"${dtConstancia.dtArchivo.nombre}\"",
                        "contentLength", "${dtConstancia.dtArchivo.peso}"
                    }
            ),
            @Result(
                    name = "emitir",
                    type = "stream",
                    params = {
                        "contentType", "${dtConstancia.dtArchivo.mime}",
                        "inputName", "fileInputStream",
                        "contentDisposition", "attachment; filename=\"${dtConstancia.dtArchivo.nombre}\""
                    }
            ),
            @Result(
                    name = "emitirMiConstancia",
                    type = "stream",
                    params = {
                        "contentType", "${dtConstancia.dtArchivo.mime}",
                        "inputName", "fileInputStream",
                        "contentDisposition", "attachment; filename=\"${dtConstancia.dtArchivo.nombre}\""
                    }
            )
        }
)
@AllowedMethods({
    "descargar",
    "emitir",
    "emitirMiConstancia"
})
public class ConstanciaAction extends AplicacionHijoAction {

    private DtConstancia dtConstancia;
    private List<DtConstancia> listaDtConstancias;
    private Long solicitudId;

    private File constancia;
    private String contentType;
    private String filename;
    private InputStream fileInputStream;

    public DtConstancia getDtConstancia() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtConstancia) this.getDtOpenSGSManagedBean();
        } else {
            return dtConstancia;
        }
    }

    public void setDtConstancia(DtConstancia dtConstancia) {
        this.dtConstancia = dtConstancia;
    }

    public List<DtConstancia> getListaDtConstancias() {
        return listaDtConstancias;
    }

    public void setListaDtConstancias(List<DtConstancia> listaDtConstancias) {
        this.listaDtConstancias = listaDtConstancias;
    }

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public File getConstancia() {
        return constancia;
    }

    public void setConstancia(File constancia) {
        this.constancia = constancia;
    }

    public String getContentType() {
        return contentType;
    }

    public void setConstanciaContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setConstanciaFileName(String filename) {
        this.filename = filename;
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
                        fieldName = "dtConstancia.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstancia.constanciaTipo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstancia.dtArchivo.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "constancia",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstancia.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstancia.constanciaTipo",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstancia.dtArchivo.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            fieldExpressions = {
                @FieldExpressionValidator(
                        fieldName = "dtConstancia.constanciaTipo",
                        expression = "dtConstancia.constanciaTipo in {'APROBADO','CURSADO','FINANCIADO','ENTREGADO','ACORDADO'}",
                        key = "OpenSGSBean.validator.expression"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtConstancia != null) {
            this.setDtOpenSGSBean(dtConstancia);
            this.setDtOpenSGSManagedBean(dtConstancia);
            this.dtConstancia.getDtArchivo().setMime(contentType);
            this.dtConstancia.getDtArchivo().setAlcanceArchivo("CONSTANCIA");
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

    @Override
    public String crear() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DataSource dataSource = new FileDataSource(this.getConstancia());
        DataHandler dataHandler = new DataHandler(dataSource);

        DtMensaje dtMensaje = new DtMensaje();

        dtMensaje = this.getOpensgsWsPort().crearConstancia(dataHandler, this.getDtConstancia(), this.getDtSesion());

        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.listar();
        }
        return INPUT;
    }

    @Override
    public String modificar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DataSource dataSource = new FileDataSource(this.getConstancia());
        DataHandler dataHandler = new DataHandler(dataSource);

        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarConstancia(dataHandler, this.getDtConstancia(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.listar();
        }
        return INPUT;
    }

    @SkipValidation
    @Override
    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.cargarTextosLista();
        List<DtConstancia> lista = null;

        lista = this.getOpensgsWsPort().listarConstancias(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());

        this.setListaDtConstancias(lista);
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

        DtConstancia dtC = (DtConstancia) this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtC != null) {
            this.setDtConstancia(dtC);
            DataHandler dataHandler = this.getOpensgsWsPort().descargarArchivo(this.getDtConstancia().getDtArchivo().getId(), this.getDtSesion());
            if (dataHandler == null) {
                addActionError(this.getText("Error.data.invalid"));
                return ERROR;
            }
            this.setFileInputStream(dataHandler.getInputStream());
        }

        return "descargar";
    }

    @SkipValidation
    public String emitir() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();

        DtConstancia dtC = (DtConstancia) this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtC != null) {
            this.setDtConstancia(dtC);
            DataHandler dataHandler = this.getOpensgsWsPort().emitirConstancia(this.getDtConstancia().getId(), this.getDtSesion());
            if (dataHandler == null) {
                addActionError(this.getText("Error.data.invalid"));
                return ERROR;
            }
            this.setFileInputStream(dataHandler.getInputStream());
        }

        return "emitir";
    }

    @SkipValidation
    public String emitirMiConstancia() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }

        DataHandler dataHandler = this.getOpensgsWsPort().emitirConstanciaSolicitud(this.getSolicitudId(), this.getId(), this.getDtSesion());
        if (dataHandler == null) {
            addActionError(this.getText("Error.data.invalid"));
            return ERROR;
        }
        this.setFileInputStream(dataHandler.getInputStream());

        return "emitirMiConstancia";
    }

    @Override
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

    @SkipValidation
    public String getDtAttributeName() {
        return "dtConstancia";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtConstancia.nombre";
            case "certificateType":
                return "dtConstancia.constanciaTipo";
            case "fileName":
                return "dtConstancia.dtArchivo.nombre";
            case "upload":
                return "constancia";
            case "applicationId":
                return "dtConstancia.dtAplicacion.id";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Constancia");
        this.setTextClassName(this.getText("OpenSGSBean.class.certificate"));
        this.setTextListName(this.getText("OpenSGSBean.list.certificates"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

}
