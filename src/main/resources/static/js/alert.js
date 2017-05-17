/**
 * Created by liyue on 2016/10/21.
 */
function showAlert(title,message,type,container) {

    $.notify({
        title: '<strong>' + title + '</strong><br>',
        message:message
    },{
        type: type
    });

    // if (typeof container === 'undefined') {
    //     container = $("#alert-container");
    // }
    //
    // container.append('<div class="alert ' + type + ' fade " role="alert" > ' +
    //     // '<strong >' + title + '</strong> ' +
    //     message + ' </div>');
    // var alertDom = container.children().last();
    // alertDom.addClass('in');
    // setTimeout(function () {
    //     alertDom.fadeTo(500, 0).slideUp(500, function(){
    //         $(this).slideUp(500);
    //         $(this).remove();
    //     });
    //     // alertDom.fadeTo(2000, 500).slideUp(500, function(){
    //     //     alertDom.slideUp(500);
    //     // });
    // },5000)
    // ;
}