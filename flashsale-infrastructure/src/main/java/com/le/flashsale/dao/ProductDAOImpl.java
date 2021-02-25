
package com.le.flashsale.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.converter.ProductConverter;
import com.le.flashsale.entity.ProductPO;
import com.le.flashsale.mapper.ProductMapper;
import com.le.flashsale.product.dao.ProductDAO;
import com.le.flashsale.product.dto.ProductDTO;

/**
 * Date 2020/11/16 4:11 下午
 * Author le
 */
@Service
public class ProductDAOImpl implements ProductDAO {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductConverter converter;

    public int deleteByPrimaryKey(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    public int insert(ProductDTO productDTO) {
        ProductPO productPO = converter.dto2Po(productDTO);
        int num = productMapper.insert(productPO);
        productDTO.setId(productPO.getId());
        return num;
    }

    public int insertSelective(ProductDTO productDTO) {
        ProductPO productPO = converter.dto2Po(productDTO);
        int num = productMapper.insertSelective(productPO);
        productDTO.setId(productPO.getId());
        return num;
    }

    public ProductDTO selectByPrimaryKey(Long id) {
        return converter.po2Dto(productMapper.selectByPrimaryKey(id));
    }

    public List<ProductDTO> selectAll() {
        return converter.pos2Dtos(productMapper.selectAll());
    }

    public int updateByPrimaryKeySelective(ProductDTO productDTO) {
        return productMapper.updateByPrimaryKeySelective(converter.dto2Po(productDTO));
    }

    public int updateByPrimaryKey(ProductDTO productDTO) {
        return productMapper.updateByPrimaryKey(converter.dto2Po(productDTO));
    }
}
