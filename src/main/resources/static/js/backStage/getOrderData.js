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
    // const ordersUpData_btnList = document.querySelectorAll(".ordersUpData");
    // const index = Array.prototype.indexOf.call(ordersUpData_btnList, clickedButton) + 1;
    // const orderOid = $('.orderOid').text();
    // alert("您點擊的是第 " + (index) + " 個按鈕");
    const ordersUpData_btnList = document.querySelectorAll(".ordersUpData");
    const index = Array.prototype.indexOf.call(ordersUpData_btnList, clickedButton);
    const orderOid = $($($(clickedButton).closest('tr')).find('td[name="orderOid"]')).text();

    alert("btn" + (index + 1) + "id: " + orderOid);

    $.ajax({

        url: '/BackStage/order/' + orderOid + "/" + index,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            let ordersList = data;
            let orders = $('#orders');
            orders.empty();

            let table = $('<table></table>').addClass('table');

            let thead = $('<thead></thead>');
            thead.append('<tr>' +
                '<th class="orders-list-th">訂單id</th>' +
                '<th class="orders-list-th">會員id</th>' +
                '<th class="orders-list-th">收貨人姓名</th>' +
                '<th class="orders-list-th">收貨人電話</th>' +
                '<th class="orders-list-th">收貨人國家</th>' +
                '<th class="orders-list-th">收貨人市</th>' +
                '<th class="orders-list-th">收貨人區</th>' +
                '<th class="orders-list-th">收貨詳細地址</th>' +
                '<th class="orders-list-th">總價</th>' +
                '<th class="orders-list-th">狀態</th>' +
                '<th class="orders-list-th">下單時間</th>' +
                '<th class="orders-list-th">付款時間</th>' +
                '<th class="orders-list-th">創建人</th>' +
                '<th class="orders-list-th">創建時間</th>' +
                '<th class="orders-list-th">修改人</th>' +
                '<th class="orders-list-th">修改時間</th>' +
                '<th class="orders-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            let tr = $('<tr></tr>');
            tr.append('<td name="oid" >' + ordersList.oid + '</td>');
            tr.append('<td name="uid" >' + ordersList.uid + '</td>');

            tr.append('<td><input class="user-input" name="recv_name" value="' + ordersList.recvName + '"></td>');
            tr.append('<td><input class="user-input" name="recv_phone" value="' + ordersList.recvPhone + '"></td>');
            tr.append('<td><input class="user-input" name="recv_province" value="' + ordersList.recvProvince + '"></td>');
            tr.append('<td><input class="user-input" name="recv_city" value="' + ordersList.recvCity + '"></td>');
            tr.append('<td><input class="user-input" name="recv_area" value="' + ordersList.recvArea + '"></td>');
            tr.append('<td><input class="user-input" name="recv_address" value="' + ordersList.recvAddress + '"></td>');

            tr.append('<td name="uid" >' + ordersList.totalPrice + '</td>');
            tr.append('<td name="uid" >' + ordersList.status + '</td>');
            tr.append('<td name="uid" >' + ordersList.orderTime + '</td>');
            tr.append('<td name="uid" >' + ordersList.payTime + '</td>');
            tr.append('<td name="uid" >' + ordersList.createdUser + '</td>');
            tr.append('<td name="uid" >' + ordersList.createdTime + '</td>');
            tr.append('<td name="uid" >' + ordersList.modifiedUser + '</td>');
            tr.append('<td name="uid" >' + ordersList.modifiedTime + '</td>');

            // tr.append('<td><input type="radio" name="isDelete" value="0" ' + (userList.isDelete == 0 ? 'checked' : '') + '>否 <input type="radio" name="isDelete" value="1" ' + (userList.isDelete == 1 ? 'checked' : '') + '>是</td>');
            // // tr.append('<td>' + (userList.isDelete ? '是' : '否') + '</td>');
            tr.append('<td>' +
                '<button class="btn btn-success btn-sm ordersUpdata" onclick="OrderUpdataEventFinish(this)">確定</button>' +
                '<button class="btn btn-warning btn-sm" onclick="getOrderData()">取消</button>' +
                '</td>');
            tbody.append(tr);

            table.append(tbody);

            orders.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}

function OrderUpdataEventFinish(clickedButton) {
    const productUpData_btnList = document.querySelectorAll(".ordersUpdata");
    const Oid = $('td[name="oid"]').text();
    const recv_name = $('input[name="recv_name"]').val();
    const recv_phone = $('input[name="recv_phone"]').val();
    const recv_province = $('input[name="recv_province"]').val();
    const recv_city = $('input[name="recv_city"]').val();
    const recv_area = $('input[name="recv_area"]').val();
    const recv_address = $('input[name="recv_address"]').val();


    alert("您點擊的是第 " + (Oid) + " 個按鈕");


    $.ajax({
        url: '/BackStage/order/modify/' + Oid,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            "oid" : Oid,
            "recvName" : recv_name,
            "recvPhone" : recv_phone,
            "recvProvince" : recv_province,
            "recvCity" : recv_city,
            "recvArea" : recv_area,
            "recvAddress" : recv_address
        }),
        success: function(result) {
            console.log(result);
            getOrderData();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });

}