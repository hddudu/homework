<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的消息</title>
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

    <div class="layui-container container" style="padding-top:70px;">
   
    	<div class="main fly-user-main layui-clear">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
      <a href="home.jsp">
        <i class="layui-icon">&#xe609;</i>
        我的主页
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="user_center.jsp">
        <i class="layui-icon">&#xe612;</i>
        用户中心
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="set.jsp">
        <i class="layui-icon">&#xe620;</i>
        基本设置
      </a>
    </li>
    <li class="layui-nav-item layui-this">
      <a href="message.jsp">
        <i class="layui-icon">&#xe611;</i>
        我的消息
      </a>
    </li>
  </ul>

  
  <div class="fly-panel fly-panel-user">
    <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
	    <button class="layui-btn layui-btn-danger" id="LAY_delallmsg">清空全部消息</button>
	    <div  id="LAY_minemsg" style="margin-top: 10px;">
        <!--<div class="fly-none">您暂时没有最新消息</div>-->
        <ul class="mine-msg">
          <li data-id="123">
            <blockquote class="layui-elem-quote">
              <a href="/jump?username=Absolutely" target="_blank"><cite>Absolutely</cite></a>回答了您的求解<a target="_blank" href="article_details.jsp"><cite>layui后台框架</cite></a>
            </blockquote>
            <p><span>1小时前</span><a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a></p>
          </li>
          <li data-id="123">
            <blockquote class="layui-elem-quote">
              系统消息：欢迎使用 layui
            </blockquote>
            <p><span>1小时前</span><a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a></p>
          </li>
        </ul>
      </div>
	  </div>
	</div>

  </div>
</div>
    </div>
    <div class="footer">
        <hr class="layui-bg-red">
      <p><a href="http://itdaima.com/">layui框架模板</a> 2017 &copy; <a href="#">itdaima.com</a></p>
      <p>layui框架模板</p>
    </div>
    </body>
</html>