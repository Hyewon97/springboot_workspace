<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>
	<tiles:insertAttribute name="title" />
</title>

<link href="/myapp/resources/admin/bootstrap/bootstrap.css" rel="stylesheet">

<script src="/myapp/resources/admin/script/jquery_341.js"></script>
<script src="/myapp/resources/admin/bootstrap/bootstrap.min.js"></script>
<script src="/myapp/resources/admin/script/scripts.js"></script>

</head>

<body>
        
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="content" />
	<tiles:insertAttribute name="footer" />
</body>

</html>
