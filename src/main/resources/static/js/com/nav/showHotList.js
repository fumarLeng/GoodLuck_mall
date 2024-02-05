function showHotList() {
    $("#hot-list").empty();
    $.ajax({
        url: "/products/hot_list",
        type: "GET",
        dataType: "JSON",
        success: function (json) {
            let list = json.data;
            console.log("count=" + list.length);
            for (let i = 0; i < list.length; i++) {
                console.log(list[i].title);
                // let hot =
                //     '<div class="col-md-12">'
                //     + '<div class="col-md-7 text-row-2"><a href="product.html?id=#{id}">#{title}</a></div>'
                //     + '<div class="col-md-2">$#{price}</div>'
                //     + '<div class="col-md-3"><img src="..#{image}collect.png" class="img-responsive" /></div>'
                //     + '</div>';
                let hot =
                    '<div class="">'
                    + '<div class="list-sub">'
                    + '<a href="product.html?id=#{id}" class="product-title-a">'
                    + '<img src="data:image/png;base64,#{image}" class="" />'
                    + '<p>#{title}</p>'
                    + '</a>'
                    + '<div class="">$#{price}</div>'
                    + '</div>'
                    + '</div>';

                hot = hot.replace(/#{id}/g, list[i].id);
                hot = hot.replace(/#{title}/g, list[i].title);
                hot = hot.replace(/#{price}/g, list[i].price);
                hot = hot.replace(/#{image}/g, list[i].image);

                $("#hot-list").append(hot);
            }
        }
    });
}