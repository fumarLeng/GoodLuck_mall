
//選中購物車產品 會計算下面總數
// function checkAllSelect(btn){
//
//     const checkList = document.querySelectorAll(".ckitem");
//     for(let i = 0 ; i < checkList.length ; i++){
//         if(btn.checked === true){
//             checkList[i].checked = true;
//         }else{
//             checkList[i].checked = false;
//         }
//     }
//
//     const selectTotal = document.querySelectorAll(".num-text");
//     let TotalPrice;
//     const TotalPriceList = document.querySelectorAll("#total-price-12").values();
//     for(let i = 0 ; i < TotalPriceList.length ; i++){
//         TotalPrice += TotalPriceList[i];
//     }
//
//     TotalPrice.innerText = TotalPrice;
// }



function updateTotal() {
    let totalNum = 0;
    let totalPrice = 0;
    $(".ckitem:checked").each(function () {
        let cid = $(this).val();
        let num = parseInt($("#num-" + cid).val());
        let price = parseFloat($("#price-" + cid).html());
        totalNum += num;
        totalPrice += price * num;
    });
    $("#selectCount").html(totalNum);
    $("#selectTotal").html(totalPrice);
}

$(document).on("change", ".ckitem", function () {
    updateTotal();
});


// ========================================================================

//顯示購物車裡面的產品
function showCartList() {
    //先清空
    $('#cart-list').empty();
    $.ajax({
        url: "/carts",
        type: "GET",
        dataType: "JSON",
        success: function (json) {
            let list = json.data;
            let selectCount = 0;
            let selectTotal = 0;
            for (let i = 0; i < list.length; i++) {
                let tr = '<tr>'
                    + '<td>'
                    + '<input name="cids" value="#{cid}" type="checkbox" class="ckitem" onclick=""/>'
                    + '</td>'
                    + '<td><img src="..#{image}collect.png" class="img-responsive" /></td>'
                    + '<td>#{title}</td>'
                    + '<td>$<span id="price-#{cid}">#{realPrice}</span></td>'
                    + '<td id="ctrl-td">'
                    + '<input type="button" value="-" class="num-btn" onclick="reduceNum(#{cida})" />'
                    + '<input id="num-#{cid}" type="text" size="2" readonly="readonly" class="num-text" value="#{num}">'
                    + '<input class="num-btn" type="button" value="+" onclick="addNum(#{cid})" />'
                    + '</td>'
                    + '<td>$<span id="total-price-#{cid}">#{totalPrice}</span></td>'
                    + '<td>'
                    + '<input type="button" onclick="delCartItem(#{deleteCid})" class="cart-del btn btn-default btn-xs" value="刪除" />'
                    + '</td>'
                    + '</tr>';
                tr = tr.replace(/#{cida}/g, list[i].cid);
                tr = tr.replace(/#{cid}/g, list[i].cid);
                tr = tr.replace("#{deleteCid}", list[i].cid)
                tr = tr.replace(/#{title}/g, list[i].title);
                tr = tr.replace(/#{image}/g, list[i].image);
                tr = tr.replace(/#{realPrice}/g, list[i].realPrice);
                tr = tr.replace(/#{num}/g, list[i].num);
                tr = tr.replace(/#{totalPrice}/g, list[i].realPrice * list[i].num);

                $("#cart-list").append(tr);
                // selectCount += list[i].num;
                // selectTotal += list[i].realPrice * list[i].num;
                // $("#selectCount").html(selectCount);
                // $("#selectTotal").html(selectTotal);
            }
        }
    });
}

// 要增加購物車產品數量會執行的方法
function addNum(cid) {
    $.ajax({
        url: "/carts/" + cid + "/num/add",
        type: "POST",
        dataType: "JSON",
        success: function (json) {
            if (json.state == 200) {
                $("#num-" + cid).val(json.data);
                let price = $("#price-" + cid).html();
                let totalPrice = price * json.data;
                $("#total-price-" + cid).html(totalPrice);
                updateTotal();

            } else {
                alert("增加購物車數量失敗" + json.message);
            }
        },
        error: function (xhr) {
            alert("請重新登錄" + xhr.message);
            location.href = "login.html";
        }
    });
}

// 要減少購物車產品數量會執行的方法
function reduceNum(cida) {
    $.ajax({
        url: "/carts/" + cida + "/num/reduce",
        type: "POST",
        dataType: "JSON",
        success: function (json) {
            if (json.state == 200) {
                $("#num-" + cida).val(json.data);
                let price = $("#price-" + cida).html();
                let totalPrice = price * json.data;
                $("#total-price-" + cida).html(totalPrice);
                updateTotal();
            } else {
                alert("增加購物車數量失敗" + json.message);
            }
        },
        error: function (xhr) {
            alert("請重新登錄" + xhr.message);
            location.href = "login.html";
        }
    });
}

//刪除按鈕點擊
function delCartItem(deleteCid) {
    console.log(deleteCid);
    if (confirm("確定要刪除嗎?")) {
        $.ajax({
            url: "/carts/deleteCart",
            type: "POST",
            data: "cids=" + deleteCid,
            dataType: "json",
            success: function (json) {
                alert("刪除成功")
                location.reload();
            },
            error: function (xhr) {
                alert("刪除失敗" + xhr.message)
            }
        })
    }
}