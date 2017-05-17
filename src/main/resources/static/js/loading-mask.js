/**
 * Created by liyue on 2017/4/27.
 */
var LoadingMask;
LoadingMask = LoadingMask || (function () {
        var pleaseWaitDiv = $('#pleaseWaitDialog');
        return {
            show: function() {
                pleaseWaitDiv.modal();
            },
            hide: function () {
                pleaseWaitDiv.modal('hide');
            }

        };
    })();