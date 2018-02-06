<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        <c:import url="/css/main.css"/>
        <c:import url="/css/prescripition_new.css"/>
    </style>

    <script type="application/javascript">
        $(document).ready(function () {
            $.ajax({
                url: "/ajax",
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
                    $("span.end").text("<fmt:message bundle="${bundle}" key="message.date.wrong"/>");
                }
                else {
                    $("span.end").text("");
                }
            });
        });
    </script>
    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2><fmt:message bundle="${bundle}" key="table.name.new.prescription"/></h2>

            <div class="container-fluid form shadow">
                <form action="/doctor" method="post">
                    <input type="hidden" name="command" value="create_prescription"/>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label for="client" class="col-md-2 col-form-label">
                            <fmt:message bundle="${bundle}" key="table.prescription.column.client"/>
                        </label>
                        <div class="col-md-4">
                            <input type="text" value="${client.name} ${client.surname}" readonly id="client"/>
                            <input type="hidden" value="${client.userId}" name="client_id">
                        </div>
                        <div class="col-md-2">
                            <span class="error">${error_client_id}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label for="date_begin" class="col-md-2 col-form-label">
                            <fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/>
                        </label>
                        <div class="col-md-4">
                            <input type="date" data-date="" name="date_begin" id="date_begin" class="begin"
                                   data-date-format="DD MMMM YYYY" value="${date_begin}" readonly/>
                        </div>
                        <div class="col-md-2">
                            <span class="error">${error_date_begin}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-1"></div>
                        <label for="date_end" class="col-md-2 col-form-label required">
                            <fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/>
                        </label>
                        <div class="col-md-3">
                            <input type="date" data-date="" name="date_end" class="end form-control"
                                   data-date-format="DD MMMM YYYY"
                                   value="${date_end}" id="date_end"/>
                            <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.date"/></span>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-4">
                            <span class="end error"></span>
                            <span class="error">${error_date_end}</span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="form-group col-md-1"></div>
                        <div class="form-group col-md-6">
                            <label for="medicine" class="required">
                                <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                            </label>
                            <select name="medicine_id" class="medicines custom-select  form-control" required
                                    id="medicine"></select>
                            <span class="helper"><fmt:message bundle="${bundle}"
                                                              key="page.message.helper.medicine"/></span>
                            <span class="error">${error_medicine_id}</span>

                        </div>
                        <div class=" form-group col-md-3">
                            <label for="medicine" class="required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/>
                            </label>

                            <input type="number" class="end form-control" min="1" step="1" name="quantity"
                                   value="${quantity}" required/>
                            <span class="helper"><fmt:message bundle="${bundle}"
                                                              key="page.message.helper.quantity"/></span>
                            <span class="error">${error_quantity}</span>

                        </div>
                    </div>

                    <div class="form-row">
                        <div class=" form-group col-md-8"></div>
                        <div class=" form-group col-md-2">
                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${bundle}"
                                                                                       key="button.submit"/></button>
                        </div>
                    </div>
                    <div class=" form-group col-md-2"></div>

                </form>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
    <div class="flex-container">
        <button class="button">
            <a href="/jsp/doctor/main.jsp"><fmt:message bundle="${bundle}" key="ref.prescription"/></a>
        </button>
    </div>


</div>


<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>


</body>
</html>