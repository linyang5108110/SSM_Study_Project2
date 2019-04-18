package com.linyang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linyang.dao.ProductDao;
import com.linyang.domian.PageBean;
import com.linyang.domian.Product;
import com.linyang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.print.SunPageSelection;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> finall() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product selectById(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delOne(Integer id) {
        productDao.delOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {
        if (ids != null) {
            for (Integer id : ids) {
                this.delOne(id);
            }
        }
    }

    @Override
    public PageBean<Product> findByPage(Integer pageNum, Integer pageSize) {

//        PageBean<Product> pageBean = new PageBean<>();
//        //封装pageBean
//        //总条数:从数据库查询出来 private Integer totalCount;
//        Integer totalCount = productDao.findTotalCount();
//        pageBean.setTotalCount(totalCount);
//        //每页的条数：从页面传参  private Integer pageSize;
//        pageBean.setPageSize(pageSize);
//        //总页数： Math.ceil(totalCount * 1.0 / pageSize)
//        //Math.ceil :向上取整，如果能被整除，结果是本身，如果不能被整除，向上加一
//        // private Integer totalPage;
//        pageBean.setTotalPage((int) Math.ceil(totalCount * 1.0 / pageSize));
//        //当前页:页面传参 private Integer pageNum;
//        pageBean.setPageNum(pageNum);
//        //数据: 从数据库中查询出来  private List<T> list;
//        //根据分页参数查询：起始行号，结束行号
//        /**
//         * 每页5条:pageSize = 5
//         * 第1页  起始行号 1  结束行号 5
//         * 第2页  起始行号 6  结束行号 10
//         * 第3页  起始行号 11  结束行号 15
//         * 第n页  起始行号 5n-(5-1)    结束行号 5n
//         * 第pageNum页 起始行号 pageSize * pageNum - (pageSize - 1) 结束行号：pageSize * pageNum
//         *
//         * pageSize * pageNum - (pageSize - 1)
//         * pageSize * pageNum  - pageSize + 1
//         * pageSize*（pageNum -1） +1
//         */
//        //起始行号
//        Integer startRow = pageSize * pageNum - (pageSize - 1);
//        Integer endRow = pageSize * pageNum;
//        List<Product> productList = productDao.findByPage(startRow, endRow);
//        pageBean.setList(productList);
//
//        return pageBean;


        //封装pageBean
        PageBean<Product> productPageBean = new PageBean<>();
        //查询数据总条数
        Integer totalCount = productDao.findTotalCount();
        productPageBean.setTotalCount(totalCount);
        //每页的条数：从页面传参  private Integer pageSize;
        productPageBean.setPageSize(pageSize);
        //总页数： Math.ceil(totalCount * 1.0 / pageSize) 全部多少页
        //Math.ceil :向上取整，如果能被整除，结果是本身，如果不能被整除，向上加一
        // private Integer totalPage;
        productPageBean.setTotalPage((int) Math.ceil(totalCount * 1.0 / pageSize));
        //当前页:页面传参 private Integer pageNum; 第几页
        productPageBean.setPageNum(pageNum);
        //起始行号
        Integer startRow = pageSize * pageNum - (pageSize - 1);
        Integer endRow = pageSize * pageNum;
        List<Product> productList = productDao.findByPage(startRow, endRow);
        productPageBean.setPageList(productList);

        return productPageBean;
    }

    @Override
    public void testPagezInfo(Integer pageNum, Integer pageSize) {
        //调用PageHelper静态方法设置分页
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<Product> all = productDao.findAll();
        //创建pageInfo对象
        PageInfo<Product> pageInfo = new PageInfo<>(all);

        System.out.println("总条数:" + pageInfo.getTotal());
        System.out.println("总页数:" + pageInfo.getPages());
        System.out.println("当前页:" + pageInfo.getPageNum());
        System.out.println("当前页显示条数:" + pageInfo.getPageSize());
        System.out.println("是否是第一页:" + pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页:" + pageInfo.isIsLastPage());
        System.out.println("上一页是:" + pageInfo.getPrePage());
        System.out.println("下一页是:" + pageInfo.getNextPage());

        System.out.println("当前页数据是:");
        List<Product> list = pageInfo.getList();
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
