let users;
const tr = $("<tr></tr>");
const td = $("<td></td>");
const select = $("<select disabled></select>").attr("name", "roleIds");
const option = $("<option></option>");


$(document).ready(function () {

    $.ajax("/user/users",
        {
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (msg) {
                users = msg;
                users.forEach(function (el) {
                    addUserInTableBody(el);
                });
            }
        }
    );
})

function addUserInTableBody(el) {
    let trLocal = tr.clone();
    trLocal.attr("id", "tr" + el.id);
    getTd(el.id, "id" + el.id).appendTo(trLocal);
    getTd(el.login, "login" + el.id).appendTo(trLocal);
    getTd(el.publicName, "publicName" + el.id).appendTo(trLocal);
    getTd(el.password, "password" + el.id).appendTo(trLocal);
    getSelect(el).appendTo(trLocal);
    trLocal.appendTo($("#tbodyUsers"));
}

let getSelect = function (el) {
    let selectLocal = select.clone();
    selectLocal.attr("size", el.roles.length);
    selectLocal.attr("id", "select" + el.id);
    el.roles.forEach(function (role) {
        option.clone().text(role.role).appendTo(selectLocal);
    });
    let tdSelect = td.clone();
    selectLocal.appendTo(tdSelect);
    return tdSelect;
};

let getTd = function (val, valueOfName) {
    let tdLocal = td.clone();
    tdLocal.attr("id", valueOfName)
    return tdLocal.text(val);
}
