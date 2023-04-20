<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  <div class="container-fluid mt-5 mb-5 content_area">
    <div class="card">
      <h5 class="card-header">도서신청관리</h5>
      <div class="card-body">

        <div class="row">
          <div class="col-md-6"></div>
          <div class="col-md-6">
            <form name="searchRequestfrm" id="searchRequestfrm" action="${pageContext.request.contextPath}/admin/request/searchRequest" method="get">
              <div class="input-group mb-3">
                <select class="form-select" name="option" id="option">
                <c:choose>
                   <c:when test="${option eq 'title'}">
                     <option value="title" selected="selected">도서명</option>
                     <option value="user_id">신청회원아이디</option>
                   </c:when>
                   <c:when test="${option eq 'user_id'}">
                     <option value="title">도서명</option>
                     <option value="user_id" selected="selected">신청회원아이디</option>
                   </c:when>
                   <c:otherwise>
                     <option value="title" selected="selected">도서명</option>
                     <option value="user_id">신청회원아이디</option>
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
                <button class="btn btn-outline-primary" type="button" id="btnSearchRequest" >검색</button>
              </div>
            </form>

          </div>
        </div>

		조회갯수 : ${totalCount}건

        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">ISBN</th>
              <th scope="col">도서명</th>
              <th scope="col">저자</th>
              <th scope="col">신청회원 ID</th>
              <th scope="col">신청일</th>
              <th scope="col">반영일</th>
              <th scope="col">반영상태</th>
              <th scope="col" class="text-end">〓</th>
            </tr>
          </thead>
          
        <c:if test="${totalCount==0}">
		<tbody>
		<tr>
		<th class="text-center" colspan="8">${zero}</th>
		</tr>
		</tbody>
		</c:if>
          
          <c:if test="${totalCount>=1}">
          <tbody>
            <!-- 반복 -->
            <c:set var="listNum" value="${Number}"/>
          	<c:forEach items="${aList}" var="dto">
            <tr>
              <th class="align-middle" scope="row">${dto.request_keynum}</th>
              <td class="align-middle">${dto.isbn}</td>
              <td class="align-middle">${dto.title}</td>
              <td class="align-middle">${dto.author}</td>
              <td class="align-middle">${dto.user_id}</td>
              <td class="align-middle">${dto.request_date}</td>
              <td class="align-middle">${dto.ref_date}</td>
              <td class="align-middle">
				<c:choose>
					<c:when test='${dto.request_state == "Y"}'>
						<span>수락</span>
					</c:when>
					<c:when test='${dto.request_state == "N"}'>
						<span>반려</span>
					</c:when>
					<c:otherwise>
						<span>${dto.request_state}</span>
					</c:otherwise>
				</c:choose>

              </td>
              <td class="align-middle text-end">
                <!-- 처리한 건의 경우 이 영역 비워 둠(-)  -->
				<c:if test="${empty dto.ref_date}">
					<button type="button" class="btn btn-warning fw-bold" data-bs-toggle="modal"
                  data-bs-target="#requestModal${dto.request_keynum}">처리</button>
				</c:if>
                
                <!-- Modal -->
                <div class="modal fade" id="requestModal${dto.request_keynum}" aria-hidden="true">
                  <div class="modal-dialog" style="max-width: 700px;">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5">도서신청 처리</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>

                      <div class="request_step01 active">
                        <div class="modal-body text-start">
                          <h4>도서신청정보</h4>
                          <ul>
                            <li>신청번호 : <span>${dto.request_keynum}</span></li>
                            <li>ISBN : <span>${dto.isbn}</span></li>
                            <li>도서명 : <span>${dto.title}</span></li>
                            <li>저자 : <span>${dto.author}</span></li>
                            <li>신청회원 ID : <span>${dto.user_id}</span></li>
                            <li>신청사유 : <span>${dto.request_text}</span></li>
                            <li>신청일 : <span>${dto.request_date}</span></li>
                          </ul>
                        </div>
                        <div class="modal-footer">
                          <form name ='requestFrm${dto.request_keynum}' method="post">
                            <input name="query" type="hidden" value="${dto.isbn}" />
                            <input name="request_keynum" type="hidden" value="${dto.request_keynum}" />
                            <button type="reset" class="btn btn-info" id="btnReject${dto.request_keynum}" onclick="requestReject(${dto.request_keynum})">반려</button>
                            <button type="button" class="btn btn-primary" id="btnSearch${dto.request_keynum}" onclick="SearchISBN(${dto.request_keynum},${dto.isbn})">신청도서조회</button>
                          </form>
                          </div>
                      </div>

                      <div class="request_step02 active">
                        <div class="modal-body text-start">
                          <div class="search_box search_box${dto.request_keynum}"></div>
                        </div>
                      </div>

                      <form class="request_step03 active" name="selectfrm${dto.request_keynum}" id="selectfrm${dto.request_keynum}" method="post">
                        <div class="modal-body text-start">
                      <div class="input-group mb-3">
                        <span class="input-group-text">ISBN</span>
                        <input type="text" class="form-control" name="sIsbn" id="sIsbn" value="" readonly>
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">제목</span>
                        <input type="text" class="form-control" name="sTitle" id="sTitle" value="" readonly>
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">저자</span>
                        <input type="text" class="form-control" name="sAuthor" id="sAuthor" value="" readonly>
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">출판사</span>
                        <input type="text" class="form-control" name="sPublisher" id="sPublisher" value="" readonly>
                      </div>
                      <div class="input-group mb-3">
                        <input type="hidden" name="sContents" id="sContents" value="" />
                        <input type="hidden" name="sThumbnail" id="sThumbnail" value="" />
                        <input type="hidden" name="sPub_date" id="sPub_date" value="" />
                      </div>
                      <div class="input-group mb-3">
                        <span class="input-group-text">등록수량</span>
                        <input type="number" class="form-control" name="sCount" id="sCount" value="" placeholder="등록 수량을 입력하세요.">
                      </div>
                    </div>
                        <div class="modal-footer">
                         	<input type="hidden" name="request_keynum" id="request_keynum" value="${dto.request_keynum}">	
	                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="bookAddCancle">취소</button>
	                      <button type="submit" class="btn btn-primary" id="bookInsert${dto.request_keynum}">등록</button>
	                    </div>
                      </form>

                    </div>
                  </div>
                </div>
              </td>
            </tr>
            <!-- 반복 -->
            <c:set var="listNum" value="${listNum-1}"></c:set>
            </c:forEach>
          </tbody>
          </c:if>
        </table>

	<c:choose>
		<c:when test="${not empty searchPv}">
			<nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
          <!-- 처음 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.startPage >1}">
					<li class="page-item"><a class="page-link"
						href="searchRequest?option=${option}&searchword=${query}&currentPage=1">&lt;&lt;</a></li>
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
						href="searchRequest?option=${option}&searchword=${query}?currentPage=${pv.startPage-pv.blockPage}">&lt;</a></li>
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
								href="searchRequest?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="searchRequest?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
						</c:otherwise>
						
					</c:choose></li>
			</c:forEach>
			<!-- 페이지번호 출력 끝 -->
	
			<!-- 다음 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.endPage < searchPv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="searchRequest?option=${option}&searchword=${query}&currentPage=${pv.startPage + pv.blockPage}">&gt;</a>
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
						href="searchRequest?option=${option}&searchword=${query}&currentPage=${pv.totalPage}">&gt;&gt;</a></li>
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
						href="request?currentPage=1">&lt;&lt;</a></li>
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
						href="request?currentPage=${pv.startPage-pv.blockPage}">&lt;</a></li>
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
								href="request?currentPage=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="request?currentPage=${i}">${i}</a>
						</c:otherwise>
					</c:choose></li>
			</c:forEach>
			<!-- 페이지번호 출력 끝 -->
	
			<!-- 다음 출력 시작 -->
			<c:choose>
				<c:when test="${pv.endPage < pv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="request?currentPage=${pv.startPage + pv.blockPage}">&gt;</a>
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
						href="request?currentPage=${pv.totalPage}">&gt;&gt;</a></li>
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
	
	$('.bookset_all_reset').on('click', function(){
		$('#searchfrm')[0].reset();
		$('.bookset_btn').trigger("click");
	});
	
	
	//신청도서검색
    $('#btnSearchRequest').click(function() {
        $('#searchRequestfrm').submit();
    });

	});//end of document

	/////////////////////////////////////////////////////////////////

	    function SearchISBN(keynum,isbn) {
		
	     var page=1;
		 var query = isbn;  //신청도서 isbn
		 var is_end=false;
		 var test = 0;
		 
		getData(); //검색조건 설정
	    getList(query, page, keynum); //검색결과 출력
	    
	    console.log(query);
	        
		}

	  //리스트초기화 및 검색조건 설정
	  	function getData(){
	  		test = 0;
	        page=1;
	        $("#top").html("");
	        $(".search_box").html("");
	        $("#moreList").html("");
	        
	        $("#top").append(`<span>검색결과 :&nbsp;</span><span id="total"></span><span>건</span>`);
 
	        target = $("#target option:selected").val();
	        query = $('.query li:first-child span').text();
	  	}//getData()
	    
	  	
	  	//검색결과 출력
	    function getList(query, page, keynum){
	        $.ajax({
	            type:"get",
	            url:"https://dapi.kakao.com/v3/search/book?",
	            headers:{"Authorization":"KakaoAK dacb438d04968f33fd5fcc9f9a1c6cd2"},
	            dataType:"json",
	            data:{"target":"isbn", "query":query, "page":page, "size":"5"},
	            success:function(data){
	            	
	            	console.log(data);
	            	
	            	 // 1. 받아온 JSON 데이터를 JavaScript 객체로 변환
	                var jsonData = JSON.parse(JSON.stringify(data));

	                // 2. JavaScript 객체를 수정 (작가, isbn, datetime)
	                for(var i=0; i<jsonData.documents.length; i++){
	                    jsonData.documents[i].authors = jsonData.documents[i].authors.join(",");
	                    
	                    jsonData.documents[i].isbn =jsonData.documents[i].isbn.replace(/[^0-9]/g, ''); // 숫자 외의 문자를 모두 제거
					    if (jsonData.documents[i].isbn.length > 13) { 
					    	jsonData.documents[i].isbn = jsonData.documents[i].isbn.slice(-13); //isbn 13자리만 자르기
					    }
	                    
					    jsonData.documents[i].contents = jsonData.documents[i].contents.replaceAll("'", "");  //'를 없애버림
	                    
					    var date = new Date(jsonData.documents[i].datetime);
					    var year = date.getFullYear();
					    jsonData.documents[i].datetime = year;

	                }

	                // 3. 수정된 JavaScript 객체를 다시 JSON 형태로 변환
	                var newJsonData = JSON.stringify(jsonData);
	       	            	
	            	//4.JSON 문자열을 객체로 변환
	            	var bookData = JSON.parse(newJsonData); 
	            	
	            	console.log("-----")
	            	console.log(bookData)
	            	
	            	
					$.each(bookData.documents, function(index, element) {
						$('.search_box'+ keynum).append(`<div><input name='all_data` + test +`' type='hidden' value='`+ JSON.stringify(element) +`' /><button class='data_btn' name='btnSelect' type='button'>선택</button><img src='`+ element.thumbnail +`' /><ul><li>제목 : `+ element.title +`</li><li>ISBN : `+ element.isbn +`</li><li>저자 : `+ element.authors +`</li><li>출판사 : `+ element.publisher +`</li><li>출판일자 : `+ element.datetime +`</li></ul></div>`);   
						test += 1;

					});           	
	            	

					//검색결과갯수
	                var total = data.meta.total_count;
	                $("#total").text(total);
	                if (total == 0) {
	                    $("#moreList").html("<p>검색 결과가 없습니다.</p>");
	                }
	                
	                //마지막페이지인지 여부 (마지막페이지면 더보기 버튼삭제)
	                is_end = data.meta.is_end;
	                if (is_end) {
	                	$("#btnMore").hide();
	                	$("#moreList").html("<p>더 이상 검색 결과가 없습니다.</p>");
	                }
	                
	             	//선택버튼 이벤트 처리
	             	function selectBtnSet(keynum) {
	             		
	             	}
	             	
	             	
	               $('.search_box'+ keynum +' button[name="btnSelect"]').click(function() {
	                  var index = $('.search_box'+ keynum +' button[name="btnSelect"]').index(this);
	                  console.log(index);
	                  var selectBookString = $('input[name="all_data'+ index +'"]').val();
	                  console.log(selectBookString)
	                  console.log(keynum)
	                  
	                  var selectBook = JSON.parse(selectBookString);
	                  
	                  $('form[name="selectfrm' + keynum + '"] input[name="sTitle"]').val(selectBook.title);
	                  $('form[name="selectfrm' + keynum + '"] input[name="sIsbn"]').val(selectBook.isbn);
	                  $('form[name="selectfrm' + keynum + '"] input[name="sAuthor"]').val(selectBook.authors);
	                  $('form[name="selectfrm' + keynum + '"] input[name="sPublisher"]').val(selectBook.publisher);
	                  $('form[name="selectfrm' + keynum + '"] input[name="sPub_date"]').val(selectBook.datetime);
	                  $('form[name="selectfrm' + keynum + '"] input[name="sContents"]').val(selectBook.contents);
	                  $('form[name="selectfrm' + keynum + '"] input[name="sThumbnail"]').val(selectBook.thumbnail);
	                }); 
	             	
	                //신청도서추가 (등록)
	               $('#bookInsert'+keynum).click(function(){
	            	   if($('form[name="selectfrm' + keynum + '"] input[name="sTitle"]').val() == ""){
	            	        alert("책을 선택하세요");
	            	        return false;
	            	    }
	            	    if($('form[name="selectfrm' + keynum + '"] input[name="sCount"]').val() == ""){
	            	        alert("등록수량을 입력하세요");
	            	        return false;
	            	    }
	            	    $('form[name="selectfrm' + keynum + '"]').attr('action', 'request/accept').submit();
	            	});

	            },
	            error:function(){
	                console.log("검색 실패");
	            }
	        });   
	    }//getList()

	    function requestReject(keynum){
	    	$('form[name="requestFrm' + keynum + '"]').attr('action', 'request/reject').submit();
	    }

	    
</script>  