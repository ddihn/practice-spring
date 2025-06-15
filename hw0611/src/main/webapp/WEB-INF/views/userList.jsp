<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String userName = (String) session.getAttribute("userName");
    String userType = (String) session.getAttribute("userType");

    if (userName == null) userName = "사용자";
    if (userType == null) userType = "user";

    String role = "일반 사용자";
    if ("admin".equalsIgnoreCase(userType)) {
        role = "관리자";
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사용자 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">HW0611</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarContent">
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="btn btn-outline-light me-2" href="mypage">마이페이지</a>
                </li>
                <c:if test="${userType == 'admin'}">
                    <li class="nav-item">
                        <a class="btn btn-warning me-2" href="userList">회원목록</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-danger me-2" href="deleteUser">회원삭제</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <form action="logout" method="get" class="d-flex">
                        <button class="btn btn-danger" type="submit">로그아웃</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">회원 목록</h2>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>권한</th>
                <th>가입일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.userType}</td>
                    <td>${user.created}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
