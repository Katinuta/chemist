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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <style>
        <c:import url="/css/main.css"/>
        .button {
            margin-top: 7%;

        }

        button {
            background-color: #00cc99;
            color: white;
            font-size: 16px;
            border: none;
            padding: 10px 10px;
            border-radius: 5px;
        }

        .flex-container {
            display: flex;
            justify-content: center;

        }

        .table > tbody > tr.top > td,
        .table > tbody > tr.top > th {
            border-top: none;
        }

        a:hover {
            text-decoration: none;
            color: white;
            background-color: #009973;

        }

        a {
            color: white;
        }

        input:not(.end) {
            background-color: #f2f2f2;
            border: none;
        }
    </style>
    <script type="application/javascript">
        $(document).ready(function () {
            $.ajax({
                url: "/controllerAjax",
                type: "get",
                data: {
                    command: "medicinesbyprescrip"
                },
                success: function (response) {
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
            $("input.end").change(function () {
                var dateEnd = $(this).val();
                var dateBegin = $(".begin").val();
                var now = new Date();
                var day = now.getDate();
                if (day < 10) day = '0' + day;
                var month = now.getMonth() + 1;
                if (month < 10) month = '0' + month;
                var dateNow = now.getFullYear() + "-" + month + "-" + day;

                if (dateEnd < dateNow) {
                    $("input.end:focus  ~ span.end").text("<fmt:message bundle="${bundle}" key="message.date.wrong"/>");
                }
                else {
                    $("input.end:focus ~ span.end").text("");
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

    <h2><fmt:message bundle="${bundle}" key="table.name.new.prescription"/></h2>
    ${error}
    <div class="container-fluid form">
        <form action="/doctor" method="get">
            <input type="hidden" name="command" value="createprescription"/>

            <table class="table">
                <tr class="top">
                    <th><fmt:message bundle="${bundle}" key="table.prescription.column.client"/></th>
                    <td>
                        <input type="text" value="${client.name} ${client.surname}" readonly/>
                        <input type="hidden" value="${client.userId}" name="clientId">
                        <span class="error">${error_clientId}</span>
                    </td>

                </tr>
                <tr class="top">
                    <th><fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/></th>
                    <td>
                        <input type="date" data-date="" name="dateBegin" class="begin" data-date-format="DD MMMM YYYY"
                               value="${dateBegin}"
                               readonly/>
                        <span class="error">${error_dateBegin}</span>
                    </td>
                </tr>
                <tr class="top">
                    <th><fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/></th>
                    <td>
                        <input type="date" data-date="" name="dateEnd" class="end" data-date-format="DD MMMM YYYY"
                               value="${dateEnd}"/>
                        <span class="end error"></span>
                        <span class="error">${error_dateEnd}</span>
                    </td>
                </tr>
                <tr>

                    <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                    <th><fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/></th>
                </tr>
                <tr>
                    <td><select name="medicineId" class="medicines" required></select>
                        <span class="error">${error_medicineId}</span>
                    </td>
                    <td>
                        <input type="number" class="end" min="1" step="1" name="quantity" value="${quantity}" required/>
                        <span class="error">${error_quantity}</span>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.quantity"/></span>
                    </td>
                </tr>
            </table>
            <div class="flex-container">
                <input type="submit" class="end" value="<fmt:message bundle="${bundle}" key="button.submit"/>">
            </div>
        </form>
    </div>


</div>


<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>


</body>
</html>