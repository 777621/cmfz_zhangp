<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>


    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                </button>
                <a class="navbar-brand" href="#">持法名州后台管理系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎<span class="navbar-primary">${admin.name}</span></a></li>
                    <li class="dropdown">
                        <a href="${path}/admin/logout">退出登录 <span class="glyphicon glyphicon-log-out"></span></a>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <!--栅格系统-->
        <!--左边手风琴部分-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" align="center">
                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-info">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-warning collapse" role="tabpanel" aria-labelledby="headingOne">
                            <a href="javascript:$('#mainid').load('${path}/user/user.jsp')" class="btn btn-warning">用户信息</a><br/>
                            <a href="javascript:$('#mainid').load('${path}/echarts/echarts.jsp')" class="btn btn-warning">用户统计</a><br/>
                            <a href="javascript:$('#mainid').load('${path}/echarts/china.jsp')" class="btn btn-warning">用户分布</a><br/>
                        </div>
                    </div>
                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-success">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    轮播图管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <a href="javascript:$('#mainid').load('${path}/banner/banner.jsp')" class="btn btn-warning">展示轮播图</a>
                        </div>
                    </div>
                    </div>
                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-info">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    专辑管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <a href="javascript:$('#mainid').load('${path}/album/album.jsp')" class="btn btn-warning">专辑信息</a>
                            </div>
                        </div>
                    </div>
                        <div class="panel panel-danger">
                            <div class="panel-heading" role="tab" id="headingfore">
                                <h4 class="panel-danger">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFore" aria-expanded="false" aria-controls="collapseFore">
                                        文章管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseFore" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFore">
                                <div class="panel-body">
                                    <a href="javascript:$('#mainid').load('${path}/article/article.jsp')" class="btn btn-warning">文章信息</a>
                                </div>
                            </div>
                        </div>
                            <div class="panel panel-warning">
                                <div class="panel-heading" role="tab" id="headingSix">
                                    <h4 class="panel-warning">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                            上师管理
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                                    <div class="panel-body">
                                        <a href="#" class="btn btn-warning"></a>
                                    </div>
                                </div>
                            </div>
                                <div class="panel panel-info">
                                    <div class="panel-heading" role="tab" id="headingSeven">
                                        <h4 class="panel-info">
                                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                                管理员管理
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
                                        <div class="panel-body">
                                            <a href="#" class="btn btn-warning">用户信息</a>
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </div>

        <!--巨幕开始-->

 <div class="col-md-9" id="mainid">
            <div class="jumbotron">
                <h3>欢迎来到持明法州后台管理系统</h3>
            </div>
        <!--右边轮播图部分-->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!--轮播图下标-->
                <ol class="carousel-indicators">
                    <!--data-target与id保持一致-->
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>
                <!--轮播图的实际内容 --><div class="carousel-inner">
                <div class="item active">
                    <!--需要轮播的图片路径-->
                    <img src="${path}/bootstrap/img/shouye.jpg" alt="">
                    <!--图片介绍-->
                    <div class="carousel-caption">
                        1
                    </div>
                </div>
                <div class="item">
                    <img src="${path}/bootstrap/img/1.png" alt="...">
                    <div class="carousel-caption">
                        2
                    </div>
                </div>

            </div>


                <!--页脚-->
                <div class="footer-info w1100">
                    <div class="info-text w1100">
                        <p class="copyright" align="center">
                            <a href="#" >@百知教育zhangp@zparkhr.com</a>
                        </p>
                    </div>
                </div>
            </div>
    <!--栅格系统-->
 </div>
        </div>
    </div>
</body>
</html>
