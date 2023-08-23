$(document).ready(function () {
    //AGREGA A CADA ELEMENTO DEL FORMULARIO EL GUARDADO DE SU ID PARA EL COOKIE
    $("#PrincipalValidationAction input:text, #PrincipalValidationAction input:radio, #PrincipalValidationAction textarea, #PrincipalValidationAction select").focusin(function () {
        $.cookie('lastSelected', $(this).attr("id"));
    });
    
    $( "#PrincipalValidationAction_elemento_finalizado").unbind( "focusin" );
    return false;
});

$(window).load(function() {
    var lsMensajeError = $('.errorMessage');
    if (lsMensajeError.length == 0) {
        if (typeof $.cookie('lastSelected') !== 'undefined') {
            window.setTimeout( setLastScroll, 500 ); // DELAY
        }
    }
    return false;
});

function setLastScroll(){
    var elementName = $.cookie('lastSelected');
    var element = $('#' + elementName);
    $("html, body").animate({ scrollTop: element.offset().top }, 1000, 'swing');
    return false;
 };
