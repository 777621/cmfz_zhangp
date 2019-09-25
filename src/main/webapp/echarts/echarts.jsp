<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="${path}/js/echarts.js"></script>
<script src="${path}/js/jquery.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    $(function () {
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '用户注册量',   //标题内容
                link: "${path}/main/main.jsp"
            },
            tooltip: {},      //鼠标提示
            legend: {
                data:['男','女']  //选项卡
            },
            xAxis: {    //横轴内容
                data: ["1月","2月","3月","4月","5月","6月"]
            },
            yAxis: {},   //纵轴自适应
            series: [{
                name: '男',
                type: 'line',
                data: [5, 20, 36, 10, 10, 20]
            },{
                name: '女',
                type: 'bar',
                data: [10, 25, 5, 20, 9, 7]
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    });

</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--兼容IE浏览器-->
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>
</head>
<body>
<!--为 ECharts 准备一个具备高宽的 DOM 容器-->
<div id="main" style="width: 600px;height: 400px;"></div>

</body>
</html>