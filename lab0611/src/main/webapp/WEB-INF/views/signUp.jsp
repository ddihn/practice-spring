<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원가입</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="card shadow-sm">
          <div class="card-header text-center bg-primary text-white">
            <h4 class="mb-0">회원가입</h4>
          </div>
          <div class="card-body">
            <form action="${pageContext.request.contextPath}/signupAction" method="post" accept-charset="UTF-8">
              <input type="hidden" name="userType" value="user" />
              <div class="mb-3">
                <label for="id" class="form-label">아이디</label>
                <input type="text" class="form-control" id="id" name="id" required placeholder="아이디를 입력하세요">
              </div>
              <div class="mb-3">
                <label for="pw" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="pw" name="pw" required placeholder="비밀번호를 입력하세요">
              </div>
              <div class="mb-3">
                <label for="adminKey" class="form-label">관리자 키 <small class="text-muted">(관리자로 가입 시 입력)</small></label>
                <input type="password" class="form-control" id="adminKey" name="adminKey" placeholder="관리자 비밀키">
              </div>
              <div class="mb-3">
                <label for="name" class="form-label">이름</label>
                <input type="text" class="form-control" id="name" name="name" required placeholder="이름을 입력하세요">
              </div>
              <div class="mb-3">
                <label for="phone" class="form-label">전화번호</label>
                <input type="tel" class="form-control" id="phone" name="phone" required placeholder="010-1234-5678">
              </div>
              <div class="mb-3">
                <label for="address" class="form-label">주소</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="주소를 입력하세요">
              </div>
              <div class="d-grid">
                <button type="submit" class="btn btn-primary">가입하기</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" ></script>
</body>
</html>
