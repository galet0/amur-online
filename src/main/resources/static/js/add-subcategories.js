$(function () {
    hideAddSubcategoryInput();
    $('#btnCancel').click(hideAddSubcategoryInput);
    $('#addSubcategoryButton').click(showAddSubcategoryInput);
})();

function loadCurrentCategoryWithSubcategories(category) {

}

function hideAddSubcategoryInput() {
    $('#addSubcategoryInput').hide();
}

function showAddSubcategoryInput() {
    $('#addSubcategoryInput').show();
}

function saveSubcategory() {
    var subcategory = {};
    subcategory.name = $('#newSubcategoryName').val();
    subcategory.categoryName = $('#categoryName').val();
    $.ajax({
        type: 'POST',
        url: '/subcategories',
        data:subcategory,
        dataType: 'json',
        contentType: 'application/json',
        success: function (subcategory) {
            addSubcategoryDOM(subcategory)
        }
    })
}

function addSubcategoryDOM(subcategory) {
    let id = subcategory.id;
    let name = subcategory.name;
    $('#subcategoriesList').append(
        $('<tr></tr>')
            .attr('id', id)
            .append(
                $('<th></th>')
                    .attr('scope','row'))
            .append(
                $('<td></td>')
                    .text(name))
    )
}