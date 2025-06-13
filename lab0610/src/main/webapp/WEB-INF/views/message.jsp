<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>입력 결과</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
  <h2 class="mb-3">${text}</h2>
  <ul class="list-group mb-3">
    <li class="list-group-item">이름: ${name}</li>
    <li class="list-group-item">학년: ${grade}</li>
    <li class="list-group-item">중간고사: ${midScore}</li>
    <li class="list-group-item">기말고사: ${finScore}</li>
  </ul>
  <a href="/lab0610/" class="btn btn-primary">홈으로</a>
  <a href="/lab0610/insert" class="btn btn-outline-secondary">다시 입력</a>
</body>
</html>
