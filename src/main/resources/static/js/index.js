/**
 * Created by liyue on 2017/4/10.
 */
$(document).ready(function(){

    function onStartStop(jobName,action) {
        $.ajax({
            url: contextPath + 'job/' + jobName + "/" + action,
            dataType: "json",
            success: function(data){

                var json = data;
                if(json.success === true){

                    refreshStatus();
                }else{
                    showAlert('',json.message,'danger');
                }

            }
        })
            .fail(function () {
                showAlert('','操作失败！','danger');
            });
    }

    function setStatus(labelStatusDom, status) {

    // <label th:case="0" class="label label-default">发呆中</label>
    //         <label th:case="1" class="label label-primary">抓取中</label>
    //         <label th:case="2" class="label label-success">成功了</label>
    //         <label th:case="-1" class="label label-default">失败了</label>
        labelStatusDom.removeClass();
        labelStatusDom.addClass("label");
        switch (status){
            case 1:
                labelStatusDom.addClass("label-primary");
                labelStatusDom.text("抓取中");
                break;
            case 2:
                labelStatusDom.addClass("label-success");
                labelStatusDom.text("成功了");
                break;
            case -1:
                labelStatusDom.addClass("label-danger");
                labelStatusDom.text("失败了");
                break;
            default:
                labelStatusDom.addClass("label-default");
                labelStatusDom.text("发呆中");
        }
    }

    function setProgress(divProgressBarDom, progress) {
        // divProgressBarDom.css("width: " + progress + "%;");
        // divProgressBarDom.css("width: " + progress + "%;");
        divProgressBarDom.css("width", progress + "%");
        divProgressBarDom.attr("aria-valuenow",progress);
        divProgressBarDom.text(progress + "%");
    }

    function setButtons(buttonStartDom,status) {
        if(status === 1){
            buttonStartDom.attr("disabled","disabled");
            // buttonStopDom.removeAttr("disabled");
        }else{
            // buttonStopDom.attr("disabled","disabled");
            buttonStartDom.removeAttr("disabled");

        }
    }

    function setDownloadList(ulDownloadListDom, buttonDownloadDom, fileList,jobName) {
        ulDownloadListDom.empty();
        if(fileList.length > 0){
            buttonDownloadDom.removeAttr("disabled");
        }else{
            buttonDownloadDom.attr("disabled","disabled");
        }
        $.each(fileList,function(index,file){
            ulDownloadListDom.append('<li><a href="' + contextPath + 'job/' + jobName + '/file/' + file + '">' + file + '</a></li>');
        });

    }

    function refreshStatus() {
            $.ajax({
                url: contextPath + 'jobs',
                dataType: "json",
                success: function(data){

                    var json = data;
                    if(json.success === true){

                        var jobs = json.jobs;
                        $.each(jobs,function(index,job){
                            var trDom = $(".job-" + job.jobName.replace(".","\\."));

                            var labelStatusDom =trDom.find("td.col-status label");
                            setStatus(labelStatusDom,job.status);

                            var divProgressBarDom = trDom.find("td.col-progress div.progress div.progress-bar");
                            setProgress(divProgressBarDom,job.progress);

                            var buttonStartDom = trDom.find(".button-start");
                            // var buttonStopDom = trDom.find(".button-stop");
                            setButtons(buttonStartDom,job.status);

                            var ulDownloadListDom = trDom.find(".download-list");
                            var buttonDownloadDom = trDom.find(".button-download");
                            setDownloadList(ulDownloadListDom,buttonDownloadDom,job.fileList,job.jobName);
                        });
                    }else{
                        showAlert('',json.message,'danger');
                    }

                }
            })
                .fail(function () {
                    //showAlert('','刷新失败！','danger');
                });
    }
    function refreshStatusLoop(){
        refreshStatus();
        setTimeout(refreshStatusLoop,1*1000);
    }

    $(".button-start").on("click",function(){
        var jobName = $(this).attr("job-name");
        onStartStop(jobName,'start');
    });
    $(".button-stop").on("click",function(){
        var jobName = $(this).attr("job-name");
        onStartStop(jobName,'stop');
    });
    //每半分钟刷一下页面
    setTimeout(refreshStatusLoop,1*1000);
});