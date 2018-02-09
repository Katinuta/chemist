$(document).ready(function () {
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
            $("input.end:focus  ~ span.end").text("Date end can not be less than date now")
        }
        else {
            $("input.end:focus ~ span.end").text("");
        }
    });
});