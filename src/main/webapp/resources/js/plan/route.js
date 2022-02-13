

 function getLocations(map, currentLocation, profile) {
    fetch('/ShoppingPlan/plan/route/api/productLocation')
        .then(res=>res.json()).then(datas=>{

        const locations = parseLocation(datas)
        const locationIds = getLocationIds(datas)
        locations.push(`${currentLocation.coords.longitude},${currentLocation.coords.latitude}`)
        getProducts(map, locations, locationIds)

        for (let i = 0; i < locations.length - 1; i++)
            getRoute(map, profile, locations[i], locations[i + 1], `id_${i}`)

    })
}

function parseLocation(locations) {
    var lcs = []
    for (let i = 0; i < locations.length; i++)
        lcs.push(`${locations[i]['longitude']},${locations[i]['latitude']}`)
    return lcs
}

function getLocationIds(locations){
    var lcIds = []
    for (let i = 0; i < locations.length; i++)
        lcIds.push(`${locations[i]['id']}`)
    return lcIds
}

 function getProducts(map, locations, locationIds) {
     var lcIds = ''
     locationIds.forEach(e => lcIds += `${e},`)

     fetch('/ShoppingPlan/plan/route/api/productName', {
         method: 'post',
         body: JSON.stringify({
             'locationIds': lcIds.substring(0, lcIds.length - 1)
         }),
         headers: {
             'Content-Type': 'application/json'
         }
     }).then(res => res.json()).then(datas => {
         for (let i = 0; i < locations.length; i++) {
             if (datas[i] !== undefined) {
                 var productInfo = datas[i]['productName']
                 var name = '<ul class="list-group">'

                 for (let j = 0; j < productInfo.length; j++)

                     name += `<p>${productInfo[j]}</p>`

                 name+='</ul>'
             }
             addMarker(
                 map,
                 locations[i],
                 i === locations.length - 1 ? '#34e5eb' : '#BF1313',
                 i === locations.length - 1 ? 'Vị trí hiện tại' : name)
         }


     })
 }

function addMarker(map, location, color, title){
    const loc = location.split(',');
     new mapboxgl.Marker({
        color: color,
    }).setPopup(new mapboxgl.Popup({ offset: 25 }).setHTML(
        `
            <img src="https://res.cloudinary.com/nguyentrungkien/image/upload/v1643466243/stall/default_upj3uc.jpg"
                style="width: 30px; height: 30px" >
            <h3 class="text-danger text-center">${title}</h3>
        `
    )).setLngLat(new mapboxgl.LngLat(parseFloat(loc[0]), parseFloat(loc[1]))).addTo(map)
}

function getRoute(map,profile, start, end, id) {
    var apiRef = `https://api.mapbox.com/directions/v5/mapbox/${profile}/${start};${end}?steps=true&geometries=geojson&language=vi&access_token=${mapboxgl.accessToken}`
    fetch(apiRef).then(res => res.json()).then(datas => {
        const data = datas['routes'][0];
        const route = data['geometry']['coordinates'];
        const geojson = {
            type: 'Feature',
            properties: {},
            geometry: {
                type: 'LineString',
                coordinates: route
            }
        }
        if (map.getLayer(id) !== undefined) {
            map.getSource(id).setData(geojson);
        } else
            map.addLayer({
                id: id,
                type: 'line',
                source: {
                    type: 'geojson',
                    data: geojson
                },
                layout: {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                paint: {
                    'line-color': '#be3838',
                    'line-width': 5,
                    'line-opacity': 1
                }
            })
        const instructions = document.getElementById('instructions');
        const steps = data.legs[0].steps;
        let tripInstructions = '';
        for (const step of steps)
            tripInstructions += `<li class="text-info">${step.maneuver.instruction}</li>`

        instructions.innerHTML = `
            <p>
                <strong>Tổng thời gian: <span class="text-danger">
                       ${Math.floor(data.duration / 60)}</span> phút 
                </strong>
            </p>
            <ol>${tripInstructions}</ol>`;
    })

}
function getMap() {
    mapboxgl.accessToken =
        'pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [105.85301239321859, 21.02623130048603],
        zoom: 13
    });


    currentLocation = new mapboxgl.GeolocateControl({
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

    currentLocation.on("geolocate", function (e) {
        new mapboxgl.Marker({
            color: "#BF1313",
        }).setLngLat(new mapboxgl.LngLat(e.coords.longitude, e.coords.latitude)).addTo(map)

        setRoute(map, e, "driving-traffic")

        chooseMapStyle(map, e)

        chooseControlStyle(map, e)

    })
}

function chooseMapStyle(map, e) {
    for (let i = 1; i <= $('#mapStyle').children().length; i++) {
        $(`#mapStyle button:nth-child(${i})`).click(() => {
            var button = $(`#mapStyle button:nth-child(${i})`)
            if (!button.hasClass('.active')) {
                $('#mapStyle').find('.active').removeClass('active')
                button.addClass('active')
                map.setStyle(`mapbox://styles/mapbox/${button.attr('id')}`)
                setRoute(map, e, $('#controls').find('.active').attr('id'))
            }
        })
    }
}

function chooseControlStyle(map, e) {
    for (let i = 1; i <= $('#controls').children().length; i++) {
        $(`#controls button:nth-child(${i})`).click(() => {
            var button = $(`#controls button:nth-child(${i})`)
            if (!button.hasClass('.active')) {
                $('#controls').find('.active').removeClass('active')
                button.addClass('active')
                setRoute(map, e, button.attr('id'))
            }
        })
    }
}

function setRoute(map, currentLocation, profile) {
    $('#instructions').html('')
    getLocations(map, currentLocation, profile)

}

$(document).ready(function(){
  getMap()
})