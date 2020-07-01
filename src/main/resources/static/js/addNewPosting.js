$(document).ready(function () {

    $.ajax({
        url: '/rest/categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $("#rootCategory").empty();
            for (var i in data) {
                if (data[i].parentCategory == null) {
                    if (countChildren(data, data[i].name) > 0) {
                        $("#rootCategory").append(
                            "<button type='button' class='btn cascader-table-category-2PKmD btnFirstCategory' onclick='funcLevel2Menu(this)')>" + data[i].name + "</button>"
                        );
                    } else {
                        $("#rootCategory").append(
                            "<button type='button' class='btn cascader-table-category-2PKmD btnFirstCategory' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                        );
                    }
                }
            }
        }
    });
});

var countChildren = function (data, parentName) {
    var count = 0;
    data.forEach(function (el) {
        if (el.parentCategory != null) {
            if (el.parentCategory.name === parentName) {
                count++;
            }
        }
    });
    return count;
}

function funcLevel2Menu(nameButtonCategory) {
    document.getElementById('colCategory2').setAttribute("style", "display: none");
    document.getElementById('colCategory3').setAttribute("style", "display: none");
    $("#secCategory").empty();
    $.ajax({
        url: '/rest/categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (countChildren(data, $(nameButtonCategory).text()) > 0) {
                for (var i in data) {
                    if (data[i].parentCategory !== null && data[i].parentCategory.name === $(nameButtonCategory).text()) {
                        document.getElementById('colCategory2').setAttribute("style", "display: block");
                        $("#secCategoryHeader").text(data[i].parentCategory.name);
                        if (countChildren(data, data[i].name) > 0) {
                            document.getElementById('colCategory2').setAttribute("style", "display: block");
                            $("#secCategory").append(
                                "<button type='button' class='btn cascader-table-category-2PKmD btnSecondCategory' onclick='funcLevel3Menu(this)')>" + data[i].name + "</button>"
                            );
                        } else {
                            document.getElementById('colCategory2').setAttribute("style", "display: block");
                            $("#secCategory").append(
                                "<button type='button' class='btn cascader-table-category-2PKmD btnSecondCategory' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                            );
                        }
                    }
                }
            }
        }
    });
}

function funcLevel3Menu(nameButtonCategory) {
    document.getElementById('colCategory3').setAttribute("style", "display: none");
    $("#threeCategory").empty();
    $.ajax({
        url: '/rest/categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (countChildren(data, $(nameButtonCategory).text()) > 0) {
                for (var i in data) {
                    if (data[i].parentCategory !== null && data[i].parentCategory.name === $(nameButtonCategory).text()) {
                        document.getElementById('colCategory3').setAttribute("style", "display: block");
                        $("#threeCategoryHeader").text(data[i].parentCategory.name);
                        $("#threeCategory").append(
                            "<button type='button' class='btn cascader-table-category-2PKmD btnFirstCategory' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                        );
                    }
                }
            }
        }
    });
}

$(".btnFirstCategory").click(function () {
    alert("skdfsldf");
    $.ajax({
        url: '/rest/categories',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            for (var i in data) {
                $("#colCategory2").setAttribute("style", "display: block");
                $("#rootCategory")
                    .append(
                        "<button type='button' class='btn cascader-table-category-2PKmD btnSecondCategory'>" + data.name + "</button>"
                    );
            }
        }
    });
});
