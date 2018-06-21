package com.example.soap;

import com.example.soap.product.ProductModel;
import com.example.soap.product.ProductRequest;
import com.example.soap.product.ProductResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {

    @PayloadRoot(namespace = "http://example.com/soap/product", localPart = "ProductRequest")
    @ResponsePayload
    public ProductResponse getProduct(@RequestPayload ProductRequest request) {

        ProductModel productModel = new ProductModel();
        productModel.setId(request.getId());
        productModel.setName("Product1");
        productModel.setDescription("My new product");

        ProductResponse response = new ProductResponse();
        response.setProduct(productModel);
        return response;
    }

}
