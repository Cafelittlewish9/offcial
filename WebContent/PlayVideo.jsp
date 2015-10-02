<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link href="css/SearchPageBorder.css" rel="stylesheet">

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->
<script src="js/bootstrap.file-input.js"></script>
<script src="js/js.cookie.js"></script>

	<%
	String name = new String();
	name = request.getParameter("filename");
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
 
  <script>
  $(function(){
	  
	  $('#textarea').focus(function(){
			$("#comment_button1").css('display','block');
		});
	  $('#comment_cancel').click(function(){
			$("#comment_button1").css('display','none');
			$("#textarea").val("");
		});
	  
	  
	  $('#textarea').keydown(function(){
		  var len = $(this).val().length;
		  if (len > 0) {
			  console.log(len);
			  $("#comment_submit").removeAttr('disabled');
		  }else{
			  console.log(len);
			  $("#comment_submit").attr('disabled','disabled');
		  }
		});

	  
	  
// 	  判斷使用者是否追加過影片
		$.ajax({
			  url:'ShowServlet',
			  type:'get',
			  data:{"memberId":'${user.memberId}',
				  	"website":'${param.filename}',
				  	'prodaction':'Check'},
			  dataType:"json",
			  success:function(data){
				  if(data==null){
					$('.addvideo').css({'display':'block'});
				  }else{
					$('.addvideo').css({'display':'none'});
				  }
			  }
		  })

	  
//		追加影片
	  $('.addvideo').click(function(){
		  if('${user.memberId}'==""){
			  $('#Login').modal('show');
			  return;
		  }else{
			  $.ajax({
				  url:'ShowServlet',
				  type:'get',
				  data:{"memberId":'${user.memberId}',
					  	"website":'${param.filename}',
					  	'prodaction':'Insert'},
				  dataType:"json",
				  success:function(data){
					 	 $('#finishedModal h4').text('追加影片成功！');
						  $('#finishedModal').modal('show');
		              		//一秒半後關閉成功畫面
		                	 setTimeout(function() {
		                     	$('#finishedModal').modal('hide');
		                     	location.reload();
		                     }, 2000);
					  $.each(data,function(i,v){
						  console.log(v.website);
					  });
				  }
			  })
		  }
		});
	  
// 	  推薦影片畫面
	  $.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'videoName':'${param.filename}',
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					console.log(v.videoId);
					
					
					 $.ajax({
						url:'VideoServlet',
						type:'get',
						data:{'videoTitle':null},
						dataType:"json",
						success:function(data){
							$.each(data,function(m,n){
								if(n.videoClassName==v.videoClassName){
									
				 					var vt = n.videoTitle.substring(0,25);
				 					var vd = n.videoDescription.substring(0,25);
				 					if(vd==null){
				 						vd="";
				 					}
				 					//順序=最新-舊
				 					if(10>=data.length-m){
				 					$('#P_div1').prepend("<div class='service' style='margin: 10px;padding-left: 10px;padding-right: 10px;'>"+"<div class='col-md-5' style='padding:5px 5px 0px 0px;'>"+
				 							     "<a href='PlayVideo.jsp?filename="+n.videoName + "'>"+
				 							     "<img src='../img/"+n.videoName+".jpg'  style='width:120px;height:68px'/></a></div>"+
				 							     "<div class='col-md-7' style='padding:5px 0px 0px 10px'>"+
				 							     "<a href='PlayVideo.jsp?filename="+n.videoName+"'>"+
				 							     "<span class='font-right'><div style='width:130px; display:inline-block;margin-left: 10px;' class='font-right'>"+vt+"</div></span></a><br>"+
				 							     "<span class='font-right'><div style='width:130px; display:inline-block;margin-left: 10px;' class='font-right'><p>"+vd+"</p></div></span></a><br>"+
				 							     "<div style='text-align:-webkit-auto;margin-left: 10px;width: 100%;'><span class='font-right' id='v_watchtimes'>"+"觀看次數:"+n.videoWatchTimes+ "views</span></div><br>"+
				 							     "</div>");
				 					}
								}
								
							});
						}
					 });
					 
					 
//						留言顯示
					 $.ajax({
						 
							url:'VideoCommentServlet',
							type:'get',
							data:{'videoId':v.videoId},
							  dataType:"json",
							  success:function(data){
// 								  console.log("DDDDDDDDDDD"+data);
								  $.each(data,function(i,id){
									  
									  $('#comment_page').prepend('<div class="row" style="margin-bottom:15px">'+
											  '<div class="col-lg-1" style="top:8px">'+
											  '<img src="'+id.memberName+'" style="width:50px;height:50px"/>'+
											  '</div>'+
											  '<div class="col-lg-4">'+
											  '<h5>'+
											  '<span style="color:#767676;">'+id.memberAccount+'</span>'+
											  '</h5>'+
											  '<p>'+
											  '<span style="color:#767676;">'+id.commentTime+'</span>'+
											  '</p>'+
											  '<p>'+
											  '<span style="color:#767676;">'+id.commentContent+'</span>'+
											  '</p>'+
											  '</div>'+
											  '</div>');
								  })
							  }
							  
						  }); 
						  

				});
			}
		})
		
		
	  
// 		會員資訊
		$.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'videoName':'${param.filename}',
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					console.log(v.videoName);
						$('#info h4').append("<h3><span>"+v.videoTitle+"</span></h3>");
						$('#info h5').append("<span style='color:#767676;'>"+v.memberAccount+"</span>");
						$('video').prepend("<source src='../mp4/"+v.videoName+".mp4' type='video/mp4' />");
				});	
			}
		})
	  
		$('#selectview').change(function(){
    	var view =  $('#selectview>:selected').val(); //$(this).val();
    	
    		$.ajax({
    			url:'ReplyArticleServlet',
    			type:'get',
    			data:{'videoName':'${param.filename}'},
    			dataType:"json",
    			success:function(data){
    				if(view =='new'){
    				}else{
    				}
    			}
    		});
    	})
    	

    	
//		影片回覆
$.ajax({
	url:'VideoServlet',
	type:'get',
	data:{'videoName':'${param.filename}',
		  'prodaction':'searchVideoName'},
	dataType:"json",
	success:function(data){
	$.each(data,function(i,v){
	console.log(v.videoId);

	
	$('#comment_submit').click(function(){
		if('${user.memberId}'==""){
			$('#Login').modal('show');
			return;
		}else{
							  
			setTimeout(function() {
	 			  location.reload();
	 			  }, 1000);

		  
		  $.ajax({
			  url:'VideoCommentServlet',
			  type:'get',
			  async:true,
			  data:{
				  "memberId":"${user.memberId}", 
				  "videoId":v.videoId,
				  "commentContent":$('#textarea').val(),
				  "sendComment":"insert"},
			  dataType:"json",
			  success:function(data){
				  
			
				  console.log("MemberIDIDIDIDID" + data.commentContent);
				  
					  $('#comment_page').append('<div class="row" style="margin-bottom:15px">'+
							  '<div class="col-lg-1" style="top:8px">'+
							  '<img src="'+data.memberName+'" style="width:50px;height:50px"/>'+
							  '</div>'+
							  '<div class="col-lg-4">'+
							  '<h5>'+
							  '<span style="color:#767676;">'+data.memberAccount+'</span>'+
							  '</h5>'+
							  '<p>'+
							  '<span style="color:#767676;">'+data.commentTime+'</span>'+
							  '</p>'+
							  '<p>'+
							  '<span style="color:#767676;">'+data.commentContent+'</span>'+
							  '</p>'+
							  '</div>'+
							  '</div>').serialize();
					 	  
		

	}
						 
});

		  
	
		 }

	});  
		  


		});
	}
})
	
	
	

	  
//		新舊排序
$('#selectview').change(function(){
  	var view =  $('#selectview>:selected').val(); //$(this).val();
  	
  		$.ajax({
  			url:'VideoCommentServlet',
  			type:'get',
  			dataType:"json",
  			success:function(data){
  				if(view =='new'){
  					$('#comment_page').empty();
  					
  					$.each(data,function(i,v){
  						  console.log(v.commentContent);
  						  $('#comment_page').append('<div class="row" style="margin-bottom:15px">'+
  								  '<div class="col-lg-1" style="top:8px">'+
  								  '<img src="'+id.memberName+'" style="width:50px;height:50px"/>'+
  								  '</div>'+
  								  '<div class="col-lg-4">'+
  								  '<h5>'+
  								  '<span style="color:#767676;">'+v.memberAccount+'</span>'+
  								  '</h5>'+
  								  '<p>'+
  								  '<span style="color:#767676;">'+v.commentTime+'</span>'+
  								  '</p>'+
  								  '<p>'+
  								  '<span style="color:#767676;">'+v.commentContent+'</span>'+
  								  '</p>'+
  								  '</div>'+
  								  '</div>');
  						  console.log(v.commentContent);
  						  
  					  })
  					
  				}else{
  					$('#comment_page').empty();
  					
  					$.each(data,function(i,v){
  						  console.log(v.commentContent);
  						  $('#comment_page').prepend('<div class="row" style="margin-bottom:15px">'+
  								  '<div class="col-lg-1" style="top:8px">'+
  								  '<img src="'+id.memberName+'" style="width:50px;height:50px"/>'+
  								  '</div>'+
  								  '<div class="col-lg-4">'+
  								  '<h5>'+
  								  '<span style="color:#767676;">'+v.memberAccount+'</span>'+
  								  '</h5>'+
  								  '<p>'+
  								  '<span style="color:#767676;">'+v.commentTime+'</span>'+
  								  '</p>'+
  								  '<p>'+
  								  '<span style="color:#767676;">'+v.commentContent+'</span>'+
  								  '</p>'+
  								  '</div>'+
  								  '</div>');
  						  console.log(v.commentContent);
  						  
  					  })
  				
  				}
  			}
  		});
		 })
		 
		 
//		  追蹤

	  $.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'videoName':'${param.filename}',
				  'memberId':'${user.memberId}',
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
			$.each(data,function(i,v){
			console.log(v.memberId);

			  $('#followMember').click(function(){
				  if('${user.memberId}'==""){
					  $('#Login').modal('show');
					  return;
				  }else{
					  
				  $.ajax({
					  url:'FollowServlet',
					  type:'get',
					  data:{"memberId":'${user.memberId}',
						  	"followId":v.memberId,
						  	'sendMemberFollow':'insert'},
					  success:function(data){
// 						  console.log("12345");
						 	 $('#finishedModal h4').text('追蹤會員成功！');
							  $('#finishedModal').modal('show');
							  
			                	 setTimeout(function() {
			                     	$('#finishedModal').modal('hide');
			                     	location.reload();
			                     }, 2000);
			              		
						  $.each(data,function(i,v){
							  console.log(v.website);
						  });
					  }
				  })
				}
			});	 
				  
			  //影片檢舉
			  $('#reportVideo').click(function(){
				  
				  if('${user.memberId}'==""){
					  $('#Login').modal('show');
					  return;
				  }else{
					  $('#report').modal('show');
				  }
				  
			  });
			  
			   $('#sendReportContent').click(function(){
					
				  $.ajax({
					  url:'ReportVideoServlet',
					  type:'get',
					  data:{"reportedVideoId":v.videoId,
						  	"reportReason":$('#reportContent').val(),
						  	'sendReportVideoMessage':'insert'},
					  success:function(data){
						  console.log("12345");
						  console.log(data);

						 	 $('#finishedModal h4').text('檢舉影片成功！');
							  $('#finishedModal').modal('show');
							  
					 	}  	
				  	})
				  
				  });
				//****

		});
	}
})	 




    	
    	
    	
		
		
  })
  </script>

 
</head>
<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

   <jsp:include page="/Header.jsp" />
   
   <jsp:include page="/Bar.jsp" />
   
   
   
<div class="row" style="width:1140px;margin-left:auto;margin-right:auto">
	
			<!-- 	影片畫面	-->
			<div class="row" style="margin-top:100px;width:800px;float:left">
		
				<div class="col-md-6 col-md-offset-1" style="padding-left: 1px;margin-left: 7.2222%; width: 92.333333%;">
			
				   <video id="video_1" class="video-js vjs-default-skin" controls preload="none" width="100%" height="400"
				      data-setup='{"techOrder": ["html5", "flash"] }' preload="auto" poster="" autoplay="autoplay">
						
<%-- 				    	<source src="../mp4/<%=name%>.mp4" type='video/mp4' /> --%>
				    	
					</video>
		
			</div>
			
			
			<!-- 	會員資訊	-->
			<div id="info" class="col-md-6 col-md-offset-1" style="margin-top:10px;background:white;margin-left: 7.2222%;width: 90.333333%;">
		
									<div style="height:68px;text-align:left;margin:0px auto">
										<div style="padding-top:5px">
											<h4>
<!-- 												<span style="color:#767676;">v.videoName</span> -->
											</h4>
										</div>
									</div>
									
									<div style="height:58px;text-align:left;margin:0px auto">
										<div style="padding-top:5px">
											<div class="row">
												<div class="col-lg-1" style="top:8px" >
													<img src="${video.member.memberName}" style="width:50px;height:50px"/>
												</div>
												<div class="col-lg-4">
													<h5>
<!-- 														<span style="font-family:Microsoft JhengHei;color:#767676;">v.memberId</span> -->
													</h5>
													<p>
													  <button type="button" class="btn btn-primary btn-xs"  id="followMember">追蹤我</button>
													</p>
												</div>
		
											</div>
										</div>
									</div>
									
									<hr style="box-shadow:1px 1px 1px #cccccc;width:98%;margin:10px">
									
									
									<div style="height:34px;text-align:left;margin:0px auto">
										<div class="stream-video-info--buttons">
											<a href="#" class="addvideo" >影片追加</a>
											<a href="#">讚</a>
											<a href="#" id="reportVideo" data-toggle="modal" data-target="#reportVideo">檢舉</a>
											
										</div>
									</div>
		
			</div>
			
			
			<!-- 	留言板 	-->
			<div class="col-md-6 col-md-offset-1" style="margin:10px 0px 15px 0px;background:white;margin-left: 7.2222%;width: 90.333333%;">
				<div >
				
					<div style="height:68px;text-align:left;margin:0px auto">
						<div style="padding-top:5px">
							<textarea id="textarea" class="form-control" rows="3" placeholder="新增留言..."></textarea>
						</div>
					</div>
				
					<div id="comment_button1" style="padding-top:20px;text-align:right;margin:0px auto;display:none;">
						<button  id="comment_submit" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#" disabled="disabled">Submit</button>
						<button  id="comment_cancel" type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#">Cancel</button>
					</div>
					
					<div id="comment_button2" style="padding:20px 0px 10px 0px ;height:48px;width:20%;text-align:left;">
						<select id="selectview" class="form-control">
							<option  value="new" name="sendComment">由新到舊</option>
							<option  value="old" name="sendComment">由舊到新</option>
						</select>
					</div>
					
									<div style="height:58px;text-align:left;margin:0px auto">
									
										<div id ="comment_page" style="padding-top:15px">
										
										</div>
									</div>
									
				</div>
			</div>
		
		</div>

	
		<!-- 	右邊列表	-->
	
		<div class="col-md-3" style="background:white;margin: 100px 0px 20px 5px;width: 29%;">
		
								<div style="height:68px;text-align:left;margin:0px auto">
									<div style="padding:15px 0px 0px 10px">
										<h4>
											<span >相關類型影片:</span>
										</h4>
									</div>
								</div>
			
							<div id="P_div1">
							
							</div>
				
			</div>
</div>

<!-- add成功Modal -->

<div id="finishedModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
  <div class="modal-dialog modal-sm" >
    <div class="modal-content">
    
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title has-error has-feedback">追加影片成功！</h4>
      </div>
    </div>
    
    
    </div>
  </div>
</div>

<!--ReportVideo-->

<div class="modal fade" id="report" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 style="text-align:center" class="modal-title" id="myModalLabel">影片檢舉</h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="Login_form" method="post">
				<fieldset style="text-align:center">
				
				<div id="l_input1-1"  class="input-group">
					  <textarea id="reportContent" class="form-control" placeholder="請輸入檢舉事由" rows="10" cols="10"></textarea>
				</div>
				
				
<!-- 				<input value="Select" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1"> -->
						
				
				<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-1">
					<input style="width: 440px" id="sendReportContent" name="operation" value="送出檢舉" class="btn btn-primary btn-small" type="submit">
				</div>
			
			</fieldset>
		 </form>
			
<!-- 		</div> -->
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