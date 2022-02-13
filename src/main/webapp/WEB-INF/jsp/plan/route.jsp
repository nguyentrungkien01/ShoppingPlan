<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="mapStyle">
    <h1>Kiểu bản đồ</h1>
     <button id="streets-v11" type="button" class="btn btn-outline-danger active">Đường phố</button>
     <button id="satellite-streets-v11" type="button" class="btn btn-outline-danger">Vệ tinh</button>
    <button id="outdoors-v11" type="button" class="btn btn-outline-danger">Ngoài trời</button>
    <button id="light-v10" type="button" class="btn btn-outline-danger">Sáng</button>
    <button id="dark-v10" type="button" class="btn btn-outline-danger">Tối</button>

</div>
<div id="controls">
     <h1>Kiểu phương tiện</h1>
    <button id="driving-traffic" type="button" class="btn btn-outline-danger active">Cơ giới</button>
    <button id="driving" type="button" class="btn btn-outline-danger">Ô tô</button>
    <button id="walking" type="button" class="btn btn-outline-danger">Xe đạp</button>
    <button id="cycling" type="button" class="btn btn-outline-danger">Đi bộ</button>
</div>
<div id="map"></div>
<div id="instructions"></div>
<h1>Danh sách các sản phẩm cần mua</h1>
<table>
    <thead>
        <tr>
            <th>STT</th>
            <th>Mã sản phẩm</th>
            <th>Tên sản phẩm</th>
            <th>Loại sản phẩm</th>
            <th>Tên quầy hàng</th>
            <th>Vị trí</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.get('orderId')}</td>
                <td>${product.get('productId')}</td>
                <td>${product.get('productName')}</td>
                <td>${product.get('categoryName')}</td>
                <td>${product.get('stallName')}</td>
                <td>${product.get('locationName')}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
