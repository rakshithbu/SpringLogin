//bootstrap date picker for date of birth
$(function () {
    var date = new Date();
    var today = new Date(date.getFullYear() - 100, date.getMonth(), date.getDate());
    $('#birthDay').datetimepicker({
        format: 'DD-MMM-YYYY',
        useCurrent: false,
        minDate: today,
        maxDate: new Date(),
        ignoreReadonly: true
    });

});

var userNameExistsFlag;
$("#name").bind('keydown paste', function () {
    console.log("here");
    var id = $(this).attr("id");
    var regex = new RegExp(".*");
    var element = this;
    setTimeout(function () {
        var text = $(element).val();
        if (regex.test(text)) {
            var userName = $('#' + id).val();

             $.get("/checkIfUserNameExists",
                    {"userName": userName},
                    function (response) {
                        if (response.successObject) {
                            $("#" + id + "Error").html("Username already exists").show();
                            userNameExistsFlag = true;
                        } else {
                            userNameExistsFlag = false;
                            $("#" + id + "Error").html("");
                        }
                    });

            return true;
        }}, 0);
});

//EMAIL validation
$('input[id $= "email"]').on('input', function (e) {
    var id = $(this).attr("id");
    var val = $('#' + id).val();
    var regex = new RegExp("^[A-Za-z0-9\\._@]+$");
    if (regex.test(val) || val == '') {
        return true;
    } else {
        var length = val.length;
        var subVal = val.substring(0, length - 1);
        if (val == '') {
            $('#' + id).val('');
        } else {
            $('#' + id).val(subVal);
        }
        $("#" + id + "Error").html("Invalid Characters").show().fadeOut(3000, "swing");
        return false;
    }
});


var name;
var email;
var userRole;
var password;
var dob;
var reCaptcha;

//Required filed Validation
function validation() {
    $('.error').text('');
    name = $('#name').val();
    email = $('#email').val();
    userRole = $('#userRole').val();
    password = $('#password').val();
    dob = $('#birthDay').val();
    reCaptcha = $('#g-recaptcha-response').val();
    var regex = new RegExp("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    if (name == '') {
        $('#nameError').text('Required');
        return false;
    } else if (userNameExistsFlag) {
        $('#nameError').text('Username already exists');
        return false;
    }else if (email == '' ||!regex.test(email)) {
        $('#emailError').text('Required/Wrong Format');
        return false;
    }else if (dob === '') {
        $('#birthDayError').text('Required');
        return false;
    } else if (userRole === '') {
        $('#userRoleError').text('Required');
        return false;
    }else if (password === '') {
        $('#passwordError').text('Required');
        return false;
    }  else if (reCaptcha.length === 0) {
        $('#g-recaptcha-responseError').text('Check the checkbox');
        return false;
    }
    return true;
}

//On click of register button
function submitForm() {

    var validationFlag = validation();

    if (validationFlag) {
        var data = {

            "userName": name,
            "email": email,
            "userRole": userRole,
            "dob": dob,
            "password": password,
            "reCaptcha": reCaptcha
        };

//Ajax form submit
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/register",
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (response) {
                if (response.status === 'success') {
                    window.location.href = response.redirectUrl;
                } else {
                    if (response.status === 'captchaFailed') {
                        $('input').val('');
                        $('#error').show();
                        $('#errorText').text('Please fill the form again with correct captcha');
                        $('html, body').animate({scrollTop: $('#error').offset().top}, 'slow');
                    }else {
                        $('#error').show();
                        $('#errorText').text('Error occured while registering.' +
                            'Please contact admin or try again after some time.');
                        $('html, body').animate({scrollTop: $('#error').offset().top}, 'slow');
                    }
                }
            }
        });
    }
}