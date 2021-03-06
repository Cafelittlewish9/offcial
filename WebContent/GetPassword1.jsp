<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GetPassWord</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<style>

.input-group-addon{
width: 100px;
background-color:#337ab7;
color:#fff;
}
/* 合併時修改用 */
#getpassword span{
    width:112px;
}
#getpassword div{
width: 450px; margin:0px auto;padding:5px
}
.modal-title{
	text-align:center;
}
.GetPasswordmodal{
    background-color: #22282e;
    padding-bottom: 55px;
    padding-top: 100px;
    display: block;
    height: 650px;
}
</style>

</head>
<body>

	<jsp:include page="/Header.jsp" />

	<jsp:include page="/Bar.jsp" />
	
<!-- GetPassword Modal -->	
<section class="GetPasswordmodal">
 <div class="modal-dialog" style="margin: 100px auto 80px auto;">
    <div class="modal-content">
    
      <div class="modal-header">
<!--         <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> -->
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="getpassword" method="post" action="${pageContext.request.contextPath}/resetPassword" method="post">
				<fieldset style="text-align:center">
		
				<div id="l_input1-2" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">您已成功申請，請到您的信箱查看</span>
					  
				</div>
						
				</fieldset>
			</form>
			
<!-- 		</div> -->
      </div>
	      	  <div class="modal-footer">
<!-- 		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
		      </div>
    </div>
  </div>
</section><!-- End -->  
  
<jsp:include page="/Footer.jsp" />
</body>
</html>