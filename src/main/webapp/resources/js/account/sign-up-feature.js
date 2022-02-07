
function checkSignUpResult(){
    var param = new URLSearchParams(window.location.search).get('error')
    if (param!=null) {
        param=window.atob(param)
        if (param === "successful")
             swal(
                'Đăng ký thành công',
                'Chúc mừng bạn đã đăng ký thành công!',
                'success'
            ).then(() => {
                window.location.href = "/ShoppingPlan/sign-in";
            })
        else if(param === "fail")
            swal(
                'Đăng ký thất bại',
                'Vui lòng kiểm tra lại thông tin!',
                'error'
            )
        else {
            switch (param){
                case "existUsername":
                    param = "Tên đăng nhập đã tồn tại"
                    break;
                case "notMatchPassword":
                    param = "Mật khẩu xác nhận không trùng khớp"
                    break;
                case "existIdCard":
                    param = "Căn cước công dân đã tồn tại"
            }
            swal(
                'Đăng ký thất bại',
                param,
                'error'
            )
        }
    }
}

$(document).ready(function(){
    checkSignUpResult()
})