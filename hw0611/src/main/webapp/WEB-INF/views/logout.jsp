<%@ page contentType="text/html; charset=UTF-8" %>
<%
    session.invalidate(); // 세션 삭제
    response.sendRedirect(request.getContextPath() + "/");
%>
