$(document).ready(function () {
        calculateSumTotal();
    }
);

function calculateSumTotal() {
    var sum = 0;
    $('.sum_product').each(function () {
        sum += parseFloat($(this).text());
    });
    $('.sum_total').text(sum.toFixed(2));
    if (sum == 0) {
        $('input[type=submit]').attr('disabled', 'disabled');
    }
    return sum;
}

function calculateSumProduct(id, amount) {
    var product = $('#row_' + id);
    var price = parseFloat(product.find('.price_product').text());
    product.find('.sum_product').text((price * amount).toFixed(2));
}

$(window).on('load', function () {

    $('.plus').click(function () {
            var medicine_id = $(this).val();
            var amount = parseInt($('#' + medicine_id).val());
            amount++;
            $.ajax({
                url: "/chemist/ajax",
                type: "get",
                data: {
                    medicine_id: medicine_id,
                    command: "increase_product_amount_cart",
                    amount: amount
                },
                success: function (response) {
                    var response = $.parseJSON(response);
                    var message = response.message;
                    amount = parseInt(response.amount);
                    if (message === undefined) {
                        $('#cartvalue').text(response.size);
                        $('#' + medicine_id).val(amount);
                        calculateSumProduct(medicine_id, amount);
                        calculateSumTotal();
                        $('.messagecount_' + medicine_id).text();
                    } else {
                        $('#cartvalue').text(response.size);
                        $('.messagecount_' + medicine_id).text(message);
                        $('#' + medicine_id).val(response.amount);
                        calculateSumProduct(medicine_id, amount);
                        calculateSumTotal();
                        if (amount == 0) {
                            $('#quantity_' + medicine_id + '>input').text('<fmt:message bundle="${bundle}" key="label.archive"/>');
                        }
                    }
                },
                error: function () {
                    $('#ajax_error').text("Error medicine increase count");

                }
            });
        }
    );
    $('.minus').click(
        function () {
            var medicine_id = $(this).val();
            var amount = parseInt($('#' + medicine_id).val());
            if (amount > 1) {
                amount--;

            }
            $.ajax({
                url: "/chemist/ajax",
                type: "get",
                data: {
                    medicine_id: medicine_id,
                    command: "reduce_product_amount_cart",
                    amount: amount
                },
                success: function (response) {
                    var response = $.parseJSON(response);
                    amount = parseInt(response.amount);
                    var message = response.message;
                    if (message === undefined) {
                        $('.messagecount_' + medicine_id).text("");
                    } else {
                        $('.messagecount_' + medicine_id).text(message);
                    }
                    $('#' + medicine_id).val(amount);

                    calculateSumProduct(medicine_id, amount);
                    calculateSumTotal();
                    $('#cartvalue').text(response.size);
                },
                error: function () {
                    $('#ajax_error').text("Error medicine reducing count");

                }
            })

        }
    );
    $('.close').click(
        function () {
            var button = $(this);
            var medicine_id = button.val();
            $.ajax(
                {
                    url: "/chemist/ajax",
                    type: "get",
                    data: {
                        medicine_id: medicine_id,
                        command: "delete_product_cart"
                    },
                    success: function (response) {
                        var response = $.parseJSON(response);
                        button.parent().parent().remove();
                        $('#cartvalue').text(response.size);
                        if (response.size != 0) {
                            calculateSumTotal();
                        } else {
                            $('.detail').remove();
                            $('#empty').text('Your cart is empty');
                            $('#suggest').text('Please add medicines to cart.');
                        }

                    },

                    error: function () {
                        $('#ajax_error').text("Error medicine deleting count");

                    }
                }
            );
        }
    );
});