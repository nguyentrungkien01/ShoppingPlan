<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${param.error!=null}">
    <h1>Sai tên đăng nhập hoặc mật khẩu!</h1>
</c:if >
<form method = "post" action="<c:url value = '/sign-in' />">
     <div class="form-group">
        <label for="username">Tên đăng nhập: </label>
        <input type="text" class="form-control" placeholder="Nhập tên đăng nhập"
            id="username" name = "username" required>
      </div>
      <div class="form-group">
          <label for="password">Mật khẩu: </label>
          <input type="password" class="form-control" placeholder="Nhập mật khẩu"
            id="password" name = "password" required>
       </div>
      <button type="submit" class="btn btn-primary">Đăng nhập</button>
      <a href="<c:url value = '/sign-up' />" class="btn btn-primary">Đăng ký</a>
</form>

