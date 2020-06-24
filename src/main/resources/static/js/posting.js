$(window).scroll(function () {
    if ( $(window).width() < 900 ) {
        if ($(this).scrollTop() > 80) {
            $(".some_info_block").addClass("active2");
        } else {
            $(".some_info_block").removeClass("active2");
        }
    }else {
        if ($(this).scrollTop() > 80) {
            $(".some_info_block").addClass("active");
        } else {
            $(".some_info_block").removeClass("active");
        }
    }

});
$(document).ready(function () {
    $.ajax({
        url: '/posting/getPostingInfo',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $(".short_info").append("<h2>" + data.title + "</h2>");
            $(".some_info_block").prepend("<h2>" + data.price + " ₽</h2>");
            $(".price_button").prepend("<span>" + data.price + " ₽</span>");
            $(".full_description").prepend("<p>" + data.fullDescription + "</p>");
        }
    });
});
$(".a_note").click(function () {
    $(".add_new_note ").toggleClass("active");
    $(" .bg_note").toggleClass("active");
});
$(".bg_note").click(function () {
    $(".add_new_note, .bg_note").toggleClass("active");
});
$(".price_button").click(function () {
    $(".some_info_block ").toggleClass("active");
});
$(".card-header .btn").click(function () {

    $(".card-header .btn").toggleClass("open");
    if ($(".card-header .btn").hasClass("open")) {
        $(".card-header .btn span").text("Показать карту");
        $(".card-header .btn svg").css("transform", "rotateX(0deg)");
    } else {
        $(".card-header .btn span").text("Скрыть карту");
        $(".card-header .btn svg").css("transform", "rotateX(180deg)");
    }

});
$(".in_favorite").click(function () {
    $(".in_favorite").toggleClass("in");
    if ($(".in_favorite").hasClass("in")) {
        $(".button_favorite svg").css("fill", "#007bff");
        $(".button_favorite span").text("В избранном");
    } else {
        $(".button_favorite svg").css("fill", "transparent");
        $(".button_favorite span").text("Добавить в избранное");
    }
});
$('.panel_control_slider img').eq(0).addClass("active");
$(document).ready(function () {
    $('.panel_control_slider img').hover(function (e) {
        var images = $('.panel_control_slider img').index(this);
        $('.panel_control_slider img').removeClass('active');
        $('.panel_control_slider img').eq(images).addClass("active");
        $('.carousel-item').removeClass('active');
        $('.carousel-item').eq(images).addClass("active");
    });
});

