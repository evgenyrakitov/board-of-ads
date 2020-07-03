var posting = null;
var start = 0;
var end = 0;
var scrollOn = 1000;
$(document).ready(function () {
    $.ajax({
        url: '/rest/posting/getProductCards',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            posting = data;
            end = start + 12;
            if (end > posting.length) {
                end = posting.length;
            }
            $(".container_cus").empty();
            for (let i = start; i < end; i++) {
                $(".container_cus").prepend(
                    '<div class="card">\n' +
                    '            <img src="' + posting[i].imagePath[0].imagePath + '" class="card-img-top" alt="...">\n' +
                    '            <div class="card-body">\n' +
                    '                <h5 class="card-title">' + posting[i].title + '</h5>\n' +
                    '                <p class="card-text">' + posting[i].price + '</p>\n' +
                    '                <a href="adDetails" class="btn btn-primary" ' +
                    'th:text="#{main-page.go_to_ad}">Перейти к объявлению</a>\n' +
                    '            </div>\n' +
                    '        </div>'
                );
            }
            start = end;
        }
    });
});

var inProgress = false;
$(window).scroll(function () {
    if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100 && !inProgress) {
        inProgress = true;
        end = start + 12;
        if (end > posting.length) {
            end = posting.length;
        }
        for (let i = start; i < end; i++) {
            var $dump = $(".container_cus"),
                curScroll = $(window).scrollTop();
            $dump.append(
                '<div class="card">\n' +
                '            <img src="' + posting[i].imagePath[0].imagePath + '" class="card-img-top" alt="...">\n' +
                '            <div class="card-body">\n' +
                '                <h5 class="card-title">' + posting[i].title + '</h5>\n' +
                '                <p class="card-text">' + posting[i].price + '</p>\n' +
                '                <a href="adDetails" class="btn btn-primary">Перейти к объявлению</a>\n' +
                '            </div>\n' +
                '        </div>'
            );
            scrollTo(0,  curScroll);
        }
    }
    start = end;
    inProgress = false;
});

