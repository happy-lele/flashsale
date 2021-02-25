
package com.le.flashsale.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.le.flashsale.entity.ProductPO;
import com.le.flashsale.product.dto.ProductDTO;

/**
 * Date 2020/11/16 4:16 下午
 * Author le
 */
@Mapper(componentModel = "spring")
public interface ProductConverter {

    ProductConverter instance = Mappers.getMapper(ProductConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    ProductPO dto2Po(ProductDTO productDTO);

    ProductDTO po2Dto(ProductPO productPO);

    List<ProductPO> dtos2Pos(List<ProductDTO> productDTOs);

    List<ProductDTO> pos2Dtos(List<ProductPO> productPOs);
}
