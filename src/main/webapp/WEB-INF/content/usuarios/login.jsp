<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url var="udelarLogoBlack" value="/images/udelar-logo-black.svg"/>

<s:url var="recuperarMiClave" action="RecuperarMiClaveAction" method="input" namespace="/usuarios"/>

<s:url var="registrarUsuario" action="RegistrarUsuarioAction" method="input" namespace="/usuarios"/>

<%@ include file="/WEB-INF/jspf/layout/header.jspf" %>

<%@ include file="/WEB-INF/jspf/navigation/usuarios/navigationLogin.jspf" %>

<%@ include file="/WEB-INF/jspf/layout/anuncios.jspf" %>

<div class="form-signin">
    <div class="card text-center">
        <div class="card-header">
            <s:text name="Login.text.enter.data"/>
        </div>
        <div class="card-body">
            <s:form action="AutenticacionAction!login" method="post" theme="simple" >
                <img class="mb-4" src="${udelarLogoBlack}" alt="Udelar">

                <div class="form-floating">
                    <s:textfield
                        id="usuario" 
                        name="usuario" 
                        class="form-control" 
                        autocomplete="email" 
                        placeholder="usuario@dominio.com"
                        type="email"
                        required="true"
                        maxlength="128"
                     />
                    <label for="usuario"><s:text name="OpenSGSBean.attribute.email"/></label>
                </div>

                <div class="form-floating">
                    <s:password
                        id="password"
                        name="password"
                        class="form-control"
                        autocomplete="current-password"
                        placeholder="Password"
                        type="password"
                        required="true"
                        maxlength="128"
                        minlength="8"
                    />
                    <label for="password"><s:text name="OpenSGSBean.attribute.password"/></label>
                </div>

                <div>
                    <s:submit cssClass="w-100 btn btn-lg btn-dark" value="Ingresar"/>
                </div>
            </s:form>
        </div>
    </div>
    <div class="pt-2">
        <i class="bi bi-lock-fill"></i>
        <a href="${recuperarMiClave}" class="link-dark">
            <s:text name="Login.text.forgotten.password"/>
        </a>
    </div>
    <div class="pt-2">
        <i class="bi bi-person-plus-fill"></i>
        <s:text name="Login.text.no.user"/>
        <a href="${registrarUsuario}" class="link-dark">
            <s:text name="Login.text.register"/>
        </a>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/layout/footer.jspf" %>

