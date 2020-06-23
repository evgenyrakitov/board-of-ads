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
    $.ajax({
        url: $("#add-reg-form").attr("action"),
        method: "PUT",
        data: $("#add-reg-form").serialize(),
        //success: alert("OK")
    });
});
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




