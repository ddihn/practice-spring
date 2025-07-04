<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>성적 처리 시스템 - 시작 화면</title>
  <!-- Bootstrap 5.3.0 CSS -->
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container">
    <div class="row justify-content-center align-items-center vh-100">
      <div class="col-md-6 col-lg-5">
        <div class="card shadow">
          <div class="card-body text-center">
            <h1 class="card-title mb-4">성적 처리 시스템</h1>
            <p class="card-text mb-4">시스템을 이용하려면 로그인 또는 회원가입이 필요합니다.</p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
              <a href="${pageContext.request.contextPath}/login" class="btn btn-primary btn-lg px-4 me-sm-3">로그인</a>
              <a href="${pageContext.request.contextPath}/signup" class="btn btn-outline-secondary btn-lg px-4">회원가입</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap 5.3.0 JS Bundle (Popper 포함) -->
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
   ></script>
</body>
</html>
