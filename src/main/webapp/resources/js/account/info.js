function getPhoneNumber() {
    fetch('/ShoppingPlan/info/api/phoneNumberData', ).then(res => res.json()).then(datas => {
        var phoneNumberList = datas['phoneNumbers']
        var phoneDatas = '<ul class="list-group" id="phoneNumbers">'
        for (let i = 0; i < phoneNumberList.length; i++) {
            phoneDatas += `
                  <li class="list-group-item td-icon" style="margin:auto" id="${phoneNumberList[i]['id']}">
                      <span style="padding: 5px 5px 5px 0;">  ${phoneNumberList[i]['name']}</span>
                     <a href="javascript:;" onclick="showInput(phoneNumberId='${phoneNumberList[i]['id']}', phoneNumber='${phoneNumberList[i]['name']}')" > <i class="fa fa-pencil ml-1"></i> </a>
                     <a href="javascript:;" onclick="deletePhoneNumber('${phoneNumberList[i]['id']}')"> <i class="fa fa-trash ml-1"></i> </a>
                 </li>
                `
        }
        phoneDatas += '</ul>'
        $('#phoneNumberDatas').html(phoneDatas)

    })
}

function deletePhoneNumber(phoneNumberId) {
    if ($('#phoneNumbers').children().length === 1) {
        swal({
            title: "Thông báo",
            text: "Bạn không thể xóa vì chủ sở hữu phải có ít nhất 1 số điện thoại",
            icon: "warning",
            dangerMode: true,
        })
        return
    }
    swal({
        title: "Xác nhận xóa",
        text: "Bạn có chắc chắn muốn xóa số điện thoại này!",
        icon: "warning",
        dangerMode: true,
    }).then(confirmDelete => {
        if (confirmDelete) {
            fetch('/ShoppingPlan/info/api/deletePhoneNumber', {
                method: 'post',
                body: JSON.stringify({
                    'phoneNumberId': phoneNumberId
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.json()).then(datas => {
                $('#inputPhoneNumber').val('')
                if (datas) {
                    swal(
                        'Xóa thành công',
                        'Chúc mừng bạn xóa số điện thoại thành công!',
                        'success'
                    )
                    getPhoneNumber()
                } else
                    swal(
                        'Xóa không thành công',
                        'Đã xảy ra sự cố trong quá trình xóa. Vui lòng thử lại sau!',
                        'error'
                    )
            })
        }
    })
}

function addPhoneNumber(phoneNumber) {
    fetch('/ShoppingPlan/info/api/addPhoneNumber', {
        method: 'post',
        body: JSON.stringify({
            'phoneNumber': phoneNumber
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        $('#inputPhoneNumber').val('')
        if (datas) {
            swal(
                'Thêm thành công',
                'Chúc mừng bạn thêm số điện thoại thành công!',
                'success'
            )
            getPhoneNumber()
        } else
            swal(
                'Thêm không thành công',
                'Đã xảy ra sự cố trong quá trình thêm. Vui lòng thử lại sau!',
                'error'
            )
    })
}

function editPhoneNumber(phoneNumberId, phoneNumber) {
    fetch('/ShoppingPlan/info/api/editPhoneNumber', {
        method: 'post',
        body: JSON.stringify({
            'phoneNumberId': phoneNumberId,
            'phoneNumberName': phoneNumber
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(datas => {
        $('#inputPhoneNumber').val('')
        if (datas) {
            swal(
                'Sửa thành công',
                'Chúc mừng bạn sửa số điện thoại thành công!',
                'success'
            )
            getPhoneNumber()
        } else {
            swal(
                'Sửa không thành công',
                'Đã xảy ra sự cố trong quá trình sửa. Vui lòng thử lại sau!',
                'error'
            )
        }
    })
}

function checkInputPhoneNumber() {
    var phoneNumber = $('#inputPhoneNumber').val()
    return !isNaN(phoneNumber) && phoneNumber.length === 10;

}

function showInput(phoneNumberId = null, phoneNumber = null) {
    $('#inputPhoneNumber').show()
    $('#confirm').show()

    if (phoneNumber != null)
        $('#inputPhoneNumber').val(phoneNumber)

    $('#confirm').click(function () {
        if (checkInputPhoneNumber()) {
            if (phoneNumberId != null) {
                console.info($('#inputPhoneNumber').val())
                editPhoneNumber(phoneNumberId, $('#inputPhoneNumber').val())
            } else
                addPhoneNumber($('#inputPhoneNumber').val())

        } else {
            swal(
                'Thao tác thất bại',
                'Số điện thoại phải có chiều dài là 10 bao gồm các ký tự (0-9)',
                'error'
            )
        }
        $('#inputPhoneNumber').hide()
        $('#confirm').hide()
    })
}
$(document).ready(function () {
    getPhoneNumber()
    $('#inputPhoneNumber').hide()
    $('#confirm').hide()
    $('#addPhoneNumber').click(function () {
        showInput()
    })

})