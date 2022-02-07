<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="" method ="post" enctype="multipart/form-data" id ="stallControl">
    <div class="form-group">
      <label for="name">Tên quầy hàng <span>(*)</span></label>
      <input type="text" class="form-control" id="name"
        placeholder="Tên quầy hàng" name="name"
        pattern=".{1,60}" title="Chiều dài 1-60 ký tự" required>
    </div>

   <div class="form-group">
     <label for="location">Vị trí <span>(*)</span></label>
        <div class="row">
             <div class="col">
                 <select class="form-control" id="location" name="location" required>
                    <c:forEach var="lc" items="${locations}">
                         <option value="${lc.get('locationId')}">${lc.get('locationName')}</option>
                    </c:forEach>
                 </select>
             </div>
             <div class="col">
                  <a class="btn btn-info" role="button" href="<c:url value = '/stall/add/location' />">Thêm vị trí mới</a>
             </div>
        </div>
   </div>


    <div class="row">
         <div class="col">
             <div class="form-group">
                <label for="longitude">Kinh độ</label>
                <input type="text" class="form-control" id="longitude" readonly >
             </div>
         </div>
         <div class="col">
             <div class="form-group">
                   <label for="latitude">Vĩ độ</label>
                  <input type="text" class="form-control" id="latitude" readonly>
             </div>
         </div>
    </div>

    <div class="form-group">
      <label for="image">Ảnh đại diện</label>
      <input type="file" class="form-control" id="image" name="image">
    </div>

    <div class="form-group">
      <label for="description">Mô tả quầy hàng</label>
     <textarea class="form-control" rows="5" name ="description" id="description"></textarea>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id = "confirm"></button>
    </div>
</form>

