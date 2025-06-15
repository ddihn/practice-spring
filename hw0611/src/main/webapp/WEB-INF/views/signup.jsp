<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- 공통 Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">HW0611</a>
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item"><a class="btn btn-outline-light me-2" href="signin">로그인</a></li>
                <li class="nav-item"><a class="btn btn-primary" href="signup">회원가입</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">회원가입</h2>
    <form action="signupAction" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">아이디</label>
            <input type="text" class="form-control" name="id" required>
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label">비밀번호</label>
            <input type="password" class="form-control" name="pwd" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control" name="name" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">전화번호</label>
            <input type="text" class="form-control" name="phone">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">주소</label>
            <input type="text" class="form-control" name="address">
        </div>
        <div class="mb-3">
            <label for="userType" class="form-label">회원 유형</label>
            <select class="form-select" name="userType">
                <option value="user" selected>일반 사용자</option>
                <option value="admin">관리자</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="adminKey" class="form-label">관리자 키 (선택)</label>
            <input type="text" class="form-control" name="adminKey">
        </div>
        <input type="hidden" name="created" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>">
        <input type="hidden" name="lastupdated" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>">
        <button type="submit" class="btn btn-success">회원가입</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
