$(document).ready(function () {
    var inputPassRep = document.getElementById('password_rep');
    $('#password').change(function () {
        var password = $(this).val();
        var passwordConfirm = $('#password_rep').val();
        if (password != passwordConfirm) {
            var spanError = $('#l_password + span.confirm');
            spanError.text("Passwords do not match!");
            inputPassRep.setCustomValidity("Passwords must match");
        } else {
            $('#l_password + span.confirm').text("");
            inputPassRep.setCustomValidity('');
        }
    });
    $('#password_rep').change(function () {
        var passwordConfirm = $(this).val();

        var password = $('#password').val();
        if (password != passwordConfirm) {
            var spanError = $('#l_password + span.confirm');

            spanError.text("Passwords do not match!");
            inputPassRep.setCustomValidity("Passwords must match");
        } else {
            $('#l_password + span.confirm').text("");
            inputPassRep.setCustomValidity('');
        }
    });


});