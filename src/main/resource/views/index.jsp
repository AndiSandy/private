<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2016/4/10
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件测试</title>
    <link rel="stylesheet" href="../assets/js/WebUploader/webuploader.css">

</head>
<body>
用来存放文件信息用来存放文件信息用来存放文件信息
<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>
</body>
<script src="../assets/js/jQuery/jquery-1.12.3.js"></script>
<script src="../assets/js/WebUploader/webuploader.js"></script>
<script src="../assets/js/jQuery/jquery-1.12.3.js"></script>
<script type="text/javascript">
    (function ($, applicationPath) {
        var applicationPath = applicationPath === "" ? "" : applicationPath || "../..";
        function initWebUpload(item, options) {
            var defaults = {
                hiddenInputId: "uploadifyHiddenInputId", // input hidden id
                onAllComplete: function (event) { }, // 当所有file都上传后执行的回调函数
                onComplete: function (event) { },// 每上传一个file的回调函数
                innerOptions: {},
                fileNumLimit: undefined,
                fileSizeLimit: undefined,
                fileSingleSizeLimit: undefined
            };

            var opts = $.extend({}, defaults, options);

            var target = $(item);
            var pickerid = guidGenerator().replace(/-/g, "");
            var uploaderStrdiv = '<div class="webuploader">' +
                    '<div id="thelist" class="uploader-list"></div>' +
                    '<div class="btns">' +
                    '<div id="' + pickerid + '">选择文件</div>' +
                        //'<a id="ctlBtn" class="btn btn-default">开始上传</a>' +
                    '</div>' +
                    '</div>';
            target.append(uploaderStrdiv);

            var $list = target.find('#thelist'),
                    $btn = target.find('#ctlBtn'),
                    state = 'pending',
                    uploader;

            var jsonData = {
                fileList: []
            };

            var webuploaderoptions = $.extend({

                        // swf文件路径
                        swf: applicationPath + '/Scripts/lib/webuploader/Uploader.swf',

                        // 文件接收服务端。
                        server: applicationPath + '/MvcPages/WebUploader/Process',

                        // 选择文件的按钮。可选。
                        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                        pick: '#' + pickerid,

                        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                        resize: false,
                        fileNumLimit: opts.fileNumLimit,
                        fileSizeLimit: opts.fileSizeLimit,
                        fileSingleSizeLimit: opts.fileSingleSizeLimit
                    },
                    opts.innerOptions);
            var uploader = WebUploader.create(webuploaderoptions);

            uploader.on('fileQueued', function (file) {
                var $list = $("#thelist");
                $list.append('<div id="' + file.id + '" class="item">' +
                        '<div class="info">' + file.name + '</div>' +
                        '<div class="state">等待上传...</div>' +
                        '<div class="del"></div>' +
                        '</div>');
            });
            uploader.on('uploadProgress', function (file, percentage) {
                var $li = $('#' + file.id),
                        $percent = $li.find('.progress .bar');

                // 避免重复创建
                if (!$percent.length) {
                    $percent = $('<span class="progress">' +
                            '<span  class="percentage"><span class="text"></span>' +
                            '<span class="bar" role="progressbar" style="width: 0%">' +
                            '</span></span>' +
                            '</span>').appendTo($li).find('.bar');
                }

                $li.find('div.state').text('上传中');
                //$li.find(".text").text(percentage * 100 + '%');
                $percent.css('width', percentage * 100 + '%');
            });
            uploader.on('uploadSuccess', function (file, response) {
                $('#' + file.id).find('div.state').text('已上传');
                var fileEvent = {
                    queueId: file.id,
                    name: file.name,
                    size: file.size,
                    type: file.type,
                    filePath: response.filePath
                };
                jsonData.fileList.push(fileEvent)
                opts.onComplete(fileEvent);

            });

            uploader.on('uploadError', function (file) {
                $('#' + file.id).find('div.state').text('上传出错');
            });

            uploader.on('uploadComplete', function (file) {
                $('#' + file.id).find('.progress').fadeOut();
                var fp = $("#" + opts.hiddenInputId);
                fp.val(JSON.stringify(jsonData));
                opts.onAllComplete(jsonData.fileList);
            });

            uploader.on('fileQueued', function (file) {
                uploader.upload();
            });

            uploader.on('filesQueued', function (file) {
                uploader.upload();
            });

            uploader.on('all', function (type) {
                if (type === 'startUpload') {
                    state = 'uploading';
                } else if (type === 'stopUpload') {
                    state = 'paused';
                } else if (type === 'uploadFinished') {
                    state = 'done';
                }

                if (state === 'uploading') {
                    $btn.text('暂停上传');
                } else {
                    $btn.text('开始上传');
                }
            });

            $btn.on('click', function () {
                if (state === 'uploading') {
                    uploader.stop();
                } else {
                    uploader.upload();
                }
            });
            //删除
            $list.on("click", ".del", function () {
                var $ele = $(this);
                var id = $ele.parent().attr("id");
                var deletefile = {};
                $.each(jsonData.fileList, function (index, item) {
                    if (item && item.queueId === id) {
                        deletefile = jsonData.fileList.splice(index, 1)[0];
                        $("#" + opts.hiddenInputId).val(JSON.stringify(jsonData));
                        $.post(/*自己发送处理删除指令*/, function (returndata) {
                            $ele.parent().remove();
                        });
                        return;
                    }
                });
            });

        }


        $.fn.powerWebUpload = function (options) {

            var ele = this;
            $.lazyload(applicationPath + "/Scripts/lib/webuploader/webuploader.css", function () { }, 'css');
            $.lazyLoad(applicationPath + "/Scripts/lib/webuploader/webuploader.min.js", function () {
                initWebUpload(ele, options);
            });

        }
    })(jQuery, applicationPath);

</script>
</html>
