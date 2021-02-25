
package com.le.flashsale.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.le.flashsale.common.Constant;
import com.le.flashsale.dto.BaseResponse;
import com.le.flashsale.enums.StatusCodeEnums;
import com.le.flashsale.product.service.ProductService;

/**
 * Date 2020/11/16 8:32 上午
 * Author le
 */
@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @Resource
    private ProductService productService;

    /**
     * 查询产品详情
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/product/detail/{product_id}")
    public BaseResponse queryProductDetail(@PathVariable("product_id") Long productId) {
        if (productId == null) {
            return BaseResponse.getFailResponse("产品id不能为空！");
        }

        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        baseResponse.setData(productService.queryProductDetail(productId));

        return baseResponse;
    }

    @RequestMapping(value = Constant.HTTP_PREFIX + "/product/list", method = RequestMethod.POST)
    public BaseResponse queryProductList() {
        BaseResponse baseResponse = new BaseResponse(StatusCodeEnums.SUCCESS.getCode(), "SUCCESS");
        baseResponse.setData(productService.queryAllProduct());
        return baseResponse;
    }

    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot";
    }
}
