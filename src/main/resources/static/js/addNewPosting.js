$(document).ready(function () {
    $.ajax({
        url: '/rest/categories/dto',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $("#rootCategory").empty();
            for (var i in data) {
                if (data[i].parentCategory == null) {
                    if (countChildren(data, data[i].id) > 0) {
                        $("#rootCategory").append(
                            "<button type='button' class='btn cascader-table-category-2PKmD btnFirstCategory text-text-1PdBw text-size-s-1PUdo' onclick='funcLevel2Menu(" + data[i].id + ")')>" + data[i].name + "</button>"
                        );

                    } else {
                        $("#rootCategory").append(
                            "<button type='button' class='btn cascader-table-category-2PKmD btnFirstCategory text-text-1PdBw text-size-s-1PUdo' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                        );
                    }
                }
            }
        }
    });
});

var funcLevel2Menu = function funcLevel2Menu(nameButtonCategory) {
    document.getElementById('colCategory3').setAttribute("style", "display: none");
    document.getElementById('colCategory4').setAttribute("style", "display: none");
    $("#secCategory").empty();
    $.ajax({
        url: '/rest/categories/dto',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (countChildren(data, nameButtonCategory) > 0) {
                for (var i in data) {
                    if (data[i].parentCategory !== null && data[i].parentId === nameButtonCategory) {
                        $("#secCategoryHeader").text(data[i].parentCategory);
                        if (countChildren(data, data[i].id) > 0) {
                            document.getElementById('colCategory2').setAttribute("style", "display: block");
                            $("#secCategory").append(
                                "<button type='button' class='btn cascader-table-category-2PKmD btn3Category text-text-1PdBw text-size-s-1PUdo' onclick='funcLevel3Menu(" + data[i].id + ")')>" + data[i].name + "</button>"
                            );
                        } else {
                            document.getElementById('colCategory2').setAttribute("style", "display: block");
                            $("#secCategory").append(
                                "<button type='button' class='btn cascader-table-category-2PKmD btn3Category text-text-1PdBw text-size-s-1PUdo' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                            );
                        }
                    }
                }
            }
        }
    });
}

var funcLevel3Menu = function funcLevel3Menu(nameButtonCategory) {
    document.getElementById('colCategory4').setAttribute("style", "display: none");
    $("#threeCategory").empty();
    $.ajax({
        url: '/rest/categories/dto',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (countChildren(data, nameButtonCategory) > 0) {
                for (var i in data) {
                    if (data[i].parentCategory !== null && data[i].parentId === nameButtonCategory) {
                        $("#threeCategoryHeader").text(data[i].parentCategory);
                        if (countChildren(data, data[i].id) > 0) {
                            document.getElementById('colCategory3').setAttribute("style", "display: block");
                            $("#threeCategory").append(
                                "<button type='button' class='btn cascader-table-category-2PKmD btn4Category text-text-1PdBw text-size-s-1PUdo' onclick='funcLevel4Menu(" + data[i].id + ")')>" + data[i].name + "</button>"
                            );
                        } else {
                            document.getElementById('colCategory3').setAttribute("style", "display: block");
                            $("#threeCategory").append(
                                "<button type='button' class='btn cascader-table-category-2PKmD btn4Category text-text-1PdBw text-size-s-1PUdo' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                            );
                        }
                    }
                }
            }
        }
    });
}
var funcLevel4Menu = function funcLevel4Menu(nameButtonCategory) {
    $("#forCategory").empty();
    $.ajax({
        url: '/rest/categories/dto',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (countChildren(data, nameButtonCategory) > 0) {
                for (var i in data) {
                    if (data[i].parentCategory !== null && data[i].parentId === nameButtonCategory) {
                        $("#forCategoryHeader").text(data[i].parentCategory);
                        document.getElementById('colCategory4').setAttribute("style", "display: block");
                        $("#forCategory").append(
                            "<button type='button' class='btn cascader-table-category-2PKmD btn5Category text-text-1PdBw text-size-s-1PUdo' onclick='window.location.replace(`/newPosting/" + data[i].name + "`)'>" + data[i].name + "</button>"
                        );
                    }
                }
            }
        }
    });
}

var countChildren = function (data, parentName) {
    var count = 0;
    data.forEach(function (el) {
        if (el.parentCategory != null) {
            if (el.parentId === parentName) {
                count++;
            }
        }
    });
    return count;
}
