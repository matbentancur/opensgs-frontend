<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/usuarios/navigationMenu.jspf" %>

<div class="row row-cols-1 row-cols-md-3 g-4">

    <s:if test="%{menuMisSolicitudes}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemMisSolicitudes.jspf" %>
    </s:if>
    <s:if test="%{menuMisDatos}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemMisDatos.jspf" %>
    </s:if>
    <s:if test="%{menuMiClave}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemMiClave.jspf" %>
    </s:if>
    <s:if test="%{menuReporte}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemReporte.jspf" %>
    </s:if>
    <s:if test="%{menuPlantillaCorreo}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemPlantillaCorreo.jspf" %>
    </s:if>
    <s:if test="%{menuNotificacion}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemNotificacion.jspf" %>
    </s:if>
    <s:if test="%{menuAplicacion}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemAplicacion.jspf" %>
    </s:if>
    <s:if test="%{menuPlantillaAplicacion}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemPlantillaAplicacion.jspf" %>
    </s:if>
    <s:if test="%{menuAnuncio}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemAnuncio.jspf" %>
    </s:if>
    <s:if test="%{menuArchivo}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemArchivo.jspf" %>
    </s:if>
    <s:if test="%{menuPreguntaFrecuente}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemPreguntaFrecuente.jspf" %>
    </s:if>
    <s:if test="%{menuUsuario}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemUsuario.jspf" %>
    </s:if>
    <s:if test="%{menuPerfil}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemPerfil.jspf" %>
    </s:if>
    <s:if test="%{menuActividad}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemActividad.jspf" %>
    </s:if>
    <s:if test="%{menuSistema}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemSistema.jspf" %>
    </s:if>
    <s:if test="%{menuServidorAplicaciones}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemServidorAplicaciones.jspf" %>
    </s:if>
    <s:if test="%{menuServidorAutenticacion}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemServidorAutenticacion.jspf" %>
    </s:if>
    <s:if test="%{menuServidorCorreo}">
        <%@ include file="/WEB-INF/jspf/menu/items/menuItemServidorCorreo.jspf" %>
    </s:if>

</div>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>