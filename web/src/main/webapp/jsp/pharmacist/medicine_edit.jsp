<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <c:import url="/css/main.css"/>
        <c:import url="/css/medicine_edit.css"></c:import>
    </style>
    <script type="application/javascript" >
        $(document).ready(function () {
            $.ajax({
                url: "/chemist/ajax",
                type: "get",
                data: {
                    command: "find_medicine_details"
                },
                success: function (response) {
                    var response = $.parseJSON(response);
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

                        if ("${medicine.releaseForm.name}" == releaseform.name) {
                            option.attr("selected", "selected");
                        }
                        selectForms.append(option);
                    });

                    $.each(producers, function (index, producer) {
                        var option = $('<option></option>');
                        option.attr('value', producer.producerId);
                        option.text(producer.name);
                        if ("${medicine.producer.name}" == producer.name) {
                            option.attr("selected", "selected");
                        }
                        selectProducer.append(option);
                    });

                    $.each(dosageUnits, function (index, dosageUnit) {
                        var option = $('<option></option>');
                        option.attr('value', dosageUnit);
                        option.text(dosageUnit);
                        if ("${medicine.dosage.unit}" == dosageUnit) {
                            option.attr("selected", "selected");
                        } else if ("${medicine.dosage.unit}" == null) {
                            $('option.default').attr("selected", "selected");
                        }
                        selectDosageUnit.append(option);
                    });

                    $.each(unitsInPack, function (index, unit) {
                        var option = $('<option></option>');
                        option.attr('value', unit);
                        option.text(unit);

                        if ("${fn:toLowerCase(medicine.unitInPackage)}" == unit) {
                            option.attr("selected", "selected");
                        }
                        selectUnitsInPack.append(option);
                    });

                    $.each(analogs, function (index, analog) {
                        var option = $('<option></option>');
                        option.attr('value', analog);
                        option.text(analog);

                        if ("${medicine.analog.medicineId}" == analog) {
                            option.attr("selected", "selected");
                        } else if ("${medicine.analog.medicineId}" == null) {
                            $('option.default_analog').attr("selected", "selected");
                        }
                        selectAnalog.append(option);
                    });
                    $("input[name='medicine_name']").change(function () {

                        this.value = this.value.toUpperCase();
                    })
                },
                error: function () {
                    $('#ajax_error').text("Error getting medicine details information");
                }

            });
        });
    </script>
</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2><fmt:message bundle="${bundle}" key="table.name.medicine.edit"/></h2>
            <span class="error" id="ajax_error">${error}</span>
            <div class="container-fluid form form-edit shadow">
                <form action="/chemist/pharmacist" method="post">
                    <input type="hidden" name="command" value="update_medicine"/>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.medicineid"/>
                        </label>
                        <div class="col-md-3">
                            <input type="text" name="medicine_id" value="${medicine.medicineId}" class="form-control"
                                   readonly pattern="[1-9]+\d*" required/>
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.quantity"/></span>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_medicine_id}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.name"/>
                        </label>
                        <div class="col-md-5">
                            <input type="text" name="medicine_name" value="${medicine.name}" class="form-control"
                                   required
                                   pattern="(^[A-Za-z]{2,}\s?[A-Za-z]*\s*$)|(^[А-Яа-яЁё]{2,}\s?[А-Яа-яЁё]*\s*$)"
                            />
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.name"/></span>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_medicine_name}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.price"/>
                        </label>
                        <div class="col-md-3">
                            <input class="form-control"
                                   type="number" min="0.01" step="0.01"
                                   name="price" value="${medicine.price}" required/>
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.name"/></span>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_price}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.balance"/>
                        </label>
                        <div class="col-md-3">
                            <input name="quantity_packages" value="${medicine.quantityPackages}" class="form-control"
                                   type="number" min="1" step="1" required
                            />
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.quantity"/></span>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_quantity_packages}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.dosage"/>
                        </label>
                        <div class="col-md-4">
                            <div class="col-md-8 group-input">
                                <input name="dosage_size" value="${medicine.dosage.size}" class="form-control"
                                       type="number" min="0.01" step="0.01"
                                />
                                <span class="helper"> <fmt:message bundle="${bundle}"
                                                                   key="page.message.helper.dosage.size"/></span>
                            </div>
                            <div class="col-md-4 group-input">
                                <select name="dosage_unit" class="unit_dosage custom-select form-control">
                                    <option class="default"></option>
                                </select>
                                <span class="helper"> <fmt:message bundle="${bundle}"
                                                                   key="page.message.helper.unit"/></span>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <span class="helper error">${error_dosage_size}</span>
                            <span class="helper error">${error_dosage_unit}</span>
                            <span class="error">${error_dosage}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.quantityinpack"/>
                        </label>
                        <div class="col-md-4">
                            <div class="col-md-7 group-input">
                                <input name="quantity_in_package" value="${medicine.quantityInPackage}"
                                       class="form-control"
                                       type="number" min="1" step="1" required
                                />
                                <span class="helper"> <fmt:message bundle="${bundle}"
                                                                   key="page.message.helper.quantity"/></span>
                            </div>
                            <div class="col-md-5 group-input">
                                <select name="unit_in_package" class="unit_in_package custom-select form-control"
                                        required></select>
                                <span class="helper"> <fmt:message bundle="${bundle}"
                                                                   key="page.message.helper.unit"/></span>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <span class="helper error">${error_quantity_in_package}</span>
                            <span class="error">${error_unit_in_package}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label ">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.prescription"/>
                        </label>
                        <div class="col-md-4">
                            <div class="form-check">
                                <c:choose>
                                    <c:when test="${medicine.isNeedRecipe}">
                                        <input class="form-check-input" type="checkbox" checked
                                               name="need_prescription"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="checkbox" name="need_prescription"/>
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label">
                                    <fmt:message bundle="${bundle}" key="page.message.helper.prescrip"/>
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_need_prescription}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.form"/>
                        </label>
                        <div class="col-md-5">
                            <select name="release_form_id" class="releaseform custom-select form-control"
                                    required></select>
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.release.form"/></span>
                        </div>
                        <div class="col-md-2">
                            <span class="error">${error_release_form_id}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.producer"/>
                        </label>
                        <div class="col-md-5">
                            <select name="producer_id" class="producer custom-select form-control" required></select>
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.producer"/></span>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_producer_id}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 col-form-label">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.analog"/>
                        </label>
                        <div class="col-md-5">
                            <select name="analog_medicine_id" class="analog custom-select form-control">
                                <option class="default_analog"></option>
                            </select>
                            <span class="helper"> <fmt:message bundle="${bundle}"
                                                               key="page.message.helper.analog"/></span>
                        </div>
                        <div class="col-md-3">
                            <span class="error">${error_analog_medicine_id}</span>
                        </div>
                    </div>
                    <div class="flex-container flex-container-edit">
                        <button>
                            <a href="javascript:history.back()"><fmt:message bundle="${bundle}"
                                                                             key="button.cancel"/></a>
                        </button>
                        <input type="submit" value="<fmt:message bundle="${bundle}" key="button.save"/>">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>