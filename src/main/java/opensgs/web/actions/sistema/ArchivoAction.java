package opensgs.web.actions.sistema;

import static com.opensymphony.xwork2.Action.INPUT;
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
import opensgs.web.webservices.DtArchivo;
import opensgs.web.webservices.DtMensaje;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ArchivoAction",
        results = {
            @Result(name = "input", location = "archivo/archivoForm.jsp"),
            @Result(name = "list", location = "archivo/archivoList.jsp"),
            @Result(name = "view", location = "archivo/archivoView.jsp"),
            @Result(
                    name = "descargar",
                    type = "stream",
                    params = {
                        "contentType", "${dtArchivo.mime}",
                        "inputName", "fileInputStream",
                        "contentDisposition", "attachment; filename=\"${dtArchivo.nombre}\"",
                        "contentLength", "${dtArchivo.peso}"
                    })
        }
)
@AllowedMethods({
    "descargar"
//    "descargarPublico"
})
public class ArchivoAction extends AplicacionHijoAction {

    private DtArchivo dtArchivo;
    private List<DtArchivo> listaDtArchivos;
    
    private File archivo;
    private String contentType;
    private String filename;
    private InputStream fileInputStream;

    public DtArchivo getDtArchivo() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtArchivo) this.getDtOpenSGSManagedBean();
        } else {
            return dtArchivo;
        }
    }

    public void setDtArchivo(DtArchivo dtArchivo) {
        this.dtArchivo = dtArchivo;
    }

    public List<DtArchivo> getListaDtArchivos() {
        return listaDtArchivos;
    }

    public void setListaDtArchivos(List<DtArchivo> listaDtArchivos) {
        this.listaDtArchivos = listaDtArchivos;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setArchivoContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setArchivoFileName(String filename) {
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
                        fieldName = "dtArchivo.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtArchivo.titulo",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "archivo",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtArchivo.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtArchivo.titulo",
                        minLength = "1",
                        maxLength = "256",
                        key = "OpenSGSBean.validator.stringLength"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtArchivo != null) {
            this.setDtOpenSGSBean(dtArchivo);
            this.setDtOpenSGSManagedBean(dtArchivo);
            this.dtArchivo.setMime(this.getContentType());
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
        DataSource dataSource = new FileDataSource(this.getArchivo());
        DataHandler dataHandler = new DataHandler(dataSource);

        DtMensaje dtMensaje = new DtMensaje();

        if (this.getAplicacionId() != null) {
            dtMensaje = this.getOpensgsWsPort().crearArchivoAplicacion(dataHandler, this.getDtArchivo(), this.getAplicacionId(), this.getDtSesion());
        } else {
            dtMensaje = this.getOpensgsWsPort().crearArchivo(dataHandler, this.getDtArchivo(), this.getDtSesion());
        }

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
        DataSource dataSource = new FileDataSource(this.getArchivo());
        DataHandler dataHandler = new DataHandler(dataSource);

        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarArchivo(dataHandler, this.getDtArchivo(), this.getDtSesion());
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
        List<DtArchivo> lista = null;

        if (this.getAplicacionId() != null) {
            lista = this.getOpensgsWsPort().listarArchivosAplicacion(this.getAplicacionId(), this.isBorrado(), this.getDtSesion());
        } else {
            lista = this.getOpensgsWsPort().listarArchivosSistema(this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtArchivos(lista);
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
        this.setCommonTextsValues();

        DtArchivo dtA = this.getOpensgsWsPort().obtenerDtArchivoPublico(this.getId());
        if (dtA != null) {
            this.setDtArchivo(dtA);
            DataHandler dataHandler = this.getOpensgsWsPort().descargarArchivoPublico(this.getId());
            if (dataHandler == null) {
                addActionError(this.getText("Error.data.invalid"));
                return ERROR;
            }
            this.setFileInputStream(dataHandler.getInputStream());
        } else {
            dtA = (DtArchivo) this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
            if (dtA != null) {
                this.setDtArchivo(dtA);
                DataHandler dataHandler = this.getOpensgsWsPort().descargarArchivo(this.getId(), this.getDtSesion());
                if (dataHandler == null) {
                    addActionError(this.getText("Error.data.invalid"));
                    return ERROR;
                }
                this.setFileInputStream(dataHandler.getInputStream());
            }
        }

        return "descargar";
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
        return "dtArchivo";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return "dtArchivo.nombre";
            case "title":
                return "dtArchivo.titulo";
            case "fileScope":
                return "dtArchivo.alcanceArchivo";
            case "upload":
                return "archivo";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("Archivo");
        this.setTextClassName(this.getText("OpenSGSBean.class.file"));
        this.setTextListName(this.getText("OpenSGSBean.list.files"));
        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());
        super.setApplicationTextsValues();
    }

}
