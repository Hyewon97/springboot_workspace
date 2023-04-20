<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style type="text/css">
.hide {
	display: none;
}
</style>
<script>
<%String popupMessage = (String) session.getAttribute("popupMessage");%>

let popupShown = false;
const popupShownKey = "popupShown";
	  console.log("<%=popupMessage%>");
	  $(document).on("submit", "#reserve", function() {
		  popupShown= true;
		 sessionStorage.setItem(popupShownKey, popupShown); 
	  });
	  
	  $(document).on("submit", "#loan", function() {
		  popupShown= true;
			 sessionStorage.setItem(popupShownKey, popupShown); 
		  });
	  
	  $(document).on("submit", "#reviewForm", function() {
		  popupShown= true;
		 sessionStorage.setItem(popupShownKey, popupShown);
	  });
	  
	  $(document).ready(function() {
		console.log("로딩 완료.");
		  const shouldShowPopup = sessionStorage.getItem(popupShownKey) === "true";
		  if (shouldShowPopup && "<%=popupMessage%>" !== "null") {
		    $(".popup>p").text("<%=popupMessage%>");
							$(".popup_back").addClass("on");
							popupShown = true;
							sessionStorage.removeItem(popupShownKey);
						}

						// 수정 클릭 시 수정창 나타남
						$('[id^="editReview_"]').click(

								function(event) {
									console.log("clicked");
									event.preventDefault();
									let revNum = this.id.split("_").pop();
									$(this).closest('.review_box').addClass(
											'hide');

									let reviewContents = $('#review_' + revNum)
											.find('[name=review_contents]')
											.text();
									$('#review_' + revNum).find(
											'[name=review_contents]').val(
											reviewContents.replace(
													/<br\s*\/?>/gi, '\n'));
									$('#review_' + revNum).closest(
											'.review_box').removeClass('hide');
								});

						// 수정취소 시 수정창 숨김
						$('[id^="close_"]').click(
								function(event) {
									event.preventDefault();
									let revNum = this.id.split("_").pop();
									$(this).closest('.review_box').addClass(
											"hide");
									$(this).closest('.review_box').prev()
											.removeClass('hide');
								});

						// 후기 작성 Form submit시 발생
						$("#reviewForm").submit(
								function(event) {
									//후기 작성 시 입력값이 없다면 팝업창 띄움.
									if ($("textarea[name='review_contents']")
											.val() === '') {
										event.preventDefault();
										$(".popup>p").text("입력하신 내용이 없습니다.");
										$(".popup_back").addClass("on");
									}

									// 줄바꿈을 <br/>로 저장
									$(this).find('[name=review_contents]').val(
											$(this).find(
													'[name=review_contents]')
													.val().replace(/\n/gi,
															'<br/>'));
								});

						// 팝업창 닫기
						$(".popup>button").click(function() {
							console.log("closed");
							$(".popup_back").removeClass("on");
						});

						// 수정 제출 시 줄바꿈 처리, 수정된 사항을 POST하고 reload
						$(".updateReview").submit(
								function(event) {
									event.preventDefault(); // 제출 제한
									$(this).find('[name=review_contents]').val(
											$(this).find(
													'[name=review_contents]')
													.val().replace(/\n/gi,
															'<br/>'));

									let form = $(this);
									let formData = form.serialize(); // form 내부의 기능들을 text String에 담는다.
									let url = form.attr('action'); // form태그의 'action'값을 url에 담는다.

									$.ajax({
										type : 'GET',
										url : url,
										data : formData,
										success : function(response) {
											location.reload();
										},
										error : function(xhr, status, error) {
											alert('Error updating review');
										}
									});
								});

						$('[id^="deleteReview_"]').click(
								function(event) {
									event.preventDefault();
									let revNum = this.id.split("_").pop();
									console.log(revNum);
									$("[id^=EditDeleteFrm_" + revNum + "]")
											.attr('action', "delete").submit();
								});

					});
</script>
<!-- 도서조회 상세보기 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>통합도서조회</h2>
		<div class="contents book_cont_view">
			<h4 class="book_title">${bldto.title }</h4>
			<p class="book_sub">
				저자 : ${bldto.author }
				<span></span>
				출판사 : ${bldto.publisher }
			</p>
			<div class="book_info_area">
				<img src="${bldto.thumbnail }" alt="책 표지" />
				<p class="book_info_text">
					ISBN :
					<span>${bldto.isbn }</span>
					<br />
					분류기호 :
					<span>${bldto.category_s }</span>
					<br />
					KDC 분류 :
					<span>${bldto.book_category }</span>
					<br />
					출판년도 :
					<span>${bldto.pub_date }</span>
					<br />
					등록일자 :
					<span>
						<fmt:formatDate value="${bldto.receive_date }" pattern="yyyy-MM-dd" />
					</span>
				</p>
				<!-- 후기 평균 별점 구하기 -->
				<c:set var="totalStars" value="0" />
				<c:set var="numReviews" value="${fn:length(revList)}" />
				<c:forEach items="${revList}" var="review">
					<c:set var="totalStars" value="${totalStars + review.star_num}" />
				</c:forEach>

				<c:if test="${numReviews > 0}">
					<c:set var="avgStars" value="${totalStars / numReviews }"></c:set>
					<fmt:formatNumber var="avgStarsInt" value="${avgStars}" type="number" maxFractionDigits="0" />
				</c:if>
				<c:if test="${numReviews eq 0}">
					<c:set var="avgStars" value="${numReviews }"></c:set>
					<fmt:formatNumber var="avgStarsInt" value="${numReviews }" type="number" maxFractionDigits="0" />
				</c:if>
				<div class="book_state_area">
					<p class="book_star star_${avgStarsInt }">
						<span>${avgStarsInt} (${pv.totalCount })</span>
						<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
						<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
						<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
						<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
						<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
						<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
						<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
						<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
						<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
						<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
					</p>
					<p class="book_state">
						대출 :
						<c:choose>
							<c:when test="${bldto.loan_state == 'Y' }">
								<span>가능</span>
							</c:when>
							<c:otherwise>
								<span>불가능</span>
							</c:otherwise>
						</c:choose>
						예약 :
						<c:choose>
							<c:when test="${bldto.borrow_state == 'Y' }">
								<span>가능</span>
							</c:when>
							<c:otherwise>
								<span>불가능</span>
							</c:otherwise>
						</c:choose>
					</p>
					<c:choose>
						<c:when test="${bldto.loan_state == 'Y' && bldto.borrow_state == 'N'}">
							<form id="loan" name="loan" action="loan" method="post">
								<input type="hidden" name="book_keynum" value="${bldto.book_keynum }" />
								<input type="hidden" name="category_s" value="${bldto.category_s }" />
								<input type="hidden" name="isbn" value="${bldto.isbn }" />
								<input type="hidden" name="loan_state" value="${bldto.loan_state }" />
								<input type="hidden" name="borrow_state" value="${bldto.borrow_state }" />
								<button type="submit">대출하기</button>
							</form>
						</c:when>
						<c:when test="${bldto.loan_state == 'N' && bldto.borrow_state == 'Y'}">
							<form id="reserve" name="reserve" action="borrow" method="post">
								<input type="hidden" name="isbn" value="${bldto.isbn }" />
								<input type="hidden" name="book_keynum" value="${bldto.book_keynum }" />
								<input type="hidden" name="category_s" value="${bldto.category_s }" />
								<input type="hidden" name="loan_state" value="${bldto.loan_state }" />
								<input type="hidden" name="borrow_state" value="${bldto.borrow_state }" />
								<button type="submit">예약하기</button>
							</form>
						</c:when>
						<c:otherwise>
							<button disabled>대출/예약 불가</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="book_info_cont">
				<p>${bldto.contents }</p>
			</div>

			<c:set var="contextPath" value="${pageContext.request.contextPath }" />

			<!-- 후기 등록하기 -->
			<div class="review_area">
				<form name="frm" id="reviewForm" action="writeRev" method="post">
					<ul>
						<li>
							<h4>리뷰 작성</h4>
							<input type="hidden" name="user_id" value="${sessionScope.authInfo.user_id}" />
							<!-- book_keynum -->
							<!-- ISBN값 -->
							<input type="hidden" name="book_keynum" value="${bldto.book_keynum }" />
							<input type="hidden" name="isbn" value="${bldto.isbn }" />

						</li>
						<li>
							<select name="star_num">
								<option value="5">⭐⭐⭐⭐⭐</option>
								<option value="4">⭐⭐⭐⭐</option>
								<option value="3">⭐⭐⭐</option>
								<option value="2">⭐⭐</option>
								<option value="1">⭐</option>
							</select>
						</li>
						<li>
							<!-- cols="50" rows="3" -->
							<textarea id="reviewText" class="review_input" name="review_contents" placeholder="리뷰를 작성해주세요."></textarea>
							<button type="submit">등록</button>
						</li>
					</ul>
				</form>

				<!-- 후기 리스트 -->
				<h4 class="review_title">
					도서 리뷰
					<span>(${pv.totalCount }건)</span>
				</h4>
				
				<c:if test="${pv.totalCount == 0}">
					<p class="review_noadta">등록된 리뷰가 없습니다.<br/>로그인 후 리뷰를 등록해보세요.</p>
				</c:if>

				<!-- Controller에서 페이지를 GET할 때 pv로 PageDTO 객체를 넘겨주고, revList로 bookReviewDTO들의 List를 넘겨준다. -->
				<!-- revList에 있는 값마다 반복문을 돌려준다. var="dto"로 각 항목에 접근할 수 있게 한다. -->
				<c:forEach items="${revList}" var="dto">
					<c:url var="path" value="review">
						<c:param name="currentPage" value="${pv.currentPage}" />
						<c:param name="num" value="${dto.review_keynum}" />
					</c:url>
					<div class="review_box">
						<p class="book_star star_${dto.star_num}">
							<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
							<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
							<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
							<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
							<img src="/myapp/resources/assets/images/star-full.svg" alt="star_full" />
							<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
							<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
							<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
							<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
							<img src="/myapp/resources/assets/images/star-none.svg" alt="star_none" />
						</p>
						<p class="review_text">
							${dto.review_contents}
							<br />
							<span>${dto.user_id}&nbsp;&nbsp;·&nbsp;&nbsp;<fmt:formatDate value="${dto.review_date }" pattern="yyyy-MM-dd" /></span>
							<c:if test="${sessionScope.authInfo != null && sessionScope.authInfo.user_id == dto.user_id }">

								<span>
									&nbsp;&nbsp;·&nbsp;&nbsp;
									<a href="" id="editReview_${dto.review_keynum }">수정</a>
								</span>
								<span>
									&nbsp;&nbsp;·&nbsp;&nbsp;
									<a href="" id="deleteReview_${dto.review_keynum }">삭제</a>
								</span>

								<form id="EditDeleteFrm_${dto.review_keynum }" method="get" action="/bookList/delete">
									<input type="hidden" name="review_keynum" value="${dto.review_keynum }" />
									<input type="hidden" name="page" value="${dto.book_keynum }" />
									<!-- <button type="submit">삭제</button> -->
								</form>
							</c:if>
						</p>
					</div>

					<!-- 수정 -->
					<c:if test="${sessionScope.authInfo != null && sessionScope.authInfo.user_id == dto.user_id }">
						<div class="review_box hide">
							<form id="review_${dto.review_keynum}" class="updateReview" method="get" action="update">
								<ul>
									<li>
										<h4>
											리뷰 수정
											<span id="close_${dto.review_keynum}">[수정 취소]</span>
										</h4>
									</li>
									<li>
										<select name="star_num">
											<option value="5" ${dto.star_num == '5' ? 'selected' : ''}>⭐⭐⭐⭐⭐</option>
											<option value="4" ${dto.star_num == '4' ? 'selected' : ''}>⭐⭐⭐⭐</option>
											<option value="3" ${dto.star_num == '3' ? 'selected' : ''}>⭐⭐⭐</option>
											<option value="2" ${dto.star_num == '2' ? 'selected' : ''}>⭐⭐</option>
											<option value="1" ${dto.star_num == '1' ? 'selected' : ''}>⭐</option>
										</select>
									</li>
									<li>
										<textarea class="review_input" placeholder="리뷰를 작성해주세요." name="review_contents">${dto.review_contents}</textarea>
										<input type="hidden" name="review_keynum" value=${dto.review_keynum } />
										<input type="hidden" name="page" value=${dto.book_keynum } />
										<button type="submit">수정</button>
									</li>
								</ul>
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>


			<c:choose>
				<c:when test="${empty pv}">
					<!-- 페이지 이동 -->
					<div class="pagination_area">
						<ul class="pagination">
							<!-- 처음 출력 시작 -->
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item disabled">
										<a class="page-link" style="cursor: pointer;" href="view?currentPage=1">
											<img style="opacity: .5;" src="/myapp/resources/assets/images/first.svg">
											<span>first</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled">
										<a class="page-link" style="cursor: no-drop">
											<img style="opacity: .2;" src="/myapp/resources/assets/images/first.svg">
											<span>first</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
							<!-- 처음 출력 끝 -->

							<!-- 이전 출력 시작 -->
							<c:choose>
								<c:when test="${pv.startPage >1}">
									<li class="page-item disabled">
										<a class="page-link" style="cursor: pointer;" href="view?currentPage=${pv.startPage-pv.blockPage}">
											<img style="opacity: .5;" src="/myapp/resources/assets/images/prev.svg">
											<span>prev</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled">
										<a class="page-link" style="cursor: no-drop">
											<img style="opacity: .2;" src="/myapp/resources/assets/images/prev.svg">
											<span>prev</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
							<!-- 이전 출력 끝 -->

							<!-- 페이지번호 출력 시작 -->
							<c:forEach var="i" begin="${pv.startPage}" end="${pv.endPage}">
								<c:choose>
									<c:when test="${i==pv.currentPage}">
										<li class="page-item active">
											<a class="page-link" style="cursor: pointer;" href="view?currentPage=${i}">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" style="cursor: pointer;" href="view?currentPage=${i}">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 페이지번호 출력 끝 -->

							<!-- 다음 출력 시작 -->
							<c:choose>
								<c:when test="${pv.endPage < pv.totalPage}">
									<li class="page-item">
										<a class="page-link" style="cursor: pointer;" href="view?currentPage=${pv.startPage + pv.blockPage}">
											<img style="opacity: .5;" src="/myapp/resources/assets/images/next.svg">
											<span>next</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link" style="cursor: no-drop">
											<img style="opacity: .2;" src="/myapp/resources/assets/images/next.svg">
											<span>next</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
							<!-- 다음 출력 끝 -->

							<!-- 마지막 출력 시작 -->
							<c:choose>
								<c:when test="${pv.endPage < pv.totalPage}">
									<li class="page-item">
										<a class="page-link" style="cursor: pointer;" href="view?currentPage=${pv.totalPage}">
											<img style="opacity: .5;" src="/myapp/resources/assets/images/last.svg">
											<span>last</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link" style="cursor: no-drop">
											<img style="opacity: .2;" src="/myapp/resources/assets/images/last.svg">
											<span>last</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
							<!-- 마지막 출력 끝 -->
						</ul>
					</div>
				</c:when>
			</c:choose>
		</div>
                <a class="notice_back" href="${pageContext.request.contextPath}/books">목록보기</a>
	</div>
</section>