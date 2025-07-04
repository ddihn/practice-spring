<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>학생 목록</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">학생 목록</h2>

    <table class="table table-hover table-bordered">
        <thead class="table-light">
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>중간고사</th>
                <th>기말고사</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="stu" items="${stuList}">
                <tr>
                    <td>${stu.idx}</td>
                    <td>${stu.name}</td>
                    <td>${stu.midScore}</td>
                    <td>${stu.finScore}</td>
                    <td>
                        <a href="delStu?stuId=${stu.idx}&stuName=${stu.name}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('정말 삭제하시겠습니까?');">
                            삭제
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
