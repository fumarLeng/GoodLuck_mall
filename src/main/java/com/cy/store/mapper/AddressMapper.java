package com.cy.store.mapper;


import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


//收貨地址
public interface AddressMapper {
    /**
     * 新增會員收貨地址
     * @param address 收貨地址
     * @return 影響的筆數
     */
    Integer insert(Address address);

    /**
     * 根據會員id統計地址數量
     * @param uid 會員id
     * @return  目前會員的收貨地址總數
     */
    Integer countByUid(Integer uid);


    /**
     * 根據會員id查詢收貨地址
     * @param uid 會員id
     * @return 收貨地址
     */
    List<Address> findByUid(Integer uid);

//------------------------------------------------------------//
    /**
     * 根據aid查詢收貨地址
     * @param aid 收貨地址id
     * @return 收貨地址資料,沒找到資料返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根據會員uid來修改會員收貨地址,先全部改成is_default=0
     * @param uid 會員id
     * @return 影響的筆數
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根據aid找到的紀錄把is_default改成1
     * @param aid 收貨地址aid
     * @param modifiedUser 更改人
     * @param modifiedTime 更改時間
     * @return
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

//------------------------------------------------------------//

    /**
     * 根據收貨地址aid刪除數據
     * @param aid 收貨地址編號
     * @return  影響的筆數
     */
    Integer deleteByAid(Integer aid);


    /**
     * 查詢某個會員的收貨地址最後一次更改的資料,
     * 這樣當會員刪除默認的地址之後,可以查詢出來自動設定新的默認
     * @param uid 會員uid
     * @return 最後一次更改的收貨地址
     */

    Address findLastModified(Integer uid);

    //----------------------------------------------//

}
