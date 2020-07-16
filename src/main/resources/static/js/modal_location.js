$("#search-location-field").click(function () {
    $("#locationModal").modal('show');
});

$('#locationModal').on('shown.bs.modal', function () {
    $('#location-search').focus();
    $("#location-search").val("");
    $("#location-list").empty();
})

$('#location-search').keypress(function (e) {
    var code = e.keyCode || e.which;

    if (code === 13) {
        e.preventDefault();
        document.getElementById("location-close").dispatchEvent(new Event('click'));
    }
});

$("#location-search").keyup(function () {
    setTimeout(function () {
        let location = $("#location-search").val();
        $.ajax({
            url: `/rest/kladr/region?name=${location}`,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                let html = [];
                data.forEach(region => {
                    html.push(`<a id="region-${region.id}" href="#" class="list-group-item list-group-item-action"> ${region.name} ${region.shortType}</a>`);
                })
                document.getElementById('location-list').innerHTML = html.join('');

                $.ajax({
                    url: `/rest/kladr/city?name=${location}`,
                    type: 'get',
                    dataType: 'json',
                    success: function (data) {
                        data.forEach(city => {
                            $("#location-list").append(`<a id="city-${city.id}" href="#" 
                    class="location list-group-item list-group-item-action">
                    ${city.shortType} ${city.name} (${city.region.name} ${city.region.shortType})</a>`)
                        })
                    }
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
    let dataId = $("#location-search").attr("data");
    let locationName = $("#location-search").val();
    locationName = locationName.replace(/\([^()]*\)/g, '').trim();
    $("#locationInput").val(dataId);
    $("#search-location-field-text").text(locationName);
    $("#locationModal").modal('hide');
    document.getElementById("btn-search").dispatchEvent(new Event('click'));
});


