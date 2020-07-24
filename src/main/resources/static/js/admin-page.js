let users;
let categories;
const tr = $("<tr></tr>");
const td = $("<td></td>");
const select = $("<select disabled></select>").attr("name", "roleIds");
const option = $("<option></option>");
const basicCategoryContainer = $('.category-container').clone();

$(document).ready(function () {

    // Populating Users Tab
    $.ajax("/rest/admin/users",
        {
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (msg) {
                users = msg;
                users.forEach(function (el) {
                    addUserInTableBody(el);
                });
            }
        });

    // Populating Categories Tab
    $.ajax("/rest/categories/dto",
        {
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (data) {
                populateCategoriesTab(data)
                $(".category-container").hide();
                $("#category_0").children().show();
            }
        });
})

// Function that populates Users Table
function addUserInTableBody(el) {
    let trLocal = tr.clone();
    trLocal.attr("id", "tr" + el.id);
    // getTd(el.id, "id" + el.id).appendTo(trLocal);
    $("<th></th>").text(el.id).attr("id", el.id).appendTo(trLocal);
    getTd(el.email, "login" + el.id).appendTo(trLocal);
    getTd(el.firstName, "firstName" + el.id).appendTo(trLocal);
    getTd(el.lastName, "lastName" + el.id).appendTo(trLocal);
    getTd(el.password, "password" + el.id).appendTo(trLocal);
    getTd(el.phone, "phone" + el.id).appendTo(trLocal);
    getTd(el.dataRegistration, "dataRegistration" + el.id).appendTo(trLocal);
    getTd(el.region.name, "region"+el.id).appendTo(trLocal);
    getTd(el.city.name, "city"+el.id).appendTo(trLocal);
    getSelect(el).appendTo(trLocal);
    trLocal.appendTo($("#tbodyUsers"));
}

// Inner Function Used in addUserInTableBody(eo)
let getSelect = function (el) {
    let selectLocal = select.clone();
    selectLocal.attr("size", el.roles.length);
    selectLocal.attr("id", "select" + el.id);
    el.roles.forEach(function (role) {
        option.clone().text(role.name).appendTo(selectLocal);
    });
    let tdSelect = td.clone();
    selectLocal.appendTo(tdSelect);
    return tdSelect;
};

// Inner Function Used in addUserInTableBody(eo)
let getTd = function (val, valueOfName) {
    let tdLocal = td.clone();
    tdLocal.attr("id", valueOfName)
    return tdLocal.text(val);
}

//==================== Categories Tab Logic ====================//

function populateCategoriesTab(categories) {
    let newCategories = [];

    // Transform categories json into list of html elements
    categories.forEach((element) => {

        let categoryContainer = $('.category-container').clone();

        categoryContainer.find('.category-container-id').val(element.id);
        categoryContainer.find('.category-container-nameRu').val(
            element.nameRu);
        categoryContainer.find('.category-container-nameEn').val(
            element.nameEn);
        categoryContainer.find('.category-edit-btn').show();
        categoryContainer.find('.category-delete-btn').show();

        categoryContainer.attr("id", "category_" + element.id)
        categoryContainer.elementId = element.id;
        categoryContainer.parentId = element.parentId;

        newCategories.push(categoryContainer);
    })

    // Iterate through list items, appending them in hierarchical order
    while (newCategories.length !== 0) {
        let currentElement = newCategories.shift();
        console.log(currentElement.parentId)
        if (($("#category_" + currentElement.parentId))) {
            currentElement.appendTo($("#category_" + currentElement.parentId))
        } else {
            newCategories.push(currentElement);
        }
    }
}

// Collapsable list implementation
$(document).on('click', '.category-container', function (event) {
    event.stopPropagation();
    if (!$(event.target).closest('.category-container').hasClass("editing")) {
        $(event.target).closest('.category-container').children(
            '.category-container').slideToggle();
    }
});

// Edit Button Handler
$(document).on("click", ".category-edit-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    let categoryContainer = $(event.target).closest('.category-container');

    categoryContainer.addClass("editing");

    categoryContainer.find('.category-edit-btn').first().hide()
    categoryContainer.find('.category-add-btn').first().hide()
    categoryContainer.find('.category-delete-btn').first().hide()
    categoryContainer.find('.category-edit-save-btn').first().show()
    categoryContainer.find('.category-edit-cancel-btn').first().show()

    categoryContainer.find('.category-container-nameRu').first().removeAttr(
        "readonly");
    categoryContainer.find('.category-container-nameEn').first().removeAttr(
        "readonly");
}));

// Edit => Cancel Button Handler
$(document).on("click", ".category-edit-cancel-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    let categoryContainer = $(event.target).closest('.category-container');

    categoryContainer.removeClass("editing");

    categoryContainer.find('.category-edit-btn').first().show()
    categoryContainer.find('.category-add-btn').first().show()
    categoryContainer.find('.category-delete-btn').first().show()
    categoryContainer.find('.category-edit-save-btn').first().hide()
    categoryContainer.find('.category-edit-cancel-btn').first().hide()

    categoryContainer.find('.category-container-nameRu').first().attr(
        "readonly", true);
    categoryContainer.find('.category-container-nameEn').first().attr(
        "readonly", true);
}));

// Edit => Save Button Handler
$(document).on("click", ".category-edit-save-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    let id = $(event.target).closest('.category-container').find(
        '.category-container-id').first().val();
    let category = $(event.target).closest('.category-container').find(
        'form').first().serialize();

    $.ajax({
        url: "/rest/categories/" + id,
        type: 'PUT',
        data: category,
    })

    $(event.target).closest('.category-container').find(
        '.category-edit-cancel-btn').first().click();
}));

// Add Button Handler
$(document).on("click", ".category-add-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    let parentId = $(event.target).closest('.category-container').find(
        '.category-container-id').val();
    $(event.target).closest('.category-container').children(
        '.category-container').slideDown()

    let categoryContainer = basicCategoryContainer.clone();
    categoryContainer.addClass('editing');

    categoryContainer.find('.category-container-id').val("");
    categoryContainer.find('.category-container-nameRu').val("");
    categoryContainer.find('.category-container-nameRu').removeAttr(
        'readonly').removeClass('form-control-plaintext').addClass(
        'form-control')
    categoryContainer.find('.category-container-nameEn').val("");
    categoryContainer.find('.category-container-nameEn').removeAttr(
        'readonly').removeClass('form-control-plaintext').addClass(
        'form-control')
    categoryContainer.find('.category-edit-btn').hide();
    categoryContainer.find('.category-add-btn').hide();
    categoryContainer.find('.category-delete-btn').hide();
    categoryContainer.find('.category-add-save-btn').show();
    categoryContainer.find('.category-add-cancel-btn').show();


    let hiddenInput = $("<input type='text' class='category-parent-id' hidden/>");
    hiddenInput.val(parentId);
    hiddenInput.appendTo(categoryContainer.find('.category-list-form'));

    if (parentId !== "ID") {
        categoryContainer.appendTo(
            $(event.target).closest('.category-container'));
    } else {
        categoryContainer.appendTo($('#category_0'))
    }
}));

// Add Button => Cancel Button Handler
$(document).on("click", ".category-add-cancel-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    $(event.target).closest('.category-container').remove();

}));

// Add Button => Save Button Handler
$(document).on("click", ".category-add-save-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    let parentId = $(event.target).closest('.category-container').find(
        '.category-parent-id').first().val();
    let category = $(event.target).closest('.category-container').find(
        'form').first().serialize();
    let addedContainer = $(event.target).closest('.category-container')

    $.ajax({
        url: "/rest/categories/" + parentId,
        type: 'POST',
        data: category,
        success: function (data) {
            console.log(addedContainer)
            addedContainer.find('.category-container-id').val(data.id)
            addedContainer.removeClass('editing');
            console.log(addedContainer)
            addedContainer.find('.category-container-nameRu').attr('readonly',
                true)
            addedContainer.find('.category-container-nameEn').attr('readonly',
                true)
            addedContainer.find('.category-container-nameRu').addClass(
                'form-control-plaintext').removeClass('form-control')
            addedContainer.find('.category-container-nameEn').addClass(
                'form-control-plaintext').removeClass('form-control')
            addedContainer.find('.category-edit-btn').show();
            addedContainer.find('.category-add-btn').show();
            addedContainer.find('.category-delete-btn').show();
            addedContainer.find('.category-add-save-btn').hide();
            addedContainer.find('.category-add-cancel-btn').hide();
        }
    })
}));


// Delete Button Handler
$(document).on("click", ".category-delete-btn", (function (event) {
    event.preventDefault();
    event.stopPropagation();

    let id = $(event.target).closest('.category-container').find(
        '.category-container-id').first().val();

    $.ajax({
        url: "/rest/categories/" + id,
        type: 'DELETE',
    })

    $(event.target).closest('.category-container').remove();

}));

//==================== End of Categories Tab Logic ====================//
