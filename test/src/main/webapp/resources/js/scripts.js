
$(document).ready(function () {

	$('.loading_btn').on("click", function(){
	    $('.loading_back').css('display', 'block');
	});

    if (window.location.href.indexOf("myapp/notice") > -1) {
		$('.nav_area .inner ul li > a').removeClass('active');
		$('.nav_area .inner ul li:nth-of-type(1) > a').addClass('active');
    } else if(window.location.href.indexOf("myapp/info") > -1){
		$('.nav_area .inner ul li > a').removeClass('active');
		$('.nav_area .inner ul li:nth-of-type(2) > a').addClass('active');
    } else if(window.location.href.indexOf("myapp/books") > -1){
		$('.nav_area .inner ul li > a').removeClass('active');
		$('.nav_area .inner ul li:nth-of-type(3) > a').addClass('active');
    } else if(window.location.href.indexOf("myapp/request") > -1){
		$('.nav_area .inner ul li > a').removeClass('active');
		$('.nav_area .inner ul li:nth-of-type(4) > a').addClass('active');
    } else if(window.location.href.indexOf("myapp/my") > -1){
		$('.nav_area .inner ul li > a').removeClass('active');
		$('.nav_area .inner ul li:nth-of-type(5) > a').addClass('active');
    }

  //모바일 메뉴
  $('.menu-trigger').each(function () {
    var $this = $(this);

    $this.on('click', function (e) {
      e.preventDefault();
      $(this).toggleClass('active');
    });
  });

  $(window).resize(function () {
    var windowWidth = $(window).width();

    if (windowWidth > 700) {
      $('.nav_area .inner ul').removeClass('active');
      $('.menu-trigger').removeClass('active');
      $('.sign_area_pc').css('display', 'block');
    } else if (windowWidth <= 700) {
      $('.sign_area_pc').css('display', 'none');
    }
  });

  $('.menu-trigger').on('click', function () {
    if ($('.nav_area .inner ul').hasClass('active')) {
      $('.nav_area .inner ul').removeClass('active');
    } else {
      $('.nav_area .inner ul').addClass('active');
    }
  });

  // 주소
  $('.addr_btn').on('click', function () {
    new daum.Postcode({
      oncomplete: function (data) {
        console.log(data);
        var roadAddr = data.roadAddress;
        var jibunAddr = data.jibunAddress;
        $('#member_post').val(data.zonecode);
        if (roadAddr !== '') {
          $('#member_addr').val(roadAddr);
        } else if (jibunAddr !== '') {
          $('#member_addr').val(jibunAddr);
        }
      },
    }).open();
  });



  $('.review_input').keyup(function (e) {
    let content = $(this).val();

    // 글자수 세기
    if (content.length == 0 || content == '') {
      $('.textCount').text('0자');
    } else {
      $('.textCount').text(content.length + '자');
    }

    // 글자수 제한
    if (content.length > 200) {
      // 200자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 200));
      // 200자 넘으면 알림창 뜨도록
      $('.popup p').html('글자수는 200자까지 입력 가능합니다.');
      $('.popup_back').addClass('on');
    }
  });

  $('.request_input').keyup(function (e) {
    let content = $(this).val();

    // 글자수 세기
    if (content.length == 0 || content == '') {
      $('.textCount').text('0자');
    } else {
      $('.textCount').text(content.length + '자');
    }

    // 글자수 제한
    if (content.length > 100) {
      // 100자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 100));
      // 100자 넘으면 알림창 뜨도록
      $('.popup p').html('글자수는 100자까지 입력 가능합니다.');
      $('.popup_back').addClass('on');
    }
  });
  
   $('.popup button').on('click', function () {
    $('.popup_back').removeClass('on');
  });
});
