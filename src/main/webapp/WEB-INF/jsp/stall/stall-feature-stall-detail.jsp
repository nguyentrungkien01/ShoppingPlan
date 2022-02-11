<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-inner">
  <h4 class="page-title">Thông tin quầy hàng</h4>
  <div class="row">
    <div class="col-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Quầy hàng: ${stallName["stallName"]}</div>
          <div class="card-head-row">
            <div class="card-category">
              <p>Vị trí: <span>${stallLocation["locationName"]}</span></p>
              <p>Tọa độ: (<span>${stallLocation["locationLongitude"]}</span> ,
                <span>${stallLocation["locationLatitude"]}</span>)
              </p>
            </div>
            <div class="card-tools">
              <a href="<c:url value = '/stall/add' />" class="btn btn-secondary" id="add">Thêm</a>
            </div>
          </div>
        </div>
        <div class="card-header">
          <div class="card-title">Danh sách sản phẩm của quầy hàng</div>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover model-list">
              <thead class="bg-thead">
                <tr>
                  <th scope="">Thao tác</th>
                  <th scope="col">Mã sản phẩm</th>
                  <th scope="col">Tên sản phẩm</th>
                  <th scope="col">Đơn giá</th>
                  <th scope="col">Loại sản phẩm</th>
                  <th scope="col">Hình</th>
                </tr>
              </thead>

              <tbody id="productListData">
              </tbody>
            </table>
          </div>
          <ul class="pagination" id="pagination"></ul>
        </div>
      </div>
    </div>
  </div>
</div>