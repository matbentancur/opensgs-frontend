package opensgs.web.actions;

import java.util.List;
import opensgs.web.webservices.DtAnuncio;
import org.apache.struts2.convention.annotation.Action;

@Action(
        value = "ComunicationAction"
)

public class ComunicationAction extends BaseAction {

    private List<DtAnuncio> listaDtAnuncios;

    public List<DtAnuncio> getListaDtAnuncios() {
        return listaDtAnuncios;
    }

    public void setListaDtAnuncios(List<DtAnuncio> listaDtAnuncios) {
        this.listaDtAnuncios = listaDtAnuncios;
    }

    public void listarAnunciosSistema() throws Exception {
        List<DtAnuncio> lista = null;
        lista = this.getOpensgsWsPort().listarAnunciosSistemaVigentes();
        this.setListaDtAnuncios(lista);
    }

}
