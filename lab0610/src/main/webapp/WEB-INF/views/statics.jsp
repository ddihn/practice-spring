<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>성적 통계</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
  <h2 class="mb-4">성적 통계</h2>
  <table class="table table-bordered text-center">
    <thead class="table-light">
      <tr>
        <th>항목</th>
        <th>중간고사</th>
        <th>기말고사</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>총합</td>
        <td>${midSum}</td>
        <td>${finSum}</td>
      </tr>
      <tr>
        <td>평균</td>
        <td>${midMean}</td>
        <td>${finMean}</td>
      </tr>
    </tbody>
  </table>
  <a href="/lab0610/" class="btn btn-primary">홈으로</a>
</body>
</html>
