<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<meta name="keywords" content="前端模板">
<meta name="description" content="前端模板">

<script src="${pageContext.request.contextPath}/resources/static/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/layui/layui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/index.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/freezeheader.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/layui/lay/modules/layer.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/layui/css/modules/layer/default/layer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/global.css"/>

<!--
-->
</head>
<body>
	<div class="layui-header header">
    	<div class="main">
          <ul class="layui-nav layui-nav-left" lay-filter="filter">
            <a class="logo" href="index.jsp" title="Fly">Fly</a>
            <li class="layui-nav-item nav-left">
              <a href="index.jsp">首页</a>
            </li>
            <li class="layui-nav-item">
              <a href="article.jsp">文章</a>
            </li>
            <li class="layui-nav-item layui-this">
              <a href="time_line.jsp">时间轴</a>
            </li>
            <li class="layui-nav-item">
           		<a href="time_line.jsp">相册</a>
         	 </li>
            </ul>
            <ul class="layui-nav layui-layout-right layui-nav-right" lay-filter="filter">
            <li class="layui-nav-item">
              <a href="home.jsp">我的主页<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item">
              <a href="javascript:;"><img src="${pageContext.request.contextPath}/resources/static/images/head.jpg" class="layui-nav-img">我</a>
              <dl class="layui-nav-child">
                <dd><a href="article_pub.jsp">文章发布</a></dd>
                <dd><a href="set.jsp">修改信息</a></dd>
                <dd><a href="javascript:;">退了</a></dd>
              </dl>
            </li>
          </ul>
       </div>
    </div>
    <div class="layui-container container">
     <div class="layui-row layui-col-space15">
        <div class="layui-col-md9">
          <ul class="layui-timeline">
              <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                  <h3 class="layui-timeline-title">发展历程</h3>
                  <p>
                    一个想法
                  </p>
                </div>
              </li>
              <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                  <h3 class="layui-timeline-title">9月5日</h3>
                  <p>正式开发前端模板</p>
                </div>
              </li>
              <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                  <div class="layui-timeline-title">未来</div>
                  <p>未来很美好</p>
                </div>
              </li>
           </ul>
        </div>
        <div class="layui-col-md3">
          <div class="layui-bg-red">3/12</div>
        </div>
    </div>

<!--分页
<div id="page"></div>
-->
<div id="footer">
	
</div>

</body>
</html>
