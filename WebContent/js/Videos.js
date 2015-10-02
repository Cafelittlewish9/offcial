$(function(){
	
	$.ajax({
		url:'videos/class?videoClassName',
		type:'get',
//			data:{'categoryID':1},
		dataType:"json",
		success:function(data){
			$.each(data.list,function(i,v){
				console.log(i)
//				var vn = v.videoName.substring(0,25);
//				var vd = v.videoDescription;
//				if(vd==null){
//					vd="";
//				}
				//順序=最新-舊
//			$('#videos_div1').prepend("<td>")
			$('#videos_div1').prepend("<div class='service'>"+
					"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
					"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
					"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
					"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
					"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
					"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
					"</div>");
				
			});
		}
	})

	
	$('#browse_type_any').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_MusicVideo').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Movietrailer').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Tvshow').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
		
	$('#browse_type_News').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Drama').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Animation').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Daily').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Interest').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
	
	$('#browse_type_Other').click(function(){
		console.log($(this).val());
		$('div button').removeClass('active');
		$(this).addClass('active');
		$('#videos_div1').empty();
		$.ajax({
			url:'videos/class?videoClassName=' + $(this).val(),
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data.list,function(i,v){
					console.log(i)
//					var vn = v.videoName.substring(0,25);
//					var vd = v.videoDescription;
//					if(vd==null){
//						vd="";
//					}
					//順序=最新-舊
//				$('#videos_div1').prepend("<td>")
				$('#videos_div1').prepend("<div class='service'>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<img src='../img/" + v.videoName + ".jpg' width='200px'/></a><br>"+
						"<a href='PlayVideo.jsp?filename=" + v.videoName + "'>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'>" + v.videoTitle + "</div></span></a><br>"+
						"<span class='font-right'><div style='width:200px; display:inline-block' class='font-right'><p>" + v.videoDescription +"</p></div></span></a><br>"+
						"<span id='v_watchtimes'>" + v.videoWatchTimes + " views</span><br></td>"+
						"</div>");
					
				});
			}
		})
	})
})