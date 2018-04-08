<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 实例 - 进度条</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="/dwr/engine.js"></script>
    <script src="/dwr/util.js"></script>
    <script src="/dwr/interface/AssignProgress.js"></script>
</head>
<body>
<% String id = request.getParameter("id");%>
<div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="0" id="<%=id%>"
         aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
        <span class="sr-only">40% 完成</span>
    </div>
</div>

<button onclick="add()">添加</button>

</body>
</html>

<script>
    
    function init() {
        dwr.engine.setActiveReverseAjax(true);
    }
    init();

    function setProgress(id,value,data) {

        $("#"+id).attr("style","width:"+value+"%");
        console.log(data);
    }
    var value = 0;
    function add() {
        value = value + 10;
        setProgress("progress",value);
    }
    AssignProgress.doWork("<%=id%>","setProgress");
</script>