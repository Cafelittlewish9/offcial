$(function() {
	$('#article001').click(function() {
		$.ajax({
			url: 'Article?subclassNo=A',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				$('#articleContent').empty();
				$.each(data.list, function(i, v) {
					$('#articleContent').append("<div class='row'>" +
													"<div class='animate fadeInLeft animated'>" +
														"<div class='col-md-3' style='height: 142.188px; overflow: hidden;'>" +
															"<a href='#'><img class='img-responsive' src='" + v.member.memberName + "' alt='img/space.jpg'></a>" +
														"</div>" +
														"<div class='col-md-9'>" +
															"<a href='#'><h2>" + v.articleTitle + "</h2></a>" +
															"<h5 style='display: inline; '>" + v.member.memberAccount + "</h5>" +
															"<span style='float: right;'>" + v.publishTime +  "</span>" +
															"<p>" + v.articleContent + "</p>" +
														"</div>" +
													"</div>" +
												"</div>");
				})
			}
		})
	})
	$('#article002').click(function() {
		$.ajax({
			url: 'Article?subclassNo=B',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				$('#articleContent').empty();
				$.each(data.list, function(i, v) {
					$('#articleContent').append("<div class='row'>" +
							"<div class='animate fadeInLeft animated'>" +
							"<div class='col-md-3' style='height: 142.188px; overflow: hidden;'>" +
							"<a href='#'><img class='img-responsive' src='" + v.member.memberName + "' alt='img/space.png'></a>" +
							"</div>" +
							"<div class='col-md-9'>" +
							"<a href='#'><h2>" + v.articleTitle + "</h2></a>" +
							"<h5 style='display: inline; '>" + v.member.memberAccount + "</h5>" +
							"<span style='float: right;'>" + v.publishTime +  "</span>" +
							"<p>" + v.articleContent + "</p>" +
							"</div>" +
							"</div>" +
					"</div>");
				})
			}
		})
	})
	$('#article003').click(function() {
		$.ajax({
			url: 'Article?subclassNo=C',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				$('#articleContent').empty();
				$.each(data.list, function(i, v) {
					$('#articleContent').append("<div class='row'>" +
							"<div class='animate fadeInLeft animated'>" +
							"<div class='col-md-3' style='height: 142.188px; overflow: hidden;'>" +
							"<a href='#'><img class='img-responsive' src='" + v.member.memberName + "' alt='img/space.png'></a>" +
							"</div>" +
							"<div class='col-md-9'>" +
							"<a href='#'><h2>" + v.articleTitle + "</h2></a>" +
							"<h5 style='display: inline; '>" + v.member.memberAccount + "</h5>" +
							"<span style='float: right;'>" + v.publishTime +  "</span>" +
							"<p>" + v.articleContent + "</p>" +
							"</div>" +
							"</div>" +
					"</div>");
				})
			}
		})
	})
	$('#article004').click(function() {
		$.ajax({
			url: 'Article?subclassNo=D',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				$('#articleContent').empty();
				$.each(data.list, function(i, v) {
					$('#articleContent').append("<div class='row'>" +
							"<div class='animate fadeInLeft animated'>" +
							"<div class='col-md-3' style='height: 142.188px; overflow: hidden;'>" +
							"<a href='#'><img class='img-responsive' src='" + v.member.memberName + "' alt='img/space.png'></a>" +
							"</div>" +
							"<div class='col-md-9'>" +
							"<a href='#'><h2>" + v.articleTitle + "</h2></a>" +
							"<h5 style='display: inline; '>" + v.member.memberAccount + "</h5>" +
							"<span style='float: right;'>" + v.publishTime +  "</span>" +
							"<p>" + v.articleContent + "</p>" +
							"</div>" +
							"</div>" +
					"</div>");
				})
			}
		})
	})
})