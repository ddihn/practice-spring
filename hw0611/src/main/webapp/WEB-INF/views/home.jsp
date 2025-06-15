<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>HW0611</title>
    <!-- Bootstrap CSS -->
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
                    <li class="nav-item">
                        <a class="btn btn-outline-light me-2" href="signin">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" href="signup">회원가입</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container text-center mt-5">
        <h1 class="display-5 fw-bold">환영합니다!</h1>
        <p class="lead">이 웹사이트는 향후 기능이 추가될 예정입니다.</p>
        <hr>
        <p class="mb-4">지금 바로 시작해보세요!</p>
        
        <!-- 큰 버튼 -->
        <div class="d-grid gap-3 col-6 mx-auto">
            <a href="signin" class="btn btn-outline-primary btn-lg">로그인</a>
            <a href="signup" class="btn btn-primary btn-lg">회원가입</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
