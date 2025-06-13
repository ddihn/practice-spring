<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>성적 순위</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
  <h2 class="mb-4">총점 기준 성적 순위</h2>
  <table class="table table-striped table-bordered text-center">
    <thead class="table-light">
      <tr>
        <th>순위</th>
        <th>이름</th>
        <th>중간고사</th>
        <th>기말고사</th>
        <th>총점</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="item" items="${rank}">
        <tr>
          <td>${item.rank}</td>
          <td>${item.name}</td>
          <td>${item.midScore}</td>
          <td>${item.finScore}</td>
          <td>${item.midScore + item.finScore}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <a href="/lab0610/" class="btn btn-primary">홈으로</a>
</body>
</html>
