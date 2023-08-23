function reloadCaptcha(context){
    var d = new Date();
    $("#captcha_image").attr("src", context +"?" +d.getTime());
    return false;
}