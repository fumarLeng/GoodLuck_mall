function getProductData() {
    $.ajax({
        url: '/BackStage/product',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let productList = data;
            let productContainer = $('#product');
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
                '<th class="member-list-th">圖片</th>' +
                '<th class="member-list-th">商品狀態</th>' +
                '<th class="member-list-th">優先級</th>' +
                '<th class="member-list-th">創建時間</th>' +
                '<th class="member-list-th">最後修改時間</th>' +

                // '<th class="member-list-th">創建人</th>' +
                // '<th class="member-list-th">最後修改人</th>' +
                '<th class="member-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            $.each(productList, function (index, product) {
                let tr = $('<tr></tr>');
                tr.append('<td>' + product.id + '</td>');
                tr.append('<td>' + product.categoryId + '</td>');
                tr.append('<td>' + product.itemType + '</td>');
                tr.append('<td>' + product.title + '</td>');
                tr.append('<td>' + product.sellPoint + '</td>');
                tr.append('<td>' + product.price + '</td>');
                tr.append('<td>' + product.num + '</td>');
                tr.append('<td>' + product.image + '</td>');
                tr.append('<td>' + product.status + '</td>');
                tr.append('<td>' + product.priority + '</td>');
                tr.append('<td>' + product.createdTime + '</td>');
                tr.append('<td>' + product.modifiedTime + '</td>');

                tr.append('<td>' +
                    '<button class="btn btn-success btn-sm producUpData" onclick="productUpdataEvent(this)">修改</button>' +
                    // '<button class="btn btn-warning btn-sm">刪除</button>' +
                    '</td>');
                tbody.append(tr);
            });
            table.append(tbody);

            productContainer.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}



function productUpdataEvent(clickedButton) {
    const producUpData_btnList = document.querySelectorAll(".producUpData");
    const index = Array.prototype.indexOf.call(producUpData_btnList, clickedButton) + 1;
    alert("您點擊的是第 " + (index) + " 個按鈕");


    $.ajax({

        url: '/BackStage/product/' + index,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            let userList = data;
            let productContainer = $('#product');
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
                '<th class="member-list-th">圖片</th>' +
                '<th class="member-list-th">商品狀態</th>' +
                '<th class="member-list-th">優先級</th>' +
                '<th class="member-list-th">創建時間</th>' +
                '<th class="member-list-th">最後修改時間</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            // $.each(userList, function (index, user) {
            let tr = $('<tr></tr>');
            tr.append('<td name="uid" >' + userList.uid + '</td>');
            tr.append('<td name="username" >' + userList.username + '</td>');
            tr.append('<td><input class="user-input" name="phone" value="' + userList.phone + '"></td>');
            tr.append('<td><input class="user-input" name="email" value="' + userList.email + '"></td>');
            // tr.append('<td><input value="' + (userList.gender == 0 ? '女生' : '男生') + '"></td>');
            tr.append('<td><input name="gender" type="radio" name="gender" value="0" ' + (userList.gender == 0 ? 'checked' : '') + '>女生 <input type="radio" name="gender" value="1" ' + (userList.gender == 1 ? 'checked' : '') + '>男生</td>');

            tr.append('<td><img src="data:image/png;base64,' + userList.avatar + '" alt="User Avatar" style="width:50px;height:50px;"></td>');

            tr.append('<td><input type="radio" name="isDelete" value="0" ' + (userList.isDelete == 0 ? 'checked' : '') + '>否 <input type="radio" name="isDelete" value="1" ' + (userList.isDelete == 1 ? 'checked' : '') + '>是</td>');
            // tr.append('<td>' + (userList.isDelete ? '是' : '否') + '</td>');
            tr.append('<td>' +
                '<button class="btn btn-success btn-sm userUpdata" onclick="userUpdataEventFinish(this)">確定</button>' +
                '<button class="btn btn-warning btn-sm" onclick="getUserData()">取消</button>' +
                '</td>');
            tbody.append(tr);
            // });
            table.append(tbody);

            productContainer.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}