$(document).ready(function () {
    $.ajax({
        url: "/ajax",
        type: "get",
        data: {
            command: "find_medicine_details"
        },
        success: function (response) {
            var response=$.parseJSON(response);
            var selectForms = $('.releaseform');
            var selectProducer = $('.producer');
            var selectDosageUnit = $('.unit_dosage');
            var selectUnitsInPack = $('.unit_in_package');
            var selectAnalog = $('.analog');
            var forms = response.forms;
            var producers = response.producers;
            var dosageUnits = response.dosageUnits;
            var unitsInPack = response.unitsInPack;
            var analogs = response.analogs;

            $.each(forms, function (index, releaseform) {
                var option = $('<option></option>');
                option.attr('value', releaseform.releaseFormId);
                option.text(releaseform.name);

                if ("${release_form_id}" == releaseform.releaseFormId) {
                    option.attr("selected", "selected");
                }
                selectForms.append(option);
            });

            $.each(producers, function (index, producer) {
                var option = $('<option></option>');
                option.attr('value', producer.producerId);
                option.text(producer.name);

                if ("${producer_id}" == producer.producerId) {
                    option.attr("selected", "selected");
                }
                selectProducer.append(option);
            });

            $.each(dosageUnits, function (index, dosageUnit) {
                var option = $('<option></option>');
                option.attr('value', dosageUnit);
                option.text(dosageUnit);

                if ("${dosage_unit}" == dosageUnit) {
                    option.attr("selected", "selected");
                } else if ("${dosage_unit}" == null) {
                    $('option.default').attr("selected", "selected");
                }
                selectDosageUnit.append(option);
            });

            $.each(unitsInPack, function (index, unit) {
                var option = $('<option></option>');
                option.attr('value', unit);
                option.text(unit);

                if ("${unit_in_package}" == unit) {
                    option.attr("selected", "selected");
                }
                selectUnitsInPack.append(option);
            });

            $.each(analogs, function (index, analog) {
                var option = $('<option></option>');
                option.attr('value', analog);
                option.text(analog);

                if ("${analog_medicine_id}" == analog) {
                    option.attr("selected", "selected");
                } else if ("${analog_medicine_id}" == null) {
                    $('option.default_analog').attr("selected", "selected");
                }
                selectAnalog.append(option);
            });
            $("input[name='medicine_name']").change(function () {

                this.value = this.value.toUpperCase();
            })
        },
        error:function(){
            $('#ajax_error').text("Error getting medicine details information");

        }
    });
});