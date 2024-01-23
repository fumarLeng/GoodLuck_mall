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
                '<th class="member-list-th">密碼</th>' +
                '<th class="member-list-th">鹽值</th>' +
                '<th class="member-list-th">電話</th>' +
                '<th class="member-list-th">郵件</th>' +
                '<th class="member-list-th">性別</th>' +
                '<th class="member-list-th">頭像</th>' +
                '<th class="member-list-th">狀態</th>' +
                '<th class="member-list-th">操作</th>' +
                '</tr>');
            table.append(thead);

            let tbody = $('<tbody></tbody>');
            $.each(userList, function (index, user) {
                let tr = $('<tr></tr>');
                tr.append('<td>' + user.uid + '</td>');
                tr.append('<td>' + user.username + '</td>');
                tr.append('<td>' + user.password + '</td>');
                tr.append('<td>' + user.salt + '</td>');
                tr.append('<td>' + user.phone + '</td>');
                tr.append('<td>' + user.email + '</td>');
                tr.append('<td>' + user.gender + '</td>');
                tr.append('<td><img src="data:image/png;base64,' + user.avatar + '" alt="User Avatar" style="width:50px;height:50px;"></td>');
                tr.append('<td>' + (user.isDelete ? '是' : '否') + '</td>');
                tr.append('<td>' +
                    '<button class="btn btn-success btn-sm">修改</button>' +
                    '<button class="btn btn-warning btn-sm">刪除</button>' +
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
