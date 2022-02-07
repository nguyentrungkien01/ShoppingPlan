<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Quầy hàng: <span>${stallName["stallName"]}</span></h1>
<p>Vị trí: <span>${stallLocation["locationName"]}</span></p>
<p>Tọa độ: (<span>${stallLocation["locationLongitude"]}</span> , <span>${stallLocation["locationLatitude"]}</span>)</p>
<p>Danh sách sản phẩm của quầy hàng</p>
<div>
    <a href="javascript:;" type="button" class="btn btn-success" id ="add">Thêm</a>
</div>
<div class="table-responsive-md">
  <table class="table table-hover table-striped">
        <thead>
            <tr>
              <th scope="">Thao tác</th>
              <th scope="col">Mã sản phẩm</th>
              <th scope="col">Tên sản phẩm</th>
              <th scope="col">Đơn giá</th>
              <th scope="col">Loại sản phẩm</th>
              <th scope="col">Hình</th>
            </tr>
        </thead>
          <tbody id="productListData"></tbody>
  </table>
</div>
<ul class="pagination" id = "pagination"></ul>