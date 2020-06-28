$("#open-modal-1").click(function(){
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
    $.ajax({
        url: "/rest/add",
        data: user_1,
       // async:"false",
        method: "PUT",
        dataType:("json"),
        success: function (user) {

            let user_ = user.email;
            let menu = "<div class='dropdown'>" +
                "  <button class='dropbtn'>"+user_+"</button>" +
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
        }
    });

});
$("#btn-open").click(function (event) {
    event.preventDefault();
    $.ajax({
        url: $("#open-form-modal").attr("action"),
        dataType: "json",
        method: "POST",
        data: $("#open-form-modal").serialize(),
        success: function (user) {
            let id = user.id;
            let email = user.email;
        $("#li").html("<span id='"+id+"'>"+email+"</span>");
        }
    });
});
$("#btn-modal-3").click(function (event) {
    event.preventDefault();
    let user_2 = $("#form-modal-3").serialize();
    $.ajax({
        url: "/user/resetPassword",
        data: user_2,
        method: "POST",
        success: function (data) {
            let emal_ = data.message;
            $("#modal-reg-password").modal("show");
            $("#reg-password").html("<p>"+emal_+"</p>");

        }
    });

});




