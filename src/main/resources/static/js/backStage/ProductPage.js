// function ProductPage() {
//
// // 假資料，包含所有內容
//     let allItems = [
//         "項目1", "項目2", "項目3", "項目4", "項目5",
//         "項目6", "項目7", "項目8", "項目9", "項目10",
//         "項目11", "項目12", "項目13", "項目14", "項目15"
//     ];
//
// // 每頁顯示的項目數量
//     let itemsPerPage = 5;
//
// // 當前頁碼
//     let currentPage = 1;
//
// // 更新分頁器和顯示內容的函數
//     function updatePage() {
//         let startIndex = (currentPage - 1) * itemsPerPage;
//         let endIndex = startIndex + itemsPerPage;
//
//         let currentItems = allItems.slice(startIndex, endIndex);
//
//         // 清空內容和分頁器
//         document.getElementById("content").innerHTML = "";
//         document.getElementById("pagination").innerHTML = "";
//
//         // 顯示當前頁的內容
//         currentItems.forEach(function (item) {
//             let itemElement = document.createElement("div");
//             itemElement.textContent = item;
//             document.getElementById("content").appendChild(itemElement);
//         });
//
//         // 創建分頁按鈕
//         let totalPages = Math.ceil(allItems.length / itemsPerPage);
//         for (let i = 1; i <= totalPages; i++) {
//             let pageButton = document.createElement("button");
//             pageButton.textContent = i;
//             pageButton.addEventListener("click", function () {
//                 currentPage = parseInt(this.textContent);
//                 updatePage();
//             });
//             document.getElementById("pagination").appendChild(pageButton);
//         }
//
//         // 更新上一頁和下一頁按鈕
//         let prevButton = document.getElementById("prev-button");
//         let nextButton = document.getElementById("next-button");
//
//         prevButton.disabled = currentPage === 1;
//         nextButton.disabled = currentPage === totalPages;
//
//         // 更新目前頁碼的顯示
//         document.getElementById("current-page").textContent = "目前頁碼：" + currentPage;
//     }
//
// // 上一頁按鈕事件處理
//     document.getElementById("prev-button").addEventListener("click", function () {
//         if (currentPage > 1) {
//             currentPage--;
//             updatePage();
//         }
//     });
//
// // 下一頁按鈕事件處理
//     document.getElementById("next-button").addEventListener("click", function () {
//         let totalPages = Math.ceil(allItems.length / itemsPerPage);
//         if (currentPage < totalPages) {
//             currentPage++;
//             updatePage();
//         }
//     });
//
// // 初始化頁面
//     updatePage();
//
// }