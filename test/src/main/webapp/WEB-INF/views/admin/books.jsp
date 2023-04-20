<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  <div class="container-fluid mt-5 mb-5 content_area">
    <div class="card">
      <h5 class="card-header">도서관리</h5>

      <div class="card-body">

        <div class="row">
          <div class="col-md-6">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary fw-bold" data-bs-toggle="modal"
              data-bs-target="#bookSetModal" id="goBookSet">도서등록</button>

            <!-- Modal -->
            <div class="modal fade" id="bookSetModal" aria-hidden="true">
              <div class="modal-dialog" style="max-width: 700px;">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5">도서등록</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="step01 active">
                    <div class="modal-body text-start">
                      <form name="searchfrm" id="searchfrm" method="post">
                        <div class="input-group mb-3">
                          <select class="form-select" name="target" id="target">
                            <option value="title">제목</option>
							<option value="publisher">출판사</option>
							<option value="isbn">ISBN</option>
                          </select>
                          <input type="text" class="form-control" name="search" id="search" placeholder="검색어를 입력하세요.">
                          <button class="btn btn-outline-primary bookset_btn" type="button"  value="검색" id="btnSearch">검색</button>
                        </div>
                      </form>
                      
					  <%-- 검색결과 출력 --%>
                      <p class="card-text" id="top"></p>
                      <div class="search_box"></div>
                      <div id="moreList" class="mt-3"></div>
                    </div>
                   
                  </div>

                  <form class="step02 active" name="selectfrm" id="selectfrm" method="post">
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
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="bookAddCancle">취소</button>
                      <button type="reset" class="btn btn-info book_reset_btn bookset_all_reset">검색 초기화</button>
                      <button type="submit" class="btn btn-primary" id="bookInsert">등록</button>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>

          <div class="col-md-6">
                     <form name="searchBookListfrm" id="searchBookListfrm" action="${pageContext.request.contextPath}/admin/books/searchBooks" method="get">
              <div class="input-group mb-3">
                <select class="form-select" name="option" id="option">
                <c:choose>
                   <c:when test="${option eq 'title'}">
                     <option value="title" selected="selected">제목</option>
                     <option value="author">저자</option>
                     <option value="isbn">ISBN</option>
                   </c:when>
                   <c:when test="${option eq 'author'}">
                     <option value="title">제목</option>
                     <option value="author" selected="selected">저자</option>
                     <option value="isbn">ISBN</option>
                   </c:when>
                   <c:when test="${option eq 'isbn'}">
                     <option value="title">제목</option>
                     <option value="author">저자</option>
                     <option value="isbn" selected="selected">ISBN</option>
                   </c:when>
                   <c:otherwise>
                     <option value="title" selected="selected">제목</option>
                     <option value="author">저자</option>
                     <option value="isbn">ISBN</option>
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
                <button class="btn btn-outline-primary" type="button" id="btnSearchBookList" >검색</button>         
              </div>
            </form>

          </div>
        </div>
		
		조회갯수 : ${totalCount}권

        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">제목</th>
              <th scope="col">저자</th>
              <th scope="col">출판사</th>
              <th scope="col">출판년도</th>
              <th scope="col">등록일자</th>
              <th scope="col" class=" text-end">〓</th>
              <th scope="col" class=" text-end">〓</th>
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
          	<c:set var="listNum" value="${Number}"/>
          	<c:forEach items="${aList}" var="dto">
	            <tr>
	              <th class="align-middle" scope="row">${listNum}</th>
	              <td class="align-middle">${dto.title}</td>
	              <td class="align-middle">${dto.author}</td>
	              <td class="align-middle">${dto.publisher}</td>
	              <td class="align-middle">${dto.pub_date}</td>
	              <td class="align-middle">${dto.receive_date}</td>
	              <td class="align-middle text-end">
	                <!-- Button trigger modal -->
	                <button type="button" class="btn btn-warning fw-bold" data-bs-toggle="modal"
	                  data-bs-target="#bookViewModal${dto.book_keynum}">상세</button>
	
	                <!-- Modal -->
	                <div class="modal fade" id="bookViewModal${dto.book_keynum}" aria-hidden="true">
	                  <div class="modal-dialog" style="max-width: 700px;">
	                    <div class="modal-content">
	                      <div class="modal-header">
	                        <h1 class="modal-title fs-5">도서 상세</h1>
	                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                      </div>
	                      <div class="modal-body text-start book_view">
	                        <img src='${dto.thumbnail}' alt="책 표지" />
	                        <ul>
	                          <li>ISBN : <span>${dto.isbn}</span></li>
	                          <li>제목 : <span>${dto.title}</span></li>
	                          <li>저자 : <span>${dto.author}</span></li>
	                          <li>출판사 : <span>${dto.publisher}</span></li>
	                          <li>출판년도 : <span>${dto.pub_date}</span></li>
	                          <li>도서소개 : <span>${dto.contents}</span></li>
	                          <li>분류기호 : <span>${dto.category_s}</span></li>
	                          <li>도서분류 : <span>${dto.book_category}</span></li>
	                          <li>입고일 : <span>${dto.receive_date}</span></li>
	                          
	                        
	                          <li>도서대출가능여부 : 
								<c:choose>
									<c:when test='${dto.loan_state == "Y"}'>
										 <span>가능</span>
									</c:when>
									<c:otherwise>
										<span>불가능</span>
									</c:otherwise>
								</c:choose>
							  </li>
	                          <li>도서예약가능여부 : 
	                          	<c:choose>
									<c:when test='${dto.borrow_state == "Y"}'>
										 <span>가능</span>
									</c:when>
									<c:otherwise>
										<span>불가능</span>
									</c:otherwise>
								</c:choose>
	                          </li>
	                          </ul>
	                      </div>
	                      <div class="modal-footer">
	                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	              </td>
	              <td class="align-middle text-end">
	                <button type="button" class="btn btn-danger fw-bold" data-bs-toggle="modal"
	                  data-bs-target="#bookDelModal${dto.book_keynum}">삭제</button>
	
	                <div class="modal fade" id="bookDelModal${dto.book_keynum}" aria-hidden="true">
	                  <div class="modal-dialog">
	                    <div class="modal-content">
	                      <div class="modal-header">
	                        <h1 class="modal-title fs-5">도서 삭제</h1>
	                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                      </div>
	                      <div class="modal-body text-start">
	                        <p>선택하신 도서를 삭제하시겠습니까?</p>
	                      </div>
	                      <div class="modal-footer">
	                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
		                    <form name="frmBookDelete${dto.book_keynum}" id="frmBookDelete${dto.book_keynum}" method="get">
	                          <input type="hidden" name="num" value="${dto.book_keynum}" />
	                          <button type="button" class="btn btn-danger" onclick="btnBookDelete(${dto.book_keynum})">삭제</button>
	                        </form>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	              </td>
	            </tr>
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
						href="searchBooks?option=${option}&searchword=${query}&currentPage=1">&lt;&lt;</a></li>
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
						href="searchBooks?option=${option}&searchword=${query}?currentPage=${pv.startPage-pv.blockPage}">&lt;</a></li>
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
								href="searchBooks?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="searchBooks?option=${option}&searchword=${query}&currentPage=${i}">${i}</a>
						</c:otherwise>
						
					</c:choose></li>
			</c:forEach>
			<!-- 페이지번호 출력 끝 -->
	
			<!-- 다음 출력 시작 -->
			<c:choose>
				<c:when test="${searchPv.endPage < searchPv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="searchBooks?option=${option}&searchword=${query}&currentPage=${pv.startPage + pv.blockPage}">&gt;</a>
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
						href="searchBooks?option=${option}&searchword=${query}&currentPage=${pv.totalPage}">&gt;&gt;</a></li>
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
						href="books?currentPage=1">&lt;&lt;</a></li>
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
						href="books?currentPage=${pv.startPage-pv.blockPage}">&lt;</a></li>
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
								href="books?currentPage=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="books?currentPage=${i}">${i}</a>
						</c:otherwise>
						
					</c:choose></li>
			</c:forEach>
			<!-- 페이지번호 출력 끝 -->
	
			<!-- 다음 출력 시작 -->
			<c:choose>
				<c:when test="${pv.endPage < pv.totalPage}">
					<li class="page-item"><a class="page-link"
						href="books?currentPage=${pv.startPage + pv.blockPage}">&gt;</a>
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
						href="books?currentPage=${pv.totalPage}">&gt;&gt;</a></li>
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
	
	//도서검색
    $('#btnSearchBookList').click(function() {
        $('#searchBookListfrm').submit();
    });

});//end of document
	
	//도서추가 버튼
	  $('#goBookSet').on('click', function(){
		  $('#search').focus();
	  });  //end of 도서추가버튼

	/////////////////////////////////////////////////////////////////
	//도서 검색 전역 변수
	    var page=1;
	    var target = $("#target option:selected").val();  //조건
	    var query = $('#search').val();  //검색어
	    var is_end=false;

	    var test = 0;
	 
	    //검색기능 (마우스클릭)
	    $("#btnSearch").click(function(){
			getData(); //검색조건 설정
	        getList(); //검색결과 출력
	        addMoreBtn(); //더보기 버튼
	        
		});
	    
	  	//검색(엔터)
	    $("#search").keydown(function(key){
	        if(key.keyCode==13){ 
	        	key.preventDefault();
	    		getData();
	            getList();
	            addMoreBtn();
	        }
	    });
	
	  //리스트초기화 및 검색조건 설정
	  	function getData(){
	  		test = 0;
	        page=1;
	        $("#top").html("");
	        $(".search_box").html("");
	        $("#moreList").html("");
	        
	        $("#top").append(`<span>검색결과 :&nbsp;</span><span id="total"></span><span>건</span>`);
 
	        target = $("#target option:selected").val();
	        query = $('#search').val();
	  	}//getData()
	    
	  	
	  	//검색결과 출력
	    function getList(){
	        $.ajax({
	            type:"get",
	            url:"https://dapi.kakao.com/v3/search/book?",
	            headers:{"Authorization":"KakaoAK dacb438d04968f33fd5fcc9f9a1c6cd2"},
	            dataType:"json",
	            data:{"target":target, "query":query, "page":page, "size":"5"},
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
						$('.search_box').append(`<div><input name='all_data` + test +`' type='hidden' value='`+ JSON.stringify(element) +`' /><button class='data_btn' name='btnSelect' ype='button'>선택</button><img src='`+ element.thumbnail +`' /><ul><li>제목 : `+ element.title +`</li><li>ISBN : `+ element.isbn +`</li><li>저자 : `+ element.authors +`</li><li>출판사 : `+ element.publisher +`</li><li>출판일자 : `+ element.datetime +`</li></ul></div>`);   
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
	                $("button[name='btnSelect']").click(function() {
	                  var index = $("button[name='btnSelect']").index(this);
	                  console.log(index);
	                  var selectBookString = $('input[name="all_data'+ index +'"]').val();
	                  console.log(selectBookString)
	                  
	                  var selectBook = JSON.parse(selectBookString);
	                  $("#sTitle").val(selectBook.title);
	                  $("#sIsbn").val(selectBook.isbn);
	                  $("#sAuthor").val(selectBook.authors);
	                  $("#sPublisher").val(selectBook.publisher);
	                  $("#sPub_date").val(selectBook.datetime);
	                  $("#sContents").val(selectBook.contents);
	                  $("#sThumbnail").val(selectBook.thumbnail);

	                }); 
	             	
	                //도서추가
	              	$('#bookInsert').click(function(){
	              		 if(document.getElementById("sCount").value==""){
	              	        alert("등록수량을 입력하세요");
	              	        return false;
	              	    }
	            		$('#selectfrm').attr('action', 'books/bookupdate').submit();
	                });

	            },
	            error:function(){
	                console.log("검색 실패");
	            }
	        });   
	    }//getList()
	    
	    //더보기 버튼
	    function addMoreBtn() {
	    	$("#moreList").append("<button id='btnMore' class='btn btn-primary btn-block' style='width: 100%;'>"+"더보기"+"</button>");

	    	//페이지가 끝났으면 자료를 더 보여주지 않게 하는 기능
	        $("#btnMore").on("click",function(){
	            if(!is_end){
	                page += 1;
	                getList(); 

	            }
	        });
	    }//addMoreBtn()
  	
	////////////////////////////////////////////////////////////////////////
	
	//삭제
	function btnBookDelete(val) {
	    // 검색했을때 특정값이 있으니깐 action을 /delete로 
	    // 검색을안했을땐 action값을 books/delete
		$('#frmBookDelete'+val).attr('action', 'books/delete').submit();
	}
	    
</script>  