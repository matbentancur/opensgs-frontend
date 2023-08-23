package opensgs.web.actions.sistema;

import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
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
import opensgs.web.webservices.DtConstanciaElementoImagen;
import opensgs.web.webservices.DtMensaje;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@Action(
        value = "ConstanciaElementoImagenAction",
        results = {
            @Result(name = "input", location = "constanciaElementoImagen/constanciaElementoImagenForm.jsp"),
            @Result(name = "list", location = "constanciaElementoImagen/constanciaElementoImagenList.jsp"),
            @Result(name = "view", location = "constanciaElementoImagen/constanciaElementoImagenView.jsp"),
            @Result(
                    name = "descargar",
                    type = "stream",
                    params = {
                        "contentType", "${dtConstanciaElementoImagen.dtArchivo.mime}",
                        "inputName", "fileInputStream",
                        "contentDisposition", "attachment; filename=\"${dtConstanciaElementoImagen.dtArchivo.nombre}\"",
                        "contentLength", "${dtConstanciaElementoImagen.dtArchivo.peso}"
                    })
        }
)
@AllowedMethods({
    "descargar"
})
public class ConstanciaElementoImagenAction extends ConstanciaHijoAction {

    private DtConstanciaElementoImagen dtConstanciaElementoImagen;
    private List<DtConstanciaElementoImagen> listaDtConstanciaElementoImagen;

    private File constanciaElementoImagen;
    private String contentType;
    private String filename;
    private InputStream fileInputStream;

    public DtConstanciaElementoImagen getDtConstanciaElementoImagen() {
        if (this.getDtOpenSGSManagedBean() != null) {
            return (DtConstanciaElementoImagen) this.getDtOpenSGSManagedBean();
        } else {
            return dtConstanciaElementoImagen;
        }
    }

    public void setDtConstanciaElementoImagen(DtConstanciaElementoImagen dtConstanciaElementoImagen) {
        this.dtConstanciaElementoImagen = dtConstanciaElementoImagen;
    }

    public List<DtConstanciaElementoImagen> getListaDtConstanciaElementoImagen() {
        return listaDtConstanciaElementoImagen;
    }

    public void setListaDtConstanciaElementoImagen(List<DtConstanciaElementoImagen> listaDtConstanciaElementoImagen) {
        this.listaDtConstanciaElementoImagen = listaDtConstanciaElementoImagen;
    }

    public File getConstanciaElementoImagen() {
        return constanciaElementoImagen;
    }

    public void setConstanciaElementoImagen(File constanciaElementoImagen) {
        this.constanciaElementoImagen = constanciaElementoImagen;
    }

    public String getContentType() {
        return contentType;
    }

    public void setConstanciaElementoImagenContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setConstanciaElementoImagenFileName(String filename) {
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
                        fieldName = "dtConstanciaElementoImagen.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.posicionX",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.posicionY",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.ancho",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.alto",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.dtArchivo.nombre",
                        key = "OpenSGSBean.validator.required"
                ),
                @RequiredFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "constanciaElementoImagen",
                        key = "OpenSGSBean.validator.required"
                )
            },
            stringLengthFields = {
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                ),
                @StringLengthFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoImagen.dtArchivo.nombre",
                        minLength = "1",
                        maxLength = "128",
                        key = "OpenSGSBean.validator.stringLength"
                )
            },
            intRangeFields = {
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.ancho",
                        min = "1",
                        max = "10000",
                        key = "OpenSGSBean.validator.intRange"
                ),
                @IntRangeFieldValidator(
                        type = ValidatorType.SIMPLE,
                        fieldName = "dtConstanciaElementoTexto.alto",
                        min = "1",
                        max = "10000",
                        key = "OpenSGSBean.validator.intRange"
                )
            }
    )
    @Override
    public String execute() throws Exception {
        if (dtConstanciaElementoImagen != null) {
            this.setDtOpenSGSBean(dtConstanciaElementoImagen);
            this.setDtOpenSGSManagedBean(dtConstanciaElementoImagen);
            this.dtConstanciaElementoImagen.getDtArchivo().setMime(contentType);
            this.dtConstanciaElementoImagen.getDtArchivo().setAlcanceArchivo("CONSTANCIA");
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
    public String listar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();
        this.cargarTextosLista();
        List<DtConstanciaElementoImagen> lista = null;

        if (this.getConstanciaId() != null) {
            lista = this.getOpensgsWsPort().listarConstanciaElementoImagen(this.getConstanciaId(), this.isBorrado(), this.getDtSesion());
        }

        this.setListaDtConstanciaElementoImagen(lista);
        return "list";
    }

    @Override
    public String crear() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        DataSource dataSource = new FileDataSource(this.getConstanciaElementoImagen());
        DataHandler dataHandler = new DataHandler(dataSource);

        DtMensaje dtMensaje = this.getOpensgsWsPort().crearConstanciaElementoImagen(dataHandler, this.getDtConstanciaElementoImagen(), this.getDtSesion());
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
        DataSource dataSource = new FileDataSource(this.getConstanciaElementoImagen());
        DataHandler dataHandler = new DataHandler(dataSource);

        DtMensaje dtMensaje = this.getOpensgsWsPort().modificarConstanciaElementoImagen(dataHandler, this.getDtConstanciaElementoImagen(), this.getDtSesion());
        this.setDtMensaje(dtMensaje);
        if (dtMensaje.isExito()) {
            return this.listar();
        }
        return INPUT;
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
            Logger.getLogger(ConstanciaElementoImagenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SkipValidation
    public String descargar() throws Exception {
        if (!this.sesionIniciada()) {
            return "login";
        }
        this.setCommonTextsValues();

        DtConstanciaElementoImagen dtCEI = (DtConstanciaElementoImagen) this.obtenerDtOpenSGSManagedBean(this.getClassName(), this.getId());
        if (dtCEI != null) {
            this.setDtConstanciaElementoImagen(dtCEI);
            DataHandler dataHandler = this.getOpensgsWsPort().descargarArchivo(this.getDtConstanciaElementoImagen().getDtArchivo().getId(), this.getDtSesion());
            if (dataHandler == null) {
                addActionError(this.getText("Error.data.invalid"));
                return ERROR;
            }
            this.setFileInputStream(dataHandler.getInputStream());
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
        return "dtConstanciaElementoImagen";
    }

    @SkipValidation
    public String getFieldNameValue(String field) {
        switch (field) {
            case "name":
                return this.getDtAttributeName() + ".nombre";
            case "positionX":
                return this.getDtAttributeName() + ".posicionX";
            case "positionY":
                return this.getDtAttributeName() + ".posicionY";
            case "width":
                return this.getDtAttributeName() + ".ancho";
            case "height":
                return this.getDtAttributeName() + ".alto";
            case "fileName":
                return this.getDtAttributeName() + ".dtArchivo.nombre";
            case "upload":
                return "constanciaElementoImagen";
            case "certificateId":
                return this.getDtAttributeName() + ".dtConstancia.id";
            case "applicationId":
                return "aplicacionId";
            default:
                addActionError(this.getText("Error.field.notExists") + ": " + field);
                return ERROR;
        }
    }

    private void setCommonTextsValues() throws Exception {
        this.setClassName("ConstanciaElementoImagen");
        this.setTextClassName(this.getText("OpenSGSBean.class.certificateImageElement"));
        this.setTextListName(this.getText("OpenSGSBean.list.certificateImageElements"));

        this.setTextNavigation(this.getTextListName());
        this.setTitleList(this.getTextListName());

        super.setCertificateTextsValues();
    }

}
