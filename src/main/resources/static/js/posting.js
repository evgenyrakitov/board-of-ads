$(window).scroll(function () {
    if ($(window).width() < 900) {
        if ($(this).scrollTop() > 80) {
            $(".some_info_block").addClass("active2");
        } else {
            $(".some_info_block").removeClass("active2");
        }
    } else {
        if ($(this).scrollTop() > 80) {
            $(".some_info_block").addClass("active");
        } else {
            $(".some_info_block").removeClass("active");
        }
    }

});
var asd = window.location.pathname;
$(document).ready(function () {
    $.ajax({
        url: '/rest' + window.location.pathname,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $(".short_info").append("<h2>" + data.title + "</h2>");
            $(".some_info_block").prepend("<h2>" + data.price + " ₽</h2>");
            $(".price_button").prepend("<span>" + data.price + " ₽</span>");
            $(".full_description").prepend("<p>" + data.fullDescription + "</p>");
            $(".show_telephone span").text(data.user.phone);
            $(".customer_info div a").text(data.user.publicName);
            for (var i in data.imagePath) {
                if (i == 0) {
                    $(".carousel-inner").append(
                        '<div class="carousel-item active" data-img = "' + data.imagePath[i].imagePath + '">\n' +
                        '   <img src="' + data.imagePath[i].imagePath + '"  class="d-block w-100" alt="...">\n' +
                        '</div>'
                    );
                    $(".panel_control_slider").append(
                        '<img class="active" data-img = "' + data.imagePath[i].imagePath + '"  src="' + data.imagePath[i].imagePath + '" onmouseover="f(this)">'
                    );
                } else {
                    $(".carousel-inner").append(
                        '<div class="carousel-item" data-img = "' + data.imagePath[i].imagePath + '">\n' +
                        '   <img src="' + data.imagePath[i].imagePath + '" class="d-block w-100" alt="...">\n' +
                        '</div>'
                    );
                    $(".panel_control_slider").append(
                        '<img class=""  data-img = "' + data.imagePath[i].imagePath + '" src="' + data.imagePath[i].imagePath + '" onmouseover="f(this)">'
                    );
                }
            }


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

function f(e) {
    var id = e.dataset.img;
    $('.panel_control_slider img').removeClass('active');
    $('.carousel-item').removeClass('active');
    $('.carousel-inner [data-img="' + id + '"]').addClass("active");
    e.classList.add("active");
}

