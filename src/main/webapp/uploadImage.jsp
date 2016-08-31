<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传图片</title>
    <link type="text/css" rel="stylesheet" href="css/common.css"/>
    <link type="text/css" rel="stylesheet" href="css/cropper.css"/>
    <style type="text/css">
        html, body {
            width: 100%;
            padding-top: 0;
            background-color: #FFF;
        }

        div.btn-box {
            display: table-cell;
            width: 390px;
            height: 360px;
            text-align: center;
            vertical-align: middle;
        }

        form.uploadImage {
            width: 100%;
        }

        div.cropper-box {
            width: 100%;
            height: 360px;
            overflow: hidden;
            border: 1px solid #DDD;
        }

        label.btn-upload {
            border: 1px solid #DDD;
            background-color: #f7f8f9;
            margin-bottom: 10px;
        }

        div.img-container {
            width: 280px;
            height: 280px;
            overflow: hidden;
            margin: 0 auto;
        }

        div.img-container img {
            width: 100%;
        }

        div.left {
            width: 360px;
            border-right: 1px solid #DDD;
            overflow: hidden;
            float: left;
        }

        div.right {
            width: 170px;
            float: right;
        }

        div.bottom-btn {
            width: 100%;
            padding: 10px 0;
            text-align: right;
        }

        div.bottom-btn button.ipt-submit {
            border: 1px solid #DDD;
            background-color: #DDD !important;
        }

        div.bottom-btn button.ipt-cancel {
            border: 1px solid #DDD;
            background-color: #FFF !important;
        }

        div.coordinate {
            display: none;
        }

        div.docs-preview {
            width: 120px;
            text-align: center;
            font-size: 16px;
            margin: 25px auto;
        }

        div.img-preview {
            width: 120px;
            height: 120px;
            overflow: hidden;
            border: 1px solid #DDD;
            margin-top: 15px;
        }

        div.img-preview img {
            width: 100%;
        }
    </style>
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="css/ie8.css"/>
    <![endif]-->
</head>
<body>
<form action="#" method="post" encType="application/x-www-form-urlencoded" class="uploadImage">
    <div class="cropper-box">
        <div class="left">
            <div class="btn-box">
                <label class="btn btn-upload" for="inputImage" title="Upload image file">
                    <input class="sr-only" id="inputImage" name="file" type="file"
                           accept="image/jpeg,image/png,image/jpg">
                    <span class="docs-tooltip" data-toggle="tooltip" title="Import image with Blob URLs"><span class="">上传图片</span></span>
                </label>
                <p style="color: #9199a0;">(只支持JPG、PNG，大小不超过10M)</p>
            </div>
            <div class="img-container">
                <img src="images/head.png" alt="logo">
            </div>
        </div>
        <div class="right">
            <div class="docs-preview">
                <p>头像预览</p>
                <div class="img-preview"></div>
                <div class="errorInfo"></div>
            </div>
            <div class="coordinate">
                <input type="text" name="dataX" id="dataX"/>
                <input type="text" name="dataY" id="dataY"/>
                <input type="text" name="dataHeight" id="dataHeight"/>
                <input type="text" name="dataWidth" id="dataWidth"/>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="bottom-btn">
        <button type="button" name="submit" class="btn ipt-cancel">取消</button>
        <button type="button" name="submit" class="btn ipt-submit">确定</button>
    </div>
</form>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/cropper/cropper.js"></script>
<script type="text/javascript" src="js/cropper/main.js"></script>
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<script type="text/javascript">
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    //允许最大上传体积大小，单位（KB）
    var imageMaxSize = 1024 * 1024 * 10;
    //判断图片大小是否在允许上传大小范围内
    function checkSize(target) {
        var fileSize = 0;
        var file;
        if (isIE && !target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            file = fileSystem.GetFile(filePath);
        } else {
            file = target.files[0];
        }
        if (file == undefined) {
            return 0;
        }

        if (!/^image\/(jpeg|jpg|png)$/.test(file.type)) {
            return 3;
        }
        return file.size < imageMaxSize ? 1 : 2;
    }

    //小图大小修复（firefox中不显示问题）
    function fixSize() {
        return $('form.uploadImage .img-preview').width() < 150;
    }

    //提交前校验判断
    function showRequest(formData, jqForm, options) {
        switch (checkSize($('#inputImage')[0])) {
            case 0:
                $('div.errorInfo').text('请先上传图片');
                return false;
                ;
            case 1:
                return true;
                ;
            case 2:
                $('div.errorInfo').text('上传图片不能大于:' + (imageMaxSize / (1024 * 1024)).toFixed(2) + 'MB');
                return false;
                ;
            case 3:
                $('div.errorInfo').text('请选择正确图片文件');
                return false;
                ;
        }
    }

    $(window).ready(function () {
        showCropper();          //加载裁剪控件
        //选择图片
        $('#inputImage').change(function () {
            if (showRequest()) {
                $('div.errorInfo').text('');           //清空错误信息
                if (fixSize()) {
                    $('form.uploadImage .img-preview').width(150).height(150);    //修改小图高度
                }
                $('div.btn-box').height(80);
                $('form.uploadImage .img-preview').width(120).height(120);
            }
        });

        //弹窗取消
        $('div.bottom-btn button.ipt-cancel').on('click', function () {
            window.parent.closeCutImageDialog();
        });

        //保存裁剪图片
        $('form.uploadImage button.ipt-submit').on('click', function () {
            if (showRequest()) {
                var serviceImgUrl = $('form.uploadImage .cropper-canvas img').attr('src');
                window.parent.hideCutImageDialog($('.img-container > img').cropper("getCroppedCanvas"), serviceImgUrl);     //关闭弹窗并赋值参数
            }
        });
    });
</script>
</body>
</html>