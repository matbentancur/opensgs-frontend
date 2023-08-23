package opensgs.web.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContext;
import opensgs.web.webservices.DtMensaje;
import opensgs.web.webservices.DtSesion;
import opensgs.web.webservices.OpensgsWS;
import opensgs.web.webservices.OpensgsWS_Service;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

@Results({
    @Result(
            name = "login",
            type = "redirectAction",
            params = {
                "actionName", "AutenticacionAction",
                "method", "input",
                "namespace", "/usuarios",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "menu",
            type = "redirectAction",
            params = {
                "actionName", "MenuAction",
                "namespace", "/usuarios",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "inicio",
            type = "redirectAction",
            params = {
                "actionName", "IndexAction",
                "namespace", "/",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    ),
    @Result(
            name = "error",
            type = "redirectAction",
            params = {
                "actionName", "ErrorAction",
                "namespace", "/",
                "actionErrors", "${actionErrors}",
                "actionMessages", "${actionMessages}"
            }
    )
})
public abstract class BaseAction extends ActionSupport implements SessionAware {

    private Map session;
    private String comando;
    private String metodo;
    private DtMensaje dtMensaje;
    private List<String> listaMensajes;
    private String title;
    private String titleList;
    private String titleForm;
    private String textNavigation;

    public Map getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public DtMensaje getDtMensaje() {
        return dtMensaje;
    }

    public void setDtMensaje(DtMensaje dtMensaje) {
        this.dtMensaje = dtMensaje;
    }

    public List<String> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<String> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleList() {
        return titleList;
    }

    public void setTitleList(String titleList) {
        this.titleList = titleList;
    }

    public String getTitleForm() {
        return titleForm;
    }

    public void setTitleForm(String titleForm) {
        this.titleForm = titleForm;
    }

    public String getTextNavigation() {
        return textNavigation;
    }

    public void setTextNavigation(String textNavigation) {
        this.textNavigation = textNavigation;
    }

    public List<AbstractMap.Entry<String, String>> leerElementosProperties(String elemento) {
        List<AbstractMap.Entry<String, String>> res = new ArrayList<AbstractMap.Entry<String, String>>();
        int i = 1;
        while (hasKey(elemento + "." + i)) {
            String opcion = this.getText(elemento + "." + i);
            String[] partes = opcion.split("\\|");
            String key = partes[0].trim();
            String value = partes.length == 1 ? key : partes[1].trim();
            res.add(new AbstractMap.SimpleEntry<>(key, value));
            i++;
        }

        return res;
    }

    public void setElementoPropertie(String elemento, String valor) throws Exception {
        ServletContext sc = ServletActionContext.getServletContext();
        String archivo = sc.getRealPath("/WEB-INF/classes/formulario.properties");
        Properties props = new Properties();

        FileInputStream in = new FileInputStream(archivo);
        Reader reader = new InputStreamReader(in, Charset.forName("iso-8859-1"));
        props.load(reader);
        props.setProperty(elemento, valor);
    }

    public OpensgsWS getOpensgsWsPort() throws Exception {
        OpensgsWS_Service opensgsService = new OpensgsWS_Service();
        return opensgsService.getOpensgsWSPort();
    }

    public DtSesion getDtSesion() {
        if (this.session.containsKey("dtSesion")) {
            DtSesion dtSesion = (DtSesion) session.get("dtSesion");
            return dtSesion;
        }
        return null;
    }

    public void agregarMensajeERROR(DtMensaje dtMensaje) {
        addActionError(StringUtils.join(dtMensaje.getMensajes(), "\n"));
    }

    public void agregarMensajeOK(DtMensaje dtMensaje) {
        addActionMessage(StringUtils.join(dtMensaje.getMensajes(), "\n"));
    }

    public void agregarMensaje(DtMensaje dtMensaje) {
        if (!dtMensaje.isExito()) {
            this.agregarMensajeERROR(dtMensaje);
        } else {
            this.agregarMensajeOK(dtMensaje);
        }
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getActionInvocation().getProxy().getActionName();
    }

    public String getNameSpace() {
        ActionContext context = ActionContext.getContext();
        return context.getActionInvocation().getProxy().getNamespace();
    }
    
    public boolean sesionIniciada(){
        if(this.getDtSesion() != null){
            return true;
        }
        return false;
    }

}
