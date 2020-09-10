<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div>
		<div class="msg">${msg}</div>
		<form class="frm" action="/user/loginProc" method="post">
			<div><input type="text" name="user_id" placeholder="아이디"></div>
			<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
			<div><input type="submit" value="login"></div>
		</form>
		<div class="linkJoin"><a href="join">Join</a></div>
	</div>
</div>