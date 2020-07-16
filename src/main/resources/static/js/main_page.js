var posting = null;
var start = 0;
var end = 0;
var scrollOn = 1000;
$(document).ready(function () {
    $.ajax({
        url: '/rest/posting/all',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            posting = data;
            end = start + 12;
            if (end > posting.length) {
                end = posting.length;
            }
            $(".container_cus").empty();
            let imageSrc = "";
            for (let i = start; i < end; i++) {
                imageSrc = posting[i].images.length > 0 ? posting[i].images[0].imagePath : `/images/image-placeholder.png`;
                $(".container_cus").prepend(
                    `    <div class="card">\n` +
                    `    <div id="${posting[i].id}" class="card-header">` +
                    '       <img src="' + imageSrc + '" class="card-img-top" alt="...">' +
                    '   </div>' +
                    '       <div class="card-body">\n' +

                    '                <h5 class="card-title">' + posting[i].title + '</h5>\n' +
                    '                <p class="card-text">' + posting[i].price + '</p>\n' +
                    '                <a href="posting/' + posting[i].id + '" class="btn btn-primary" ' +
                    'th:text="#{main_page.go_to_ad}">Перейти к объявлению</a>\n' +
                    '            </div>\n' +
                    '        </div>'
                );
            }
            start = end;
            showFavoriteIcon();
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
        let imageSrc = "";
        for (let i = start; i < end; i++) {
            imageSrc = posting[i].images.length > 0 ? posting[i].images[0].imagePath : `/images/image-placeholder.png`;
            var $dump = $(".container_cus"),
                curScroll = $(window).scrollTop();
            $dump.append(
                `    <div class="card">\n` +
                `    <div id="${posting[i].id}" class="card-header">` +
                '       <img src="' + imageSrc + '" class="card-img-top" alt="...">' +
                '   </div>' +
                '       <div class="card-body">\n' +

                '                <h5 class="card-title">' + posting[i].title + '</h5>\n' +
                '                <p class="card-text">' + posting[i].price + '</p>\n' +
                '                <a href="posting/' + posting[i].id + '" class="btn btn-primary" ' +
                'th:text="#{main_page.go_to_ad}">Перейти к объявлению</a>\n' +
                '            </div>\n' +
                '        </div>'
            );
            showFavoriteIcon();
            scrollTo(0, curScroll);
        }
    }
    start = end;
    inProgress = false;
});





function favoriteAction(post_id) {
    let favoriteIcon = $(`#favorite-${post_id}`);
    let isActive = $(`#favorite-${post_id}`).attr("data-marker");
    if (isActive == "on") {
        $.ajax({
            url: '/rest/user/favoritePostings/delete',
            type: 'post',
            data: {"id": post_id},
            success: function () {
                favoriteIcon.attr("data-marker", "off");
                favoriteIcon.css("fill", "white");
                favoriteIcon.css("color", "#007bff");
            },
            error: function () {
                alert("bad");
            }
        })
    } else {
        $.ajax({
            url: '/rest/user/favoritePostings/add',
            type: 'post',
            data: {"id": post_id},
            success: function () {
                favoriteIcon.attr("data-marker", "on");
                favoriteIcon.css("fill", "#007bff");
                favoriteIcon.css("color", "white");
            },
            error: function () {
                alert("bad");
            }
        })
    }
}

function showFavoriteIcon() {
    $.get("/rest/user_profile/favoritePostings", function (postingsDTOs) {
        if ($.isArray(postingsDTOs)) {
            $(".card-header").each(function () {
                let postingId = $(this).attr("id");
                $(this).append(`<svg data-marker="off"  data-id="${postingId}" onclick="favoriteAction(${postingId})" id="favorite-${postingId}" xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="favorite-icon-off feather feather-heart"><path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg>`);
            })

            postingsDTOs.forEach(favoritePosting => {
                $(".card-header svg").each(function (index) {
                    let postingId = $(this).attr("data-id");
                    if (postingId == favoritePosting.id) {
                        $(this).attr("data-marker", "on");
                        $(this).toggleClass("favorite-icon-on", "favorite-icon-off");
                    }
                })
            })
        }

    })
}

