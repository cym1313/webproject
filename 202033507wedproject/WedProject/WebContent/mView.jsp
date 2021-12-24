<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
    <%@ page import  = "cs.dit.dao.*, cs.dit.dto.*, java.util.List, java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=Ddevice-width, initial-scale-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>상세보기</title>
</head>
<body>
<div class="container">
<h2>상세보기</h2>
<br/>
<from action="a" method="post">
<input type="hidden" name="id" value="${dto.id}">

<table class="table table-striped table-hover">
<tr>
<th>id</th> <td>${dto.id}</td>
<th>password</th> <td><input type = "password" value="${dto.pwd}" name="pwd"></td>
</tr>
<tr>
<th>name</th> <td><input type = "text" value="${dto.name}" name="name"></td>
<th>email</th> <td><input type = "text" value="${dto.email}" name="email"></td>
</tr>
<tr>
<th>joinDate</th> <td colspan="3"><input type = "text" value="${dto.joinDate}" name="joinDate"></td>
</tr>
<tr>
<td colspan="4">
<input type="submit" value="멤버 수정">
<input type="button" value="멤버 삭제" onclick="location.href='delete.do?id=${dto.id}'"> 
<input type="button" value="멤버 목록" onclick="location.href='list.do'"> 
<input type="button" value="멤버 등록" onclick="location.href='insertForm.do'"> 
</td>
</tr>
</table>
<br><br>
</from>
</div>
<script type="text/javascript">

var xhr1 = new XMLHttpRequest();
var xhr2 = new XMLHttpRequest();

var id = encodeURIComponent(document.getElementById("id").value);

function replylist(){
	var replyTable = document.getRlementById("replyTable");
	replyTable.innerHTML = "";
	xhr1.onreadystatechange = function(){ 
		if(this.readyState == 4 && this.status == 200){ 
			var json = this.responseText;
			var list = JSON.parse(json);
			
			 for (var i=0 in list){
			 var row = replyTable.insertRow();
			 var celli = row.insertCell(0);
			 var cel12 = row.insertCell(1);
			 cell1.innerHTML = list[i].num; 
			 cell2.innerHTML = list[i].reply; 
			 }
		}
	};
	 xhr1.open('POST', rlist.rp, true);
			 xhr1.setRequestHeader ("Content-Type", "application/x-www-form-urlencoded; charset-UTF-8; "); 
	 var data='';
	 data += 'id=' + id; 
	 xhr1.send(data);
}
function rinsert(){
	var reply = encodeURIComponent(document.getElementById("reply").value); 
	xhr2.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("reply").value="";
	replylist(); 
	}
	};
	 xhr2.open('POST', 'rinsert.rp', true);
	 xhr2. setRequestHeader ("Content-Type", "application/x-www-form-urlencoded; charset-UTF-8"); 
	 var data = '';
	 data += 'reply=' + reply;
	 data += '&id='+ id;
	 xhr2.send (data);
}
window.onload=function(){
	replylist();
	}
</script>
<div class="container">
<table class="table" style="text-align:center; border: 1px solid #ddddddd">
<tr>
<td style="background-color:#fafafa; text-align:center">댓글: </td>
<td><input class="from-control" type="text" id="reply" size="80"></td>
<td colspan="2"><button class="btn btn-primary pull-right" onclick="rinsert();">한줄 댓글 작성</button></td>
</tr>
</table>
<table class="table" style="text-align:center; border: 1px solid #ddddddd">
<tbody id = "replyTable">
</tbody>
</table>
</div>
</body>
</html>