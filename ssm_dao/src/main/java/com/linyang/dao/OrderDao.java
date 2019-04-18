package com.linyang.dao;

import com.linyang.domian.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {

    /**
     * 查询全部
     * @return
     */
    @Select("select * from orders")
    @Results({
            //使用注解方式完成表的一对一的映射关系  映射product属性
            /**
             * colum：对应列表字段
             * property：属性名字
             * one: 一对一
             * select：namespace + id
             */
            @Result(column = "productId",property = "product",
                one = @One(select = "com.linyang.dao.ProductDao.selectById")
            )
    })
    List<Order> findAll();

    @Insert("insert into orders(orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,productId) values(#{orderNum} ,#{orderTime} ,#{peopleCount},#{orderDesc}, #{payType}, #{orderStatus}, #{product.id})")
     void  save(Order order);

    //删除单个
    @Delete("DELETE FROM orders WHERE id = #{id}")
    void delOne(Integer id);
}
