<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>로그인</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        >
</head>
<body class="bg-light">
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="card shadow-sm">
          <div class="card-header text-center bg-primary text-white">
            <h4 class="mb-0">로그인</h4>
          </div>
          <div class="card-body">
            <form action="${pageContext.request.contextPath}/loginAction" method="post" accept-charset="UTF-8">
              <div class="mb-3">
                <label for="id" class="form-label">아이디</label>
                <input type="text" class="form-control" id="id" name="id" required placeholder="아이디를 입력하세요">
              </div>
              <div class="mb-3">
                <label for="pw" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="pw" name="pw" required placeholder="비밀번호를 입력하세요">
              </div>
              <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe">
                <label class="form-check-label" for="rememberMe">로그인 상태 유지</label>
              </div>
              <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">로그인</button>
              </div>
            </form>
            <div class="mt-3 text-center">
              <a href="${pageContext.request.contextPath}/signup">회원가입</a> |
              <a href="#">비밀번호 찾기</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          ></script>
</body>
</html>
