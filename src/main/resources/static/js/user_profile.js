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
        document.getElementById("user_page_content").innerHTML = "<h1 class=\"heading\">Мои объявления</h1>";
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
    }
}