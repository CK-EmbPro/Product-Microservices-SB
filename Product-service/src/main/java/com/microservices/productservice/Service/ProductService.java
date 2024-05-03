package com.microservices.productservice.Service;

import com.microservices.productservice.Mapper.Mapper;
import com.microservices.productservice.Model.Product;
import com.microservices.productservice.ProductRepository.ProductRepository;
import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Autowired
    public final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productRequest){
        ProductResponse productResponse = null;
        try {
           Product product = Mapper.mapRequestToProduct(productRequest);
            Product savedProduct = productRepository.save(product);
            productResponse = Mapper.mapProductToResponse(savedProduct);
            log.info("Product {} is saved", product.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return productResponse;
    }

    public List<ProductResponse> getAllProducts(){
        List<ProductResponse> productsResponse = null;
        try {
            List<Product> allProducts = productRepository.findAll();
            productsResponse = allProducts.stream().map(singleProduct -> Mapper.mapProductToResponse(singleProduct)).collect(Collectors.toList());

        }catch (Exception e){
            e.printStackTrace();
        }

        return productsResponse;


    }

}
