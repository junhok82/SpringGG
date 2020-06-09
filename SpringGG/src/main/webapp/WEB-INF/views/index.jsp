<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>
<body>
	<input type="text" id="txt" value="소환사 이름 검색">
	<button id="btn">검색</button>
	<p id="info"></p>
	<img src="img/CHALLENGER.png">

	<!-- ajax통신을 위한 스크립트 -->
	<script>
		$(document).ready(function(){	
 			$("#btn").click(function() {
 				var name = $("#txt").val();
				$.ajax({
					url : 'summoner/' + name,
					type : 'GET',
					dataType : 'json',//The data type expected of the server response. from server
					error : function(xhr, status, msg) {
						alert("상태값 :" + status + " Http에러메시지 :" + msg);
					},
					success : function(data) {
						$("#info").empty();	
						$("#info").append("<img src='http://ddragon.leagueoflegends.com/cdn/10.11.1/img/profileicon/" + data.profileIconId + ".png'/> <br>");
						$("#info").append(data.name + "<br>");
						//$("#info").append("<img src='img/" + data.tier + ".png'/> <br>");	
					}
				});//ajax
			});//btn 
		});//ready
	</script>

</body>
</html>