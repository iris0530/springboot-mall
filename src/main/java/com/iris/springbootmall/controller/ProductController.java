package com.iris.springbootmall.controller;

import com.iris.springbootmall.constant.ProductCategory;
import com.iris.springbootmall.dto.ProductQueryParams;
import com.iris.springbootmall.dto.ProductRequestDTO;
import com.iris.springbootmall.model.Product;
import com.iris.springbootmall.service.ProductService;
import com.iris.springbootmall.util.Page;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            // 查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            
            // 排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            
            // 分頁 Pagination 
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
            ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
        
        // 取得 product list
        List<Product> productList = productService.getProducts(productQueryParams);
        
        // 取得 product 總數
        Integer total = productService.countProduct(productQueryParams);
        
        // 分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);
        
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
    
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        
        return ObjectUtils.isEmpty(product) ? 
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() : 
                    ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(
            @RequestBody @Valid ProductRequestDTO productRequestDTO){
        Integer productId = productService.createProduct(productRequestDTO);
        
        Product product = productService.getProductById(productId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, 
                                                 @RequestBody @Valid ProductRequestDTO productRequestDTO){
        Product product = productService.getProductById(productId);
        
        // 檢查 product 是否存在
        if(ObjectUtils.isEmpty(product)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        // 修改商品的數據
        productService.updateProduct(productId, productRequestDTO);
        
        Product updateProduct = productService.getProductById(productId);
        
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
    
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
