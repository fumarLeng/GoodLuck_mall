// comSweetAlert2.js

// 初始化SweetAlert的Toast配置

//SweetAlart2
const Toast = Swal.mixin({
    toast: true,
    position: 'top-center',
    showConfirmButton: true,
    timer: 3000,
    customClass: {
        popup: 'my-swal-popup',
    },
    onOpen: toast => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
});
// 登入
function setupLogin() {
    $("#btn-login").click(function() {
        $.ajax({
            url: "/users/login",
            type: "POST",
            data: $("#form-login").serialize(),
            dataType: "json",
            success: function(json) {
                if (json.state === 200) {

                    Toast.fire({
                        icon: 'success',
                        title: '登入成功~',
                    });
                    window.location.href = "index.html";

                } else {

                    Toast.fire({
                        icon: 'error',
                        title: '登入失敗'
                    });
                }
            },
            error: function(xhr, status, error) {

                Toast.fire({
                    icon: 'error',
                    title: '請求失敗，私服器忙線中，請稍後~'
                });
            }
        });
    });
}
$(document).ready(function() {
    setupLogin();
});
