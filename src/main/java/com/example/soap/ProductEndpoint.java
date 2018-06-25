package com.example.soap;

import com.example.dao.domain.Product;
import com.example.dao.service.ProductService;
import com.example.soap.product.ProductModel;
import com.example.soap.product.ProductRequest;
import com.example.soap.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private final ProductService productService;

    @PayloadRoot(namespace = "http://example.com/soap/product", localPart = "ProductRequest")
    @ResponsePayload
    public ProductResponse getProduct(@RequestPayload ProductRequest request) {

        Product product = productService.getProductById(request.getId());
        log.info(product.toString());
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setDescription(product.getDescription());

        ProductResponse response = new ProductResponse();
        response.setProduct(productModel);
        return response;
    }

}
