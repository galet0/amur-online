(function () {
    loadCategories();

})();

function loadCategories() {
    $.ajax({
        type: 'GET',
        url: '/categories',
        data: 'json',
        success: function (categories) {
            useCategories(categories);
        }
    });

    function useCategories(categories) {
        $('#categories').empty();

        $.each(categories, function (i, category) {
            let currentCategory = category.name;
            let categoryUrl = category.url;
            $('#categories').append(
                $('<li></li>').addClass('nav-item dropdown')
                    .append(
                        $('<a></a>').addClass('nav-link')
                            //.attr('data-toggle', 'dropdown')
                            .attr('aria-haspopup', 'true')
                            .attr('href', '/categories/' + category.id + '/' + currentCategory)
                            .attr('aria-expanded', 'false')
                            .text(currentCategory)
                    ).append(
                    $('<div></div>').addClass('dropdown-menu')
                        .attr('aria-labelledby',"navbarDropdownMenuLink1")
                        .append(
                            $('<ul></ul>').attr('id', category.id)
                        )
                )
            )
            createSubCategories(category);
        });
    }
}

function createSubCategories(subCategories) {
    $('#'+ subCategories.id).empty();
    $.each(subCategories.subcategories, function (i, subCategory) {
        let currentCategories = subCategory.name;
        let subcategoryUrl = subCategory.url;
        $('#'+ subCategories.id).append(
            $('<li></li>').addClass('dropdown-item dropdown')
                .attr('id', currentCategories).append(
                $('<a></a>').addClass('dropdown-item')
                    .attr('href', '/subcategories/' + subCategory.id + '/' + currentCategories)
                    .text(currentCategories)
            )
        )

    });
}
    