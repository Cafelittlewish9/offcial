//<script type="text/javascript">
	var xmlHttp;

	function createXMLHttpRequest() {	//XMLHttpRequest : 在不重新加載頁面的情況下更新網頁、在頁面已加載後從服務器請求數據、在頁面已加載後從服務器接收數據、在後台向服務器發送數據
		if (window.ActiveXObject) {		// 老版本的Internet Explorer （IE5 和IE6）使用ActiveX 對象
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}

	
	function sendMessage() { // 查找id屬性		
		if(user!=''){
			var msg = document.getElementById("text").value; // 要取得的欄位是value
			// 按下傳送紐，但文字欄位中並沒有文字時
			if (msg == "") {
				refreshMessage(); // 重整訊息區
				return;
			}
			// 傳送訊息
			var param = "task=send&msg=" +user+" : " + msg;
			ajaxRequest(param); // ajax 請求
			document.getElementById("text").value = ""; // 清空文字欄位					
		}else {location.href="Login2.jsp";}
	}
	// 	 定時查詢用這個
	function queryMessage() {
		var param = "task=query";
		ajaxRequest(param);
	}

	function ajaxRequest(param) {
		var url = "ChatRoomServlet";
		createXMLHttpRequest();
		xmlHttp.onreadystatechange = refreshMessage;
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;");
		xmlHttp.send(param);
	}

	function refreshMessage() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				// 處理顯示訊息的表格區域
				var messages = xmlHttp.responseXML
						.getElementsByTagName("message");

				var table_body = document.getElementById("MessagesArea");
				var length = table_body.childNodes.length;
				for (var i = 0; i < length; i++) {
					// 先移除原有的列（row）
					table_body.removeChild(table_body.childNodes[0]);
				}
				// 處理取回的訊息
				var messages = xmlHttp.responseXML.getElementsByTagName("message");	
					length = messages.length;
				for (var i = length - 1; i >= 0; i--) {
					var message = messages[i].firstChild.data;
					// 在表格中新增一列來排列訊息
					var row = createRow(message);
					table_body.appendChild(row);
				}
				// 下次2秒後會再查詢一下有無新訊息
				setTimeout("queryMessage()", 2000);
			}
		}
	}
	function createRow(message) {
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		var cell_data = document.createTextNode(message);
		cell.appendChild(cell_data);
		row.appendChild(cell);
		return row;
	}
//	滾動卷軸MessagesArea
//	$(row).ready(function() {
//	$("div").scroll(function() {
//		var div = document.getElementById('MessagesArea');
//		div.scrollTop = div.scrollHeight;
//	});
//});

