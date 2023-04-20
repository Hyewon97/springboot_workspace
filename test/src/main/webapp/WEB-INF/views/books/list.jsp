<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 통합도서조회 -->
<section class="subpage_wrap">
	<div class="inner">
		<h2>통합도서조회</h2>
		<div class="contents books_cont">

			<p class="books_tab">
				<a class="books_tab_1 active" href="${pageContext.request.contextPath}/books">도서조회</a>
				·
				<a class="books_tab_2" href="${pageContext.request.contextPath}/books/new">신착도서</a>
				·
				<a class="books_tab_3" href="${pageContext.request.contextPath}/books/popular">인기도서</a>
			</p>


			<div class="books_tab_cont_1 active">
				<div class="books_search_area">
					<form action="/myapp/books/search" method="get">
						<select name="search_item">
							<c:choose>

								<c:when test="${option eq 'search_title'}">
									<option value="search_title" selected="selected">도서명</option>
									<option value="search_author">저자명</option>
									<option value="search_publisher">발행처</option>
									<option value="search_isbn">ISBN</option>
								</c:when>
								<c:when test="${option eq 'search_author'}">
									<option value="search_title">도서명</option>
									<option value="search_author" selected="selected">저자명</option>
									<option value="search_publisher">발행처</option>
									<option value="search_isbn">ISBN</option>

								</c:when>
								<c:when test="${option eq 'search_publisher' }">
									<option value="search_title">도서명</option>
									<option value="search_author">저자명</option>
									<option value="search_publisher" selected="selected">발행처</option>
									<option value="search_isbn">ISBN</option>
								</c:when>
								<c:when test="${option eq 'search_isbn' }">
									<option value="search_title">도서명</option>
									<option value="search_author">저자명</option>
									<option value="search_publisher">발행처</option>
									<option value="search_isbn" selected="selected">ISBN</option>
								</c:when>

								<c:otherwise>
									<option value="search_title" selected="selected">도서명</option>
									<option value="search_author">저자명</option>
									<option value="search_publisher">발행처</option>
									<option value="search_isbn">ISBN</option>
								</c:otherwise>
							</c:choose>

						</select>
						<c:choose>

							<c:when test="${not empty query }">
								<input type="text" name="query" value="${query }" />
							</c:when>

							<c:otherwise>
								<input type="text" name="query" placeholder="검색어를 입력하세요." />
							</c:otherwise>
						</c:choose>

						<!-- 도서분류값 -->
						<c:choose>
							<c:when test="${not empty cate}">

								<input class="bn_input" name="categories" id="categories" type="hidden" value="${cate }" />
							</c:when>
							<c:otherwise>

								<input class="bn_input" name="categories" id="categories" type="hidden" value="a" />
							</c:otherwise>
						</c:choose>


						<button type="submit" name="searchbutton">
							<img src="/myapp/resources/assets/images/search_icon.png" alt="돋보기 검색 아이콘" />
						</button>
					</form>
				</div>
				<p class="bn_area">
					<a class="bn_a" onclick="sendData('a')">전체</a>
					<span>·</span>
					<a class="bn_0" onclick="sendData('0')">총류</a>
					<span>·</span>
					<a class="bn_1" onclick="sendData('1')">철학</a>
					<span>·</span>
					<a class="bn_2" onclick="sendData('2')">종교</a>
					<span>·</span>
					<br />
					<a class="bn_3" onclick="sendData('3')">사회과학</a>
					<span>·</span>
					<a class="bn_4" onclick="sendData('4')">순수과학</a>
					<span>·</span>
					<a class="bn_5" onclick="sendData('5')">기술과학</a>
					<span>·</span>
					<br />
					<a class="bn_6" onclick="sendData('6')">예술</a>
					<span>·</span>
					<a class="bn_7" onclick="sendData('7')">언어</a>
					<span>·</span>
					<a class="bn_8" onclick="sendData('8')">문학</a>
					<span>·</span>
					<a class="bn_9" onclick="sendData('9')">역사</a>
				</p>

				<c:if test="${empty printdtos && empty search_result}">
					<h4 class="booklist_text" style="margin-top: 100px;">
						검색 결과가 없습니다.
					</h4>
				</c:if>
				
				<div class="booklist_box">
					<c:choose>

						<c:when test="${not empty printdtos}">
							<br/><br/>
							<c:forEach var="dto" items="${printdtos }">
								<div class="list">
									<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
										<img src="${dto.thumbnail }" alt="책 표지" />
									</a>
									<div class="list_text">
	                                    <a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
	                                        <h4>${dto.title}</h4>
	                                    </a>
										<p>
											저자 : ${dto.author }
											<br />
											출판사 : ${dto.publisher }
											<br />
											<c:choose>
												<c:when test="${dto.avg_rating != null }">
													평점 : <span>★${dto.avg_rating.charAt(0)}</span> (${dto.countisbn })
												</c:when>

												<c:otherwise>
													평점 : <span>★0</span>(0)
												</c:otherwise>
											</c:choose>


										</p>
										<p>${dto.contents }</p>
									</div>
									<p>
										대출 :
										<span>${dto.loan_state == 'Y' ? '가능' : '불가능' }</span>
										<br>
										예약 :
										<span>${dto.borrow_state == 'Y' ? '가능' : '불가능' }</span>
									</p>
								</div>
							</c:forEach>
						</c:when>
						<c:when test="${not empty  search_result}">
							<c:if test="${not empty query  }">
								<h4 class="booklist_text">
									<!-- 총 <span>0000</span>건 -->
									"
									<span class="search_text">${query }</span>
									"에 대한
									<span>${count}</span>
									건의 결과가 있습니다.
								</h4>
							</c:if>

							<c:forEach var="dto" items="${search_result }">

								<div class="list">
									<a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
										<img src="${dto.thumbnail }" alt="책 표지" />
									</a>
									<div class="list_text">
	                                    <a href="${pageContext.request.contextPath}/books/view?page=${dto.book_keynum}">
	                                        <h4>${dto.title}</h4>
	                                    </a>

										<p>
											저자 : ${dto.author }
											<br />
											출판사 : ${dto.publisher }
											<br />

											<c:choose>
												<c:when test="${dto.avg_rating != null }">
												평점 : <span>★${dto.avg_rating.charAt(0)}</span> (${dto.countisbn })
												</c:when>
												<c:otherwise>
												평점 : <span>★0</span> (0)
												</c:otherwise>
											</c:choose>

										</p>
										<p>${dto.contents }</p>
									</div>
									<p>
										대출 :
										<span>${dto.loan_state == 'Y' ? '가능' : '불가능' }</span>
										<br>
										예약 :
										<span>${dto.borrow_state == 'Y' ? '가능' : '불가능' }</span>
									</p>
								</div>
							</c:forEach>
						</c:when>
					</c:choose>

					<div class="pagination_area">
						<ul class="pagination">
							<!-- 처음 출력 시작 -->
							<c:choose>

								<c:when test="${not empty searchResultPv }">
									<c:choose>
										<c:when test="${searchResultPv.startPage >1}">
											<li class="page-item disabled">
												<a class="page-link" style="cursor: pointer;" href="search?search_item=${option}&query=${query}&categories=${cate}&searchbutton=&currentPage=1">
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
										<c:when test="${searchResultPv.startPage >1}">
											<li class="page-item disabled">
												<a class="page-link" style="cursor: pointer;" href="search?search_item=${option}&query=${query}&categories=${cate}&searchbutton=&currentPage=${searchResultPv.startPage-searchResultPv.blockPage}">
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
									<c:forEach var="i" begin="${searchResultPv.startPage}" end="${searchResultPv.endPage}">
										<c:choose>
											<c:when test="${i==searchResultPv.currentPage}">
												<li class="page-item active">
													<a class="page-link" style="cursor: pointer;" href="search?search_item=${option}&query=${query}&categories=${cate}&searchbutton=&currentPage=${i}">${i}</a>
												</li>
											</c:when>
											<c:otherwise>
												<li class="page-item">
													<a class="page-link" style="cursor: pointer;" href="search?search_item=${option}&query=${query}&categories=${cate}&searchbutton=&currentPage=${i}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- 페이지번호 출력 끝 -->

									<!-- 다음 출력 시작 -->
									<c:choose>
										<c:when test="${searchResultPv.endPage < searchResultPv.totalPage}">
											<li class="page-item">
												<a class="page-link" style="cursor: pointer;" href="search?search_item=${option}&query=${query}&categories=${cate}&searchbutton=&currentPage=${searchResultPv.startPage + searchResultPv.blockPage}">
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
										<c:when test="${searchResultPv.endPage < searchResultPv.totalPage}">
											<li class="page-item">
												<a class="page-link" style="cursor: pointer;" href="search?search_item=${option}&query=${query}&categories=${cate}&searchbutton=&currentPage=${searchResultPv.totalPage}">
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
								</c:when>
								<c:when test="${not empty pv}">
									<c:choose>
										<c:when test="${pv.startPage >1}">
											<li class="page-item disabled">
												<a class="page-link" style="cursor: pointer;" href="?currentPage=1">
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
												<a class="page-link" style="cursor: pointer;" href="?currentPage=${pv.startPage-pv.blockPage}">
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
													<a class="page-link" style="cursor: pointer;" href="?currentPage=${i}">${i}</a>
												</li>
											</c:when>
											<c:otherwise>
												<li class="page-item">
													<a class="page-link" style="cursor: pointer;" href="?currentPage=${i}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- 페이지번호 출력 끝 -->

									<!-- 다음 출력 시작 -->
									<c:choose>
										<c:when test="${pv.endPage < pv.totalPage}">
											<li class="page-item">
												<a class="page-link" style="cursor: pointer;" href="?currentPage=${pv.startPage + pv.blockPage}">
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
												<a class="page-link" style="cursor: pointer;" href="?currentPage=${pv.totalPage}">
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
								</c:when>

							</c:choose>

							<!-- 마지막 출력 끝 -->

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<script>
	$(document).ready(function() {
		var link = document.location.href;
		var tab = link.split('/books').pop();
		$('.books_tab a[href="' + tab + '"]').trigger("click");
	});

	for (let i = 1; i < 12; i++) {
		if ($('.bn_input').val() == $('.bn_area a:nth-of-type(' + i + ')')
				.attr('class').split('_')[1]) {
			$('.bn_area a.bn_' + String($('.bn_input').val())).addClass(
					'active');
		}
	}

	$('.bn_area a').on('click', function() {
		$('.bn_area a').removeClass('active');
		$('.bn_input').val($(this).attr('class').split('_')[1]);
		$(this).addClass('active');
	})

	function sendData(value) {
		var category = document.getElementById("categories");
		category.value = value;
		$('button[name="searchbutton"]').trigger('click');
	}
</script>