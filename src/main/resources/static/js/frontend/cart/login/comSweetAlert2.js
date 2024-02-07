// comSweetAlert2.js

// 初始化SweetAlert的Toast配置

//SweetAlart2
const Toast = Swal.mixin({
    toast: true,
    position: 'top-center',
    showConfirmButton: true,
    timer: 5000,
    customClass: {
        popup: 'my-swal-popup',
    },
    onOpen: toast => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
});

