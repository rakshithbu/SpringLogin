<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <span class="navbar-brand">Company Name</span>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/welcome">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo">User Info</a></li>
            <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </c:if>
        </ul>
    </div>
</nav>
