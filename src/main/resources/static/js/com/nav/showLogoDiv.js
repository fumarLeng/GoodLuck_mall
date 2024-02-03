function showLogoDiv() {

    let logoHtml =
        '<div class="row" id="logo-position">' +
        '<div class="col-md-3" id="logo-div">' +
        '<a id="logo-a" href="index.html"><img src="../../../images/index/logo.png" id="logo-img" alt=""/>' +
        '<b id="header-left-b">租你</b>' +
        '<b id="header-right-b">好運</b>' +
        '<b id="shop-b">商城</b>' +
        '</a>' +
        '</div>' +
        '<div class="col-md-9 top-item">' +
        '<ul class="list-inline pull-right">' +
        // '<li><a href="favorites.html"><span class="fa fa-heart"></span>&nbsp;收藏</a></li>' +
        // '<li class="li-split">|</li>' +
        '<li><a href="orders.html"><span class="fa fa-file-text"></span>&nbsp;訂單</a></li>' +
        '<li class="li-split">|</li>' +
        '<li><a href="cart.html"><span class="fa fa-cart-plus"></span>&nbsp;購物車</a></li>' +
        '<li class="li-split">|</li>' +
        '<li>' +
        '<div class="btn-group">' +
        '<button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown">' +
        '<span id="top-dropdown-btn"><span class="fa fa-gears"></span>&nbsp;管理 <span class="caret"></span></span>' +
        '</button>' +
        '<ul class="dropdown-menu top-dropdown-ul" role="menu">' +
        '<li><a href="password.html">修改密碼</a></li>' +
        '<li><a href="userdata.html">個人資料</a></li>' +
        '<li><a href="upload.html">上傳頭像</a></li>' +
        '<li><a href="address.html">收貨管理</a></li>' +
        '</ul>' +
        '</div>' +
        '</li>' +
        '<li class="li-split">|</li>' +
        // '<li><a href="login.html"><span class="fa fa-user"></span>&nbsp;登入</a></li>' +
        '<li><a href="login.html" onclick="Logout();"><span class="fa fa-sign-out"></span>&nbsp;登出</a></li>' +
        '</ul>' +
        '</div>' +
        '</div>';

    let logoPosition = document.getElementById("logo-position");
    logoPosition.innerHTML = logoHtml;

}

function Logout() {

    if (confirm('確定要登出嗎')) {
        $.ajax({
            url: "/users/logout", //
            type: 'GET',
            success: function (json) {
                alert("登出成功!");
                window.location.href = 'login.html';
            },
            error: function (xhr) {

                console.error('登出失敗', +xhr.message);
            }
        });

    }
}

