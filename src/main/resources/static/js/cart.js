/*按加号数量增*/
function addNum(rid) {
	var n = parseInt($("#goodsCount"+rid).val());
	$("#goodsCount"+rid).val(n + 1);
	calcRow(rid);
}
/*案減號數量減*/
function reduceNum(rid) {
	var n = parseInt($("#goodsCount"+rid).val());
	if (n == 0)
		return;
	$("#goodsCount"+rid).val(n - 1);
	calcRow(rid);
}
/*全選或不選*/
function checkall(ckbtn) {
	$(".ckitem").prop("checked", $(ckbtn).prop("checked"));
	//calcTotal();
}
//删除按钮
function delCartItem(btn) {
	
	$(btn).parents("tr").remove();
	//calcTotal();
}
//批量删除按钮
function selDelCart() {
	//遍历所有按钮
	for (var i = $(".ckitem").length - 1; i >= 0; i--) {
		//如果选中
		if ($(".ckitem")[i].checked) {
			//删除
			$($(".ckitem")[i]).parents("tr").remove();
		}
	}
	//calcTotal();
}
$(function() {
	//單選一個也要算價格
	$(".ckitem").click(function() {
			//calcTotal();
		})
		//開始時就算價格
		//calcTotal();
})
//計算小季
function calcRow(rid) {
	//拿單價
	var vprice = parseFloat($("#goodsPrice"+rid).html());
	//取數量
	var vnum = parseFloat($("#goodsCount"+rid).val());
	//小计金额
	var vtotal = vprice * vnum;
	//給他一個職
	$("#goodsCast"+rid).html("¥" + vtotal);
}
//计算总价格的方法
/*
function calcTotal() {
	//选中商品的数量
	var vselectCount = 0;
	//选中商品的总价
	var vselectTotal = 0;

	//循环遍历所有tr
	for (var i = 0; i < $(".cart-body tr").length; i++) {
		//计算每个商品的价格小计开始
		//取出1行
		var $tr = $($(".cart-body tr")[i]);
		//取单价
		var vprice = parseFloat($tr.children(":eq(3)").children("span").html());
		//取数量
		var vnum = parseFloat($tr.children(":eq(4)").children(".num-text").val());
		//小计金额
		var vtotal = vprice * vnum;
		//赋值
		$tr.children(":eq(5)").children("span").html("¥" + vtotal);
		//计算每个商品的价格小计结束

		//检查是否选中
		if ($tr.children(":eq(0)").children(".ckitem").prop("checked")) {
			//计数
			vselectCount++;
			//计总价
			vselectTotal += vtotal;
		}
		//将选中的数量和价格赋值
		$("#selectTotal").html(vselectTotal);
		$("#selectCount").html(vselectCount);
	}
}*/