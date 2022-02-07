// const gAccessToken ='pk.eyJ1Ijoic2hvcHBpbmdwbGFucHJvamVjdCIsImEiOiJja3o5Ync5ZWQxcG0xMnJwNGd1OHhyNzlzIn0.QFSOZ7PTOvoyGDY4oHfyaw'
//
// function test(searchText){
//     fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${searchText}.json?access_token=${gAccessToken}&country=vn&language=vi&type=address`)
//         .then(res=>res.json()).then(datas=>{
//         console.info(datas)
//     })
// }
// test('lái Thiêu, Thuận An, Bình Dương')

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

$(document).ready(function(){
    checkAddLocationResult()
})