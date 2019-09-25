<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        $("#bntable").jqGrid({
            styleUI:"Bootstrap",
            url:"${path}/banner/findAll",
            autowidth:true,
            datatype:"json",
            pager:"#pager",
            rowNum:2,
            rowList:[3,6,9,12],
            viewrecords:true,
            editurl:"${path}/banner/edit",
            colNames:['ID','名称','图片','状态','描述','上传时间'],
            colModel:[
                {name:'id'},
                {name:'name',editable:true},
                {name:'img_path',editable:true,edittype:"file",
                    formatter:function (cellValue) {
                        return "<img src='${path}/upload/photo/"+cellValue+"' style='width:100px;height:80px'>";
                    }
                },
                {name:"status",align:"center",
                    formatter:function (cellValue,option,rows) {
                        if(cellValue==1){
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rows.id+"\",\""+cellValue+"\")'>不展示</button>";
                        }else{
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rows.id+"\",\""+cellValue+"\")'>展示</button>";
                        }
                    }
                },
                {name:"description",editable:true},
                {name:"up_date"},
            ]
        });
        $("#bntable").jqGrid("navGrid","#pager",{edit:true,add:true,del:true,addtext:"添加",edittext:"修改",deltext:"删除"},
        {
            closeAfterEdit:true,
            beforeShowForm:function (obj) {
                obj.find("#img_path").attr("disabled",true)
            }
        },
        {
            closeAfterAdd:true,
            afterSubmit:function(data) {
                alert(data);
                $.ajaxFileUpload({
                    url:"${path}/banner/bannerUpload",
                    datatype:"json",
                    type:"post",
                    fileElementId:"img_path",
                    data:{id:data.responseText},
                    success:function(){
                        //刷新表单
                        $("#bntable").trigger("reloadGrid");
                    }
                });
                return "sss";

            }
        },
        {closeAfterDel:true}
        ); //开启JQGRID的增删改

    });
    function updateStatus(id,status) {
        if(status==1){
            $.ajax({
                url:"${path}/banner/updateStatus",
                type:"post",
                dataType:"json",
                data:{"id":id,"status":"2"},
                success:function () {
                    //刷新表单
                    $("#bntable").trigger("reloadGrid");
                }
            });
        }else{
            $.ajax({
                url:"${path}/banner/updateStatus",
                type:"post",
                dataType:"json",
                data:{"id":id,"status":"1"},
                success:function () {
                    //刷新表单
                    $("#bntable").trigger("reloadGrid");
                }
            });
        }
    }
</script>
<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>轮播图信息</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">轮播图信息</a></li>
    </ul>
    <!--初始表单-->
    <table id="bntable"></table>
    <div id="pager"></div>
</div>