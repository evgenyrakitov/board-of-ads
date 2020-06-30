$("#location").click(function(){
    $("#locationModal").modal('show');
});
$('#locationModal').on('shown.bs.modal', function () {
    $('#location-search').focus();
})

$("#location-search").keypress(function () {
    setTimeout(function () {
        let location = $("#location-search").val();
        $.ajax({
            url: `/rest/kladr/region?name=${location}`,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $("#location-list").empty();
                data.forEach(region => {
                    $("#location-list").append(`<a id="${region.id}" href="#" 
                    class="list-group-item list-group-item-action list-group-item-light">
                    ${region.name} ${region.shortType}</a>`)
                })
            }
        })
        $.ajax({
            url: `/rest/kladr/city?name=${location}`,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                data.forEach(city => {
                    $("#location-list").append(`<a id="${city.id}" href="#" 
                    class="list-group-item list-group-item-action list-group-item-light">
                    ${city.name} ${city.shortType}</a>`)
                })
            }
        })
    },200)

})