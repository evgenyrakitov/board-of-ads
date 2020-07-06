$("#location").click(function () {
    $("#locationModal").modal('show');
});
$('#locationModal').on('shown.bs.modal', function () {
    $('#location-search').focus();
    $("#location-search").val("");
    $("#location-list").empty();
    $(".location").click(function () {
        let locationId = $(this).attr("id");
        let locationName = $(this).text();
        $("#location-search").val(locationName);
        $("#location-list").empty();
    })
})

$("#location-search").keyup(function () {
    setTimeout(function () {
        let location = $("#location-search").val();
        $.ajax({
            url: `/rest/kladr/region?name=${location}`,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $("#location-list").empty();
                data.forEach(region => {
                    $("#location-list").prepend(`<a id="${region.code}" href="#" 
                    class="list-group-item list-group-item-action">
                    ${region.name} ${region.shortType} </a>`)
                })
            }
        })
        $.ajax({
            url: `/rest/kladr/city?name=${location}`,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                data.forEach(city => {
                    $("#location-list").append(`<a id="${city.code}" href="#" 
                    class="location list-group-item list-group-item-action">
                    ${city.shortType} ${city.name} (${city.region.name} ${city.region.shortType})</a>`)
                })
            }
        })
    }, 200)

})

$("#location-list").click(function (event) {
    let id = event.target.id;
    let name = event.target.text;
    name = name.replace(/\s+/g, ' ').trim();
    $("#location-search").attr('data', id);
    $("#location-search").val(name);
    $("#location-list").empty();

})

$("#location-close").click(function () {
    let locationCode = $("#location-search").attr("data");
    if (typeof locationCode !== typeof undefined && locationCode !== false) {
        $.ajax({
            url: `/rest/posting/all/${locationCode}`,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $("#locationModal").modal('hide');
                $(".container_cus").empty();
                data.forEach(posting => {
                    $(".container_cus").prepend(
                        '<div class="card">\n' +
                        '            <img src="' + posting.imagePath[0].imagePath + '" class="card-img-top" alt="...">\n' +
                        '            <div class="card-body">\n' +
                        '                <h5 class="card-title">' + posting.title + '</h5>\n' +
                        '                <p class="card-text">' + posting.price + '</p>\n' +
                        '                <a href="adDetails" class="btn btn-primary" ' +
                        'th:text="#{main_page.go_to_ad}">Перейти к объявлению</a>\n' +
                        '            </div>\n' +
                        '        </div>'
                    );
                })
            }
        });
    } else {
        $("#locationModal").modal('hide');
    }
})

