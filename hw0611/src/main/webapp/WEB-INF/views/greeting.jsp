<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String userName = (String) session.getAttribute("userName");
    String userType = (String) session.getAttribute("userType");

    Integer totalUsers = (Integer) session.getAttribute("totalUsers");
    Integer todayUsers = (Integer) session.getAttribute("todayUsers");

    if (userName == null) userName = "사용자";
    if (userType == null) userType = "user";
    if (totalUsers == null) totalUsers = 0;
    if (todayUsers == null) todayUsers = 0;

    String role = "일반 사용자";
    if ("admin".equalsIgnoreCase(userType)) {
        role = "관리자";
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>환영합니다</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">HW0611</a>
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav mb-2 mb-lg-0">
                <% if ("admin".equals(userType)) { %>
                    <li class="nav-item"><a class="btn btn-warning me-2" href="userList">회원목록</a></li>
                    <li class="nav-item"><a class="btn btn-danger me-2" href="deleteUser">회원삭제</a></li>
                <% } %>
                <li class="nav-item"><a class="btn btn-outline-light me-2" href="mypage">마이페이지</a></li>
                <li class="nav-item">
                    <form action="logout" method="get" class="d-flex">
                        <button class="btn btn-danger" type="submit">로그아웃</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5 text-center">
    <h2 class="mb-4">🎉 환영합니다, <%= userName %>님!</h2>
    <p class="lead">현재 접속 중인 역할: <strong><%= role %></strong></p>

    <div class="row justify-content-center mt-4">
        <div class="col-md-4">
            <div class="card shadow-lg border-primary">
                <div class="card-body">
                    <h5 class="card-title">총 회원 수</h5>
                    <p class="display-6 text-primary"><%= totalUsers %> 명</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mt-3 mt-md-0">
            <div class="card shadow-lg border-success">
                <div class="card-body">
                    <h5 class="card-title">오늘 가입한 회원</h5>
                    <p class="display-6 text-success"><%= todayUsers %> 명</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
