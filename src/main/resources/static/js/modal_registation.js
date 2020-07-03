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
    let login = $("#login").val();
    let password = $("#password").val();
    let password_confirm = $("#password_confirm").val();
    let public_name = $('#public_name').val();
    let phone = $("#phone").val();
    let resultArray = new Array();
    let sum;

    var loginTrue;
    var public_nameTrue;
    var phoneTrue;

    //=============== test 0 -  login is email ========//
    let loginRe = new RegExp("@");
    if ((checker(login, loginRe) == 1) && (login.length > 7)) {
        successField("#login");
    } else {
        warningField("#login");
    }
    //========= test1 - is password's not empty =========//
    //!! краснить только проблемный пароль! только пустой!
    if (passwordExist(password) && passwordExist(password_confirm) == true) {
        console.log("это код из modal_registration: оба пароля существуют!");
        successField("#password");
        successField("#password_confirm");
    }
    else {
        //вдруг один из паролей - пуст....
        console.log("это код из modal_registration. один из паролей пуст");
        warningField("#password");
        warningField("#password_confirm");
        alert("увы, не введён пароль...");
        resultArray.push(false);
        exit(1);
    }
    //=========== password equals? =============//
    if (passwordEquals(password, password_confirm) == true) {
        console.log("пароли совпали. хорошо.");
    } else {
        warningField("#password");
        warningField("#password_confirm");
        alert("проверьте совпадение паролей!");
    }
    //=========  password strong? ===============//
    var passwordStrong = summator(password);
    console.log(passwordStrong);
    if ((passwordStrong < 3) || (password.length < 5)){
        infoField("#password");
        infoField("#password_confirm");
        alert("пожалуйста, используйте более сложный пароль.");
    }
    //========== check phone number ============/
    var correctPhone = new RegExp("\\d{10}|(\\d{3}(\\s|-)){2}(\\d{2}(\\s|-)\\d{2})");
    if(checker(phone, correctPhone) == 1) {
        console.log("формат телефона - верен");
        successField("#phone");
    }
    else {
        console.log("неверный формат телефона");
        warningField("#phone");
        alert("введите номер телефоне в формате 913-123-45-67");
    }
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




