<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />

	<link href="css/SearchPageBorder.css" rel="stylesheet">
	
	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>

 	<!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="video-js.css" rel="stylesheet" type="text/css">
  	<!-- video.js must be in the <head> for older IEs to work. -->
  <script src="video.js"></script>
  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>

<script type="text/javascript">
var divs = document.getElementById("div1");


$(function(){
		$.ajax({
			url:'VideoServlet',
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					//順序=最新-舊
					if(v.memeberId=="2"){
						console.log(v.memberId);
						var vn = v.videoName.substring(0,30);
						$('#tr1').prepend("<td style='margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;' >")
						$('#tr1 td:first').append("<a href='PlayVideo.jsp?filename="+v.videoName + "'><img src='../img/"+v.videoName +".jpg'/></a><br>");
						$('#tr1 td:first').append("<a style='text-decoration:none;color:#167ac6;font-weight: 600;font-family:sans-serif; font-size:15px' href='PlayVideo.jsp?filename="+v.videoName+"'><span>"+vn+"</span></a><br>");
						$('#tr1 td:first').append("<span style='font-weight: 550;font-family:Microsoft JhengHei; font-size:12px'>"+v.videoWatchTimes+ "views</span><br></td>");
					}else{
					var vn = v.videoName.substring(0,30);
					$('#tr2').prepend("<td style='margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;' >")
					$('#tr2 td:first').append("<a href='PlayVideo.jsp?filename="+v.videoName + "'><img src='../img/"+v.videoName +".jpg'/></a><br>");
					$('#tr2 td:first').append("<a style='text-decoration:none;color:#167ac6;font-weight: 600;font-family:sans-serif; font-size:15px' href='PlayVideo.jsp?filename="+v.videoName+"'><span>"+vn+"</span></a><br>");
					$('#tr2 td:first').append("<span style='font-weight: 550;font-family:Microsoft JhengHei; font-size:12px'>"+v.videoWatchTimes+ "views</span><br></td>");
					}
				});
			}
		})
})
</script>
<style>
#tr1 td{
	width:240px;
	height:180px;
}

</style>

</head>

<body style="background:rgba(0,0,0,0.05)">

    <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   
   	<jsp:include page="/VideoUpload.jsp" />
   
   	<jsp:include page="/CreateLive.jsp" />
    
<center>
        <div style="background:white; width:92%; margin-top:100px">
			<table>
				<tr id="tr1">
					<div style="height:40px;text-align:left;">
					<span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">推薦:</span>
					</div>
<!-- 					<td id="td1" style="margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;" > -->


<!-- 					</td> -->
				</tr>
			</table>
			
			<hr style="width:99%;box-shadow:1px 1.5px 1px #cccccc;">
		
			<table>
				<tr id="tr2">
					<div style="height:40px;text-align:left;">
					<span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">會員一:</span>
					</div>
<!-- 					<td id="td1" style="margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;" > -->


<!-- 					</td> -->
				</tr>
			</table>
			
			<hr style="width:99%;box-shadow:1px 1.5px 1px #cccccc;">
			
			<table>
				<tr id="tr3">
					<div style="height:40px;text-align:left;">
					<span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">會員二:</span>
					</div>
<!-- 					<td id="td1" style="margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;" > -->


<!-- 					</td> -->
				</tr>
			</table>
			
			<hr style="width:99%;box-shadow:1px 1.5px 1px #cccccc;">
			
			<table>
				<tr id="tr4">
					<div style="height:40px;text-align:left;">
					<span style="line-height:50px;margin-left:40px;font-family:Microsoft JhengHei;color:#767676;">會員三:</span>
					</div>
<!-- 					<td id="td1" style="margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;" > -->


<!-- 					</td> -->
				</tr>
			</table>
			
			<hr style="width:99%;box-shadow:1px 1.5px 1px #cccccc;">
			
		</div>
		
		
</center>
<jsp:include page="/Footer.jsp" />
</body>
</html>