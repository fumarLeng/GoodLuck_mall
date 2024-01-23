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
                    '<button class="btn btn-success btn-sm">修改</button>' +
                    '<button class="btn btn-warning btn-sm">刪除</button>' +
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

