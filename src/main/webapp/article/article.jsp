<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id',{
            uploadJson:"${path}/article/upload",//指定上传图片的路径
            filePostName:"photo",  //上传图片的名称
            allowFileManager:true, //是否展示浏览远程按钮
            fileManagerJson:"${path}/article/queryPhoto", //浏览远程图片的路径
            afterBlur:function () { //kindEditor失去焦点后执行
                this.sync();  //将kindEditor中的内容同步到表单中
            }
        }

    );


</script>
<script>
    $(function () {
        $("#bntable").jqGrid({
            styleUI:"Bootstrap",
            url:"${path}/article/findAll",
            autowidth:true,
            datatype:"json",
            pager:"#pager",
            rowNum:2,
            rowList:[3,6,9,12],
            viewrecords:true,
            editurl:"${path}/article/edit",
            colNames:['ID','名称','作者','内容','创建时间','状态','上师id'],
            colModel:[
                {name:'id'},
                {name:'title',editable:true},
                {name:'author'},
                {name:'content'},
                {name:"create_date",align:"center"},
                {name:"status",
                    formatter:function (cellValue,option,rows) {
                        if(cellValue==1){
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rows.id+"\",\""+cellValue+"\")'>不展示</button>";
                        }else{
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rows.id+"\",\""+cellValue+"\")'>展示</button>";
                        }
                    }
                },
                {name:"t_id",editable:true},
            ]
        });
        $("#bntable").jqGrid("navGrid","#pager",{edit:false,add:false,del:true,deltext:"删除"}

        ); //开启JQGRID的增删改
        //点击按钮触发事件
        $("#btn1").click(function () {
            //只读属性 选择最后点击的id
            var rowId = $("#bntable").jqGrid("getGridParam","selrow");
            if(rowId!=null) {
                //根据行id获取行数据
                var row = $("#bntable").jqGrid("getRowData",rowId);
                $("#title").val(row.title);
                $("#author").val(row.author);
                KindEditor.html("#editor_id",row.content);
                //展示模态框
                $("#myModal").modal("show");
                $("#myFooter").html("<button class='btn btn-default' onclick='updateArticle(\""+rowId+"\")'>修改</button>" +
                    "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
            }else{
                alert("请选择一行");
            }
        });
        //添加操作
        $("#btn2").click(function () {
            //清空表单
            $("#myForm")[0].reset();
            KindEditor.html("#editor_id","");
            //展示模态框
            $("#myModal").modal("show");
            $("#myFooter").html("<button class='btn btn-default' onclick='addArticle()'>提交</button>" +
                "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
        });
    });
    //点击添加按钮  添加文章
    function addArticle() {
        $.ajax({
           url:"${path}/article/add",
           type:"post",
           dataType:"json",
           data:$("#myForm").serialize(),
           success:function () {
               //关闭模态框
               $("#myModal").modal('hide');
               //刷新页面
               $("#bntable").trigger("reloadGrid");
           }
        });
    }
    //点击修改按钮  修改文章
    function updateArticle(rowId) {
        $.ajax({
           url:"${path}/article/update?id="+rowId,
           type:"post",
           dataType:"json",
           data:$("#myForm").serialize(),
           success:function () {
               //关闭模态框
               $("#myModal").modal('hide');
               //刷新页面
               $("#bntable").trigger("reloadGrid");
           } 
        });
    }
    //删除
    $("#btn3").click(function () {
        //只读属性 选择最后点击的id
        var rowId = $("#bntable").jqGrid("getGridParam","selrow");
        if(rowId!=null) {
            $.ajax({
                url:"${path}/article/delete?id="+rowId,
                type:"post",
                dataType:"json",
                data:$("#myForm").serialize(),
                success:function () {
                    //关闭模态框
                    $("#myModal").modal('hide');
                    //刷新页面
                    $("#bntable").trigger("reloadGrid");
                }
            });
        }else{
            alert("请选择一行");
        }
    });
</script>
<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>文章信息</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">文章信息</a></li>
    </ul>
    <div class="panel panel-body">
        <button id="btn1" class=" btn btn-info">展示文章信息</button>&emsp;
        <button id="btn2" class="btn btn-info">添加文章</button>&emsp;
        <button id="btn3" class="btn btn-info">删除文章</button>
    </div>
    <!--初始表单-->
    <table id="bntable"></table>
    <div id="pager"></div>
    <!-- Modal 模态框 -->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:730px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">文章信息</h4>
                </div>
                <div class="modal-body">
                    <form id="myForm">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">名称</span>
                            <input id="title" name="title" type="text" class="form-control"aria-describedby="basic-addon1">
                        </div><br><br/>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon2">作者</span>
                            <input id="author" name="author" type="text" class="form-control" aria-describedby="basic-addon2">
                        </div><br><br/>
                        <div align="center" class="input-group">
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                        </textarea>
                    </div>
                    </form>
                </div>
                <div class="modal-footer" id="myFooter">
                    <!--
                    <button type="button" class="btn btn-default">提交</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                    -->
                </div>
            </div>
        </div>
    </div>
</div>