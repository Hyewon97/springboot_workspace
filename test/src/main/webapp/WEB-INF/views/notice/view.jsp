<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
<script>
	$(document).ready(function() {
		
		<%--
		var content = $('pre.content').text(); // p 태그의 텍스트 가져오기
		content = content.replaceAll("\n", '<br/>'); // a를 b로 바꾸기
		$('pre.content').text(content); // 변경된 텍스트로 p 태그 업데이트
		--%>
		
		$('#list').click(function() {
			$('#frm').attr('action', 'notice').submit();
		});
	});
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- /myapp 현재프로젝트 경로 리턴 -->

        <!-- 공지사항 상세보기 -->
        <section class="subpage_wrap notice_wrap">
            <div class="inner">
                <h2>도서관 소식</h2>
                <div class="contents notice_content">
                    <h4>${dto.title}</h4>
                    <p>
                        <span>작성자 : </span>관리자
                        <span>작성일자 : </span>${dto.reg_date}
                        <span>조회 : </span>${dto.readcount}
                    </p>
                </div>

                <div class="notice_cont_area">
                	<c:if test="${!empty dto.upload_img}">
						<img alt="공지사항 이미지" src='${srcImg}' >
					</c:if>
                    <p class="content">
                    	${dto.content}
                    </p>
                </div>
                
                <div class="notice_bottom notice_bottom_file">
                <c:if test="${empty dto.upload_file }">
                    <a>
                        <span>첨부파일 없음</span> 
                    </a>
                </c:if>
              	<c:if test="${!empty dto.upload_file}">
                    <a href="contentdownload?num=${dto.num}">
                        <span>[첨부파일 다운로드] : </span> ${fn:substringAfter(dto.upload_file,"_")}
                    </a>
                </c:if>   
                </div>

                 <div class="notice_bottom top">
                 	<c:choose>
		                 <c:when test="${pn.nextnum==0}">
		                 	<a>
		                        <span>다음 글</span>
		                        다음 글이 없습니다.
		                    </a>
		                 </c:when>
		                 <c:otherwise>
		                 	<c:url var="path" value="info">
								<c:param name="currentPage" value="${pv.currentPage}" />
								<c:param name="num" value="${pn.nextnum}" />
							</c:url>
			                <a href="${path}">
		                        <span>다음 글</span>
		                        ${pn.nextsub}
		                    </a>
		                 </c:otherwise>
	                </c:choose>	 
                </div>

                <div class="notice_bottom bottom">
					<c:choose>
		                 <c:when test="${pn.prenum==0}">
		                 	<a>
		                        <span>이전 글</span>
		                        이전 글이 없습니다.
		                    </a>
		                 </c:when>
		                 <c:otherwise>
		                 	<c:url var="path" value="info">
								<c:param name="currentPage" value="${pv.currentPage}" />
								<c:param name="num" value="${pn.prenum}" />
							</c:url>
			                <a href="${path}">
		                        <span>이전 글</span>
		                        ${pn.presub}
		                    </a>
		                 </c:otherwise>
	                </c:choose>	 
                </div>

                <a class="notice_back" href="notice?currentPage=${pv.currentPage}">목록보기</a>

            </div>
        </section>