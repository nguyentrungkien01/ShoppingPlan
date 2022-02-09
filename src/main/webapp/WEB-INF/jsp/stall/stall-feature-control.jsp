<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-inner">
  <h4 class="page-title">Thông tin quầy hàng</h4>
  <div class="row">
    <div class="col-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Quầy hàng</div>
          <div class="card-category">Nhập thông tin quầy hàng</div>
        </div>
        <form action="" method="post" enctype="multipart/form-data" id="stallControl">
          <div class="form-group form-show-validation row">
            <label for="name" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Tên quầy hàng <span
                class="required-label">*</span></label>
            <div class="col-lg-8 col-md-9 col-sm-8">
              <input type="text" class="form-control" id="name" name="name" placeholder="Nhập tên quầy hàng..." required
                pattern=".{1,60}" oninvalid="this.setCustomValidity('Chiều dài từ 1-60 ký tự')"
                oninput="this.setCustomValidity('')">
            </div>
          </div>

          <div class="form-group form-show-validation row">
            <label for="location" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Vị trí <span
                class="required-label">*</span></label>
            <div class="col-lg-6 col-md-6 col-sm-8">
              <select class="form-control" id="location" name="location" required>
                <c:forEach var="lc" items="${locations}">
                  <option value="${lc.get('locationId')}">${lc.get('locationName')}</option>
                </c:forEach>
              </select>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12">
              <a class="btn btn-info" role="button" href="<c:url value = '/stall/add/location' />">Thêm vị trí mới</a>
            </div>
          </div>

          <div class="separator-solid"></div>
          <div class="card-header">
            <div class="card-title">Toạ độ</div>
            <div class="card-category">Nhập toạ độ</div>
          </div>

          <div class="form-group form-show-validation row">
            <label for="longitude" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Kinh độ</label>
            <div class="col-lg-8 col-md-9 col-sm-8">
              <input type="text" class="form-control" id="longitude" readonly>
            </div>
          </div>

          <div class="form-group form-show-validation row">
            <label for="latitude" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Vĩ độ</label>
            <div class="col-lg-8 col-md-9 col-sm-8">
              <input type="text" class="form-control" id="latitude" readonly>
            </div>
          </div>

          <div class="separator-solid"></div>
          <div class="card-header">
            <div class="card-title">Thông tin khác</div>
          </div>

          <div class="form-group form-show-validation row">
            <label for="image" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Ảnh đại diện</label>
            <div class="col-lg-8 col-md-9 col-sm-8">
              <input type="file" class="form-control" id="image" name="image">
            </div>
          </div>

          <div class="form-group form-show-validation row">
            <label for="description" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Mô tả quầy hàng</label>
            <div class="col-lg-8 col-md-9 col-sm-8">
              <textarea class="form-control" rows="5" name="description" id="description"></textarea>
            </div>
          </div>

          <div class="separator-solid"></div>
          <div class="form-group btn-center">
            <button type="submit" class="btn btn-primary mb-3" id="confirm"></button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>