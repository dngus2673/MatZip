<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<title>${title}</title>
</head>
<body>
	<div id="container">
		<header>
			환영합니다. logout / menu
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