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

$("#search-input").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#btn-search").click();
    }
});

$("#btn-search").click(function () {
    let search = $("#search-form").serialize();
        getPostings(search);
    });




function getPostings(search) {
    $.get("/rest/posting/search", search, function (data) {
        let postings = [];
        let div = "";
        postings = data;
        if (postings.length !== 0){
        for (let i = 0; i < postings.length; i++) {
            div += "<div  id='post" + postings[i].id + "' class='card'><img src='" + postings[i].imagePath[0].imagePath + "' class='card-img-top' alt='...'>" +
                "<div class='card-body'>" +
                "<h5 class='card-title'>" + postings[i].title + "</h5>" +
                "<p class='card-text'>" + postings[i].price + "</p>" +
                "<a href='adDetails' class='btn btn-primary'th:text='#{main-page.go_to_ad}'>Перейти к объявлению</a>" +
                "</div></div>";
        }
        $("#body-post").html(div);
        }else {
            $("#modal-search").modal("show");
        }
    });
}



