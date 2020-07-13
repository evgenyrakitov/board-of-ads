$(".open-modal-1").click(function () {
    $("#modal-reg-1").modal('show');
});
$("#open-modal-2").click(function () {
    $("#modal-reg-2").modal("show");
});
$("#open-modal-3").click(function () {
    $("#modal-reg-3").modal("show");
});

$("#btn-reg").click(function (event) {
    event.preventDefault();
    let user_1 = $("#add-reg-form").serialize();
    $.post($("#add-reg-form").attr("action"), user_1, function (user) {
        let k = [];
        k = user;

        let email_ = user.email;
        let menu = "<div class='dropdown'>" +
            "  <button class='dropbtn'>" + email_ + "</button>" +
            "  <div class='dropdown-content'>" +
            "    <a href='#'>Мои объявления</a>" +
            "    <a href='#'>Мои отзывы</a>" +
            "    <a href='#'>Избранное</a>" +
            "<a href='#'>Сообщения</a>" +
            "<a href='#'>Уведомления</a>" +
            "<a href='#'>Кошелек</a>" +
            "<a href='#'>Платные услуги</a>" +
            "<a href='#'>Настройки</a>" +
            "<a href='/logout'>Выйти</a>" +
            "</div></div>";
        $("#li").html(menu);
    });

});

/*
$("#btn-open").click(function (event) {
    event.preventDefault();
    $.ajax({
        url: $("#open-form-modal").attr("action"),
        method: "POST",
        data: $("#open-form-modal").serialize(),
        success: function (user) {
        // $("#open-modal-1").html("<span>"+user.publicName+"</span>");
        }
    });
});
*/

$("#btn-modal-3").click(function (event) {
    event.preventDefault();
    let user_2 = $("#form-modal-3").serialize();
    $.ajax({
        url: "/rest/resetPassword",
        data: user_2,
        method: "POST"
    });

});




