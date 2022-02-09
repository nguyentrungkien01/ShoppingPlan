<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-inner">
    <h4 class="page-title">Thêm toạ độ</h4>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">Thông tin toạ độ</div>
                </div>
                <div class="card-body">
                    <form action="<c:url value='/stall/add/location'/>" method="post">
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group form-show-validation row">
                                    <label for="locationName" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Tên
                                        địa chỉ<span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="locationName" name="locationName"
                                            placeholder="Nhập địa chỉ..." required pattern=".{1,100}"
                                            oninvalid="this.setCustomValidity('Chiều dài từ 1-100 ký tự')"
                                            oninput="this.setCustomValidity('')">
                                    </div>
                                </div>
                            </div>


                            <div class="col-6">
                                <div class="form-group form-show-validation row">
                                    <label for="longitude" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Kinh
                                        độ<span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="longitude" name="longitude" readonly
                                            required>
                                    </div>
                                </div>
                            </div>

                            <div class="col-6">
                                <div class="form-group form-show-validation row">
                                    <label for="latitude" class="col-lg-3 col-md-3 col-sm-4 mt-sm-2 text-right">Vĩ
                                        độ<span class="required-label">*</span></label>
                                    <div class="col-lg-8 col-md-9 col-sm-8">
                                        <input type="text" class="form-control" id="latitude" name="latitude" readonly
                                            required>
                                    </div>
                                </div>
                            </div>


                        </div>
                </div>
                <div class="card-footer btn-center">
                    <button type="submit" class="btn btn-primary">Thêm</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-10">
            <div id="info"></div>
            <div id="map"></div>
        </div>
    </div>
</div>
</div>
<script>
    mapboxgl.accessToken =
        'pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
    var currentMarker = []
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [106.67694, 10.81972],
        zoom: 13,
    });

    const geocoder = new MapboxGeocoder({
        accessToken: mapboxgl.accessToken,
        marker: {
            color: 'orange'
        },
        mapboxgl: mapboxgl
    });
    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true,
        showUserHeading: true
    }));

    map.on('mousemove', (e) => {
        document.getElementById("info").innerHTML = JSON.stringify(e.lngLat.wrap());
    });

    map.on('click', (e) => {
        if (currentMarker.length >= 1) {
            currentMarker[0].remove()
            currentMarker.pop()
        }

        const marker = new mapboxgl.Marker({
            color: "#BF1313",
            draggable: true
        }).setLngLat(e.lngLat.wrap()).addTo(map)

        marker.on('dragend', function () {
            $("#longitude").val(marker.getLngLat().lng)
            $("#latitude").val(marker.getLngLat().lat)
        });

        $("#longitude").val(e.lngLat.wrap().lng)
        $("#latitude").val(e.lngLat.wrap().lat)

        currentMarker.push(marker)
    })

    map.addControl(geocoder);
</script>