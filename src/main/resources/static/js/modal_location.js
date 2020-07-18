/* Глобальная переменная, нужна для таймера, запоминает последний искомый локейшен (город или регион) */
var LAST_SEARCH_LOCATION = "";

/* Код для работы крестика который очищает поле ввода локейшена - начало */
$('.position-relative :input').on('keydown focus', function () {
    if ($(this).val().length > 0) {
        $(this).nextAll('.form-clear').removeClass('d-none');
    }
}).on('keydown keyup blur', function () {
    if ($(this).val().length === 0) {
        $(this).nextAll('.form-clear').addClass('d-none');
    }
});
$('.form-clear').on('click', function () {
    $(this).addClass('d-none').prevAll(':input').val('');
    search_locations();
    $('#location-search').focus();
});
/* Код для работы крестика который очищает поле ввода локейшена - конец */

/* Таймер - вызывает поиск локации если поле ввода изменилось */
let location_update_timer = setInterval(function () {
    let location = $("#location-search").val();
    if (location !== LAST_SEARCH_LOCATION) {
        search_locations();
    }
}, 300);


$(".location-quick-item-clickable").click(event => location_quick_item_click(event));

$("#search-location-field").click(function () {
    $("#locationModal").modal('show');
});

$('#locationModal').on('shown.bs.modal', function () {
    $('#location-search').focus();
    $("#location-list").empty();
    search_locations();
})

$('#location-search').keypress(function (e) {
    let code = e.keyCode || e.which;

    if (code === 13) {
        e.preventDefault();
        let selectedItems = $(".location-suggest-active");
        if (selectedItems.length === 1) {
            selectedItems[0].dispatchEvent(new Event('click'));
        }
    }
});

/* Обрабатывает выделение локейшена из списка стрелками вверх / вниз */
$('#location-search').keydown(function (e) {
    let code = e.keyCode || e.which;

    if (code === 40) { // down arrow
        let items = $(".location-suggest-item");
        if (items.length > 0) {
            let selectedIndex = -1;

            for (let i = 0; i < items.length; i++) {
                if (items[i].classList.contains("location-suggest-active")) {
                    selectedIndex = i;
                    if (i != items.length - 1) {
                        items[selectedIndex].classList.remove("location-suggest-active");
                    }
                }
            }
            if ((selectedIndex - 1) < (items.length)) {
                items[selectedIndex + 1].classList.add("location-suggest-active");
                items[selectedIndex + 1].scrollIntoView({block: "nearest", behavior: "smooth", inline: "nearest"});
            }
        }
    }
    if (code === 38) { // up arrow
        let items = $(".location-suggest-item");
        if (items.length > 0) {
            let selectedIndex = -1;

            for (let i = 0; i < items.length; i++) {
                if (items[i].classList.contains("location-suggest-active")) {
                    selectedIndex = i;
                    items[selectedIndex].classList.remove("location-suggest-active");
                }
            }
            if ((selectedIndex - 1) >= 0) {
                items[selectedIndex - 1].classList.add("location-suggest-active");
                items[selectedIndex - 1].scrollIntoView({block: "nearest", behavior: "smooth", inline: "nearest"});
            }
        }
    }
});

/* Выполняет поиск локаций и заполняет список подсказок. Если введенный запрос короче 2х символов то просто очищает подсказки. */
const search_locations = function () {
    let location = $("#location-search").val();
    LAST_SEARCH_LOCATION = location;

    if (location.length >= 2) {
        $.ajax({
            url: `/rest/location/search/${location}`,
            type: 'get',
            dataType: 'json',
            success: function (results) {
                let html = [];
                results.forEach(result => {
                    html.push(`<a `);
                    html.push(`href="#" class="list-group-item list-group-item-action location-suggest-item" `);
                    html.push(`data-region-id="${result.regionId}" data-city-id="${result.cityId}" data-beauty-name="${result.shortName}" `);
                    html.push(`onclick="location_list_item_click(event)" `);
                    html.push(`>`);
                    html.push(`${result.beautyName}`);
                    html.push(`</a>`);
                })
                document.getElementById('location-list').innerHTML = html.join('');
            }
        })
    } else {
        document.getElementById('location-list').innerHTML = '';
    }
}

/* Обрабатывает нажатия на кнопки быстрого выбора региона, Вся Россиия, Регион и Город пользователя (если есть) */
const location_quick_item_click = function (event) {
    let regionId = event.currentTarget.getAttribute('data-region-id');
    let cityId = event.currentTarget.getAttribute('data-city-id');
    let LocationBeautyName = event.currentTarget.getAttribute('data-beauty-name');

    $("#search-location-field-text").text(LocationBeautyName);
    $("#locationInputCityId").val(cityId);
    $("#locationInputRegionId").val(regionId);

    $("#locationModal").modal('hide');

    document.getElementById("btn-search").dispatchEvent(new Event('click'));
}

/* Обрабатывает нажатия на элементы списка выбора локейшена */
const location_list_item_click = function (event) {
    let regionId = event.currentTarget.getAttribute('data-region-id');
    let cityId = event.currentTarget.getAttribute('data-city-id');
    let LocationBeautyName = event.currentTarget.getAttribute('data-beauty-name');

    $("#search-location-field-text").text(LocationBeautyName);
    $("#locationInputCityId").val(cityId);
    $("#locationInputRegionId").val(regionId);

    $("#locationModal").modal('hide');
    $("#location-search").val(LocationBeautyName);

    document.getElementById("btn-search").dispatchEvent(new Event('click'));
}
