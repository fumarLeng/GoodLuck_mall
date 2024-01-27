function getOrderData() {
    $.ajax({
        url: '/BackStage/order',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let orderList = data;
            let ordersContainer = $('#orders');
            ordersContainer.empty();

            let table = $('<table></table>').addClass('table');

            let thead = $('<thead></thead>');
            thead.append('<tr>' +
                '<th class="member-list-th">訂單id</th>' +
                '<th class="member-list-th">會員id</th>' +
                '<th class="member-list-th">收貨人姓名</th>' +
                '<th class="member-list-th">收貨人電話</th>' +
                '<th class="member-list-th">收貨人國家</th>' +
                '<th class="member-list-th">收貨人市</th>' +
                '<th class="member-list-th">收貨人區</th>' +
                '<th class="member-list-th">收貨詳細地址</th>' +
                '<th class="member-list-th">總價</th>' +
                '<th class="member-list-th">狀態</th>' +
                '<th class="member-list-th">下單時間</th>' +
                '<th class="member-list-th">付款時間</th>' +
                '<th class="member-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            $.each(orderList, function (index, order) {
                let tr = $('<tr></tr>');
                tr.append('<td name="orderOid">' + order.oid + '</td>');
                tr.append('<td>' + order.uid + '</td>');
                tr.append('<td>' + order.recvName + '</td>');
                tr.append('<td>' + order.recvPhone + '</td>');
                tr.append('<td>' + order.recvProvince + '</td>');
                tr.append('<td>' + order.recvCity + '</td>');
                tr.append('<td>' + order.recvArea + '</td>');
                tr.append('<td>' + order.recvAddress + '</td>');
                tr.append('<td>' + order.totalPrice + '</td>');
                tr.append('<td>' + order.status + '</td>');
                tr.append('<td>' + order.orderTime + '</td>');
                tr.append('<td>' + order.payTime + '</td>');

                tr.append('<td>' +
                    '<button class="btn btn-success btn-sm ordersUpData" onclick="OrderUpdataEvent(this)">修改</button>' +
                    // '<button class="btn btn-warning btn-sm">刪除</button>' +
                    '</td>');
                tbody.append(tr);
            });
            table.append(tbody);

            ordersContainer.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}



function OrderUpdataEvent(clickedButton) {
    const ordersUpData_btnList = document.querySelectorAll(".ordersUpData");
    const index = Array.prototype.indexOf.call(ordersUpData_btnList, clickedButton) + 1;
    const orderOid = $('#orderOid').text();
    alert("您點擊的是第 " + (index) + " 個按鈕");


    $.ajax({

        url: '/BackStage/orderOid/' + orderOid,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            let productList = data;
            let productContainer = $('#orders');
            productContainer.empty();

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
                '<th class="member-list-th">圖片存放路徑</th>' +
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
            let tr = $('<tr></tr>');
            tr.append('<td name="id" >' + productList.id + '</td>');

            tr.append('<td><input class="user-input" name="category_id" value="' + productList.categoryId + '"></td>');
            tr.append('<td><input class="user-input" name="item_type" value="' + productList.itemType + '"></td>');
            tr.append('<td><input class="user-input" name="title" value="' + productList.title + '"></td>');
            tr.append('<td><input class="user-input" name="sell_point" value="' + productList.sellPoint + '"></td>');
            tr.append('<td><input class="user-input" name="price" value="' + productList.price + '"></td>');
            tr.append('<td><input class="user-input" name="num" value="' + productList.num + '"></td>');
            tr.append('<td><input class="user-input" name="image" value="' + productList.image + '"></td>');
            tr.append('<td><input class="user-input" name="status" value="' + productList.status + '"></td>');
            tr.append('<td><input class="user-input" name="priority" value="' + productList.priority + '"></td>');


            // tr.append('<td name="id" >' + productList.categoryId + '</td>');
            // tr.append('<td name="id" >' + productList.itemType + '</td>');
            // tr.append('<td name="id" >' + productList.title + '</td>');
            // tr.append('<td name="id" >' + productList.sellPoint + '</td>');
            // tr.append('<td name="id" >' + productList.price + '</td>');
            // tr.append('<td name="id" >' + productList.num + '</td>');
            // tr.append('<td name="id" >' + productList.image + '</td>');
            // tr.append('<td name="id" >' + productList.status + '</td>');
            // tr.append('<td name="id" >' + productList.priority + '</td>');
            tr.append('<td name="id2" >' + productList.createdTime + '</td>');
            tr.append('<td name="id2" >' + productList.modifiedTime + '</td>');
            tr.append('<td name="id2" >' + productList.createdUser + '</td>');
            tr.append('<td name="id2" >' + productList.modifiedUser + '</td>');

            // tr.append('<td><input type="radio" name="isDelete" value="0" ' + (userList.isDelete == 0 ? 'checked' : '') + '>否 <input type="radio" name="isDelete" value="1" ' + (userList.isDelete == 1 ? 'checked' : '') + '>是</td>');
            // // tr.append('<td>' + (userList.isDelete ? '是' : '否') + '</td>');
            tr.append('<td>' +
                '<button class="btn btn-success btn-sm userUpdata" onclick="OrderUpdataEventFinish(this)">確定</button>' +
                '<button class="btn btn-warning btn-sm" onclick="getProductData()">取消</button>' +
                '</td>');
            tbody.append(tr);

            table.append(tbody);

            productContainer.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}

function OrderUpdataEventFinish(clickedButton) {
    const productUpData_btnList = document.querySelectorAll(".producUpData");
    const idValue = $('td[name="id"]').text();
    const categoryId = $('input[name="category_id"]').val();
    const ItemType = $('input[name="item_type"]').val();
    const title = $('input[name="title"]').val();
    const sellPoint = $('input[name="sell_point"]').val();
    const price = $('input[name="price"]').val();
    const num = $('input[name="num"]').val();
    const image = $('input[name="image"]').val();
    const status = $('input[name="status"]').val();
    const priority = $('input[name="priority"]').val();
    // const index = Array.prototype.indexOf.call(productUpData_btnList, clickedButton) + 1;
    // const userNameValue = $('td[name="username"]').text();
    // const phone = $('input[name="phone"]').val();
    // const email = $('input[name="email"]').val();
    // const userGender = $('input[name="gender"]:checked').val();
    // const userIsDelete = $('input[name="isDelete"]:checked').val();

    // const userGender = $('input[name="gender"]:checked').val();
    // const userGender = $('input[name="phone"]').text();



    alert("您點擊的是第 " + (idValue) + " 個按鈕");


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
            "image" : image,
            "status" : status,
            "priority" : priority
        }),
        success: function(result) {
            console.log(result);
            getProductData();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });

}