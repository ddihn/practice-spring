<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
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
    <h2 class="mb-4">로그인</h2>
    <form action="signinAction" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">아이디</label>
            <input type="text" class="form-control" name="id" required>
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label">비밀번호</label>
            <input type="password" class="form-control" name="pwd" required>
        </div>
        <button type="submit" class="btn btn-primary">로그인</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
