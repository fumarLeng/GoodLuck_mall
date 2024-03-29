function productUpdataEvent(clickedButton) {
    // 獲取所有更新按鈕並找出被點擊按鈕的索引
    const productUpdateButtonList = document.querySelectorAll(".producUpData");
    const index = Array.from(productUpdateButtonList).indexOf(clickedButton);
    let productId = $('.productId').eq(index).text();

    // 發送 AJAX 請求以獲取商品數據
    $.ajax({
        url: `/BackStage/product/${productId}`,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            const productList = data;
            const productContainer = $('#product');
            const prev_next_buttons = $('#prev-next-buttons');
            productContainer.empty();
            prev_next_buttons.hide();

            const table = $('<table></table>').addClass('table');
            const thead = $('<thead></thead>').append(`

                 <tr class="product-list-tr" id="product-list-tr-img">
                    <td class="product-list-td-img">
                    <img id="productImage" src="data:image/png;base64,${productList.image}" style="width: 50%;" />
                    <label for="imageUpload" class="btn btn-primary btn-sm">更改圖片</label>
                    <input id="imageUpload" onchange="updataImg()" type="file" class="form-control-file" name="newImage" style="display: none;"  /></td>
                </tr>  
                <tr>
                    <th class="product-list-th modify-td">商品id</th>
                    <td class="product-list-td" name="productId" >${productList.id}</td>
                </tr>
                <tr>
                    <th class="product-list-th">分類id</th>
                    <td><input class="product-list-input" name="category_id" value="${productList.categoryId}"></td>
                    
                </tr>
                <tr>
                    <th class="product-list-th">商品系列</th>
                    <td><input class="product-list-input" name="item_type" value="${productList.itemType}"></td>
                </tr>  
                <tr>
                    <th class="product-list-th">商品標題</th>
                    <td><input class="product-list-input" name="title" value="${productList.title}"></td>
                </tr>  

                <tr>
                    <th class="product-list-th">商品賣點</th>
                    <td><input class="product-list-input" name="sell_point" value="${productList.sellPoint}"></td>
                </tr>
                  
                <tr>
                    <th class="product-list-th">商品單價</th>
                    <td><input class="product-list-input" name="price" value="${productList.price}"></td>
                </tr>  
                <tr>
                    <th class="product-list-th">庫存數</th>
                    <td><input class="product-list-input" name="num" value="${productList.num}"></td>
                </tr>  
                
                <tr>
                    <th class="product-list-th modify-td">商品狀態</th>
                    <td class="product-list-td" name="">
                        <label><input class="order-radio" type="radio" name="status" value="1" ${productList.status === 1 ? 'checked' : ''}>上架</label>
                        <label><input class="order-radio" type="radio" name="status" value="2" ${productList.status === 2 ? 'checked' : ''}>下架</label>
                        <label><input class="order-radio" type="radio" name="status" value="3" ${productList.status === 3 ? 'checked' : ''}>刪除</label>
                    </td>
                </tr>
                
                <tr>
                    <th class="product-list-th">優先級</th>
                    <td><input class="product-list-input" name="priority" value="${productList.priority}"></td>
                </tr>  
                <tr>
                    <th class="product-list-th">創建時間</th>
                    <td class="product-list-td">${productList.createdTime}</td>
                </tr>  
                <tr>
                    <th class="product-list-th">最後修改時間</th>
                    <td class="product-list-td">${productList.modifiedTime}</td>
                </tr>  
                <tr>
                    <th class="product-list-th">創建人</th>
                    <td class="product-list-td">${productList.createdUser}</td>
                </tr>  
                <tr>
                    <th class="product-list-th">最後修改人</th>
                    <td class="product-list-td">${productList.modifiedUser}</td>
                </tr>  
                <tr>
                    <th class="product-list-th">功能</th>
                    <td class="product-list-button-td">
                        <button class="btn btn-success productBtn" id="product-ok" onclick="productUpdataEventFinish(this)">確定</button>
                        <button class="btn btn-success productBtn" id="product-cancel" onclick="getProductData()">取消</button>
                    </td>
                </tr>  
            
                
            `);
            table.append(thead);

            productContainer.append(table);
            updataImg();
            changeColor();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(`AJAX請求失敗：${textStatus}, ${errorThrown}`);
        }
    });
}
function productDown(){

}

function productUpdataEventFinish(clickedButton) {



    const productUpData_btnList = document.querySelectorAll(".producUpData");
    const idValue = $('td[name="productId"]').text();
    const categoryId = $('input[name="category_id"]').val();
    const ItemType = $('input[name="item_type"]').val();
    const title = $('input[name="title"]').val();
    const sellPoint = $('input[name="sell_point"]').val();
    const price = $('input[name="price"]').val();
    const num = $('input[name="num"]').val();

    const image = $('#productImage').attr('src');
    const base64ImageData = image.replace(/^data:image\/\w+;base64,/, ''); // 去除MIME前缀

    const status = findCheckedValue();
    const priority = $('input[name="priority"]').val();


    // alert("您點擊的是第 " + (idValue) + " 個按鈕");

    //SweetAlart2
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-center',
        showConfirmButton: true,
        timer: 1000,
        customClass: {
            popup: 'my-swal-popup',
        },
        onOpen: toast => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    });


    $.ajax({
        url: '/BackStage/product/modify/' + idValue,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": idValue,
            "categoryId" : categoryId,
            "itemType" : ItemType,
            "title" : title,
            "sellPoint" : sellPoint,
            "price" : price,
            "num" : num,
            "image" : base64ImageData,
            "status" : status,
            "priority" : priority
        }),
            success: function(result) {
                const prev_next_buttons = $('#prev-next-buttons');
                prev_next_buttons.show();
                Toast.fire({
                    icon: 'success',
                    title: '資料更新成功~',
                }).then(() => {
                    setTimeout(() => {
                        // window.location.href = "index.html";

                        getProductData();
                    }, 100); // 延遲時間更改為1000毫秒
                });


        },
        error: function(xhr, status, error) {
            // console.error(error);
            Toast.fire("數據格式錯誤" ,"數據格式錯誤","warning");
        }
    });

}
//
function findCheckedValue(){

    const statusList = document.querySelectorAll(".order-radio");

    for(let i = 0 ; i < statusList.length ; i++){

        if (statusList[i].checked){

            return statusList[i].value;
        }
    }
}






//分頁

let currentPage = 1; // 第一頁
let pageSize = 5; // 頁數據
let productCount; // 總筆數
function updatePaginationControls() {
    // 實現分頁控制的邏輯
    // 可使用 currentPage 和 pageSize 來擷取適當的資料
    // 並根據需求更新使用者介面
}
function getProductData(){
// function getProductDataPage() {
    $.ajax({
        url: '/BackStage/product/page?page=' + currentPage + '&pageSize=' + pageSize,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let productList = data;
            let productContainer = $('#product');
            productContainer.empty();

            const prev_next_buttons = $('#prev-next-buttons');
            prev_next_buttons.show();

            let table = $('<table></table>').addClass('table');

            let thead = $('<thead></thead>');
            thead.append('<tr>' +
                '<th class="member-list-th">商品id</th>' +
                '<th class="member-list-th">分類id</th>' +
                '<th class="member-list-th">商品系列</th>' +
                '<th class="member-list-th">商品標題</th>' +
                '<th class="member-list-th">商品賣點</th>' +
                '<th class="member-list-th">商品單價</th>' +
                '<th class="member-list-th">庫存數</th>' +
                '<th class="member-list-th">圖片</th>' +
                '<th class="member-list-th">商品狀態</th>' +
                '<th class="member-list-th">優先級</th>' +
                '<th class="member-list-th">創建時間</th>' +
                '<th class="member-list-th">最後修改時間</th>' +
                '<th class="member-list-th">創建人</th>' +
                '<th class="member-list-th">最後修改人</th>' +
                '<th class="member-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            $.each(productList, function (index, product) {
                let tr = $('<tr></tr>');
                tr.append('<td name="productId" class="productId">' + product.id + '</td>');
                tr.append('<td>' + product.categoryId + '</td>');
                tr.append('<td>' + product.itemType + '</td>');
                tr.append('<td>' + product.title + '</td>');
                tr.append('<td>' + product.sellPoint + '</td>');
                tr.append('<td>' + product.price + '</td>');
                tr.append('<td>' + product.num + '</td>');
                tr.append('var<td>' + '<img alt="" class="tr-img" src="data:image/png;base64,' + product.image +   '"/></td>');
                tr.append('<td class="statusList">' + product.status + '</td>');
                tr.append('<td>' + product.priority + '</td>');
                tr.append('<td name="id" >' + product.createdTime + '</td>');
                tr.append('<td name="id" >' + product.modifiedTime + '</td>');
                tr.append('<td>' + product.createdUser + '</td>');
                tr.append('<td>' + product.modifiedUser + '</td>');

                tr.append('<td>' +
                    '<button class="btn btn-success btn-sm producUpData" onclick="productUpdataEvent(this)">修改</button>' +
                    '</td>');
                tbody.append(tr);
            });
            table.append(tbody);

            productContainer.append(table);

            updatePaginationControls();

            changeSwitchName();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}


// 1:上架, 2:下架, 3:刪除
function changeSwitchName(){
    const statusLis = document.querySelectorAll(".statusList");

    for(let i = 0 ; i < statusLis.length ; i++){
        switch (statusLis[i].innerText){

            case "1":
                statusLis[i].innerText = "上架";
                statusLis[i].classList.add("statusGreen");
                break;
            case "2":
                statusLis[i].innerText = "下架";
                // statusLis[i].classList.add("statusYellow");
                statusLis[i].classList.add("statusRed");
                break;
            // case "3":
            //     statusLis[i].innerText = "刪除";
            //     statusLis[i].classList.add("statusRed");
            //     break;

        }
    }
}


function handlePaginationButtonClick(page) {
    if (page >= 1) {
        currentPage = page;
        // fetchDataForCurrentPage();

    }
}

function initializePage() {

    pageBtnEvent();

    // 添加分頁控制的事件監聽器（例如，上一頁和下一頁按鈕）
    $('#prev-button').click(function () {
        handlePaginationButtonClick(currentPage - 1);

        // getProductCount();
        // console.log("getProductCount: " + productCount);
        pageBtnEvent();

        $("#current-page").text(currentPage);
        getProductData();
    });

    $('#next-button').click(function () {
        handlePaginationButtonClick(currentPage + 1);

        // getProductCount();
        // console.log("getProductCount: " + productCount);
        pageBtnEvent();

        $("#current-page").text(currentPage);
        getProductData();
    });
}

initializePage();

function pageBtnEvent(){
    getProductCount();
    console.log("getProductCount: " + productCount);
    if (currentPage === 1) {
        $('#prev-button').prop('disabled', true);
        $('#prev-button').addClass('btn-disabled');
    } else {
        $('#prev-button').prop('disabled', false);
        $('#prev-button').removeClass('btn-disabled');
    }

    if(currentPage >= productCount){
        $('#next-button').prop('disabled', true);
        $('#next-button').addClass('btn-disabled');
    } else {
        $('#next-button').prop('disabled', false);
        $('#next-button').removeClass('btn-disabled');
    }

}


// 更新分页控件的函数
function updatePaginationControls() {
    let paginationContainer = document.getElementById("pagination");
    if (!paginationContainer) {
        paginationContainer = document.createElement("div");
        paginationContainer.id = "pagination";
        document.body.appendChild(paginationContainer);
    }
    paginationContainer.innerHTML = ''; // 清空已有的分页内容

}

// 页面加载完成时获取第一页的数据
$(document).ready(function() {
    getProductData(1, 5);
});

function getProductCount(){
    $.ajax({
        url: '/BackStage/product/page/count',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            productCount = data / pageSize;
            // return productCount;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}


function changeColor() {
    let inputElements = document.querySelectorAll(".product-list-input");

    inputElements.forEach(function (inputElement) {
        inputElement.style.color = "greenyellow";
        inputElement.addEventListener("input", function () {

            let inputValue = inputElement.value;

            if (inputValue === "") {
                inputElement.style.color = "greenyellow";
            } else {
                inputElement.style.color = "red";
            }
        });
    });
}


function updataImg(){

    let imageUpload = document.getElementById("imageUpload");
    let productImage = document.getElementById("productImage");

    imageUpload.addEventListener("change", function() {
        let selectedFile = imageUpload.files[0];
        let reader = new FileReader();

        reader.onload = function(event) {
            productImage.src = event.target.result;
        };

        reader.readAsDataURL(selectedFile);
    });
}