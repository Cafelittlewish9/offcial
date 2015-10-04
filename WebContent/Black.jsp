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
	var memberId = '${user.memberId}';
	var memberAccount = '${user.memberAccount}';
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
<!-- 					<form class="form-horizontal" id="editBlack" role="form" -->
<%-- 						method="post" action="<c:url value='/Black'/>"> --%>
						<div class="friendslist">
							<c:forEach var="black" items="${bList}">
								<div class="friends">
									<img src="${black.member.memberName}" />
								</div>
								<div style="height: 38px;">
									<h4 style="display: inline">${black.member.memberAccount}
											<!-- 								<button id="follow_cancel" type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#">取消追蹤</button> -->
										</h4>
									<button type="button" style="float: right" name="operation"
										value="delete" class="btn btn-danger">取消黑名單</button>
								</div>
							</c:forEach>
						</div>
						<!-- 						<div class="modal-footer" style="text-align: center"> -->
						<!-- 							<button type="submit" class="btn btn-primary" name="sendMemberFollow" value="submit">確認</button> -->
						<!-- 							<input type="reset" class="btn btn-default" name="sendMemberFollow" value="取消"> -->
						<!-- 						</div> -->
					</form>
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