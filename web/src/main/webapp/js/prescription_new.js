$(document).ready(function () {
    $.ajax({
        url: "/chemist/ajax",
        type: "get",
        data: {
            command: "show_medicines_by_prescrip"
        },
        success: function (response) {
            var response = $.parseJSON(response);
            var selectMedicines = $('.medicines');
            var medicines = response.medicines;

            $.each(medicines, function (index, medicine) {
                var option = $('<option></option>');
                option.attr('value', medicine.medicineId);
                option.text(medicine.name);
                if (medicine.medicineId == "${medicineId}") {
                    option.attr("selected", "selected");
                }
                selectMedicines.append(option);
            });

        },
        error:function(){
            $('#ajax_error').text("Error finding medicines by prescriptions");

        }
    });
    $("#date_end").change(function () {
        var dateEnd = $(this).val();
        var dateBegin = $(".begin").val();
        var now = new Date();
        var day = now.getDate();
        if (day < 10) day = '0' + day;
        var month = now.getMonth() + 1;
        if (month < 10) month = '0' + month;
        var dateNow = now.getFullYear() + "-" + month + "-" + day;

        if (dateEnd < dateNow) {
            $("span.end").text("Date end  must not be before now");
        }
        else {
            $("span.end").text("");
        }
    });
});