<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <form action="<c:url value='/stall/add/location'/>" method ="post">
        <div class = "row">
            <div class = "col-md-6">
                  <div class="form-group">
                    <label for="locationName">Tên địa chỉ <span>(*)</span></label>
                    <input type="text" class="form-control" placeholder="Địa chỉ"
                        id="locationName" name ="locationName"
                         pattern=".{1,100}" title="Chiều dài 1-100 ký tự" required>
                  </div>
           </div>

           <div class = "col-md-2">
                  <div class="form-group">
                    <label for="longitude">Kinh độ <span>(*)</span></label>
                    <input type="text" class="form-control" id="longitude" name ="longitude"
                            readonly required>
                  </div>
          </div>

          <div class = "col-md-2">
               <div class="form-group">
                  <label for="latitude">Vĩ độ <span>(*)</span></label>
                  <input type="text" class="form-control" id="latitude" name ="latitude"
                        readonly required>
                </div>
           </div>

            <div class = "col-md-2">
                  <button type="submit" class="btn btn-primary">Thêm</button>
            </div>
        </div>
    </form>
</div>
<div id="info"></div>
<div id="map"></div>

<script>
    mapboxgl.accessToken = 'pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
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
        })
    );

    map.on('mousemove', (e) => {
       document.getElementById("info").innerHTML= JSON.stringify(e.lngLat.wrap());
    });

    map.on('click', (e)=>{
        if(currentMarker.length >=1){
                currentMarker[0].remove()
                currentMarker.pop()
            }

        const marker = new mapboxgl.Marker({
            color: "#BF1313",
            draggable: true
        }).setLngLat(e.lngLat.wrap()).addTo(map)

        marker.on('dragend', function(){
           $("#longitude").val(marker.getLngLat().lng)
           $("#latitude").val(marker.getLngLat().lat)
        });

        $("#longitude").val(e.lngLat.wrap().lng)
        $("#latitude").val(e.lngLat.wrap().lat)

        currentMarker.push(marker)

    })

    map.addControl(geocoder);
</script>

