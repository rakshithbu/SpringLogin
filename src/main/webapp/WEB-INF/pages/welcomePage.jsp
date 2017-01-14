<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap-datetimepicker.min.css">

    <!-- Website CSS style -->
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <title>Welcome</title>
</head>
<body>
<div class="row alert alert-danger" style="text-align: center;display: none" id="error">
    <span id="errorText"></span>
</div>
<div class="container">
    <div class="row main">
        <div class="panel-heading" style="padding: 0%">
            <div class="panel-title text-center">
                <h1 class="title">Company Name</h1>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">Your Name <span
                        style="color: #FF0000;">&#42</span></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="name" id="name"
                               placeholder="Enter your Name"/>
                    </div>
                    <div class="error" id="nameError"></div>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="cols-sm-2 control-label">Your Email <span style="color: #FF0000;">&#42</span></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="email" id="email"
                               placeholder="Enter your Email"/>
                    </div>
                    <div class="error" id="emailError"></div>
                </div>
            </div>

            <div class="form-group">
                <label class="cols-sm-2 control-label">DOB<span style="color: #FF0000;">&#42</span></label>
                <div class="cols-sm-10">
                    <div class='input-group'>
                        <span class="input-group-addon"> <i class="fa fa-calendar fa" aria-hidden="true"></i></span>
                        </span>
                        <input type="text" id="birthDay" placeholder="Enter your DOB"
                               title="Select Date" class="form-control"
                               placeholder="Date Of Birth" readonly="true"/>
                    </div>
                    <div class="error" id="birthDayError"></div>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="cols-sm-2 control-label">User Type<span
                        style="color: #FF0000;">&#42</span></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa-caret-down fa" aria-hidden="true"></i></span>
                        <select id="userRole"
                                class="form-control">
                            <option value="">-- Select --</option>
                            <option value="ADMIN">ADMIN</option>
                            <option value="USER">USER</option>
                        </select>
                    </div>
                    <div class="error" id="userRoleError"></div>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label">Password<span style="color: #FF0000;">&#42</span></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="Enter your Password"/>
                    </div>
                </div>
                <div class="error" id="passwordError"></div>
            </div>

            <div class="form-group">
                <div class="g-recaptcha" data-sitekey="${sitekey}"
                     id="g-recaptcha-response"></div>
                <div class="error" id="g-recaptcha-responseError"></div>
            </div>

            <div class="form-group ">
                <button type="button" class="btn btn-primary btn-lg btn-block login-button" onclick="submitForm()">
                    Register
                </button>
            </div>
            <div class="login-register">
                <a href="/login"><b>Already Registered? Login</b></a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/moments.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script type="text/javascript" src="/resources/js/userRegistration.js"></script>
</body>
</html>