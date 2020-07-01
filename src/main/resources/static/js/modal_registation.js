$("#open-modal-1").click(function(){
    $("#modal-reg-1").modal('show');    //при клике сюда открыть окну входа
});
$("#open-modal-2").click(function () {
    $("#modal-reg-2").modal("show");    //при клике на регистрация открыть новую окну регистрации
});
$("#open-modal-3").click(function () {
    $("#modal-reg-3").modal("show");    //а это восстановление пароля
});

//begin отрисовка окна словно бы юзер вошёл в систему, типа заглушка
$("#btn-reg").click(function (event) {  //в окне регистрации
    event.preventDefault();
    let user_1 = $("#add-reg-form").serialize();    //тут уже есть юзер
    //begin namor script


    //end script

    $.post($("#add-reg-form").attr("action"), user_1, function(user){
        let k = [];
        k = user;

            let email_ = user.email;    //это в зеленый круг, ниже выпадающее меню.
            let menu = "<div class='dropdown'>" +
                "  <button class='dropbtn'>"+email_+"</button>" +
                "  <div class='dropdown-content'>" +
                "    <a href='#'>Мои объяБления</a>" +
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
//end

$("#btn-open").click(function (event) {
    event.preventDefault();
    $.ajax({
        url: $("#open-form-modal").attr("action"),
        method: "POST",
        data: $("#open-form-modal").serialize(),
        success: function (user) {
        $("#open-modal-1").html("<span>"+user.publicName+"</span>");
        }
    });
});

$("#btn-modal-3").click(function (event) {
    event.preventDefault();
    $.ajax({
        url: $("#form-modal-3").attr("action"),
        method: "POST",
        data: $("#form-modal-3").serialize(),
        success: function (user) {
            alert("Ваш пароль - "+user.password);
        }
    });
})




