<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
<meta name="keywords" content="前端模板">
<meta name="description" content="前端模板">

<script src="${pageContext.request.contextPath}/resources/static/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/layui/layui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/index.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/freezeheader.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/layui/lay/modules/layer.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/sliders.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/html5.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/layui/css/modules/layer/default/layer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/global.css"/>
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
            <li class="layui-nav-item">
              <a href="time_line.jsp">时间轴</a>
            </li>
            <li class="layui-nav-item">
              <a href="time_line.jsp">相册</a>
            </li>
            </ul>
            <ul class="layui-nav layui-layout-right layui-nav-right" lay-filter="filter">
           
            <li class="layui-nav-item">
              <a href="home.jsp">个人中心<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item">
              <a href="javascript:;"><img src="${pageContext.request.contextPath}/resources/static/images/head.jpg" class="layui-nav-img">我</a>
              <dl class="layui-nav-child">
                <dd><a href="javascript:;">修改信息</a></dd>
                <dd><a href="javascript:;">安全管理</a></dd>
                <dd><a href="javascript:;">退了</a></dd>
              </dl>
            </li>
          </ul>
       </div>
    </div>

    <div class="layui-container container">
          <div class="fly-none">
              <h2><i class="iconfont icon-404"></i></h2>
              <p>你瞧！页面或者数据被<a href="#"> 纸飞机 </a>运到火星了</p>
        </div>
    </div>
    <div class="footer">
        <hr class="layui-bg-red">
      <p><a href="http://itdaima.com/">layui框架模板</a> 2017 &copy; <a href="#">itdaima.com</a></p>
      <p>layui框架模板</p>
    </div></body>
</html>