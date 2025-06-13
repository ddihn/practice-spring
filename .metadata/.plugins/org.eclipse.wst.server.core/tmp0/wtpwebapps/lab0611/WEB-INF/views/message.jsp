<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>알림</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
  <div class="card p-4 shadow">
    <div class="card-body text-center">
      <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>
      <a href="${pageContext.request.contextPath}/" class="btn btn-primary mt-3">메인으로</a>
    </div>
  </div>
</body>
</html>
