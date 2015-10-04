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
			  data:{"memberId":memberId,
				  	"website":filename,
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
				  data:{"memberId":memberId,
					  	"website":filename,
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
		

	  
// 	  檢舉影片,推薦影片
	  $.ajax({
			url:'VideoServlet',
			type:'get',
			data:{'videoName':filename,
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
				
				$('#sendReportContent').click(function(){
			 		  $.ajax({
			 			  url:'ReportVideoServlet',
			 			  type:'get',
			 			  data:{"reportedVideoId":data[0].videoId,
			 				  	"reportReason":$('#reportContent').val(),
			 				  	'sendReportVideoMessage':'insert'},
			 			  dataType:"json",
			 			  success:function(data){
			 			
			 				 console.log(data);
			 				 if(data){
			           		     	//關閉列表 顯示成功畫面
			                   	   		$('#reportVideo').modal('hide');
			                     	 	setTimeout(function() {
			                     	 			$('#finishedModal h4').text('檢舉成功');
			        	                     	$('#finishedModal').modal('show');
			        	                     }, 100);
			                          	//一秒半後關閉成功畫面
			        	                setTimeout(function() {
			        	                     	$('#finishedModal').modal('hide');
			        	                     }, 2000);
			        	                setTimeout(function() {
			        	                		 location.reload();
			        		                 }, 3000);
			           		     		
			           		     	}
			 			 	}  	
			 		  	})
			 	})
				
			 	
			 	$.each(data,function(i,v){
					
					 $.ajax({
						url:'VideoServlet',
						type:'get',
						data:{'videoClassName':v.videoClassName,
								'prodaction':'searchVideoClassName'},
						dataType:"json",
						success:function(data){
							console.log(data);
							$.each(data,function(m,n){
								console.log("videoclassname"+n.videoclassname);
									
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
//								  console.log("DDDDDDDDDDD"+data);
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
			data:{'videoName':filename,
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
	  
//		影片回覆
$.ajax({
	url:'VideoServlet',
	type:'get',
	data:{'videoName':filename,
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
				  "memberId":memberId, 
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
			data:{'videoName':filename,
				  'memberId':memberId,
				  'prodaction':'searchVideoName'},
			dataType:"json",
			success:function(data){
			$.each(data,function(i,v){
			console.log("HERE : "+v.memberId);
			
//			判斷會員是否已被追蹤
			$.ajax({
				  url:'FollowServlet',
				  type:'get',
				  data:{"memberId":'${user.memberId}',
					  	"followId":v.memberId,
					  	'sendMemberFollow':'check'},
				  dataType:"json",
				  success:function(datas){
					  console.log("COME ON : "+v.memberId);
					  if(datas==null){
						$('#followMember').css({'display':'block'});
					  }else{
						$('#followMember').css({'display':'none'});
					  }
				  }
			  })
			
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
		
			});
		}
	})	
		
  })