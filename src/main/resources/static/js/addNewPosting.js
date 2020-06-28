const btn = $("<button type='button' class='btn cascader-table-category-2PKmD' ></button>");

$(document).ready(function () {
    $("#btnTransport").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
        var el = document.getElementById('transport');
        // var el = $('#transport');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
        // el.getAttribute("style") ==="display: none"? el.setAttribute("style","display: block") : el.setAttribute("style","display: none") ;
    });
    $("#btnImmovables").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
        var el = document.getElementById('immovables');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnJob").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
        var el = document.getElementById('job');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnAuto").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('auto');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnMotorcycle").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('motorcycle');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnTruck").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('truck');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnWaterTransport").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('waterTransport');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnParts").click(function () {
        hideAllDiv3col()
    });
    $("#btnApartments").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('apartments');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnSmallApartments").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('smallApartments');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnCottage").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('cottage');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnPlace").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('place');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnAutoHouse").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('autoHouse');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnComercApartments").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('comercApartments');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnApartmentsAbroad").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('apartmentsAbroad');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnVacancy").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('vacancy');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnSummary").click(function () {
        hideAllDiv3col()
        var el = document.getElementById('summary');
        el.getAttribute("style") === "display: none" ? el.setAttribute("style", "display: block") : el.setAttribute("style", "display: none");
    });
    $("#btnPersonalItems").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
    });
    $("#btnForHome").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
    });
    $("#btnConsumerElectronics").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
    });
    $("#btnHobby").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
    });
    $("#btnAnimals").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
    });
    $("#btnForBusiness").click(function () {
        hideAllDiv2col()
        hideAllDiv3col()
    });
});


function hideAllDiv2col() {
    var el = document.getElementsByName("colCategory2");
    el.forEach((value, key) => value.setAttribute("style", "display: none"))
}

function hideAllDiv3col() {
    var el = document.getElementsByName("colCategory3");
    el.forEach((value, key) => value.setAttribute("style", "display: none"))
}


const btnFirstCategory = "btnFirstCategory";
$(document).ready(function () {
    $.ajax({
        url: '/categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            // $(".rootCat");
            for (var i in data) {
                $("#rootCategory").append(
                    "<button type='button' class='btn cascader-table-category-2PKmD btnFirstCategory" + "' >" + data[i].name + "</button>"
                );
            }
        }
    });
});
const btnSecondCategory = "btnSecondCategory";

$(".firstCategory").click(function () {
    $.ajax({
        url: '/categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            // $(".secCategory");
            for (var i in data) {


                // el.forEach((value, key) => value.setAttribute("style", "display: block"));

                $("#secCategory")
                    .setAttribute("style", "display: block")
                    .append(
                        "<button type='button' class='btn cascader-table-category-2PKmD btnSecondCategory'>" + data[i].name + "</button>"
                    );
            }
        }
    });
});

// рабочее заполнение
// $("#btnTest").click(function () {
//     $.ajax({
//         url: '/categories',
//         type: 'get',
//         dataType: 'json',
//         success: function (data) {
//             $(".ADDssa");
//             for (var i in data) {
//                 $("#wwwww").append(
//                 "<button type='button' class='btn cascader-table-category-2PKmD' >"+data[i].name+"</button>"
//                 );
//             }
//         }
//     });
//
// });