
package com.le.flashsale.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.product.dao.ProductDAO;
import com.le.flashsale.product.dto.ProductDTO;
import com.le.flashsale.product.service.ProductService;

/**
 * Date 2020/11/16 1:49 下午
 * Author le
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDAO productDAO;

    @Override
    public ProductDTO queryProductDetail(Long productId) {
        return productDAO.selectByPrimaryKey(productId);
    }

    @Override
    public List<ProductDTO> queryAllProduct() {
        return productDAO.selectAll();
    }
}
