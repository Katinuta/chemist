$(document).ready(function () {
    var inputPassRep=document.getElementById('new_password_rep');
    function validatePasswords() {
        var newPassword=$("#new_password").val();
        var newPasswordRepeat=$('#new_password_rep').val();
        if(newPassword!=newPasswordRepeat){
            var spanError=$('#l_new_password + span.error');
            spanError.text("Passwords do not match!");
            inputPassRep.setCustomValidity("Passwords must match");
        }else{
            $('#l_new_password + span.error').text("");
            inputPassRep.setCustomValidity('');
        }
    }

    $('#new_password').on('change',validatePasswords);
    $('#new_password_rep').on('change',validatePasswords);
});