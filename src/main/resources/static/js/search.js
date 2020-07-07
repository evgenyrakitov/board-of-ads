
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



$("#location").click(function () {
    $.get("/rest/kladr/region/allRegions", null, function (data1) {
        let regions = [];
        let name = "";
        let option = "";
        let cities = [];
        let regionsId;
        let city = "";
        regions = data1;

        for (let i = 0; i < regions.length; i++) {
            regionsId = regions[i].id;
            name = regions[i].name;
            option += "<option id='regions"+regionsId+"'>"+name+"</option>";
            getAllRegionsCities(regionsId);
        }
        $("#location").append(option);

    });
});
function getAllRegionsCities(id){
    //let id_ = id;
   $.get("/rest/kladr/city/regionCities/", { id:id }, function (data2) {
       let cites = [];
       let city = "";
       let cityId = "";
       let option = "";
       cites = data2;
       for (let i = 0; i < cites.length; i++) {
           cityId = cites[i].id;
           city = cites[i].name;
           option += "<option id='cites"+id+"'>"+city+"</option>";

       }

       $("#location").append(option);
   });
}

//$(".container_cus").empty();

$("#btn-search").click(function () {
   // let search = $("#search-form").serialize();
   let category = $("#findFromCategory").val();
    let region = $("#location").val();
    let search = $("#search-input").val();
    let inlineCheckbox1 = $("#inlineCheckbox1").val();
    let inlineCheckbox2 = $("#inlineCheckbox2").val();

    $.get("/rest/posting/search", {category:category, region:region, search:search, inlineCheckbox1:inlineCheckbox1,
        inlineCheckbox2:inlineCheckbox2}, function (data) {
alert("ok");

    });

});