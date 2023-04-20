<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 희망도서신청 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>희망도서신청</h2>
		<div class="contents request_area">
			<c:set var="index" value="${1}" />
			<p class="books_tab">
				<a class="books_tab_1 active" href="${pageContext.request.contextPath}/request/request">도서신청</a>
				<a class="books_tab_2" href="${pageContext.request.contextPath}/request/list">신청내역</a>
			</p>

			<div class="request_tab_cont_1 active">
				<div class="books_search_area">
					<form name="searchfrm" id="searchfrm" method="post">
						<select name="target" id="target">
							<option value="title" selected="selected">도서명</option>
							<option value="publisher">출판사</option>
							<option value="isbn">ISBN</option>
						</select>
						<input type="text"  name="search" id="search" placeholder="검색어를 입력하세요.">
						<button type="button" id="btnSearch">
							<img src="/myapp/resources/assets/images/search_icon.png" alt="돋보기 검색 아이콘">
						</button>
					</form>
				</div>
                      
				<%-- 검색결과 출력 --%>			
				<div class="request_bookset">
					<p class="card-text" id="top"></p>
					<div class="inner"></div>		
					<div id="moreList"></div>
				</div>


				<div class="request_form">
					<div class="inner">
						<h4>선택 도서정보</h4>
							<img src="/myapp/resources/assets/images/none_img.png" alt="책 표지" />
							
							<p>선택된 도서가 없습니다.<br />도서검색 후 신청할 도서를 선택해주세요.</p>
					</div>
					<div class="inner">
					<!--  action="${pageContext.request.contextPath}/request/request/requestInsert" -->
						<h4>신청 사유</h4>
						<form name="selectfrm" id="selectfrm" method="post">
							<textarea class="request_input" name="reason" id="reason" placeholder="신청사유를 작성해주세요."></textarea>
							<input type="hidden" name="sIsbn" id="sIsbn" value="" />
                      		<input type="hidden" name="sTitle" id="sTitle" value="" />
							<input type="hidden" name="sAuthor" id="sAuthor" value="" />
							<input type="hidden" name="sPublisher" id="sPublisher" value="" />
							<input type="hidden" name="sContents" id="sContents" value="" />
                        	<input type="hidden" name="sThumbnail" id="sThumbnail" value="" />
                        	<input type="hidden" name="sPub_date" id="sPub_date" value="" />
							<button id="requestInsert">도서신청</button>
						</form>
					</div>
				</div>

			</div>



		</div>
	</div>
</section>

<style>
	p#top {
		margin: 10px;
	}
	
	p#top span {
		font-family: 'neo_b';
		font-size: 14px;
		color: #635845;
	}
</style>

<script>
//도서 검색 전역 변수
	    var page=1;
	    var target = $("#target option:selected").val();  //조건
	    var query = $('#search').val();  //검색어
	    var is_end=false;

	    var test = 0;
	 
	    //검색기능 (마우스클릭)
	    $("#btnSearch").on("click",function(){
			getData(); //검색조건 설정
	        getList(); //검색결과 출력
      	});
	    
	  	//검색(엔터)
	    $("#search").keydown(function(key){
	        if(key.keyCode==13){ 
	        	key.preventDefault();
	    		getData();
	            getList();
	        }
	    });
	
	  //리스트초기화 및 검색조건 설정
	  	function getData(){
	  		test = 0;
	        page=1;
	        $("#top").html("");
	        $('.request_bookset .inner').html("");
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
	            data:{"target":target, "query":query, "page":page, "size":"50"},
	            success:function(data){
	            	
	            	console.log(data);
	            	
	            	 // 1. 받아온 JSON 데이터를 JavaScript 객체로 변환
	                var jsonData = JSON.parse(JSON.stringify(data));

	                // 2. JavaScript 객체를 수정 (작가, isbn, datetime)
	                for(var i=0; i<jsonData.documents.length; i++){
	                    
	                	jsonData.documents[i].isbn =jsonData.documents[i].isbn.replace(/[^0-9]/g, ''); // 숫자 외의 문자를 모두 제거
					    if (jsonData.documents[i].isbn.length > 13) { 
					    	jsonData.documents[i].isbn = jsonData.documents[i].isbn.slice(-13); //isbn 13자리만 자르기
					    }
	                	
	                	jsonData.documents[i].authors = jsonData.documents[i].authors.join(",");
	                    if(jsonData.documents[i].authors == "") {
	                    	jsonData.documents[i].authors = "미상"
	                    }

					    jsonData.documents[i].contents = jsonData.documents[i].contents.replaceAll("'", "");  //'를 없애버림
					    jsonData.documents[i].title = jsonData.documents[i].title.replaceAll("'", "&acute;");
					    
					    var date = new Date(jsonData.documents[i].datetime);
					    var year = date.getFullYear();
					    jsonData.documents[i].datetime = year;
	                }

	                // 2-2. isbn이 977,978,999만 저장
	                for(var i=0; i<jsonData.documents.length; i++){
	                	
	                	var isbn = jsonData.documents[i].isbn;
	                	if (!isbn.startsWith("978") && !isbn.startsWith("979")) {
	                		jsonData.documents.splice(i, 1);
	                	    i--; // splice 메서드를 사용하여 배열에서 요소를 삭제하면 인덱스가 변경되므로 i를 1 감소시켜야 합니다.
	                	  }
	                }
                	//검색 갯수 변경
                	if(jsonData.meta.total_count != jsonData.documents.length){
                		jsonData.meta.total_count = jsonData.documents.length;
                	}

	                // 3. 수정된 JavaScript 객체를 다시 JSON 형태로 변환
	                var newJsonData = JSON.stringify(jsonData);
	       	            	
	            	//4.JSON 문자열을 객체로 변환
	            	var bookData = JSON.parse(newJsonData); 
	            	
	            	console.log("-----")
	            	console.log(bookData)
	            	console.log(Object.keys(bookData.documents).length)
	            	
	            	
					$.each(bookData.documents, function(index, element) {
						
						if(element.thumbnail == ''){
							element.thumbnail = '/myapp/resources/assets/images/none_img.png';
						}
						
						$('.request_bookset .inner').append(`<div class='request_book'><input name='all_data` + test +`' type='hidden' value='`+ JSON.stringify(element) +`' /><img src=' `+ element.thumbnail +` ' alt='책 표지' /><div class='request_text'><h4>` + element.title + `</h4><p>저자 : `+ element.authors +`</p><p>출판사 : `+ element.publisher +`</p><p>출판년도 : `+ element.datetime +`</p><button class='data_btn' name='btnSelect' type='button'>선택</button></div></div>`)
						// $("#moreList").html("<p>마지막 검색결과 입니다.</p>");
						test += 1;

					});           	
	            	

					//검색결과갯수
	                var total = Object.keys(bookData.documents).length;
	                $("#total").text(total);
	                if (total == 0) {
	                	$("#top").hide();
	                	$('.request_bookset .inner').append("<p class='request_no'>검색 결과가 존재하지 않습니다.</p>");
	                    //$("#moreList").html("<p>검색 결과가 없습니다.</p>");
	                }
	                
	                //마지막페이지인지 여부 (마지막페이지면 더보기 버튼삭제)
	                is_end = data.meta.is_end;
	                if (is_end) {
	                	$("#btnMore").hide();
	                	// $("#moreList").html("<p>마지막 검색결과 입니다.</p>");
	                }
	                
	             	//선택버튼 이벤트 처리
	                $("button[name='btnSelect']").click(function() {
	                  var index = $("button[name='btnSelect']").index(this);
	                  console.log(index);
	                  var selectBookString = $('input[name="all_data'+ index +'"]').val();
	                  console.log(selectBookString);
              
	                  var selectBook = JSON.parse(selectBookString);
	                  $('.request_form .inner:first').html("");
	                  $('.request_form .inner:first').append(`<h4>선택 도서정보</h4><img src=' ` +selectBook.thumbnail+ `' alt='책 표지' /><ul><li>`+selectBook.title+`</li><li>저자 :`+selectBook.authors +`</li><li>출판사 :`+selectBook.publisher+`</li><li>출판년도 :`+selectBook.datetime+`</li></ul>`);
	                  $("#sTitle").val(selectBook.title);
	                  $("#sIsbn").val(selectBook.isbn);
	                  $("#sAuthor").val(selectBook.authors);
	                  $("#sPublisher").val(selectBook.publisher);
	                  $("#sPub_date").val(selectBook.datetime);
	                  $("#sContents").val(selectBook.contents);
	                  $("#sThumbnail").val(selectBook.thumbnail);

	                }); 
	             	
	                //도서신청
	              	$('#requestInsert').click(function(){
	              		var link = document.location.href;
	              		
						if(document.getElementById("sTitle").value==""){
	              	    	alert("도서를 선택하세요");
	              	        return false;
	              	    }
						
						if(link.split('/request').pop() == 'Insert'){
							link = link.replace("/requestInsert","");
							console.log(link);
						}
					
            			$('#selectfrm').attr('action', link + '/requestInsert').submit();
            			
            			alert('도서신청을 완료했습니다.');
	                });
	                

	            },
	            error:function(){
	                console.log("검색 실패");
	            }
	        });   
	    }//getList()

    ///////////////////////////////////////////////

    
</script>