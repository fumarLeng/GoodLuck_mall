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
            thead.append(`<tr>
                <th class="member-list-th">訂單id</th>
                <th class="member-list-th">會員id</th>
                <th class="member-list-th">收貨人姓名</th>
                <th class="member-list-th">收貨人電話</th>
                <th class="member-list-th">收貨人國家</th>
                <th class="member-list-th">收貨人市</th>
                <th class="member-list-th">收貨人區</th>
                <th class="member-list-th">收貨詳細地址</th>
                <th class="member-list-th">總價</th>
                <th class="member-list-th">狀態</th>
                <th class="member-list-th">下單時間</th>
                <th class="member-list-th">付款時間</th>
                <th class="member-list-th">操作</th>
                </tr>`);
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
                tr.append('<td class="statusList">' + order.status  + '</td>');
                tr.append('<td>' + order.orderTime + '</td>');
                tr.append('<td>' + order.payTime + '</td>');

                tr.append('<td>' +
                    '<button class="btn btn-success btn-sm ordersUpData" onclick="OrderUpdataEvent(this)">修改</button>' +
                    '</td>');
                tbody.append(tr);
            });
            table.append(tbody);

            ordersContainer.append(table);

            changeSwitchNameOrder();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}
function changeSwitchNameOrder(){
    const statusLis = document.querySelectorAll(".statusList");

    for(let i = 0 ; i < statusLis.length ; i++){
        switch (statusLis[i].innerText){
            case "0":
                statusLis[i].classList.add("statusRed");
                statusLis[i].innerText = "已付款"
                break;
            case "1":
                statusLis[i].classList.add("statusGreen");
                statusLis[i].innerText = "完成"
                break;
            // case "2":
            //     statusLis[i].classList.add("statusYellow");
            //     statusLis[i].innerText = "已取消"
            //     break;
            // case "3":
            //     statusLis[i].classList.add("statusRed");
            //     statusLis[i].innerText = "已關閉"
            //     break;
            // case "4":
            //     statusLis[i].classList.add("statusGreen");
            //     statusLis[i].innerText = "已完成"
            //     break;
        }
    }
}


function OrderUpdataEvent(clickedButton) {

    const ordersUpData_btnList = document.querySelectorAll(".ordersUpData");
    const index = Array.prototype.indexOf.call(ordersUpData_btnList, clickedButton);
    const orderOid = $($($(clickedButton).closest('tr')).find('td[name="orderOid"]')).text();

    // alert("btn" + (index + 1) + "id: " + orderOid);

    $.ajax({

        url: '/BackStage/order/' + orderOid + "/" + index,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            let ordersList = data;
            let orders = $('#orders');
            orders.empty();

            let table = $('<table></table>').addClass('table');

            const thead = $('<thead></thead>').append(`
                
                <tr>
                    <th class="product-list-th modify-td">訂單id</th>
                    <td class="product-list-td" name="oid" >${ordersList.oid}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">會員id</th>
                    <td class="product-list-td" name="uid" >${ordersList.uid}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">收貨人姓名</th>
                    <td class="product-list-td" name="recv_name" >${ordersList.recvName}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">收貨人電話</th>
                    <td class="product-list-td" name="recv_phone" >${ordersList.recvPhone}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">收貨人國家</th>
                    <td class="product-list-td" name="recv_province" >${ordersList.recvProvince}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">收貨人市</th>
                    <td class="product-list-td" name="recv_city" >${ordersList.recvCity}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">收貨人區</th>
                    <td class="product-list-td" name="recv_area" >${ordersList.recvArea}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">收貨詳細地址</th>
                    <td class="product-list-td" name="recv_address" >${ordersList.recvAddress}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">總價</th>
                    <td class="product-list-td" name="total_price" >${ordersList.totalPrice}</td>
                </tr>
                
                <tr>
                    <th class="product-list-th modify-td">狀態</th>
                    <td class="product-list-td" name="uid">
                        <label><input class="order-radio" type="radio" name="status" value="0" ${ordersList.status === 0 ? 'checked' : ''}> 未到貨</label>
                        <label><input class="order-radio" type="radio" name="status" value="1" ${ordersList.status === 1 ? 'checked' : ''}> 完成</label>
                     
                    </td>
                </tr>

              
                <tr>
                    <th class="product-list-th modify-td">下單時間</th>
                    <td class="product-list-td" name="order_time" >${ordersList.orderTime}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">付款時間</th>
                    <td class="product-list-td" name="pay_time" >${ordersList.payTime}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">創建人</th>
                    <td class="product-list-td" name="created_user" >${ordersList.createdUser}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">創建時間</th>
                    <td class="product-list-td" name="created_time" >${ordersList.createdTime}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">修改人</th>
                    <td class="product-list-td" name="modified_user" >${ordersList.modifiedUser}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">修改時間</th>
                    <td class="product-list-td" name="modified_time" >${ordersList.modifiedTime}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">操作</th>
                    <td class="product-list-button-td">
                        <button class="btn btn-success productBtn" id="product-ok" onclick="OrderUpdataEventFinish(this)">確定</button>
                        <button class="btn btn-success productBtn" id="product-cancel" onclick="getOrderData()">取消</button>
<!--                        <button class="btn btn-success productBtn" id="product-close" onclick="getOrderData()">下架</button>-->
                    </td>
                </tr>
                
             
                `);

            table.append(thead);

            orders.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}
function getStatusJSON() {
    // 获取所有单选按钮
    let radios = document.getElementsByName('status');
    let statusValue;

    for (let i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            return statusValue = radios[i].value;
            // break;
        }
    }

}

function OrderUpdataEventFinish(clickedButton) {
    const productUpData_btnList = document.querySelectorAll(".ordersUpdata");
    const Oid = $('td[name="oid"]').text();
    const status = getStatusJSON();
    // const recv_name = $('input[name="recv_name"]').val();
    // const recv_phone = $('input[name="recv_phone"]').val();
    // const recv_province = $('input[name="recv_province"]').val();
    // const recv_city = $('input[name="recv_city"]').val();
    // const recv_area = $('input[name="recv_area"]').val();
    // const recv_address = $('input[name="recv_address"]').val();
    //


    // alert("您點擊的是第 " + (Oid) + " 個按鈕");


    $.ajax({
        url: '/BackStage/order/modify/' + Oid,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            "oid" : Oid,
            "status" : status
            // "recvName" : recv_name,
            // "recvPhone" : recv_phone,
            // "recvProvince" : recv_province,
            // "recvCity" : recv_city,
            // "recvArea" : recv_area,
            // "recvAddress" : recv_address
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

