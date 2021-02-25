package com.le.flashsale.converter;

import com.le.flashsale.entity.ProductPO;
import com.le.flashsale.product.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:39:36+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class ProductConverterImpl implements ProductConverter {

    @Override
    public ProductPO dto2Po(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        ProductPO productPO = new ProductPO();

        productPO.setName( productDTO.getName() );
        productPO.setId( productDTO.getId() );
        productPO.setCode( productDTO.getCode() );
        productPO.setRemark( productDTO.getRemark() );
        productPO.setDeleted( productDTO.getDeleted() );
        productPO.setCreateTime( productDTO.getCreateTime() );
        productPO.setUpdateTime( productDTO.getUpdateTime() );

        return productPO;
    }

    @Override
    public ProductDTO po2Dto(ProductPO productPO) {
        if ( productPO == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( productPO.getId() );
        productDTO.setName( productPO.getName() );
        productDTO.setCode( productPO.getCode() );
        productDTO.setRemark( productPO.getRemark() );
        productDTO.setDeleted( productPO.getDeleted() );
        productDTO.setCreateTime( productPO.getCreateTime() );
        productDTO.setUpdateTime( productPO.getUpdateTime() );

        return productDTO;
    }

    @Override
    public List<ProductPO> dtos2Pos(List<ProductDTO> productDTOs) {
        if ( productDTOs == null ) {
            return null;
        }

        List<ProductPO> list = new ArrayList<ProductPO>( productDTOs.size() );
        for ( ProductDTO productDTO : productDTOs ) {
            list.add( dto2Po( productDTO ) );
        }

        return list;
    }

    @Override
    public List<ProductDTO> pos2Dtos(List<ProductPO> productPOs) {
        if ( productPOs == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( productPOs.size() );
        for ( ProductPO productPO : productPOs ) {
            list.add( po2Dto( productPO ) );
        }

        return list;
    }
}
