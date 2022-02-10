<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<form action="" method ="post" enctype="multipart/form-data" id ="stallDetailControl">
    <div class ="row">
        <div class = "col">
            <div class="form-group">
              <label for="name">Tên sản phẩm <span>(*)</span></label>
              <input type="text" class="form-control" id="name"
                placeholder="Tên sản phẩm" name="name"
                pattern=".{1,30}" title="Chiều dài 1-30 ký tự" required>
            </div>

             <div class="form-group">
                 <label for="category">Loại sản phẩm <span>(*)</span></label>
                 <select class="form-control" id="category" name="category" required>
                    <c:forEach var="cat" items="${categories}">
                         <option value="${cat.get('categoryId')}">${cat.get('categoryName')}</option>
                    </c:forEach>
                 </select>

           </div>

            <div class="form-group">
              <label for="image">Ảnh đại diện</label>
              <input type="file" class="form-control" id="image" name="image">
            </div>

        </div>

        <div class= "col">
            <h1>Bảng đơn vị</h1>
            <div id = "unitTypePanel">
            </div>
            <table>
                <thead>
                    <tr>
                        <th colspan ="3" id = "title">
                        </th>
                    </tr>
                    <tr>
                        <th>STT</th>
                        <th>Tên đơn vị</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody id ="unitData">
                </tbody>
            </table>
            <h1>Danh sách đơn vị đã chọn</h1>
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên đơn vị</th>
                        <th>Đơn giá</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody id ="unitDataResult">
                </tbody>
            </table>
        </div>
    </div>

    <div class=row>
        <div class="form-group">
            <button type="submit" class="btn btn-primary" id = "confirm"></button>
        </div>
    </div>
</form>

