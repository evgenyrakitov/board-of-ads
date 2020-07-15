$(".open-modal-1").click(function(){
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
    $.post($("#add-reg-form").attr("action"), user_1, function(user){
        let k = [];
        k = user;


    //begin namor script
    let email = $("#login-reg-form").val();
    let password = $("#password-reg-form").val();
    let password_confirm = $("#password_confirm-reg-form").val();
    let first_name = $('#first_name-reg-form').val();
    let last_name = $('#last_name-reg-form').val();
    let region = $('#regionId').children().val();
    let city = $('#citiesId').children().val();
    let phone = $("#phone-reg-form").val();
    let sum = 0;


            let email_ = user.email;
            let menu = "<div class='dropdown'>" +
                "  <button class='dropbtn'>"+email_+"</button>" +
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


    //========= test1 - is password's not empty =========//
    if (reg.passwordExist(password) && reg.passwordExist(password_confirm) === true) {
        reg.successField("#password-reg-form");
        reg.successField("#password_confirm-reg-form");
        sum++;  //2
    }
    else {
        //вдруг один из паролей - пуст....
        reg.warningField("#password-reg-form");
        reg.warningField("#password_confirm-reg-form");
        alert("увы, не введён пароль...");  //!! поменять на нормальный вывод
    }
    //=========== password's equals? =============//
    if (reg.passwordEquals(password, password_confirm) === true) {
        reg.successField("#password-reg-form");
        reg.successField("#password_confirm-reg-form");
        sum++;  //3
    } else {
        reg.warningField("#password-reg-form");
        reg.warningField("#password_confirm-reg-form");
        alert("проверьте совпадение паролей!");
    }
    //=========  password strong? ===============//
    var passwordStrong = reg.summator(password);
    if ((passwordStrong < 2) || (password.length < 5)){
        reg.infoField("#password-reg-form");
        reg.infoField("#password_confirm-reg-form");
        alert("пожалуйста, используйте более сложный пароль.");
    } else {
        sum++;  //4
    }
    //========== check phone number ============/
    var correctPhone = new RegExp("\\d{10}|(\\d{3}(\\s|-)){2}(\\d{2}(\\s|-)\\d{2})");
    if (reg.checker(phone, correctPhone) === true) {
        reg.successField("#phone-reg-form");
        sum++;  //5
    }
    else {
        reg.warningField("#phone-reg-form");
        alert("введите номер телефоне в формате 913-123-45-67");
    }
    //============== check exist public name  ==========//
    if (first_name.length > 3 && last_name.length > 3) {
        reg.successField('#first_name-reg-form');
        reg.successField('#last_name-reg-form');
        sum++;  //6!
    } else {
        alert("попробуйте имя и фамилию более 3 символов!");
        reg.warningField('#first_name-reg-form');
        reg.warningField('#last_name-reg-form');
    }
    if (sum === 6) {
        reg.save(email, password, first_name, last_name, region, city, phone);
        $("#modal-reg-2").modal('toggle');
    }
    else {
    }
    //end script
});

document.getElementById('region').addEventListener('change', loadCities);
function loadCities(){
    $("#citiesId").children().remove();
    let regionId = {
        id : this.selectedIndex
    }
    $.ajax({
        url: '/rest/getCities',
        type: 'POST',
        data: JSON.stringify(regionId),
        contentType: "application/json",
        dataType: "json",
        success: function (cities) {
            $("#citiesId").append("<select class='custom-select' style='margin-top: 10px' id='cities'>");
            $("#cities").append($("<option disabled hidden selected></option>").attr("value", 0).attr("label", 'Город'));
            for(let i=0; i<cities.length; i++) {
                let city_id = cities[i].id;
                let city_name = cities[i].name;
                $("#cities").append($("<option></option>").attr("value", city_id).attr("label", city_name));
                //$("<option value=\"${cities[i].id}\" label=\"${cities[i].name}\"></option>").appendTo($("#cities"));
            }
            $("#regionId").append("</select");
        }
    })
}


$("#btn-modal-3").click(function (event) {
    event.preventDefault();
    $('#reset-email-sent-letter-message').hide();
    $('#user-not-found-message').hide();
    let user_2 = $("#form-modal-3").serialize();
    $.ajax({
        url: "/rest/resetPassword",
        data: user_2,
        method: "POST",
        success: function (bool) {
            if (bool) {
                $('#reset-email-sent-letter-message').show();
                $("#btn-modal-3").hide();
            } else {
                $('#user-not-found-message').slideDown();
            }

        }
    });
});




