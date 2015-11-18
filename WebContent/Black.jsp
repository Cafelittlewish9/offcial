<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.lang.*"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Setting Button的形狀 -->

<link rel="stylesheet" href="css/PersonalPage.css">
<script src="js/js.cookie.js"></script>
<!-- </head> -->
<!-- <body> -->
<!-- Create LiveShow -->


<center>
	<!-- <button style="border-style: outset;border-color:black" id="createlive" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#CreateModal"> -->
	<!--   Create Your LiveShow -->
	<!-- </button> -->

</center>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:'PersonalPage',
		type:'post',
		dataType:"json",
		success:function(data){
			$.each(data.black, function(i, v) {
				$('#friendslistxxx').append("<div class='friends'>" + 
						"<img src='" + v.member.memberName + "' />" + 
					"</div>" + 
					"<div style='height: 38px;'>" + 
						"<h4 style='display: inline'>" + v.member.memberAccount + "</h4>" + 
						"<button id='unfollow' type='button' style='float:right' name='sendMemberFollow' value='delete' class='btn btn-danger'>" + 
						"<a href='Black?blackedId=" + v.blackedId + "&operation=delete' style='color:white;text-decoration:none'>取消黑名單</a></button>" + 
					"</div>");
			})
			
// 			<div class="friends">
// 			<img src="${black.member.memberName}" />
// 		</div>
// 		<div style="height: 38px;">
// 			<h4 style="display: inline">${black.member.memberAccount}
// 			</h4>
// 			<button type="button" style="float: right" name="operation" value="delete" class="btn btn-danger"><a href="${pageContext.request.contextPath}/Black?blackedId=${black.blackedId}&operation=delete" style="color:white;text-decoration:none">取消黑名單</a></button>
// 		</div>
		}
	});
});
</script>



<!-- Modal -->
<div class="modal fade" id="blackModal" tabindex="-1" role="dialog"
	aria-labelledby="demoBlack" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" style="text-align: center" id="demoBlack">編輯黑名單</h4>
			</div>
			<div class="modal-body">
				<fieldset style="text-align: center">

						<div id="friendslistxxx" class="friendslist">
<%-- 							<c:forEach var="black" items="${bList}"> --%>
<!-- 								<div class="friends"> -->
<%-- 									<img src="${black.member.memberName}" /> --%>
<!-- 								</div> -->
<!-- 								<div style="height: 38px;"> -->
<%-- 									<h4 style="display: inline">${black.member.memberAccount} --%>
<!-- 									</h4> -->
<!-- 									<button type="button" style="float: right" name="operation" -->
<%-- 										value="delete" class="btn btn-danger"><a href="${pageContext.request.contextPath}/Black?blackedId=${black.blackedId}&operation=delete" style="color:white;text-decoration:none">取消黑名單</a></button> --%>
<!-- 								</div> -->
<%-- 							</c:forEach> --%>
						</div>
						<!-- 						<div class="modal-footer" style="text-align: center"> -->
						<!-- 							<button type="submit" class="btn btn-primary" name="sendMemberFollow" value="submit">確認</button> -->
						<!-- 							<input type="reset" class="btn btn-default" name="sendMemberFollow" value="取消"> -->
						<!-- 						</div> -->
					
				</fieldset>
			</div>
		</div>
	</div>
</div>

<!-- 成功Modal -->

<div id="finishedModal" class="modal fade bs-example-modal-sm"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
	aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">


			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
</div>
<!-- </body> -->
<!-- </html> -->