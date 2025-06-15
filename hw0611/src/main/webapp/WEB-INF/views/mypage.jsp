<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.kopo.hw0611.User" %>
<%
    User user = (User) request.getAttribute("user");

    String userName = (String) session.getAttribute("userName");
    String userType = (String) session.getAttribute("userType");

    if (userName == null) userName = "사용자";
    if (userType == null) userType = "user";

    String role = "일반 사용자";
    if ("admin".equalsIgnoreCase(userType)) {
        role = "관리자";
    }

    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
    <div class="container-fluid">
       <a class="navbar-brand" href="/hw0611">HW0611</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarContent">
            <ul class="navbar-nav mb-2 mb-lg-0">
                <% if (session.getAttribute("userName") != null) { %>
                    <li class="nav-item">
                        <a class="btn btn-outline-light me-2" href="mypage">마이페이지</a>
                    </li>
                    <% if ("admin".equalsIgnoreCase(userType)) { %>
                        <li class="nav-item">
                            <a class="btn btn-warning me-2" href="userList">회원 목록</a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-danger me-2" href="deleteUser">회원 삭제</a>
                        </li>
                    <% } %>
                    <li class="nav-item">
                        <form action="logout" method="get" class="d-flex">
                            <button class="btn btn-danger" type="submit">로그아웃</button>
                        </form>
                    </li>
                <% } else { %>
                    <li class="nav-item">
                        <a class="btn btn-outline-light me-2" href="signin">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" href="signup">회원가입</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<!-- User Info Form -->
<div class="container mt-5">
    <h2 class="mb-4">내 정보</h2>

    <% if ("true".equals(success)) { %>
        <div class="alert alert-success">정보가 성공적으로 수정되었습니다.</div>
    <% } else if ("fail".equals(error)) { %>
        <div class="alert alert-warning">수정에 실패했습니다. 다시 시도해주세요.</div>
    <% } else if ("db".equals(error)) { %>
        <div class="alert alert-danger">DB 오류가 발생했습니다.</div>
    <% } %>

    <% if (user != null) { %>
        <form action="mypageUpdate" method="post">
            <input type="hidden" name="idx" value="<%= user.getIdx() %>" />
            <table class="table table-bordered">
                <tr>
                    <th>아이디</th>
                    <td><%= user.getId() %></td>
                </tr>

                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" class="form-control" name="pwd" placeholder="비워두면 변경되지 않음" /></td>
                </tr>

                <tr>
                    <th>이름</th>
                    <td><input type="text" class="form-control" name="name" value="<%= user.getName() %>" required /></td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><input type="text" class="form-control" name="phone" value="<%= user.getPhone() != null ? user.getPhone() : "" %>" /></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td><input type="text" class="form-control" name="address" value="<%= user.getAddress() != null ? user.getAddress() : "" %>" /></td>
                </tr>
                <tr>
                    <td colspan="2" class="text-end">
                        <button type="submit" class="btn btn-primary">정보 수정</button>
                    </td>
                </tr>
            </table>
        </form>
    <% } else { %>
        <div class="alert alert-danger">사용자 정보를 불러오지 못했습니다.</div>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
