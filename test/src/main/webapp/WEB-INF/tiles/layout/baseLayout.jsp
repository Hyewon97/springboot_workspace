<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- 아이폰 css format 제거 -->
        <meta name="format-detection" content="telephone=no" />
        <link rel="shortcut icon" href="/myapp/resources/assets/images/favicon.ico">
        <title>
          <tiles:insertAttribute name="title" />
        </title>

        <!-- style -->
        <link rel="stylesheet" href="/myapp/resources/css/common.css" />
        <link rel="stylesheet" href="/myapp/resources/css/main.css" />
        <link rel="stylesheet" href="/myapp/resources/css/sign.css" />
        <link rel="stylesheet" href="/myapp/resources/css/notice.css" />
        <link rel="stylesheet" href="/myapp/resources/css/info.css" />
        <link rel="stylesheet" href="/myapp/resources/css/books.css" />
        <link rel="stylesheet" href="/myapp/resources/css/request.css" />
        <link rel="stylesheet" href="/myapp/resources/css/my.css" />
        <link rel="stylesheet" href="/myapp/resources/css/recommend.css" />
        <link rel="stylesheet" href="/myapp/resources/css/mymy.css" />
        <link rel="stylesheet" href="/myapp/resources/css/loading.css" />

        <!-- script -->
        <script type="text/javascript" src="/myapp/resources/js/jquery-3.6.3.js"></script>
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script defer type="text/javascript" src="/myapp/resources/js/scripts.js"></script>
        
      </head>

      <body>
        <!-- 공통팝업 -->
        <section class="popup_back">
          <article class="popup">
            <p>팝업 내용입니다.</p>
            <button>확인</button>
          </article>
        </section>
        
    	<!-- 로딩 -->
	    <section class="loading_back">
	        <article class="loading">
	            <div class="load_ani">
	                <div></div>
	                <div></div>
	                <div></div>
	                <div></div>
	                <div></div>
	            </div>
	            <p>정보를 불러오는 중입니다.</p>
	        </article>
	    </section>
        

        <div class="wrap">
          <tiles:insertAttribute name="header" />
          <tiles:insertAttribute name="content" />
          <tiles:insertAttribute name="footer" />
        </div>

      </body>

      </html>