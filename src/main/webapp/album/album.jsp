<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {
        $("#abtable").jqGrid({
            styleUI:"Bootstrap",
            url:"${path}/album/findAll",
            autowidth:true,
            datatype:"json",
            pager:"#abpage",
            rowNum:2,
            rowList:[3,6,9,12],
            viewrecords:true,
            editurl:"${path}/album/edit",
            colNames : [ 'ID', '名称', '评分', '作者','播音员', '集数','简介','封面','上架时间' ],
            colModel : [
                {name : 'id', width : 55},
                {name : 'title',width : 90,editable:true},
                {name : 'score',width : 80,align : "right",editable:true},
                {name : 'author',width : 80,align : "right",editable:true},
                {name : 'broadcast',width : 80,align : "right",editable:true},
                {name : 'count',width : 150,sortable : false,editable:true},
                {name : 'brief',editable:true},
                {name : 'cover_img',width : 100,editable:true,edittype:"file",
                    formatter:function (cellValue) {
                        return "<img src='${path}/upload/photo/"+cellValue+"' style='width:100px;height:80px'>";
                    }
                },
                {name : 'pub_date'}
            ],
                subGrid : true,   //是否开启子表格
                subGridRowExpanded : function(subgrid_id, row_id) {
                    addSubGrid(subgrid_id,row_id);
                }
            });
        $("#abtable").jqGrid('navGrid', '#abpage', {add : true, edit : true,del : true,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                closeAfterEdit:true,
                beforeShowForm:function (obj) {
                    obj.find("#img_path").attr("disabled",true)
                }
            },
            {
                closeAfterAdd:true,
                afterSubmit:function(data) {

                    $.ajaxFileUpload({
                        url:"${path}/album/albumUpload",
                        datatype:"json",
                        type:"post",
                        fileElementId:"cover_img",
                        data:{id:data.responseText},
                        success:function(){
                            //刷新表单
                            $("#abtable").trigger("reloadGrid");
                        }
                    });
                    return "sss";

                }
            },
            {closeAfterDel:true}
        );

    });
    function addSubGrid(subgridId,rowId) {
        var subgridTableId = subgridId + "table";
        var pagerId = subgridId+"page";
        //字表中创建表单  div工具栏
        $("#" + subgridId).html(
            "<table id='"+subgridTableId+"'/>" +
            "<div id='"+pagerId+"'/>");

        $("#" + subgridTableId).jqGrid(
            {
                url : "${path}/chapter/findAll?albumId="+rowId,
                datatype : "json",
                rowNum : 20,
                pager : "#"+pagerId,
                autowidth:true,
                styleUI:"Bootstrap",
                height:"auto",
                editurl:"${path}/chapter/edit?albumId="+rowId,
                colNames : [ 'ID',  '路径', '大小','时长','上传时间','专辑id','操作' ],
                colModel : [
                    {name : "id",  index : "num",width : 80,key : true},
                    {name : "url",index : "item",  width : 130,edittype:"file",editable:true},
                    {name : "size",index : "qty",width : 70,align : "right"},
                    {name : "duration",index : "unit",width : 70,align : "right"},
                    {name : "up_date",index : "total",width : 70,align : "right",sortable : false},
                    {name : "album_id"},
                    {name : "url",index : "total",width : 70,
                        formatter:function (value) {
                            return "<a href='#'onclick='play(\""+value+"\")'> <span class='glyphicon glyphicon-play-circle'/></a>&emsp;&nbsp;" +
                                "<a href='#'onclick='downloads(\""+value+"\")'> <span class='glyphicon glyphicon-download-alt'/></a>";
                        }
                    }
                ],
            });
        $("#" + subgridTableId).jqGrid('navGrid',
            "#"+pagerId, {edit : true, add : true, del : true,edittext:"修改",addtext:"添加",deltext:"删除"},
            {},
            {
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    alert(data)
                    alert(data.responseText)
                    console.log(data)
                    $.ajaxFileUpload({
                        url:"${path}/chapter/uploadChapter",
                        type:"post",
                        datatype:"json",
                        fileElementId:"url",
                        data:{id:data.responseText},
                        success:function () {
                            $("#"+subgridTableId).trigger("reloadGrid");
                        }
                    });
                }
            },
            {}
        );
    }
    //在线播放
    function play(name) {
        //alert("播放"+name);
        $("#myModal").modal("show");
        $("#myAudio").attr("src","${path}/upload/aodio/"+name);
    }
    //下载
    function downloads(name) {
        //alert("下载"+name);
        location.href="${path}/chapter/download?fileName="+name;
    }
</script>



<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>专辑信息</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">专辑信息</a></li>
    </ul>
    <!--初始表单-->
    <table id="abtable"></table>
    <!--分页工具栏-->
    <div id="abpage"></div>
    <!--点击播放模态框-->
    <div id = "myModal"class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="myAudio" controls/>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>