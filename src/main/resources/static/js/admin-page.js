let users;
const tr = $("<tr></tr>");
const td = $("<td></td>");
const select = $("<select disabled></select>").attr("name", "roleIds");
const option = $("<option></option>");


$(document).ready(function () {

    $.ajax("/rest/admin/users",
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
    // getTd(el.id, "id" + el.id).appendTo(trLocal);
    $("<th></th>").text(el.id).attr("id", el.id).appendTo(trLocal);
    getTd(el.email, "login" + el.id).appendTo(trLocal);
    getTd(el.firstName, "firstName" + el.id).appendTo(trLocal);
    getTd(el.lastName, "lastName" + el.id).appendTo(trLocal);
    getTd(el.password, "password" + el.id).appendTo(trLocal);
    getTd(el.phone, "phone" + el.id).appendTo(trLocal);
    getTd(el.dataRegistration, "dataRegistration" + el.id).appendTo(trLocal);
    getTd(el.region.name, "region"+el.id).appendTo(trLocal);
    getTd(el.city.name, "city"+el.id).appendTo(trLocal);
    getSelect(el).appendTo(trLocal);
    trLocal.appendTo($("#tbodyUsers"));
}

let getSelect = function (el) {
    let selectLocal = select.clone();
    selectLocal.attr("size", el.roles.length);
    selectLocal.attr("id", "select" + el.id);
    el.roles.forEach(function (role) {
        option.clone().text(role.name).appendTo(selectLocal);
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
