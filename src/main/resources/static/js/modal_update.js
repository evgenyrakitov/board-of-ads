import * as upd from './registration.js';

$("#btn-update").click(function (event) {
    event.preventDefault();

    //begin namor script
    let id = $("#update-id-form").val();
    let email = $("#update-login-form").val();
    let password = $("#update-password-form").val();
    let password_confirm = $("#update-password_confirm-form").val();
    let first_name = $('#update-first_name-form').val();
    let last_name = $('#update-last_name-form').val();
    let region = $('#update-regionId').children().val();
    let city = $('#update-citiesId').children().val();
    let phone = $("#update-phone-form").val();
    let sum = 0;

    //=============== test 0 -  login is email ========//
    let loginRe = new RegExp("\\w+@\\w+\\.\\w{2,4}");
    if ((upd.checker(email, loginRe) === true) && (email.length > 7)) {
        upd.successField("#update-login-form");
        sum++;  //1
    } else {
        upd.warningField("#update-login-form");
    }

    //========= test1 - is password's not empty =========//
    if (upd.passwordExist(password) && upd.passwordExist(password_confirm) === true) {
        upd.successField("#update-password-form");
        upd.successField("#update-password_confirm-form");
        sum++;  //2
    }
    else {
        //вдруг один из паролей - пуст....
        upd.warningField("#update-password-form");
        upd.warningField("#update-password_confirm-form");
        alert("увы, не введён пароль...");  //!! поменять на нормальный вывод
    }
    //=========== password's equals? =============//
    if (upd.passwordEquals(password, password_confirm) === true) {
        upd.successField("#update-password-form");
        upd.successField("#update-password_confirm-form");
        sum++;  //3
    } else {
        upd.warningField("#update-password-form");
        upd.warningField("#update-password_confirm-form");
        alert("проверьте совпадение паролей!");
    }
    //=========  password strong? ===============//
    var passwordStrong = upd.summator(password);
    if ((passwordStrong < 2) || (password.length < 5)){
        upd.infoField("#update-password-form");
        upd.infoField("#update-password_confirm-form");
        alert("пожалуйста, используйте более сложный пароль.");
    } else {
        sum++;  //4
    }
    //========== check phone number ============/
    var correctPhone = new RegExp("\\d{10}|(\\d{3}(\\s|-)){2}(\\d{2}(\\s|-)\\d{2})");
    if (upd.checker(phone, correctPhone) === true) {
        upd.successField("#update-phone-form");
        sum++;  //5
    }
    else {
        upd.warningField("#update-phone-form");
        alert("введите номер телефоне в формате 913-123-45-67");
    }
    //============== check exist public name  ==========//
    if (first_name.length > 3 && last_name.length > 3) {
        upd.successField('#update-first_name-form');
        upd.successField('#update-last_name-form');
        sum++;  //6!
    } else {
        alert("попробуйте имя и фамилию более 3 символов!");
        upd.warningField('#update-first_name-form');
        upd.warningField('#update-last_name-form');
    }
    if (sum === 6) {
        upd.edit(id, email, password, first_name, last_name, region, city, phone);
        $("#modal-update").modal('toggle');
    }
    else {
    }
    //end script
});

document.getElementById('update-region').addEventListener('change', loadCities);
function loadCities(){
    $("#update-citiesId").children().remove();
    let update_regionId = {
        id : this.selectedIndex
    }
    $.ajax({
        url: '/rest/getCities',
        type: 'POST',
        data: JSON.stringify(update_regionId),
        contentType: "application/json",
        dataType: "json",
        success: function (cities) {
            $("#update-citiesId").append("<select class='custom-select' style='margin-top: 10px' id='update-cities'>");
            $("#update-cities").append($("<option disabled hidden selected></option>").attr("value", 0).attr("label", 'Город'));
            for(let i=0; i<cities.length; i++) {
                let update_city_id = cities[i].id;
                let update_city_name = cities[i].name;
                $("#update-cities").append($("<option></option>").attr("value", update_city_id).attr("label", update_city_name));
                //$("<option value=\"${cities[i].id}\" label=\"${cities[i].name}\"></option>").appendTo($("#cities"));
            }
            $("#update-regionId").append("</select");
        }
    })
}

