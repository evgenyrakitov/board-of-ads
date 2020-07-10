let category = [];
$("#findFromCategory").click(function () {
    //event.preventDefault();
    $.get("/rest/categories/dto", null, function (data) {
        let parentCategory = "";

        category = data;
        let option = "";
        let id = "";
        let name = "";
        for (let i = 0; i < category.length; i++) {
            id = category[i].id;
            name = category[i].name;
            parentCategory = category[i].parentCategory;
            if (parentCategory == null ) {
                option += "<option style='background: #d6d6d6' id='category" + id +"'>" + name + "</option>";
                option +=    getCategories(name);
            }

        }

        $("#findFromCategory").append(option);
    });
});


function getCategories(parentCategory) {
        let option = "";
        let parentCategory_1 = "";
        for (let i = 0; i < category.length; i++) {
            if (category[i].parentCategory === parentCategory){
                option += "<option style='font-weight: bold' id='1category" + category[i].id + "'>" + category[i].name + "</option>";
                for (let j = 0; j < category.length; j++) {
                    parentCategory_1 = category[j].parentCategory;
                    if (parentCategory_1 === category[i].name) {
                        option += "<option id='2category" + category[j].id + "'>" + category[j].name + "</option>";
                    }

                }
            }
        }
        return option;
}

    $("#btn-search").click(function () {
        let search = $("#search-form").serialize();
        $.ajax({
            url: "/rest/posting/search",
            method: "GET",
            data: search,
            dataType: 'json',
            success: function (data) {
                if (data.length === 0) {
                    alert("По Вашему поиску ничего не найдено");
                } else drawPosting(data);
            }
        });


    });

    function drawPosting(data) {
        $(".container_cus").empty();
        data.forEach(posting => {
            $(".container_cus").append(
                '<div class="card">\n' +
                '            <img src="' + posting.imagePath[0].imagePath + '" class="card-img-top" alt="...">\n' +
                '            <div class="card-body">\n' +
                '                <h5 class="card-title">' + posting.title + '</h5>\n' +
                '                <p class="card-text">' + posting.price + '</p>\n' +
                '                <a href="adDetails" class="btn btn-primary" ' +
                'th:text="#{main-page.go_to_ad}">Перейти к объявлению</a>\n' +
                '            </div>\n' +
                '        </div>'
            );
        });
    }