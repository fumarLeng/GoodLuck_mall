function getUserData() {
    $.ajax({
        url: '/BackStage/user',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let userList = data;
            let memberContainer = $('#member');
            memberContainer.empty();

            let table = $('<table></table>').addClass('table');

            let thead = $('<thead></thead>');
            thead.append('<tr>' +
                '<th class="member-list-th">ID</th>' +
                '<th class="member-list-th">名稱</th>' +
                // '<th class="member-list-th">密碼</th>' +
                // '<th class="member-list-th">加密(鹽值)</th>' +
                '<th class="member-list-th">電話</th>' +
                '<th class="member-list-th">郵件</th>' +
                '<th class="member-list-th">性別</th>' +
                '<th class="member-list-th">頭像</th>' +
                '<th class="member-list-th">是否刪除</th>' +
                '<th class="member-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            $.each(userList, function (index, user) {
                let tr = $('<tr></tr>');
                tr.append('<td>' + user.uid + '</td>');
                tr.append('<td>' + user.username + '</td>');
                // tr.append('<td>' + user.password + '</td>');
                // tr.append('<td>' + user.salt + '</td>');
                tr.append('<td>' + user.phone + '</td>');
                tr.append('<td>' + user.email + '</td>');
                // tr.append('<td>' + user.gender + '</td>');
                tr.append("<td>" + (user.gender == 0 ? "女生" : "男生") + "</td>");
                tr.append('<td><img src="data:image/png;base64,' + user.avatar + '" alt="User Avatar" style="width:50px;height:50px;"></td>');
                tr.append('<td>' + (user.isDelete ? '是' : '否') + '</td>');
                tr.append('<td>' +
                    '<button class="btn btn-success btn-sm userUpdata" onclick="userUpdataEvent(this)">修改</button>' +
                    // '<button class="btn btn-warning btn-sm">刪除</button>' +
                    '</td>');
                tbody.append(tr);
            });
            table.append(tbody);

            memberContainer.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}

function userUpdataEvent(clickedButton) {
    const userUpdata_btnList = document.querySelectorAll(".userUpdata");
    const index = Array.prototype.indexOf.call(userUpdata_btnList, clickedButton) + 1;
    // alert("您點擊的是第 " + (index) + " 個按鈕");


    $.ajax({

        url: '/BackStage/user/' + index,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            let userList = data;
            let memberContainer = $('#member');
            memberContainer.empty();

            let table = $('<table></table>').addClass('table');

            let thead = $('<thead></thead>');
            thead.append('<tr>' +
                '<th class="member-list-th">ID</th>' +
                '<th class="member-list-th">名稱</th>' +
                // '<th class="member-list-th">密碼</th>' +
                // '<th class="member-list-th">加密(鹽值)</th>' +
                '<th class="member-list-th">電話</th>' +
                '<th class="member-list-th">郵件</th>' +
                '<th class="member-list-th">性別</th>' +
                '<th class="member-list-th">頭像</th>' +
                '<th class="member-list-th">是否刪除</th>' +
                '<th class="member-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            // $.each(userList, function (index, user) {
            let tr = $('<tr></tr>');
                // tr.append('<td><input class="user-input" name="uid" value="' + userList.uid + '"></td>');
                // tr.append('<td><input class="user-input" name="username" value="' + userList.username + '"></td>');
                // tr.append('<td><input value="' + userList.password + '"></td>');
                // tr.append('<td><input value="' + userList.salt + '"></td>');
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

                memberContainer.append(table);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}
function userUpdataEventFinish(clickedButton) {
    const userUpdata_btnList = document.querySelectorAll(".userUpdata");
    const index = Array.prototype.indexOf.call(userUpdata_btnList, clickedButton) + 1;
    const uidValue = $('td[name="uid"]').text();
    const userNameValue = $('td[name="username"]').text();
    const userGender = $('input[name="gender"]:checked').val();
    const userIsDelete = $('input[name="isDelete"]:checked').val();
    // const userGender = $('input[name="gender"]:checked').val();
    // const userGender = $('input[name="phone"]').text();



    alert("您點擊的是第 " + (index) + " 個按鈕");


    $.ajax({
        url: '/BackStage/user/modify/' + index,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            "uid": uidValue,
            "username": userNameValue,
            "gender": userGender,
            "isDelete": userIsDelete
        }),
        success: function(result) {
            // 請求成功時的回調函數
            console.log(result);
            getUserData();
        },
        error: function(xhr, status, error) {
            // 請求失敗時的回調函數
            console.error(error);
        }
    });

}