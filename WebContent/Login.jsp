<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <title>Login</title> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="js/bootstrap.js"></script> -->
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="css/justified-nav.css"> -->
<!-- <script src="js/bootstrap.file-input.js"></script> -->
<!-- <script src="js/js.cookie.js"></script> -->

<script src="js/Login.js"></script>
<style>
.input-group-addon{
width: 100px;
background-color:#337ab7;
color:#fff;
}
/* 合併時修改用 */
#SignUp_form span,#Login_form span,#getpassword span{
    width:112px;
}
#SignUp_form div,#Login_form div,#getpassword div{
width: 450px; margin:0px auto;padding:5px
}

</style>
<script>

</script>
<!-- </head> -->
<!-- <body> -->
<!-- Button trigger modal -->
<!-- <center> -->
<!-- <button type="button" class="btn btn-primary btn-lg" id="signup" data-toggle="modal" data-target="#SignUp">註冊</button> -->
<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#Login">登入</button> -->
<!-- </center> -->

<!-- SignUp Modal -->


<!-- Login Modal -->
<div class="modal fade" id="Login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 style="text-align:center" class="modal-title" id="myModalLabel">會員登入</h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="Login_form" method="post" action="<c:url value="/login" />">
				<fieldset style="text-align:center">
				
				<div id="l_input1-1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">帳號</span>
					  <input id="Login_memberAccount" type="text" name="memberAccount" class="form-control" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				<div id="l_input2-1"  class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">密碼</span>
					  <input id="Login_memberPassword" type="password" name="memberPassword" class="form-control" maxlength="20" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				
<!-- 				<input value="Select" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1"> -->
						
				</fieldset>
<!-- 			</form> -->
<!-- 			<form > -->
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-1">
				<input style="width: 440px" id="l_submit2-1" name="operation" value="登入" class="btn btn-primary btn-small" type="submit">
			</div>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group2-2">
				<button style="width: 440px" id="l_submit2-2" name="operation" class="btn btn-primary btn-small" data-dismiss="modal" data-toggle="modal" type="button" data-target="#mypassword">忘記密碼</button>
			</div>
			
<!-- 		</div> -->
      </div>
      	  <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
    </div>
  </div>
</div>

<!-- 註冊成功Modal -->

<div id="signupfinished" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">註冊成功！</h4>
      </div>
    </div>    
    
    </div>
  </div>
</div>


<!-- 提取密碼Modal -->
<div class="modal fade" id="mypassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 style="text-align:center" class="modal-title" id="myModalLabel">提取密碼</h4>
      </div>
      
      <div class="modal-body">
<!-- 		<div class="upload-form"> -->
			<form id="getpassword" method="post" action="">
				<fieldset style="text-align:center">
				
				<div id="l_input1-2" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">帳號</span>
					  <input id="get_memberAccount" type="text" name="memberAccount" class="form-control" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				
				<div id="l_input2-2" class="input-group">
					  <span  class="input-group-addon" id="basic-addon1">E-mail</span>
					  <input id="get_memberEmail" type="text" name="memberEmail" class="form-control" maxlength="40" placeholder="請輸入" aria-describedby="basic-addon1">
				</div>
				
				<input value="Select" type="hidden" name="prodaction" class="form-control" aria-describedby="basic-addon1">
				
				</fieldset>
			</form>
			<div style="width: 450px; margin:0px auto;padding:5px" class="input-group3-1">
				<input style="width: 440px" id="l_submit3-1" name="operation" value="提取密碼" class="btn btn-primary btn-small" type="submit">
			</div>
<!-- 		</div> -->
      </div>
	      	  <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
    </div>
  </div>
</div>
