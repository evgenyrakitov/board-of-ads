let formatMoney = function (x) {
    var parts = x.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    return parts.join(".");
}

let on_profile_page_load = function () {
    $('#navlink-my-postings').click(function (e) {
        user_profile.show_postings(this);
        return true;
    });

    $('#navlink-my-feedbacks').click(function (e) {
        user_profile.show_feedbacks(this);
        return true;
    });

    $('#navlink-my-favorites').click(function (e) {
        user_profile.show_favorites(this);
        return true;
    });

    $('#navlink-my-messages').click(function (e) {
        user_profile.show_messages(this);
        return true;
    });

    $('#navlink-my-notifications').click(function (e) {
        user_profile.show_notifications(this);
        return true;
    });

    $('#navlink-my-settings').click(function (e) {
        user_profile.show_settings(this);
        return true;
    });

    $('#navlink-my-rating').click(function (e) {
        user_profile.show_rating(this);
        return true;
    });

    let event = new Event("click");
    switch (window.location.hash) {
        case "#postings":
            document.getElementById("navlink-my-postings").dispatchEvent(event);
            break;
        case "#feedbacks":
            document.getElementById("navlink-my-feedbacks").dispatchEvent(event);
            break;
        case "#favorites":
            document.getElementById("navlink-my-favorites").dispatchEvent(event);
            break;
        case "#messages":
            document.getElementById("navlink-my-messages").dispatchEvent(event);
            break;
        case "#notifications":
            document.getElementById("navlink-my-notifications").dispatchEvent(event);
            break;
        case "#settings":
            document.getElementById("navlink-my-settings").dispatchEvent(event);
            break;
        case "#rating":
            document.getElementById("navlink-my-rating").dispatchEvent(event);
            break;
        default:
            document.getElementById("navlink-my-postings").dispatchEvent(event);
            break;
    }
}

let user_profile = {
    deselectAllNavLinks: function () {
        $(".profile-sidebar-navigation-link-2olMF").each(function () {
            this.classList.remove("profile-sidebar-navigation-link-active-3sgHn");
        });
        $("#navlink-my-rating").attr("href", "#rating");
        $("#navlink-my-rating").removeAttr("style");
    },

    show_postings: function (element) {
        this.deselectAllNavLinks();
        element.classList.add("profile-sidebar-navigation-link-active-3sgHn");
        this.draw_postings_block();
        //document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Мои объявления</h1>";
    },
    show_feedbacks: function (element) {
        this.deselectAllNavLinks();
        element.classList.add("profile-sidebar-navigation-link-active-3sgHn");
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Мои отзывы</h1>";
    },
    show_favorites: function (element) {
        this.deselectAllNavLinks();
        element.classList.add("profile-sidebar-navigation-link-active-3sgHn");
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Избранное</h1>";
    },
    show_messages: function (element) {
        this.deselectAllNavLinks();
        element.classList.add("profile-sidebar-navigation-link-active-3sgHn");
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Сообщения</h1>";
    },
    show_notifications: function (element) {
        this.deselectAllNavLinks();
        element.classList.add("profile-sidebar-navigation-link-active-3sgHn");
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Уведомления</h1>";
    },
    show_settings: function (element) {
        this.deselectAllNavLinks();
        element.classList.add("profile-sidebar-navigation-link-active-3sgHn");
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Настройки</h1>";
    },
    show_rating: function (element) {
        this.deselectAllNavLinks();
        $("#navlink-my-rating").attr("style", "color: black");
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Рейтинг</h1>";
    },

    ///////// HTML GENERATORS ////////

    draw_postings_block: function () {
        $.get("/rest/user_profile/postings", function (postingsDTOs) {

            let postingBlock = document.getElementById("user_page_content");
            let html = [];
            let i = 0;

            html.push('<h1 class="heading">Мои объявления</h1>')
            html.push('<div class="js-personal-items">');
            postingsDTOs.forEach(function (dto) {
                i++;

                html.push('<div class="personal-items-line-3mthn"></div>');

                if (i == 1) {
                    html.push('<div class="item-snippet-root-1ZIn9" data-marker="item-snippet/1148965022">');
                } else {
                    html.push('<div class="item-snippet-root-1ZIn9" data-marker="item-snippet/1148965022" style="border-top: 1px solid #f5f5f5;">');
                }

                html.push('<div class="item-snippet-snippet-1XIaf">');
                html.push('<a class="item-preview-root-PsYRy item-snippet-column-1FtP4 item-snippet-column_preview-1O6d5" href="' + dto.url + '">');
                html.push(`<div class="item-preview-image-16c92" style="background-image: url('/images/image-placeholder.png');"></div>`);
                html.push('<div class="item-preview-icons-2ty4_"><i class="item-preview-icon-2pte7 item-preview-icon_photo-2wudU">1</i></div>');
                html.push('</a>');
                html.push('<div class="item-body-root-371IO item-snippet-column-1FtP4 item-snippet-column_body-2uA-_">');
                html.push('<div class="title-title-1BFXf">');
                html.push('<div class="text-text-1PdBw text-size-ms-23YBR text-bold-3R9dt"><a href="' + dto.url + '" target="_self" class="link-link-39EVK link-design-default-2sPEv link-novisited-1w4JY">' + dto.title + '</a></div>');
                html.push('</div>');
                html.push('<div class="price-root-1DXKW">');
                html.push('<span class="text-text-1PdBw text-size-m-4mxHN text-bold-3R9dt">' + formatMoney(dto.price) + '<span class="text-text-1PdBw">&nbsp;<span class="number-format-currency-3hVeG">₽</span></span> </span>');
                html.push('<div class="price-icons-1BIe6"></div>');
                html.push('</div>');
                html.push('<div class="item-body-actions-3pWTl"></div>');
                html.push('</div>');
                html.push('<div class="item-info-root-wBabw item-snippet-column-1FtP4 item-snippet-column_info-2khYL" data-marker="item-info/1148965022">');
                html.push('<div class="actions-dropdown-root-jPjxy" data-marker="item-info/1148965022/dropdown-button"><span class="tooltip-tooltip-box-2rApK"><span class="tooltip-target-wrapper-XcPdv"><button class="actions-dropdown-button-3vVVq" type="button">•••</button></span></span></div>');
                html.push('<div class="item-info-row-2W-ds"><span class="text-text-1PdBw text-size-s-1PUdo">Информация о сроке размещения</span></div>');
                html.push('<div class="item-info-row-2W-ds"></div>');
                html.push('<div class="item-info-row-2W-ds"></div>');
                html.push('<div class="item-info-row-2W-ds"></div>');
                html.push('<div class="item-info-row-2W-ds">');
                html.push(`<div class="counters-link-wrap-38XDX"><a href="` + dto.url + `" target="_self" class="link-link-39EVK link-design-default-2sPEv link-novisited-1w4JY"><i class="counters-icon-2rkLp counters-icon_views-1_u_8" style="background-image: url('/images/views.svg');"></i><span class="counters-text-2nXu_ text-text-1PdBw text-size-s-1PUdo"><span class="text-text-1PdBw">` + dto.viewCount + `</span></span></a></div>`);
                html.push(`<div class="counters-link-wrap-38XDX"><a href="` + dto.url + `" target="_self" class="link-link-39EVK link-design-default-2sPEv link-novisited-1w4JY"><i class="counters-icon-2rkLp counters-icon_favorites-1dqSi" style="background-image: url('/images/likes.svg');"></i><span class="counters-text-2nXu_ text-text-1PdBw text-size-s-1PUdo"><span class="text-text-1PdBw">` + dto.favoritesCount + `</span></span></a></div>`);
                html.push('</div>');
                html.push('<div class="actions-root-1xyPW"><button type="button" data-marker="publish-button/1148965022" class="button-button-2Fo5k button-size-s-3-rn6 button-default-mSfac width-width-12-2VZLz" aria-busy="false"><span class="button-textBox-Row9K">Редактировать</span></button></div>');
                html.push('</div>');
                html.push('</div>');
                html.push('</div>');

            })
            html.push('</div>');
            postingBlock.innerHTML = html.join('');
        });
    }
}

