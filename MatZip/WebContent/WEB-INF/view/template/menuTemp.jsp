<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<c:forEach items="${css}" var="item">
	<link rel="stylesheet" type="text/css" href="/res/css/${item}.css">
</c:forEach>
</head>
<body>
	<div id="container">
		<header>
			<div id="headerLeft">
				<div class="containerPImg">
					<c:choose>
						<c:when test="${loginUser.profile_img != null}">
							<img class="pImg" alt="" src="/res/img/user/${loginUser.i_user}/${loginUser.profile_img}">
						</c:when>
						<c:otherwise>
							<img class="pImg" alt="" src="/res/img/default_profile.jpg">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="ml5">${loginUser.nm}님&nbsp;환영합니다.</div>
				<div class="ml15" id="headerLogout"><a href="/user/logout">logout</a></div>
			</div>
			<div id="headerRight">
				<a href="/restaurant/restMap">지도</a>
				<a class="ml30" href="/restaurant/restReg">등록</a>
				<a class="ml30" href="/user/restFavorite">찜</a>
			</div>
		</header>
		<section>
			<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
		</section>
		<footer>
			회사정보
		</footer>
	</div>
</body>
</html>