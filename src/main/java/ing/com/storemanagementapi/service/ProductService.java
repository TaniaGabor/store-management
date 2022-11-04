package ing.com.storemanagementapi.service;

import ing.com.storemanagementapi.dto.ProductDto;
import ing.com.storemanagementapi.exception.ApiEntityNotFoundException;
import ing.com.storemanagementapi.mapper.ProductMapper;
import ing.com.storemanagementapi.model.Product;
import ing.com.storemanagementapi.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductService {
    final ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .parallelStream()
                .map(mapper::productToProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiEntityNotFoundException("product", id));
        return mapper.productToProductDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productRepository.save(mapper.productDtoToProduct(productDto));
        return mapper.productToProductDto(product);
    }

    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product product = mapper.productDtoToProduct(productDto);
        product.setId(id);
        productRepository.findById(id)
                .ifPresentOrElse(
                        (val) -> {
                            val.setName(product.getName());
                            val.setPrice(product.getPrice());
                            val.setQuantity(product.getQuantity());
                        },
                        () -> {
                            productRepository.save(product);
                        }
                );
        return mapper.productToProductDto(product);
    }

    public void deleteProductById(long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        (product) -> {
                            productRepository.delete(product);
                        },
                        () -> {
                            throw new ApiEntityNotFoundException("product", id);
                        }
                );
    }

}
