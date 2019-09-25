<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        $("#bntable").jqGrid({
            styleUI:"Bootstrap",
            url:"${path}/user/findAll",
            autowidth:true,
            datatype:"json",
            pager:"#pager",
            rowNum:2,
            rowList:[3,6,9,12],
            viewrecords:true,
            colNames:['ID','图片','姓名','昵称','密码','性别','状态','手机号','创建时间',"地址"],
            colModel:[
                {name:'id'},
                {name:'cover',editable:true,edittype:"file",
                    formatter:function (cellValue) {
                        return "<img src='${path}/upload/photo/"+cellValue+"' style='width:100px;height:80px'>";
                    }
                },
                {name:'name',editable:true},
                {name:"nikename",align:"center"},
                {name:"password"},
                {name:"sex"},
                {name:"status",
                    formatter:function (cellValue,option,rows) {
                        if(cellValue==1){
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rows.id+"\",\""+cellValue+"\")'>不展示</button>";
                        }else{
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rows.id+"\",\""+cellValue+"\")'>展示</button>";
                        }
                    }
                },
                {name:"phone",editable:true},
                {name:"create_date"},
                {name:"address"},
            ]
        });
        $("#bntable").jqGrid("navGrid","#pager",{edit:true,add:true,del:true,addtext:"添加",edittext:"修改",deltext:"删除"}

        ); //开启JQGRID的增删改

    });
    function updateStatus(id,status) {
        if(status==1){
            $.ajax({
                url:"${path}/user/updateStatus",
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
                url:"${path}/user/updateStatus",
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
    $("#btn1").click(function () {
        $.ajax({
            url:"${path}/user/showAll",
            type:"post",
            dataType:"json",
            data:{},
            success:function () {
                //刷新表单
                $("#bntable").trigger("reloadGrid");
            }
        });
    });
    $("#btnphone").click(function () {
        var phone = $("#phoneInput").val();
        alert(phone);
        $.post("${path}/user/phone?phone="+phone,function (data){},"json");
    });
</script>
<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>
    <div class="panel panel-body">
        <button id = "btn1" class="btn btn-info">导出用户数据</button>&nbsp;
        <div align="right">
            <input id="phoneInput" name="phone" type="text">&nbsp;
            <button id="btnphone" class="btn btn-info">发送验证码</button>
        </div>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户信息</a></li>
    </ul>
    <!--初始表单-->
    <table id="bntable"></table>
    <div id="pager"></div>

</div>