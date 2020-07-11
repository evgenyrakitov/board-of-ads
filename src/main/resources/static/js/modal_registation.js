import * as reg from './registration.js';

$(".open-modal-1").click(function(){
    $("#modal-reg-1").modal('show');    //окно входа
});
$("#open-modal-2").click(function () {
    $("#modal-reg-2").modal("show");    //окно регистрации
});
$("#open-modal-3").click(function () {
    $("#modal-reg-3").modal("show");
});

$("#btn-reg").click(function (event) {
    event.preventDefault();
    // let user_1 = $("#add-reg-form").serialize();        //переделать!

    //begin namor script
    let email = $("#login-reg-form").val();
    let password = $("#password-reg-form").val();
    let password_confirm = $("#password_confirm-reg-form").val();
    let public_name = $('#public_name-reg-form').val();
    let phone = $("#phone-reg-form").val();
    let sum = 0;
    let successValidate;

    //=============== test 0 -  login is email ========//
    let loginRe = new RegExp("\\w+@\\w+\\.\\w{2,4}");
    if ((reg.checker(email, loginRe) === true) && (email.length > 7)) {
        reg.successField("#login-reg-form");
        sum++;  //1
    } else {
        reg.warningField("#login-reg-form");
    }

    //========= test1 - is password's not empty =========//
    //!! краснить только проблемный пароль! только пустой!
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
    if (reg.passwordEquals(password, password_confirm) == true) {
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
    if(reg.checker(phone, correctPhone) === true) {
        reg.successField("#phone-reg-form");
        sum++;  //5
    }
    else {
        reg.warningField("#phone-reg-form");
        alert("введите номер телефоне в формате 913-123-45-67");
    }
    //============== check exist public name  ==========//
    if(public_name.length > 3) {
        reg.successField('#public_name-reg-form');
        sum++;  //6!
    } else {
        alert("попробуйте имя более 3 символов!");
        reg.warningField('#public_name-reg-form');
    }
    if (sum == 6) {
        reg.save(email, password, public_name, phone);
        $("#modal-reg-2").modal('toggle');
    }
    else {
    }
    //end script
});

$("#btn-modal-3").click(function (event) {
    event.preventDefault();
    let user_2 = $("#form-modal-3").serialize();
    $.ajax({
        url: "/rest/resetPassword",
        data: user_2,
        method: "POST",
        success: function (bool) {
            if (bool){
                $("#modal-reg-password").modal("show");
                $("#reg-password").html("<p>Вскоре вы получите электронное письмо для сброса пароля</p>");
            } else {
                $("#modal-reg-2").modal("show");
                $("#message-reset-password").html("<h4>Пользователь с таким Email не зарегистрирован</h4>" +
                    "<br><p>Пройдите регистрацию</p>");
            }
        }
    });

});
