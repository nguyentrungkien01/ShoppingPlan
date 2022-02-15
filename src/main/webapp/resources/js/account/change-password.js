
function changePasswordResult() {
    var param = new URLSearchParams(window.location.search).get('error')
    if (param != null) {
        param = window.atob(param)
        switch (param){
            case "successful":
                swal(
                    'Đổi mật khẩu thành công!',
                    'Chúc mừng bạn đã thêm sản phẩm vào quầy hàng thành công!',
                    'success'
                ).then(() => window.location.href = `/ShoppingPlan/homepage`)
                break
            case "confirmNotMatch":
                swal(
                    'Đổi mật khẩu thất bại!',
                    'Mật khẩu xác nhận không đúng',
                    'error'
                )
                break
            case "passwordNotExist":
                swal(
                    'Đổi mật khẩu thất bại!',
                    'Mật khẩu cần đổi không đúng!',
                    'error'
                )
                break
            default:
                swal(
                    'Đổi mật khẩu thất bại!',
                    'Thông tin nhập không hợp lệ! Vui lòng thử lại!',
                    'error'
                )
                break
        }
    }
}

$(document).ready(function(){
    changePasswordResult()
})