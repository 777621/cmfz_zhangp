<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!--页面添加脚本-->
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id',
            ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', '|', 'selectall', '-',
                'title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold',
                'italic', 'underline', 'strikethrough', 'removeformat', '|', 'image',
                'flash', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink', '|', 'about']
        );

    });
</script>
<!--在需要显示编辑器的位置添加textarea输入框-->
<div align="center">
<textarea id="editor_id" name="content" style="width:700px;height:300px;">

</textarea>
</div>