<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Login</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/loginpage.css">
</head>
<body>
<jsp:include page="_menu.jsp"/>
<c:if test="${param.error == 'true'}">
    <div class="alert alert-danger" style="text-align: center">
        Login Failed!!! Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>
<c:if test="${userRegisterSuccess eq 'success'}">
    <div class="alert alert-success" style="text-align: center">
        You have Successfully Registered.Please verify your email to login!!
    </div>
</c:if>
<c:if test="${enableUser eq 'success'}">
    <div class="alert alert-success" style="text-align: center">
        You have successfully verified your account.You can now login to your account.
    </div>
</c:if>
<form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
    <div class="container">
        <div class="row">

            <div class="row">
                <div class="form_bg">
                    <form>
                        <h2 class="text-center">Company Name</h2>
                        <br/>
                        <div class="form-group">
                            <input type="text" class="form-control" name='username' id="username" placeholder="Username">
                            <div class="error" id="usernameError"> </div>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name='password' id="password" placeholder="Password">
                            <div class="error" id="passwordError"> </div>
                        </div>
                        <br/>
                        <div class="align-center">
                            <button type="submit" name="submit" value="submit" onclick="return validate()"
                                    class="btn btn-primary btn-lg btn-block login-button">Login
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script>
    function validate(){
        $('.error').text('');
        var username =$('#username').val();
        var password =$('#password').val();
        if(username ===''){
            $('#usernameError').text('Required');
            return false;
        }
        else if(password===''){
            $('#passwordError').text('Required');
            return false;
        }
        return true;
    }
</script>
</body>
</html>