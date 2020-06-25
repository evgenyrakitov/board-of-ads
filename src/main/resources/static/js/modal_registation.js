$("#open-modal-1").click(function(){
    $("#modal-reg-1").modal('show');
});
$("#open-modal-2").click(function () {
    $("#modal-reg-2").modal("show");
});
$("#open-modal-3").click(function () {
    $("#modal-reg-3").modal("show");
});

let add_reg_form = $("#add-reg-form");
$("#btn-reg").click(function (event) {
    event.preventDefault();
    let user_ = add_reg_form.serialize();
    $.post(add_reg_form.attr("action"), user_, function(user){
        let role = "";
        let userRoles = user.roles;
        for (let j = 0; j< userRoles.length; j++) {
            role += userRoles[j].name + " ";
        }

        let nsp = $("<div class='dropdown'><button class='mainmenubtn' id='"+user.id+"'>"+user.login+
            "<br> ВЫ АВТОРИЗОВАНЫ - "+role+"</button><div class='dropdown-child'>"+
            "<li><a href='#' id='"+user.id+"'>Рейтинг</a></li>"+
            "<li><a href='#' id='"+user.id+"'>Мои объявления</a></li>"+
            "<li><a href='#' id='"+user.id+"'>Мои отзывы</a></li>"+
            "<li><a href='#' id='"+user.id+"'>Избранное</a></li>"+
            "<li><a href='#' id='"+user.id+"'>Сообщения</a></li>" +
            "<li><a href='#' id='"+user.id+"'>Уведомления</a></li>" +
            "<li><a href='#' id='"+user.id+"'>Кошелек</a></li>"+
            "<li><a href='#' id='"+user.id+"'>Платные услуги</a></li>" +
            "<li><a href='#' id='"+user.id+"'>ВЫЙТИ</a></li>" +
            "</div></div>");

        $("#li-nav-a").html(nsp);




    });

   /* $.ajax({
        url: $("#add-reg-form").attr("action"),
        method: "PUT",
        data: $("#add-reg-form").serialize(),
        success: function (user) {}
    });*/

});
$("#btn-open").click(function (event) {
    event.preventDefault();
    $.ajax({
        url: $("#open-form-modal").attr("action"),
        method: "POST",
        data: $("#open-form-modal").serialize(),

    });

    $("#open-modal-1").html("<span>"+user.publicName+"</span>");
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




