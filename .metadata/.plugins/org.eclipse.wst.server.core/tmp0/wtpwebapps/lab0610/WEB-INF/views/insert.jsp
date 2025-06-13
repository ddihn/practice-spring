<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>성적 입력</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
  <h2 class="mb-4">성적 입력</h2>
  <form action="/lab0610/insertAction" method="post" class="row g-3">
    <div class="col-md-6">
      <label class="form-label">이름</label>
      <input type="text" name="name" class="form-control" required>
    </div>
    <div class="col-md-6">
      <label class="form-label">학년</label>
      <input type="text" name="grade" class="form-control" required>
    </div>
    <div class="col-md-6">
      <label class="form-label">중간고사 점수</label>
      <input type="number" name="midScore" class="form-control" step="0.01" required>
    </div>
    <div class="col-md-6">
      <label class="form-label">기말고사 점수</label>
      <input type="number" name="finScore" class="form-control" step="0.01" required>
    </div>
    <div class="col-12">
      <button type="submit" class="btn btn-primary">등록</button>
      <a href="/lab0610/" class="btn btn-secondary">홈으로</a>
    </div>
  </form>
</body>
</html>
