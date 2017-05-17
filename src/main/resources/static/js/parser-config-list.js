/**
 * Created by liyue on 2016/10/21.
 */
$(document).ready(function(){

    var currentRowIndex = -1;
    function getCurrentDataId() {
        var domTr = $(".data-table tbody").children().eq(currentRowIndex);
        return domTr.attr("data-id");
    }

    function removeRowFromTable() {
        var domTbody = $(".data-table tbody");
        if (currentRowIndex >= 0) {
            var domTr = domTbody.children().eq(currentRowIndex);
            if (domTbody.children().length > 1) {
                domTr.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                    domTr.nextUntil().each(function () {
                        $(this).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            $(this).removeClass('animated slideInUp');
                        });
                        $(this).addClass('animated slideInUp');
                    });
                    $(this).removeClass('animated fadeOutLeft');

                    $(this).remove();
                });

                domTr.addClass('animated fadeOutLeft');
            }
        }
    }

    function deleteRow(id) {
        LoadingMask.show();
        $.ajax({
            type: "POST",
            url: contextPath + 'parser-config/delete/' + id,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        })
            .done(function (data) {
                var json = data;
                if (json.success === true) {
                    showAlert('', "删除成功！", 'success');
                    removeRowFromTable();
                } else {

                    showAlert('', json.message === undefined ? '删除失败！' : json.message, 'danger');
                }
            })

            .fail(function () {

                showAlert('', '删除失败！', 'danger');
            })
            .always(function () {
                LoadingMask.hide();
            });
    }

    $("#btnDeleteConfirm").on("click",function(){
        if(currentRowIndex < 0) return ;
        var id = getCurrentDataId();
        deleteRow(id);
    });

    $(".data-table tbody").on("click",".action-delete-row",function(){
        currentRowIndex = $(".data-row").index($(this).parents("tr"));
        $("#confirmDeleteModal").modal('show');
    });
});