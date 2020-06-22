
let modal_1 = document.getElementById("modal-reg");
let btn_reg_1 = document.getElementById("open-modal-reg");
let span_1 = document.getElementsByClassName("close")[0];
let btn_open = document.getElementById("btn-open");

btn_reg_1.onclick = function() {
    modal_1.style.display = "block";
}

span_1.onclick = function() {
    modal_1.style.display = "none";
}
window.onclick = function(event) {
    if (event.target === modal_1) {
        modal_1.style.display = "none";
    }
}
btn_open.onclick = function () {
    modal_1.style.display = "none";
}

let btn_reg = document.getElementById("btn-reg");
 btn_reg.onclick = function () {
let div = "";
div = "<a href='/logout'>Logout</a>"
    $("#li-auth").html(div);
}



