package com.linyang.dao;

import com.linyang.domian.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ProductDao {
    /**
     * 查询全部
     *
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    @Insert("INSERT INTO product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) VALUES(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product WHERE id = #{id}")
    Product selectById(Integer id);

    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id}")
    void update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void delOne(Integer id);
}
