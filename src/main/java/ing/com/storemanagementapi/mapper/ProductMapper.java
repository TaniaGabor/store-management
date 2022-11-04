package ing.com.storemanagementapi.mapper;

import ing.com.storemanagementapi.dto.ProductDto;
import ing.com.storemanagementapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);
}
