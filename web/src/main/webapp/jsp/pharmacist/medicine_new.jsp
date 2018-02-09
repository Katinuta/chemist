<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="application/javascript" src="/js/medicine_new.js"></script>
        <style>
            <c:import url="/css/main.css"/>
            <c:import url="/css/medicine_new.css"/>
        </style>
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
                <h2><fmt:message bundle="${bundle}" key="table.name.createmedicine"/></h2>
                <div class="container-fluid form shadow">
                    <span class="error" id="ajax_error">${error}</span>
                    <form action="/pharmacist" method="post">
                        <input type="hidden" name="command" value="add_medicine"/>
                        <div class="form-group row">
                            <div class="col-md-1"></div>
                            <label for="medicine_name" class="col-md-3 col-form-label required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.name"/>
                            </label>
                            <div class="col-md-5">
                                    <input type="text" name="medicine_name" value="${fn:escapeXml(medicine_name)}" id="medicine_name" class="form-control"
                                           required
                                           pattern="(^[A-Za-z]{2,}\\-?\s?[A-Za-z]*\s*$)|(^[А-Яа-яЁё]{2,}\\-?\s?[А-Яа-яЁё]*\s*$)"        />
                                    <span class="helper"> <fmt:message bundle="${bundle}"
                                                                       key="page.message.helper.name"/></span>
                            </div>
                            <div class="col-md-3">
                                <span class="error">${error_medicine_name}</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-1"></div>
                            <label for="price" class="col-md-3 col-form-label required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.price"/>
                            </label>
                            <div class="col-md-3">
                                    <input id="price" class="form-control"
                                            type="number" min="0.01" step="0.01"
                                            name="price" value="${price}"
                                            required
                                    />
                                    <span class="helper"> <fmt:message bundle="${bundle}"
                                                                       key="page.message.helper.price"/></span>
                            </div>
                            <div class="col-md-3">
                                <span class="error">${error_price}</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-1"></div>
                            <label for="quantity_packages" class="col-md-3 col-form-label required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/>
                            </label>
                            <div class="col-md-3">
                                    <input id="quantity_packages" class="form-control"
                                            type="number" min="1" step="1"
                                            name="quantity_packages"
                                            value="${quantity_packages}"
                                            required
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
                            <label  class="col-md-3 col-form-label">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.dosage"/>
                            </label>
                            <div class="col-md-4">
                                <div class="col-md-8 group-input">
                                        <input type="number" min="0.01" step="0.01" name="dosage_size" value="${dosage_size}"
                                               class="form-control"/>
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
                            <label for="quantity_in_package" class="col-md-3 col-form-label required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.quantityinpack"/>
                            </label>
                            <div class="col-md-4">
                                <div class="col-md-7 group-input">
                                    <input type="number" min="1" step="1" name="quantity_in_package" id="quantity_in_package"
                                           value="${quantity_in_package}"class="form-control" required/>
                                    <span class="helper"> <fmt:message bundle="${bundle}"
                                                                       key="page.message.helper.quantity"/></span>
                                </div>
                                <div class="col-md-5 group-input">
                                    <select name="unit_in_package" class="unit_in_package custom-select form-control" required></select>
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
                            <label  class="col-md-3 col-form-label ">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.prescription"/>
                            </label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <c:choose>
                                        <c:when test="${need_prescription==true}">
                                            <input class="form-check-input" type="checkbox" checked name="need_prescription"/>
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
                            <label  class="col-md-3 col-form-label required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.form"/>
                            </label>
                            <div class="col-md-5">
                                    <select name="release_form_id" class="releaseform custom-select form-control" required></select>
                                    <span class="helper"> <fmt:message bundle="${bundle}"
                                                                       key="page.message.helper.release.form"/></span>
                            </div>
                            <div class="col-md-3">
                                <span class="error">${error_release_form_id}</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-1"></div>
                            <label  class="col-md-3 col-form-label required">
                                <fmt:message bundle="${bundle}" key="table.medicine.column.producer"/>
                            </label>
                            <div class="col-md-5">
                                    <select name="producer_id" class="producer custom-select form-control" required></select>
                                    <span class="helper"> <fmt:message bundle="${bundle}"
                                                                       key="page.message.helper.producer"/></span>
                            </div>
                            <div class="col-md-2">
                                <span class="error">${error_producer_id}</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-1"></div>
                            <label  class="col-md-3 col-form-label">
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
                        <div class="flex-container">
                            <button>
                                <a href="javascript:history.back()"><fmt:message bundle="${bundle}" key="button.cancel"/></a>
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