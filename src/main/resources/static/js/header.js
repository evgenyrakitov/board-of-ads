$(window).scroll(function () {
    if ($(this).scrollTop() > 1) {
        $("header").addClass("active");
    } else {
        $("header").removeClass("active");
    }
});
var userLang = navigator.language || navigator.userLanguage;

navigator.se
var categories = null;

$(document).ready(function () {
    if (localStorage.getItem('locale') == null) {
        localStorage.setItem("locale", "ru");
        $(".dropdown-toggle").html('<img src="images/header/ru.svg" width="30">');
    } else {
        if (localStorage.getItem("locale") == "ru") {
            $(".dropdown-toggle").html('<img src="images/header/ru.svg" width="30">');
        } else {
            $(".dropdown-toggle").html('<img src="images/header/en.svg" width="30">');
        }
    }
    $.ajax({
        url: '/rest/categories/root_categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $("#header_category_list").empty();
            categories = data;
            var count = 0;
            var more = "";
            var lang_name = null;
            for (var i in data) {
                if (localStorage.getItem('locale') == "en") {
                    more = "more..."
                    lang_name = data[i].nameEn;
                } else {
                    var more = "еще...";
                    lang_name = data[i].nameRu;
                }
                if (count == 0) {
                    $("#header_category_list").append(
                        "<li class='nav-item nav_category'><span class='all_category'  onclick='openAllCategories()'>" + more + "</span>\n" +
                        "     <ul class='navbar-nav' id='moreCategories'></ul>" +
                        "</li>"
                    );
                }
                if (count < 5) {
                    $("#header_category_list").prepend(
                        "<li class='nav-item '>\n" +
                        "     <a class='nav-link text-primary' href='#'>" + lang_name + "</a>\n" +
                        "</li>"
                    );
                }
                $("#moreCategories").append(
                    '<div>\n' +
                    '       <div class="top-rubricator-blockTitle">\n' +
                    '           <a href="">' + lang_name + '</a>\n' +
                    '       </div>\n' +
                    '       <ul class="sub_categories navbar-nav" id="sub_categories_' + data[i].id + '"></ul>\n' +
                    '   </div>'
                );
                $("#findFromCategory").append(
                    '<option class="bgOption">' + lang_name + '</option>'
                );
                count++;
            }
        }

    });

});


function openAllCategories() {
    $(".sub_categories").empty();
    for (var i in categories) {
        var more = "";
        var lang_name = null;

        $.ajax({
            url: '/rest/categories/getCategoriesByParentCategory/' + categories[i].id,
            type: 'get',
            dataType: 'json',
            success: function (info) {
                for (var j in info) {
                    if (localStorage.getItem('locale') == "en") {
                        more = "more..."
                        lang_name = categories[j].nameEn;
                    } else {
                        var more = "еще...";
                        lang_name = categories[j].nameRu;
                    }
                    $("#sub_categories_" + categories[i].id + "").append(
                        '<li class="nav-item"><a href="/posting/' + info[j].id + '">' + lang_name + '</a></li>'
                    );
                    $("#findFromCategory").append(
                        '<option>' + lang_name + '</option>'
                    );
                }
            },
            async: false
        });

    }
    $("#moreCategories").toggleClass("on-off");
    $(".bg_black_header").toggleClass("on-off");
}

$(".bg_black_header").click(function () {
    $("#moreCategories").removeClass("on-off");
    $(".bg_black_header").removeClass("on-off");
    $(".search").removeClass("on_search");
});
$(".open_search").click(function () {
    $(".search").toggleClass("on_search");
    $(".bg_black_header").toggleClass("on-off");
});
$("#menuToggle").click(function () {
    $("header").toggleClass("open_header");
    $("#menuToggle").toggleClass("open_header");
});
$(".locale_ru").click(function () {
    localStorage.setItem("locale", "ru");
});
$(".locale_en").click(function () {
    localStorage.setItem("locale", "en");
});
