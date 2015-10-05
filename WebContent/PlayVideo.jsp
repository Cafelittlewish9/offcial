<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link href="css/SearchPageBorder.css" rel="stylesheet">

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->
<script src="js/bootstrap.file-input.js"></script>
<script src="js/js.cookie.js"></script>

<%
	String name = new String();
	name = request.getParameter("filename");
	String memberName = request.getParameter("memberName");
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=name%></title>

<!-- Chang URLs to wherever Video.js files will be hosted -->
<link href="video-js.css" rel="stylesheet" type="text/css">
<!-- video.js must be in the <head> for older IEs to work. -->
<script src="video.js"></script>

<!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
<script>
	videojs.options.flash.swf = "video-js.swf";
</script>

<link href="css/PlayVideo.css" rel="stylesheet">

<script type="text/javascript">
	var filename = '${param.filename}';
	var memberId = '${user.memberId}';
</script>


<!-- 	PlayVideo頁面的jQuery -->
<script type="text/javascript" src="js/PlayVideo.js"></script>

<script type="text/javascript">

//影片標題會員頭像那一個區塊
$(function(){
	$.ajax({
		url:'VideoServlet',
		type:'get',
		data:{'videoName':'${param.filename}',
	  		  'prodaction':'searchVideoName'},
		dataType:"json",
		success:function(data){
			$.each(data,function(i,v){
				$('#pic').prepend('<img src="'+v.memberName+'" style="width: 50px; height: 50px" />');
			})
		}
													  
	}); 
											
});
									
</script>


</head>
<body
	style="background: rgba(0, 0, 0, 0.05); font-family: Microsoft JhengHei">

	<jsp:include page="/Header.jsp" />

	<jsp:include page="/Bar.jsp" />



	<div class="row"
		style="width: 1140px; margin-left: auto; margin-right: auto">

		<!-- 	影片畫面	-->
		<div class="row" style="margin-top: 100px; width: 800px; float: left">

			<div class="col-md-6 col-md-offset-1"
				style="padding-left: 1px; margin-left: 7.2222%; width: 92.333333%;">

				<video id="video_1" class="video-js vjs-default-skin" controls
					preload="none" width="100%" height="400"
					data-setup='{"techOrder": ["html5", "flash"] }' preload="auto"
					poster="" autoplay="autoplay"> <%-- 				    	<source src="../mp4/<%=name%>.mp4" type='video/mp4' /> --%>

				</video>

			</div>


			<!-- 	會員資訊	-->
			
			<div id="info" class="col-md-6 col-md-offset-1"
				style="margin-top: 10px; background: white; margin-left: 7.2222%; width: 90.333333%;">

				<div style="height: 68px; text-align: left; margin: 0px auto">
					<div style="padding-top: 5px">
						<h4>
							<!-- 												<span style="color:#767676;">v.videoName</span> -->
						</h4>
					</div>
				</div>

				<div style="height: 58px; text-align: left; margin: 0px auto">
					<div style="padding-top: 5px">
						<div class="row">
							<div class="col-lg-1" style="top: 8px" id="pic">
<!-- 								<img src="XXxxxxx" style="width: 50px; height: 50px" /> -->
							</div>
							<div class="col-lg-4">
								<h5>
<!-- 																							<span style="font-family:Microsoft JhengHei;color:#767676;">v.memberId</span> -->
								</h5>
								<p>
									<button type="button" class="btn btn-primary btn-xs" id="followMember">追蹤我</button>
								</p>
							</div>

						</div>
					</div>
				</div>
				<hr
					style="box-shadow: 1px 1px 1px #cccccc; width: 98%; margin: 10px">

				<div style="height: 34px; text-align: left; margin: 0px auto">
					<div class="stream-video-info--buttons">
						<a href="#" class="addvideo">影片追加</a> <a href="#">讚</a> <a
							style="cursor: pointer;" data-toggle="modal"
							data-target="#reportVideo">檢舉</a>

					</div>
				</div>

			</div>
			

			<!-- 	留言板 	-->
			<div class="col-md-6 col-md-offset-1"
				style="margin: 10px 0px 15px 0px; background: white; margin-left: 7.2222%; width: 90.333333%;">
				<div>

					<div style="height: 68px; text-align: left; margin: 0px auto">
						<div style="padding-top: 5px">
							<textarea id="textarea" class="form-control" rows="3"
								placeholder="新增留言..."></textarea>
						</div>
					</div>

					<div id="comment_button1"
						style="padding-top: 20px; text-align: right; margin: 0px auto; display: none;">
						<button id="comment_submit" type="button"
							class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#" disabled="disabled">Submit</button>
						<button id="comment_cancel" type="button"
							class="btn btn-default btn-sm" data-toggle="modal"
							data-target="#">Cancel</button>
					</div>

					<div id="comment_button2"
						style="padding: 20px 0px 10px 0px; height: 48px; width: 20%; text-align: left;">
						<select id="selectview" class="form-control">
							<option value="new">由新到舊</option>
							<option value="old">由舊到新</option>
						</select>
					</div>

					<div style="height: 58px; text-align: left; margin: 0px auto">

						<div id="comment_page" style="padding-top: 15px"></div>
					</div>

				</div>
			</div>

		</div>


		<!-- 	右邊列表	-->

		<div class="col-md-3"
			style="background: white; margin: 100px 0px 20px 5px; width: 29%;">

			<div style="height: 68px; text-align: left; margin: 0px auto">
				<div style="padding: 15px 0px 0px 10px">
					<h4>
						<span>相關類型影片:</span>
					</h4>
				</div>
			</div>

			<div id="P_div1"></div>

		</div>
	</div>

	<!--ReportVideo-->

	<div class="modal fade" id="reportVideo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 style="text-align: center" class="modal-title"
						id="myModalLabel">影片檢舉</h4>
				</div>

				<div class="modal-body">


					<fieldset style="text-align: center">

						<div id="report_input1-1">
							<textarea id="reportContent" class="form-control"
								placeholder="請輸入檢舉事由" rows="10" cols="10"></textarea>
						</div>

					</fieldset>

					<div style="width: 450px; margin: 0px auto; padding: 5px">
						<input style="width: 440px" id="sendReportContent" value="送出檢舉"
							class="btn btn-primary btn-small" type="submit">
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!--ReportVideo-->

	<jsp:include page="/Footer.jsp" />
</body>
</html>