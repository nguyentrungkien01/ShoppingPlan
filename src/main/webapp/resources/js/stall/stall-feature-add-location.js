
function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function checkAddLocationResult(){
    var param = new URLSearchParams(window.location.search).get('error')
    if (param!=null) {
        param=window.atob(param)
        if (param === "successful")
            swal(
                'Thêm vị trí thành công',
                'Chúc mừng bạn đã thêm vị trí thành công!',
                'success'
            ).then(() => {
                window.location.href = getCookie("prePageAddLocation")
            })
        else if (param ==="noLocationData")
            swal(
                'Thêm vị trí thất bại',
                'Vui lòng chọn 1 địa điểm trên bản đồ',
                'error'
            )
        else
            swal(
                'Thêm vị trí thất bại',
                'Vui lòng kiểm tra lại thông tin!',
                'error'
            )
    }
}

function getMap(){
    mapboxgl.accessToken =
        'pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
    var currentMarker = []
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [105.85301239321859, 21.02623130048603],
        zoom: 16,
    })

    const searchLocation = new MapboxGeocoder({
        accessToken: mapboxgl.accessToken,
        marker: {
            color: 'orange'
        },
        mapboxgl: mapboxgl
    })
    map.addControl(searchLocation, 'top-left')

    currentLocation= new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true,
        showUserHeading: true
    })

    map.addControl(currentLocation, 'top-left')

    map.on("load", function () {
        currentLocation.trigger();
    })

    currentLocation.on("geolocate", function(e){
        $("#longitude").val(e.coords.longitude)
        $("#latitude").val(e.coords.latitude)
    })

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
    chooseMapStyle(map)

}
function chooseMapStyle(map) {
    for (let i = 1; i <= $('#mapStyle').children().length; i++) {
        $(`#mapStyle button:nth-child(${i})`).click(() => {
            var button = $(`#mapStyle button:nth-child(${i})`)
            if (!button.hasClass('.active')) {
                $('#mapStyle').find('.active').removeClass('active')
                button.addClass('active')
                map.setStyle(`mapbox://styles/mapbox/${button.attr('id')}`)
            }
        })
    }

}
$(document).ready(function(){
    getMap()
    checkAddLocationResult()
})