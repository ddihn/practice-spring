<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>사용자 대시보드</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    rel="stylesheet"/>
</head>
<body class="bg-light">
<nav class="navbar navbar-light bg-white border-bottom mb-4">
  <div class="container-fluid d-flex justify-content-between align-items-center">
    <!-- 로고 / 브랜드 -->
    <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">
      성적처리시스템
    </a>
    <!-- 항상 보이는 우측 메뉴 -->
    <div class="d-flex align-items-center">
      <span class="me-3">Hello, ${sessionScope.loginId}</span>
      <a class="btn btn-outline-secondary btn-sm" 
         href="${pageContext.request.contextPath}/logout">
        로그아웃
      </a>
    </div>
  </div>
</nav>
<div class="container py-5">
  <h2 class="mb-4">성적 통계 및 순위</h2>

  <div class="row mb-4">
    <div class="col-md-3">
      <div class="card text-center">
        <div class="card-body">
          <h5>중간총점</h5>
          <p class="display-6">${sumMid}</p>
        </div>
      </div>
    </div>
    <div class="col-md-3">
      <div class="card text-center">
        <div class="card-body">
          <h5>기말총점</h5>
          <p class="display-6">${sumFin}</p>
        </div>
      </div>
    </div>
    <div class="col-md-3">
      <div class="card text-center">
        <div class="card-body">
          <h5>중간평균</h5>
          <p class="display-6">${avgMid}</p>
        </div>
      </div>
    </div>
    <div class="col-md-3">
      <div class="card text-center">
        <div class="card-body">
          <h5>기말평균</h5>
          <p class="display-6">${avgFin}</p>
        </div>
      </div>
    </div>
  </div>

  <!-- 순위 테이블 -->
  <div class="card">
    <div class="card-header">전체 순위</div>
    <div class="card-body p-0">
      <table class="table table-hover mb-0">
        <thead class="table-light">
          <tr>
            <th>순위</th>
            <th>이름</th>
            <th>중간</th>
            <th>기말</th>
            <th>합계</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${rankList}">
          <tr>
          	<td>${s.rank}</td>
            <td>${s.name}</td>
            <td>${s.midScore}</td>
            <td>${s.finScore}</td>
            <td>${s.midScore + s.finScore}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
