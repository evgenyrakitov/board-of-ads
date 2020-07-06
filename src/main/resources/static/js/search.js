$("#findFromCategory").click(function () {
    //event.preventDefault();
    $.get("/rest/categories", null, function (data) {
        let parentCategory = [];
        let parentCategoryId = "";
        let category = [];
        category = data;
        let id = "";
        let nameRu = "";
        let option = "";

        for (let i = 0; i < category.length; i++) {
            id = category[i].id;
            nameRu = category[i].nameRu;
            parentCategory = category[i].parentCategory;
            if (parentCategory==null) continue;
            option += "<option id='category"+id+"'>"+nameRu+"</option>";
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