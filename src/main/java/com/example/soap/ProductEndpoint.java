package com.example.soap;

import com.example.dao.domain.Product;
import com.example.dao.service.ProductService;
import com.example.soap.product.AddProductRequest;
import com.example.soap.product.AddProductResponse;
import com.example.soap.product.GetAllProductsResponse;
import com.example.soap.product.GetProductByIdRequest;
import com.example.soap.product.GetProductByIdResponse;
import com.example.soap.product.ProductModel;
import com.example.soap.product.ServiceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private final ProductService productService;
    private final static String NAMESPACE_URI = "http://example.com/soap/product";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProduct(@RequestPayload GetProductByIdRequest request) {

        Product product = productService.getProductById(request.getId());
        log.info(product.toString());
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setDescription(product.getDescription());

        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productModel);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<ProductModel> productModels = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            ProductModel ob = new ProductModel();
            BeanUtils.copyProperties(products.get(i), ob);
            productModels.add(ob);
        }
        response.getProducts().addAll(productModels);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
    @ResponsePayload
    public AddProductResponse addProduct(@RequestPayload AddProductRequest request) {
        AddProductResponse response = new AddProductResponse();
        ServiceStatus status = new ServiceStatus();
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        try {
            productService.addProduct(product);
            ProductModel productModel = new ProductModel();
            BeanUtils.copyProperties(product, productModel);
            response.setProduct(productModel);
            status.setStatusCode("SUCCESS");
            status.setMessage("Content Added Successfully");
            response.setServiceStatus(status);
        } catch (SQLException e) {
            status.setStatusCode("CONFLICT");
            status.setMessage("Content Already Available");
            response.setServiceStatus(status);
            log.error("Content Already Available. " + e.getMessage());
        }
        return response;
    }

}
