<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet"  type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>

    </style>
    <script type="application/javascript">

        $(document).ready(function(){

            $(".medicineForAdd").click(function () {
                var button=$(this);
                var medicineForAdd= button.val();

                $.ajax({url: "/controllerAjax",
                    type : "get",
                    data:{
                        medicineForAdd:medicineForAdd,
                        command:"addproduct"
                    },
                    success: function(response){
                        $('#cartvalue').text(response.size);

                        button.text("Product was added");

                      button.addClass("disabled");
                    }});
            })


        });
        $(window).on('load',function () {
            $.ajax({url: "/controllerAjax",
                    type : "get",
                    data:{

                        command:"getCartIdProducts"
                    },
                    success: function(response){
                        var idsArray=response.ids;
                        if(response.ids!=null){
                            idsArray.forEach(function(item, i, idsArray){
                                var buttons=$('button.medicineForAdd').toArray();
                                console.log(buttons);
                                buttons.forEach(function (button, i, buttons) {

                                    if(button.value==item){
                                        button.textContent="Product was added";
                                        button.classList.add("disabled");
                                    }
                                });
//
                            })
                        }
                    }}

            )
        });

    </script>
    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
</head>
<body>

    <nav class="navbar navbar-inverse ">
        <c:import url="navbar.jsp"/>
    </nav>

    <c:if test="${ not flagFind and empty prescriptions}">
            <c:if test="${  currentpage ==null}">
                <c:set var="currentpage" value="1"/>
            </c:if>
        <c:import url="/client">
            <c:param name="command" value="allmedicine"/>

            <c:param name="currentpage" value="${currentpage}"/>
        </c:import>
    </c:if>

    <c:if test="${flagFind}">
        <c:import url="medicine.jsp"/>
    </c:if>

    <c:if test="${not empty prescriptions }">
        <c:import url="prescription.jsp"/>
    </c:if>
    ${error}

    <footer class="container-fluid text-center">
        <c:import url="/jsp/common/footer.jsp"/>
    </footer>
</body>
</html>