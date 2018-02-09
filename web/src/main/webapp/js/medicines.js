$(document).ready(function () {
    $(".medicineForAdd").click(function () {
        var button = $(this);
        var medicine_id = button.val();
        $.ajax({
            url: "/ajax",
            type: "get",
            data: {
                medicine_id: medicine_id,
                command: "add_product_to_cart"
            },
            success: function (response) {
                var response = $.parseJSON(response);
                $('#cartvalue').text(response.size);
                button.text("Product was added");
                button.addClass("disabled");
            }
        });
    })

});
$(window).on('load', function () {
    $.ajax({
            url: "/ajax",
            type: "get",
            data: {
                command: "get_cart_products_id"
            },
            success: function (response) {
                var response = $.parseJSON(response);
                var idsArray = response.ids;
                if (response.ids != null) {
                    idsArray.forEach(function (item, i, idsArray) {
                        var buttons = $('button.medicineForAdd').toArray();
                        buttons.forEach(function (button, i, buttons) {
                            if (button.value == item) {
                                button.textContent = "Product was added";
                                button.classList.add("disabled");
                            }
                        });
                    })
                }
            }
        }
    )
});