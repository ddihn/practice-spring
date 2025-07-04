<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>관리자 대시보드</title>
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
  <h2 class="mb-4">관리자 성적 관리</h2>

  <c:if test="${not empty msg}">
    <div class="alert alert-success">${msg}</div>
  </c:if>

  <!-- 성적 등록 폼 -->
  <div class="card mb-4">
    <div class="card-header">새 성적 등록</div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/admin/insertScore" method="post">
        <div class="row g-3">
          <div class="col-md-3">
            <input type="text" name="name" class="form-control" placeholder="이름" required/>
          </div>
          <div class="col-md-2">
            <input type="text" name="grade" class="form-control" placeholder="학년" required/>
          </div>
          <div class="col-md-2">
            <input type="number" name="midScore" class="form-control" placeholder="중간" required/>
          </div>
          <div class="col-md-2">
            <input type="number" name="finScore" class="form-control" placeholder="기말" required/>
          </div>
          <div class="col-md-3 text-end">
            <button class="btn btn-primary">등록</button>
          </div>
        </div>
      </form>
    </div>
  </div>

  <!-- 성적 목록 & 삭제 -->
  <div class="card">
    <div class="card-header">전체 성적 목록</div>
    <div class="card-body p-0">
      <table class="table table-striped mb-0">
        <thead class="table-light">
          <tr>
            <th>학생 번호</th>
            <th>이름</th>
            <th>중간</th>
            <th>기말</th>
            <th>합계</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${scoreList}">
          <tr>
            <td>${s.idx}</td>
            <td>${s.name}</td>
            <td>${s.midScore}</td>
            <td>${s.finScore}</td>
            <td>${s.midScore + s.finScore}</td>
            <td>
              <form action="${pageContext.request.contextPath}/admin/deleteScore" method="post" style="display:inline">
                <input type="hidden" name="idx" value="${s.idx}"/>
                <input type="hidden" name="name" value="${s.name}"/>
                <button class="btn btn-sm btn-danger">삭제</button>
              </form>
            </td>
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
