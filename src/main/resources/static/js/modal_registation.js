import { passwordExist, warningField, successField, save, dd, checker, infoField, passwordEquals, summator } from './registration.js';

$("#open-modal-1").click(function(){
    $("#modal-reg-1").modal('show');    //при клике сюда открыть окну входа
});
$("#open-modal-2").click(function () {
    $("#modal-reg-2").modal("show");    //при клике на регистрация открыть новую окну регистрации
});
$("#open-modal-3").click(function () {
    $("#modal-reg-3").modal("show");    //а это восстановление пароля
});

//===========================================================================================//
//begin отрисовка окна словно бы юзер вошёл в систему, типа заглушка
$("#btn-reg").click(function (event) {  //в окне регистрации
    event.preventDefault();
    //let user_1 = $("#add-reg-form").serialize();    //тут уже есть юзер
    //begin namor script
    let email = $("#login").val();
    let password = $("#password").val();
    let password_confirm = $("#password_confirm").val();
    let public_name = $('#public_name').val();
    let phone = $("#phone").val();
    let resultArray = new Array();
    let sum = 0;


    //=============== test 0 -  login is email ========//
    let loginRe = new RegExp("@");
    if ((checker(email, loginRe) == 1) && (email.length > 7)) {
        successField("#login");
        sum++;  //1
    } else {
        warningField("#login");
    }
    //========= test1 - is password's not empty =========//
    //!! краснить только проблемный пароль! только пустой!
    if (passwordExist(password) && passwordExist(password_confirm) == true) {
        console.log("это код из modal_registration: оба пароля существуют!");
        successField("#password");
        successField("#password_confirm");
        sum++;  //2
    }
    else {
        //вдруг один из паролей - пуст....
        console.log("это код из modal_registration. один из паролей пуст");
        warningField("#password");
        warningField("#password_confirm");
        alert("увы, не введён пароль...");  //!! поменять на нормальный вывод
    }
    //=========== password equals? =============//
    if (passwordEquals(password, password_confirm) == true) {
        console.log("пароли совпали. хорошо.");
        sum++;  //3
    } else {
        warningField("#password");
        warningField("#password_confirm");
        alert("проверьте совпадение паролей!");
    }
    //=========  password strong? ===============//
    var passwordStrong = summator(password);
    console.log(passwordStrong);
    if ((passwordStrong < 2) || (password.length < 5)){
        infoField("#password");
        infoField("#password_confirm");
        alert("пожалуйста, используйте более сложный пароль.");
    } else {
        sum++;  //4
    }
    //========== check phone number ============/
    var correctPhone = new RegExp("\\d{10}|(\\d{3}(\\s|-)){2}(\\d{2}(\\s|-)\\d{2})");
    if(checker(phone, correctPhone) == 1) {
        console.log("формат телефона - верен");
        successField("#phone");
        sum++;  //5
    }
    else {
        console.log("неверный формат телефона");
        warningField("#phone");
        alert("введите номер телефоне в формате 913-123-45-67");
    }
    //============== check exist public name  ==========//
    if(public_name.length > 3) {
        successField('#public_name');
        sum++;  //6!
    } else {
        warningField('#public_name');
    }
    //если успешно иначе оставляем подсветку
    if (sum == 6) {
        save(email, password, public_name, phone);
       $("#modal-reg-2").modal('toggle');
    }
    else {
        console.log("это последний шанс всё  исправить...");
    }
    //end script

    $.post($("#add-reg-form").attr("action"), function(user){
        let k = [];
        k = user;

            let email_ = user.email;
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
    // $('#add-reg-form').modal('toggle');"#open-modal-2"
});
//end

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




