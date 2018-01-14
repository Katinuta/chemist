<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>
        <c:import url="/css/footer.css"></c:import>
    </style>
    <script type="application/javascript">
        $(document).ready(function () {
            $.ajax({
                url: "/controllerAjax",
                type: "get",
                data: {
                    command: "releaseform"
                },
                success: function (response) {
                    var selectForms = $('.releaseform');
                    var selectProducer = $('.producer');
                    var selectDosageUnit = $('.unit_dosage');
                    var selectUnitsInPack = $('.unit_in_package');
                    var selectAnalog = $('.analog');
                    var forms = response.forms;
                    var producers = response.producers;
                    var dosageUnits = response.dosageUnits;
                    var unitsInPack = response.unitsInPack;
                    var analogs=response.analogs;

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
                        }else if("${medicine.dosage.unit}"==null){
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
                        }else if("${medicine.analog.medicineId}"==null){
                            $('option.default_analog').attr("selected", "selected");
                        }
                        selectAnalog.append(option);
                    });
                    $("input[name='name']").change(function () {
                        this.value=this.value.toUpperCase();
                    })
                }
            });
        });
    </script>
</head>
<body>
<header>
    <nav class="navbar ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/jsp/pharmacist/main.jsp"><fmt:message bundle="${bundle}"
                                                                                     key="ref.brand"/></a>
            </div>
            <ul class="nav navbar-nav">
                <c:url var="newmedicine" value="/controller">
                    <c:param name="command" value="newmedicine"/>
                </c:url>
                <li>
                    <a href="${newmedicine}"><fmt:message bundle="${bundle}" key="ref.newmedicine"/></a>
                </li>
                <li>
                    <a href="javascript:showStickySuccessToast();">Message</a>
                </li>

            </ul>

            <form class="navbar-form navbar-right" action="/controller">
                <input name="command" type="hidden" value="findmedicine"/>
                <div class="input-group">
                    <input type="text" name="name" class="form-control"
                           placeholder=<fmt:message bundle="${bundle}" key="placeholder.search"/>>
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <form action="/controller" method="get">
            <input type="hidden" name="command" value="createmedicine"/>
            <table class="table table-hover">
                <caption><fmt:message bundle="${bundle}" key="table.name.createmedicine"/></caption>
                ${error}
                <tr>
                    <th>
                        <fmt:message bundle="${bundle}" key="table.medicine.column.medicineid"/>
                    </th>
                    <td>
                        <input type="text" name="medicine_id" value="${medicine.medicineId}" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.name"/>
                        </label>
                    </th>
                    <td>
                        <input type="text" name="name" value="${medicine.name}" required />
                    </td>

                </tr>
                <tr>
                    <th>
                        <label class="required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.price"/>
                        </label>
                    </th>
                    <td>
                        <input type="number" min="0.01" step="0.01" name="price" value="${medicine.price}" required/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>
                        <label class="required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.balance"/>
                        </label>
                    </th>
                    <td>
                        <input type="number" min="1" step="1" name="quantity_packages"
                               value="${medicine.quantityPackages}" required  />
                    </td>

                </tr>
                <tr>
                    <th>
                        <label>
                            <fmt:message bundle="${bundle}" key="table.medicine.column.dosage"/>
                        </label>
                    </th>
                    <td>
                        <input type="number" min="0.01" step="0.01" name="dosage_size" value="${medicine.dosage.size}"/>
                        <select name="unit_dosage" class="unit_dosage">
                            <option class="default"></option>
                        </select>
                    </td>

                </tr>
                <tr>
                    <th>
                        <label class="required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.quantityinpack"/>
                        </label>
                    </th>
                    <td>
                        <input type="number" min="1" step="1" name="quantity_in_pack"
                               value="${medicine.quantityInPackage}" required/>
                        <select name="unit_in_package" class="unit_in_package" required ></select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>
                        <label>
                            <fmt:message bundle="${bundle}" key="table.medicine.column.prescription"/>
                        </label>
                    </th>
                    <td>
                        <c:choose>
                            <c:when test="${medicine.isNeedRecipe}">
                                <input type="checkbox" checked name="need_prescription" />
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="need_prescription" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>
                        <label class="required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.form"/>
                        </label>
                    </th>
                    <td >
                        <select name="release_form_id" class="releaseform" required ></select>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="required">
                            <fmt:message bundle="${bundle}" key="table.medicine.column.producer"/>
                        </label>
                    </th>
                    <td>
                        <select name="producer_id" class="producer" required ></select>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label >
                            <fmt:message bundle="${bundle}" key="table.medicine.column.analog"/>
                        </label>
                    </th>
                    <td>
                        <select name="analog_id" class="analog">
                            <option class="default_analog"></option>
                        </select>
                    </td>
                </tr>
            </table>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="<fmt:message bundle="${bundle}" key="button.submit"/>">
                </td>
            </tr>
        </form>
    </div>
</main>

</body>
<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</html>