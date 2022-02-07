<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h1> Các quầy hàng cá nhân (Số lượng: <span id ="stallAmount"></span>)</h1>
<div>
    <a href="<c:url value = '/stall/add' />" class="btn btn-success" id="add">Thêm</a>
</div>
<div class="row" id = "stallInfo"></div>
<ul class="pagination" id = "pagination"></ul>