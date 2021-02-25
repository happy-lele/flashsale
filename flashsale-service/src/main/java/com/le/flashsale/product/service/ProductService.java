
package com.le.flashsale.product.service;

import java.util.List;

import com.le.flashsale.product.dto.ProductDTO;

/**
 * Date 2020/11/16 1:49 下午
 * Author le
 */
public interface ProductService {

    ProductDTO queryProductDetail(Long productId);


    List<ProductDTO> queryAllProduct();

}
