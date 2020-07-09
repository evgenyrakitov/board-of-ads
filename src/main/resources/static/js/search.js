
$("#findFromCategory").click(function () {
    //event.preventDefault();
    $.get("/rest/categories", null, function (data) {
        let parentCategory = [];
        let parentCategory_2 = [];
        let parentCategory_1 = [];
        let category = [];
        let id = "";
        category = data;
        let nameRu = "";
        let option = "";
        let select = "";
        for (let i = 0; i < category.length; i++) {
            id = category[i].id;
            nameRu = category[i].nameRu;
            parentCategory = category[i].parentCategory;
            if (parentCategory == null) {
                option += "<option style='background: #7f8fa6' id='category"+id+"'>"+nameRu+"</option>";
            }
            for (let j = 0; j < category.length ; j++) {
                parentCategory_1 = category[j].parentCategory;
                if (parentCategory_1==null)continue;
                if(parentCategory_1.id === id){
                    option += "<option id='1parent-category"+category[j].id+"'>"+category[j].nameRu+"</option>";
                } else {
                    parentCategory_2 = parentCategory_1.parentCategory;
                    if (parentCategory_2 == null)continue;
                    if (parentCategory_2.id === id){
                        option += "<option id='2parent-category"+parentCategory_1.id+"'>"+category[j].nameRu+"</option>";
                    }
                }
            }
        }
        $("#findFromCategory").append(option);
    });
});

$("#btn-search").click(function () {
    let search = $("#search-form").serialize();
    $.ajax({
        url: "/rest/posting/search",
        method: "GET",
        data: search,
        dataType: 'json',
        success: function (data) {
            drawPosting(data);
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