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
                tr.append("<td>" + (user.gender === 0 ? "女生" : "男生") + "</td>");
                tr.append('<td><img src="data:image/png;base64,' + user.avatar + '" alt="User Avatar" style="width:50px;height:50px;"></td>');
                tr.append('<td>' + (user.isDelete === 0 ? '否' : '是') + '</td>');
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

            const thead = $('<thead></thead>').append(`

                <tr class="product-list-tr" id="product-list-tr-img">
                    <td class="product-list-td-img">
                    <img id="productImage" src="data:image/png;base64,${userList.avatar}" style="width: 50%;" />
                </tr> 
                
                <tr>
                    <th class="product-list-th modify-td">ID</th>
                    <td class="product-list-td" name="uid" >${userList.uid}</td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">名稱</th>
                    <td><input class="product-list-input" name="username" value="${userList.username}"></td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">電話</th>
                    <td><input class="product-list-input" name="phone" value="${userList.phone}"></td>
                </tr>
                <tr>
                    <th class="product-list-th modify-td">郵件</th>
                    <td><input class="product-list-input" name="email" value="${userList.email}"></td>
                </tr>
                 <tr>
                    <th class="product-list-th modify-td">性別</th>
                    <td class="product-list-td" name="">
                        <label><input class="order-radio" type="radio" name="gender" value="0" ${userList.gender === 0 ? 'checked' : ''}> 女</label>
                        <label><input class="order-radio" type="radio" name="gender" value="1" ${userList.gender === 1 ? 'checked' : ''}> 男</label>
                    </td>
                </tr>
               
                <tr>
                    <th class="product-list-th modify-td">是否刪除</th>
                    <td class="product-list-td" name="">
                        <label><input class="order-radio" type="radio" name="is_delete" value="0" ${userList.isDelete === 0 ? 'checked' : ''}> 未刪除</label>
                        <label><input class="order-radio" type="radio" name="is_delete" value="1" ${userList.isDelete === 1 ? 'checked' : ''}> 已刪除</label>
                    </td>
                </tr>
                
                 <tr>
                    <th class="product-list-th">功能</th>
                    <td class="product-list-button-td">
                        <button class="btn btn-success productBtn" id="product-ok" onclick="userUpdataEventFinish(this)">確定</button>
                        <button class="btn btn-success productBtn" id="product-cancel" onclick="getUserData()">取消</button>
<!--                        <button class="btn btn-success productBtn" id="product-close" onclick="getProductData()">下架</button>-->
                    </td>
                </tr>  
            
            `);
                table.append(thead);

                memberContainer.append(table);
                changeColor();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('AJAX請求失敗：' + textStatus + ', ' + errorThrown);
        }
    });
}
function userUpdataEventFinish(clickedButton) {
    const userUpdata_btnList = document.querySelectorAll(".userUpdata");
    // const index = Array.prototype.indexOf.call(userUpdata_btnList, clickedButton) + 1;
    // const userNameValue = $('td[name="username"]').text();
    const uidValue = $('td[name="uid"]').text();

    const username = $('td[name="username"]').val();
    const phone = $('input[name="phone"]').val();
    const email = $('input[name="email"]').val();


    const userGender = getGenderJSON();
    const userIsDelete = getIsDeleteJSON();

    // const userGender = $('input[name="gender"]:checked').val();
    // const userIsDelete = $('input[name="isDelete"]:checked').val();
    // const userGender = $('input[name="gender"]:checked').val();
    // const userGender = $('input[name="phone"]').text();



    // alert("您點擊的是第 " + (uidValue) + " 個按鈕");


    $.ajax({
        url: '/BackStage/user/modify/' + uidValue,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            "uid": uidValue,
            "username": username,
            "phone" : phone,
            "email" : email,
            "gender": userGender,
            "isDelete": userIsDelete
        }),
        success: function(result) {
            console.log(result);
            getUserData();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });

}

function getGenderJSON() {
    // 获取所有单选按钮
    let radios = document.getElementsByName('gender');
    let statusValue;

    for (let i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            return statusValue = radios[i].value;
            // break;
        }
    }
}
function getIsDeleteJSON() {
    // 获取所有单选按钮
    let radios = document.getElementsByName('is_delete');
    let statusValue;

    for (let i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            return statusValue = radios[i].value;
            // break;
        }
    }
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