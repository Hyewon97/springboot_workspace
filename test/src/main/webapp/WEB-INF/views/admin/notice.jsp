<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  <div class="container-fluid mt-5 mb-5 content_area">
    <div class="card">
      <h5 class="card-header">공지사항관리</h5>

      <div class="card-body">

        <div class="row">
          <div class="col-md-6">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary fw-bold" data-bs-toggle="modal"
              data-bs-target="#noticeSetModal">글쓰기</button>

            <!-- Modal -->
            <div class="modal fade" id="noticeSetModal" aria-hidden="true">
              <div class="modal-dialog" style="max-width: 700px;">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">공지사항 작성</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>

                  <form name="frm" id="frm" method="post" enctype="multipart/form-data">
                    <div class="modal-body text-start">
                      <div class="input-group mb-3">
                        <span class="input-group-text">제목</span>
                        <input type="text" name="title" class="form-control" placeholder="제목을 입력하세요.">
                      </div>
                      <div class="input-group mb-3">
                        <div class="input-group-text">
                          <input class="form-check-input mt-0" type="checkbox" id="fix" name="fix" value="Y">
                        </div>
                        <p class="form-control mb-0">게시판 상단 고정</p>
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">본문</span>
                        <textarea name="content" class="form-control" rows="10"></textarea>
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">본문 이미지</span>
                        <input class="form-control" type="file" name="imgname" id="imgname" accept="image/*" >
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">첨부파일</span>
                        <input class="form-control" type="file"  name="filename" id="filepath">
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">작성(수정)자</span>
                        <input type="hidden" name="memberEmail" value="admin01" />
						<input class="form-control" type="text" name="memberName" value="관리자" readonly  />
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                      <input type="hidden" value="">
                      <button type="button" id="btnSave" class="btn btn-primary">등록</button>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>

          <div class="col-md-6">
            <form  name="searchNoticefrm" id="searchNoticefrm" action="${pageContext.request.contextPath}/admin/notice/search" method="get">
              <div class="input-group mb-3">
                <select class="form-select" name="option" id="option">
                <c:choose>
                   <c:when test="${option eq 'title'}">
                      <option value="title" selected="selected">제목</option>
                      <option value="admin_id">작성자 ID</option>
                      <option value="admin_name">작성자 이름</option>
                   </c:when>
                   <c:when test="${option eq 'admin_id'}">
                      <option value="title">제목</option>
                      <option value="admin_id" selected="selected">작성자 ID</option>
                      <option value="admin_name">작성자 이름</option>
                   </c:when>
                   <c:when test="${option eq 'admin_name'}">
                      <option value="title">제목</option>
                      <option value="admin_id">작성자 ID</option>
                      <option value="admin_name" selected="selected">작성자 이름</option>
                   </c:when>
                   <c:otherwise>
                      <option value="title" selected="selected">제목</option>
                      <option value="admin_id">작성자 ID</option>
                      <option value="admin_name">작성자 이름</option>
                   </c:otherwise>
                </c:choose>
                </select>
                <c:choose>
                   <c:when test="${empty query}">
                   <input type="text" class="form-control" placeholder="검색어를 입력하세요." name="searchword" id="searchword" value="">
                   </c:when>
                   <c:otherwise>
                   <input type="text" class="form-control" placeholder="검색어를 입력하세요." name="searchword" id="searchword" value="${query}">
                   </c:otherwise>
                </c:choose>
                <button class="btn btn-outline-primary" type="button" id="btnNoticeSearch" >검색</button>
              </div>
            </form>
          </div>
        </div>

		조회갯수 : ${totalCount2}개
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">고정</th>
              <th scope="col">제목</th>
              <th scope="col">작성자</th>
              <th scope="col">작성일자</th>
              <th scope="col" class=" text-end">〓</th>
              <th scope="col" class=" text-end">〓</th>
            </tr>
          </thead>
          
          <tbody>
            <c:if test="${totalCount==0}">
				<tr>
					<td colspan="7" class="not_cont text-center">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
          <c:set var="boardNum" value="${Number}"/>
          
          <c:forEach items="${aList}" var="dto">
            <tr>
              <th class="align-middle" scope="row">${boardNum}</th>
              <td class="align-middle">${dto.fix}</td>
              <td class="align-middle">${dto.title}
              	<%-- 첨부파일있으면 --%>
					<c:if test="${!empty dto.upload_file}">
						<span><strong>F</strong></span>
					</c:if>
				<%-- 사진첨부있으면 --%>
					<c:if test="${!empty dto.upload_img}">
						<span><strong>I</strong></span>
					</c:if>
					
			  </td>
              <td class="align-middle">${dto.admin_name}</td>
              <td class="align-middle">${dto.reg_date}</td>
              <td class="align-middle text-end">
                <!-- Button trigger modal -->
                <button type="button" id="updateView" class="btn btn-warning fw-bold" data-bs-toggle="modal" data-bs-target="#noticeModModal${dto.num}" >상세/수정</button>
 

                <!-- Modal -->
                <div class="modal fade" id="noticeModModal${dto.num}" aria-hidden="true">
                  <div class="modal-dialog" style="max-width: 700px;">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5">공지사항 상세/수정</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      	
                      </div>
                      <form name="frmUpdate${dto.num}" id="frmUpdate${dto.num}" method="post" enctype="multipart/form-data">
                        <div class="modal-body text-start">
                          <div class="input-group mb-3">
                            <span class="input-group-text">제목</span>
                            <input class="form-control" type="text" name="title" id="title" value="${dto.title}">
                          </div>
                          <div class="input-group mb-3">
                            <div class="input-group-text">
	                            <c:if test="${dto.fix=='Y'}">
	                            <input type="checkbox" id="fix" name="fix" checked>
	                            </c:if>
                            	<c:if test="${dto.fix!='Y'}">
	                            	<input type="checkbox" id="fix" name="fix" value='Y'>
	                            </c:if>
                            </div>
                            <p class="form-control mb-0">게시판 상단 고정</p>
                          </div>
                          <div class="input-group mb-3">
	                        <span class="input-group-text">본문</span>
	                        <%
							pageContext.setAttribute("LF", "\n");
							pageContext.setAttribute("BR", "<br/>");
							%>
							<c:set var="str1" value="${dto.content}" />		
							<textarea id="content" name="content" class="form-control contentUpdate" rows="10"><c:out value="${fn:replace(str1, BR, LF)}" /></textarea>                        
                          </div>

                          <div class="input-group mb-3">
                            <span class="input-group-text">본문 이미지</span>
                            <input class="form-control" type="text" value='${fn:substringAfter(dto.upload_img,"_")}'>
                          </div>
                          <div class="input-group mb-3">
                            <span class="input-group-text">첨부파일</span>
                            <input class="form-control" type="text" value='${fn:substringAfter(dto.upload_file,"_")}'>
                          </div>
                           <div class="input-group mb-3">
                            <span class="input-group-text">작성(수정)자</span>
                            <input type="text" class="form-control" value="${dto.admin_name}" readonly>
                          </div>
                                                   
                          <hr/>
                          <h4>첨부 파일 수정하기</h4>
                          <div class="input-group mb-3">
                            <span class="input-group-text">본문 이미지</span>
                            <input class="form-control" type="file" name="imgname" accept="image/*">
                          </div>
                          <div class="input-group mb-3">
                            <span class="input-group-text">첨부파일</span>
                            <input class="form-control" type="file" name="filename">
                          </div>

                        </div>
                        <div class="modal-footer">
                          <button type="button" id="cancle" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                          <input type="hidden" name="num" value="${dto.num}" />
                          <input type="hidden" name="currentPage" value="${pv.currentPage}" />
                          <button type="submit" class="btn btn-warning" onclick="btnUpdate(${dto.num})">수정</button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </td>
              <td class="align-middle text-end">
                <button type="button" class="btn btn-danger fw-bold" data-bs-toggle="modal"
                  data-bs-target="#noticeDelModal${dto.num}">삭제</button>

                <div class="modal fade" id="noticeDelModal${dto.num}" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5">공지사항 삭제</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div class="modal-body text-start">
                        <p>선택하신 공지사항을 삭제하시겠습니까?</p>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        <form name="frmDelete${dto.num}" id="frmDelete${dto.num}" method="get">
                          <input type="hidden" name="num" value="${dto.num}" />
                          <button type="button" class="btn btn-danger" onclick="btnDelete(${dto.num})">삭제</button>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
            <c:set var="boardNum" value="${boardNum-1}"></c:set>
            </c:forEach>
            

          </tbody>

          
        </table>

	<c:choose>
		<c:when test="${not empty searchPv}">
			<nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
          <!-- 처음 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.startPage >1}">
					<li class="page-item"><a class="page-link"
						href="search?option=${option}&searchword=${query}&currentPage=1">&lt;&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&lt;&lt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 처음 출력 끝 -->
	
			<!-- 이전 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.startPage >1}">
					<li class="page-item"><a class="page-link"
						href="search?option=${option}&searchword=${query}&currentPage=${pv.startPage-pv.blockPage}">&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&lt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 이전 출력 끝 -->
	
			<!-- 페이지번호 출력 시작 -->
			<c:forEach var="i" begin="${searchPv.startPage}" end="${searchPv.endPage}">
				<li><c:choose>
						<c:when test="${i==searchPv.currentPage}">
							<a class="page-link page-item active"
								href="search?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="search?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
						</c:otherwise>
						
					</c:choose></li>
			</c:forEach>
			<!-- 페이지번호 출력 끝 -->
	
			<!-- 다음 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.endPage < searchPv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="search?option=${option}&searchword=${query}&currentPage=${pv.startPage + pv.blockPage}">&gt;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&gt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 다음 출력 끝 -->
	
			<!-- 마지막 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.endPage < searchPv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="search?option=${option}&searchword=${query}&currentPage=${pv.totalPage}">&gt;&gt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&gt;&gt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 마지막 출력 끝 -->
          </ul>
        </nav>
		</c:when>
		
		<c:when test="${empty searchPv and not empty pv}">
		<nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
          <!-- 처음 출력 시작 -->
			<c:choose>
				<c:when test="${pv.startPage >1}">
					<li class="page-item"><a class="page-link"
						href="notice?currentPage=1">&lt;&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&lt;&lt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 처음 출력 끝 -->
	
			<!-- 이전 출력 시작 -->
			<c:choose>
				<c:when test="${pv.startPage >1}">
					<li class="page-item"><a class="page-link"
						href="notice?currentPage=${pv.startPage-pv.blockPage}">&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&lt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 이전 출력 끝 -->
	
			<!-- 페이지번호 출력 시작 -->
			<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
				<li><c:choose>
						<c:when test="${i==pv.currentPage}">
							<a class="page-link page-item active"
								href="notice?currentPage=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="notice?currentPage=${i}">${i}</a>
						</c:otherwise>
						
					</c:choose></li>
			</c:forEach>
			<!-- 페이지번호 출력 끝 -->
	
			<!-- 다음 출력 시작 -->
			<c:choose>
				<c:when test="${pv.endPage < pv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="notice?currentPage=${pv.startPage + pv.blockPage}">&gt;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&gt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 다음 출력 끝 -->
	
			<!-- 마지막 출력 시작 -->
			<c:choose>
				<c:when test="${pv.endPage < pv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="notice?currentPage=${pv.totalPage}">&gt;&gt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<p class="page-link">&gt;&gt;</p>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 마지막 출력 끝 -->
          </ul>
        </nav>
		</c:when>
	</c:choose>

		
      </div>
    </div>
  </div>
  
  
  <script>
$(document).ready(function () {
	
	//게시글검색
    $('#btnNoticeSearch').click(function() {
        $('#searchNoticefrm').submit();
    });

	//글쓰기
	  $('#btnSave').on('click', function(){
		  $('#frm textarea[name=content]').val($('[name=content]').val().replace(/\n/gi, '<br/>'));
		  $('#frm').attr('action', 'notice/write').submit();
	  });

	  $('#filepath').change(function () {
	    console.log(this.files[0].size);
	    if (this.files[0].size > 1000000000) {
	      alert('1GB이하만 첨부할 수 있습니다.');
	      $('#filepath').val('');
	      return false;
	    }
	  });

	  $('#imgname').change(function () {
	    var fileVal = $('#imgname').val();
	    if (fileVal != '') {
	      var ext = fileVal.split('.').pop().toLowerCase(); //확장자분리
	      //아래 확장자가 있는지 체크
	      if ($.inArray(ext, ['jpg', 'jpeg', 'gif', 'png']) == -1) {
	        alert('"jpg,gif,jpeg,png" 파일만 업로드 할 수 있습니다.');
	        $('#imgname').val('');
	        return false;
	      }
	    }
	  });

		$('#cancle').click(function(){
			$('#frmUpdate #title').val('${dto.title}');
			$('#content').val('${dto.content}');
		}); 

	
	});//end of document

	
	//수정
	function btnUpdate(val) {
		$('#frmUpdate'+ val +' textarea[name=content]').val($('#frmUpdate'+ val +' textarea[name=content]').val().replace(/\n/gi, '<br/>'));
		$('form[name=frmUpdate'+ val +']').attr('action', 'notice/update').submit();
	}

	
	//삭제
	function btnDelete(val) {
		$('#frmDelete'+val).attr('action', 'notice/delete').submit();
	}
</script>