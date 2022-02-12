// //
//
// // fetch(apiRef).then(res=>res.json()).then(datas=> console.info(datas))
// //
// //
//
//
// fetch('/ShoppingPlan/plan/route/api/productLocation')
//     .then(res=>res.json()).then(datas=>{
//         var locations = ''
//
//     for(let i=0; i<datas.length; i++)
//         locations+=`${datas[i]['longitude']},${datas[i]['latitude']};`
//     locations= locations.substring(0, locations.length-1);
//
//     var accessToken = 'pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
//
//     var apiRef= `https://api.mapbox.com/directions/v5/mapbox/driving/${locations}?steps=true&geometries=geojson&language=vi&access_token=${accessToken}`
//     fetch(apiRef).then(res=>res.json()).then(datas=> console.info(datas))
// })
function getMap() {
    mapboxgl.accessToken =
        'pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
    const map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [105.85301239321859, 21.02623130048603],
        zoom: 13
    });

    var start = [105.85301239321859, 21.02623130048603];

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
        $("#longitude").val(e.coords.longitude)
        $("#latitude").val(e.coords.latitude)

        start = [e.coords.longitude, e.coords.latitude];
    })
    return map
}

function getRoute(map){
   fetch('/ShoppingPlan/plan/route/api/productLocation').then(res=>res.json()).then(datas=>{
           var locations = ''

           for(let i=0; i<datas.length; i++)
               locations+=`${datas[i]['longitude']},${datas[i]['latitude']};`
           locations= locations.substring(0, locations.length-1);
           var apiRef= `https://api.mapbox.com/directions/v5/mapbox/cycling/${locations}?steps=true&geometries=geojson&language=vi&access_token=${mapboxgl.accessToken}`

           fetch(apiRef).then(res=>res.json()).then(result=>{
                const data = result['routes'][0];
                const route = data['geometry']['coordinates'];
                const geojson = {
                       type: 'Feature',
                       properties: {},
                       geometry: {
                         type: 'LineString',
                         coordinates: route
                       }
                }
                console.info(map.getSource('route'))
                if (map.getSource('route')!==undefined) {
                   map.getSource('route').setData(geojson);
                 }
                else
                   map.addLayer({
                     id: 'route',
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
                       'line-width': 10,
                       'line-opacity': 0.75
                     }
                   })

                 // this is where the code from the next step will go
            })
   })
 // add turn instructions here at the end
}


$(document).ready(function(){
   const map = getMap()
    getRoute(map)
})