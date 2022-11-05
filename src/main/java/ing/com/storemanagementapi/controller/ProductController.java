package ing.com.storemanagementapi.controller;

import ing.com.storemanagementapi.dto.ProductDto;
import ing.com.storemanagementapi.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getAllProducts() {
        log.info("Get all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        log.info("Product created successfully");
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable("id") long id, @Valid @RequestBody ProductDto productDto) {
        log.info("Product was updated");
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") long id) {
        log.info("Product was deleted");
        productService.deleteProductById(id);
    }
}
