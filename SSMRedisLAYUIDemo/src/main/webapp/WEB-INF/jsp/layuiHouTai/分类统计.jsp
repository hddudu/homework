<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <title>众创数字资产综合运营管理平台</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
</head>
<body>
	<div class="container">
		<div class="content-search">
			<form class="layui-form layui-form-pane" action="">
				<div class="layui-inline">
					<label class="layui-form-label">分类名称</label>
					<div class="layui-input-block">
						<input  autocomplete="off" placeholder="请输入分类名称" class="layui-input" type="text"/>
					</div>
				</div>			
				<div class="layui-inline">
					<label class="layui-form-label">开始时间</label>
					<div class="layui-input-inline">
						<input class="layui-input layui-date"  placeholder="开始时间" id="start">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">截止时间</label>
					<div class="layui-input-inline">
						<input class="layui-input layui-date" placeholder="结束时间" id="end">
					</div>
				</div>
				<div class="layui-inline"><button class="layui-btn" lay-submit="">查询</button></div>
			</form>
		</div>

		<fieldset class="layui-elem-field mt10">
		  <legend>2016-12-01 至 2016-12-28共计：</legend>
		  <div class="layui-field-box">
			<table class="layui-table" lay-skin="nob">
			  <tbody>
			    <tr>
			      <td>商品成本：<span class="price">1200000.00</span></td>
			      <td>成交金额：<span class="price">1200000.00</span></td>
			      <td>成交AGR：1200000</td>
			    </tr>
			  </tbody>
			</table>
		  </div>
		</fieldset>
		<div class="tar mt10">
			<button class="layui-btn layui-btn-normal layui-btn-small">导出</button>
		</div>
		<div class="table-list">
			<table class="layui-table" lay-skin="line">
			  <thead>
			    <tr>
			      
			      <th>分类名称</th>
			      <th>库存</th>
			      <th>已售数量</th>
			      <th>商品成本</th>
			      <th>成交AGR</th>
			      <th>成交金额</th>
			    </tr> 
			  </thead>
			  <tbody>
			    <tr>
			    	<td>名牌手表</td>
			    	<td>8000</td>
			    	<td>1000</td>
			    	<td><span class="price">100000.00</span></td>
			    	<td>100000</td>
			    	<td><span class="price">100000.00</span></td>
			    </tr>
			    
			  </tbody>
			</table>   
		</div>
	</div>
	<div id="table-pages" style="text-align:center"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
    <script>
		layui.use(['jquery', 'laydate','form','layer','laypage'], function(){
			var $ = layui.jquery,
			    laydate = layui.laydate,
			    layer = layui.layer,
			    laypage = layui.laypage;


			// 分页
			 laypage({
			    cont: 'table-pages'
			    ,pages: 2
			    ,skip: true
			  });

            // 开始时间及截止时间
            var start = {
			   choose: function(datas){
			      end.min = datas; //开始日选好后，重置结束日的最小日期
			      end.start = datas //将结束日的初始值设定为开始日
			    }
			  };
			  
			  var end = {
			    choose: function(datas){
			      start.max = datas; //结束日选好后，重置开始日的最大日期
			    }
			  };

			$('#start').click(function(){
				start.elem = this;
    			laydate(start);
			});

			$('#end').click(function(){
				end.elem = this
    			laydate(end);
			});

		   
			
		});
	</script>
</body>
</html>