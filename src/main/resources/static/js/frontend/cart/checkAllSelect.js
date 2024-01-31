function checkAllSelect(btn){

    const checkList = document.querySelectorAll(".ckitem");
    for(let i = 0 ; i < checkList.length ; i++){
        if(btn.checked === true){
            checkList[i].checked = true;
        }else{
            checkList[i].checked = false;
        }
    }

}

