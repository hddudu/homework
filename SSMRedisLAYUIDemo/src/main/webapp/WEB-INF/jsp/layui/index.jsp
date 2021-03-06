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
<script src="${pageContext.request.contextPath}/resources/static/js/index/sliders.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/index/html5.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/layui/css/modules/layer/default/layer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/global.css"/>
<script type="text/javascript">
$(function(){
	var oLi = document.getElementById("tab").getElementsByTagName("li");
	var oUl = document.getElementById("ms-main").getElementsByTagName("div");
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "cur";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		}	
	}
});
</script>
<!--
-->
</head>
<body>
<div class="layui-header header">
  <div class="main">
    <ul class="layui-nav layui-nav-left" lay-filter="filter">
      <a class="logo" href="index.jsp" title="Fly">Fly</a>
      <li class="layui-nav-item layui-this nav-left">
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

<div class="layui-container container">
  <div class="layui-row layui-col-space20">
    <div class="layui-col-md8">
      <div class="carousel">
        <div class="layui-carousel" id="images-carousel">
          <div carousel-item>
            <div>
              <a href="article_details.jsp"><img lay-src="${pageContext.request.contextPath}/resources/static/images/1.jpeg" width="100%" height="280px;" /></a>
            </div>
            <div>
              <img lay-src="${pageContext.request.contextPath}/resources/static/images/2.jpeg" width="100%" height="280px;" />
            </div>
            <div>
              <img lay-src="${pageContext.request.contextPath}/resources/static/images/3.jpg" width="100%" height="280px;" />
            </div>
            <div>
              <img lay-src="${pageContext.request.contextPath}/resources/static/images/4.jpg" width="100%" height="280px;" />
            </div>
            <div>
              <img lay-src="${pageContext.request.contextPath}/resources/static/images/5.jpg" width="100%" height="280px;" />
            </div>
          </div>
        </div>
      </div>
      <div class="article-main">
        <h2>
          文章推荐
        </h2>

        <div class="article-list">
          <figure><img lay-src="${pageContext.request.contextPath}/resources/static/images/01.jpg"></figure>
          <ul>
            <h3>
              <a href="article_details.jsp">住在手机里的朋友</a>
            </h3>
            <p>现在跨界联姻，时尚、汽车以及运动品牌联合手机制造商联合发布手机产品在行业里已经不再新鲜，上周我们给大家报道过著名手表制造商瑞士泰格·豪雅（Tag Heuer） 联合法国的手机制造商Modelabs发布的一款奢华手机的部分谍照，而近日该手机终于被正式发布了${pageContext.request.contextPath}/resources.</p>
            <p class="autor">
              <span class="lm f_l"><a href="#">个人博客</a></span>
              <span class="dtime f_l">2014-02-19</span>
              <span class="viewnum f_r">浏览（<a href="#">459</a>）</span>
              <span class="pingl f_r">评论（<a href="#">30</a>）</span></p>
          </ul>
        </div>

        <div class="article-list">
          <figure><img lay-src="${pageContext.request.contextPath}/resources/static/images/01.jpg"></figure>
          <ul>
            <h3>
              <a href="article_details.jsp">住在手机里的朋友</a>
            </h3>
            <p>通信时代，无论是初次相见还是老友重逢，交换联系快餐式的友谊 ${pageContext.request.contextPath}/resources.</p>
            <p class="autor">
              <span class="lm f_l"><a href="#">个人博客</a></span>
              <span class="dtime f_l">2014-02-19</span>
              <span class="viewnum f_r">浏览（<a href="#">459</a>）</span>
              <span class="pingl f_r">评论（<a href="#">30</a>）</span></p>
          </ul>
        </div>
        <div class="article-list">
          <figure><img lay-src="${pageContext.request.contextPath}/resources/static/images/02.jpg"></figure>
          <ul>
            <h3>
              <a href="article_details.jsp">住在手机里的朋友</a>
            </h3>
            <p>通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是
              彼此交换名片，然后郑重或是出于礼貌用手机记的过客，
              通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后
              郑重或是出于礼貌用手机记的过客，这种快餐式的通信时代，无论是初次相见还是老友
              重逢，通信时代，无论是初次相见还是老友重种快餐式的这种快餐式的友谊 ${pageContext.request.contextPath}/resources.</p>
            <p class="autor">
              <span class="lm f_l"><a href="#">个人博客</a></span>
              <span class="dtime f_l">2014-02-19</span>
              <span class="viewnum f_r">浏览（<a href="#">459</a>）</span>
              <span class="pingl f_r">评论（<a href="#">30</a>）</span></p>
          </ul>
        </div>
        <div class="article-list">
          <figure><img lay-src="${pageContext.request.contextPath}/resources/static/images/02.jpg"></figure>
          <ul>
            <h3>
              <a href="article_details.jsp">住在手机里的朋友</a>
            </h3>
            <p>通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是
              彼此交换名片，然后郑重或是出于礼貌用手机记的过客，
              通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后
              郑重或是出于礼貌用手机记的过客，这种快餐式的通信时代，无论是初次相见还是老友
              重逢，通信时代，无论是初次相见还是老友重种快餐式的这种快餐式的友谊 ${pageContext.request.contextPath}/resources.</p>
            <p class="autor">
              <span class="lm f_l"><a href="#">个人博客</a></span>
              <span class="dtime f_l">2014-02-19</span>
              <span class="viewnum f_r">浏览（<a href="#">459</a>）</span>
              <span class="pingl f_r">评论（<a href="#">30</a>）</span></p>
          </ul>
        </div>
        <div class="article-list">
          <figure><img lay-src="${pageContext.request.contextPath}/resources/static/images/02.jpg"></figure>
          <ul>
            <h3>
              <a href="article_details.jsp">住在手机里的朋友</a>
            </h3>
            <p>通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是
              彼此交换名片，然后郑重或是出于礼貌用手机记的过客，
              通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后
              郑重或是出于礼貌用手机记的过客，这种快餐式的通信时代，无论是初次相见还是老友
              重逢，通信时代，无论是初次相见还是老友重种快餐式的这种快餐式的友谊 ${pageContext.request.contextPath}/resources.</p>
            <p class="autor">
              <span class="lm f_l"><a href="#">个人博客</a></span>
              <span class="dtime f_l">2014-02-19</span>
              <span class="viewnum f_r">浏览（<a href="#">459</a>）</span>
              <span class="pingl f_r">评论（<a href="#">30</a>）</span></p>
          </ul>
        </div>

        <div class="article-list">
          <figure><img lay-src="${pageContext.request.contextPath}/resources/static/images/05.jpg"></figure>
          <ul>
            <h3>
              <a href="article_details.jsp">住在手机里的朋友</a>
            </h3>
            <p>通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是
              彼此交换名片，然后郑重或是出于礼貌用手机记的过客，
              通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后
              郑重或是出于礼貌用手机记的过客，这种快餐式的通信时代，无论是初次相见还是老友
              重逢，通信时代，无论是初次相见还是老友重种快餐式的这种快餐式的友谊 ${pageContext.request.contextPath}/resources.</p>
            <p class="autor">
              <span class="lm f_l"><a href="#">个人博客</a></span>
              <span class="dtime f_l">2014-02-19</span>
              <span class="viewnum f_r">浏览（<a href="#">459</a>）</span>
              <span class="pingl f_r">评论（<a href="#">30</a>）</span></p>
          </ul>
        </div>
      </div>
      <!--分页-->
      <div id="page"></div>
    </div>
    <div class="layui-col-md4">
        <form class="layui-form" method="post" action="message.jsp">
            <div class="layui-form-item">
              <div class="layui-input-inline" style="width:76%;"> 
                <input type="text" name="title" lay-verify="required" placeholder="请输入关键字" class="layui-input">
              </div>
              <button class="layui-btn" lay-filter="*" lay-submit>搜索</button>
            </div>
         </form>
      <div class="ad"> <img lay-src="${pageContext.request.contextPath}/resources/static/images/ad.jpg"> </div>
      <div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a>点击排行</a></li>
          <li><a>最新文章</a></li>
          <li><a>站长推荐</a></li>
          <li><a>最新评论</a></li>
        </ul>
      </div>
      <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
          <ul>
            <li><a href="#" target="_blank">住在手机里的朋友</a></li>
            <li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码${pageContext.request.contextPath}/resources.</a></li>
            <li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
            <li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
            <li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码${pageContext.request.contextPath}/resources.</a></li>
            <li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="#" target="_blank">住在手机里的朋友</a></li>
            <li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>

            <li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="#" target="_blank">住在手机里的朋友</a></li>
            <li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
            <li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码${pageContext.request.contextPath}/resources.</a></li>
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
            <li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码${pageContext.request.contextPath}/resources.</a></li>
            <li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="#" target="_blank">住在手机里的朋友</a></li>
            <li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
          </ul>
        </div>
      </div>
      <div class="cloud">
        <h3>标签云</h3>
        <ul>
          <li><a href="#">个人博客</a></li>
          <li><a href="#">web开发</a></li>
          <li><a href="#">前端设计</a></li>
          <li><a href="#">Html</a></li>
          <li><a href="#">CSS3</a></li>
          <li><a href="#">Html5+css3</a></li>
          <li><a href="#">百度</a></li>
          <li><a href="#">Javasript</a></li>
          <li><a href="#">web开发</a></li>
          <li><a href="#">前端设计</a></li>
          <li><a href="#">Html</a></li>
          <li><a href="#">CSS3</a></li>
          <li><a href="#">Html5+css3</a></li>
          <li><a href="#">百度</a></li>
        </ul>
      </div>
      <div class="tuwen">
        <h3>图文推荐</h3>
        <ul>
          <li><a href="#"><img lay-src="${pageContext.request.contextPath}/resources/static/images/01.jpg"><b>住在手机里的朋友</b></a>
            <p><span class="tulanmu"><a href="#">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img lay-src="${pageContext.request.contextPath}/resources/static/images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
            <p><span class="tulanmu"><a href="#">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img lay-src="${pageContext.request.contextPath}/resources/static/images/03.jpg"><b>手机的16个惊人小秘密，据说${pageContext.request.contextPath}/resources.</b></a>
            <p><span class="tulanmu"><a href="#">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img lay-src="${pageContext.request.contextPath}/resources/static/images/06.jpg"><b>住在手机里的朋友</b></a>
            <p><span class="tulanmu"><a href="#">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
          <li><a href="#"><img lay-src="${pageContext.request.contextPath}/resources/static/images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
            <p><span class="tulanmu"><a href="#">手机配件</a></span><span class="tutime">2015-02-15</span></p>
          </li>
        </ul>
      </div>
      <div class="ad"> <img lay-src="${pageContext.request.contextPath}/resources/static/images/03.jpg"> </div>
      <div class="links">
        <h3><span>[<a href="#">申请友情链接</a>]</span>友情链接</h3>
        <ul>
          <li><a href="#">web开发</a></li>
          <li><a href="#">前端设计</a></li>
          <li><a href="#">Html</a></li>
          <li><a href="#">CSS3</a></li>
          <li><a href="#">Html5+css3</a></li>
          <li><a href="#">百度</a></li>
        </ul>
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
