package com.groupad.backend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupad.backend.model.Order;
import com.groupad.backend.model.OrderedProduct;
import com.groupad.backend.model.OrderedProductPK;
import com.groupad.backend.model.Product;
import com.groupad.backend.model.Review;
import com.groupad.backend.repository.OrderRepository;
import com.groupad.backend.repository.OrderedProductRepository;
import com.groupad.backend.repository.ProductRepository;
import com.groupad.backend.repository.ReviewRepository;
import com.groupad.backend.exception.ProductNotFoundException;

@RestController
class ProductController {

    // for singleton pattern
    @Autowired
    ProductRepository pRepo;

    @Autowired
    ReviewRepository rRepo;

    @Autowired
    OrderRepository oRepo;

    @Autowired
    OrderedProductRepository opRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ProductController() {
    }

    // @GetMapping("/products")
    // public List<Product> getAllProducts() {
    // return repository.findAll();
    // }

    @PostMapping("/products")
    public Product putNewProduct(@RequestBody Product newProduct) {
        return pRepo.save(newProduct);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return pRepo.findAll();
    }

    @GetMapping("/products/by-ids")
    public List<Product> getProductsByIds(@RequestParam String ids) {
        // convert string list of ids to long
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(i -> Long.valueOf(i.trim())).toList();
        return pRepo.findAllById(idList);
    }

    @GetMapping("/products/{productId}")
    public Product findProductById(@PathVariable Long productId) throws ProductNotFoundException {
        return pRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @PutMapping("/api/products/{productId}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long productId) {

        return pRepo.findById(productId)
                .map(product -> {
                    product.setProductId(newProduct.getProductId());
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setType(newProduct.getType());
                    product.setBrand(newProduct.getBrand());
                    product.setInstock(newProduct.getInstock());
                    product.setPrice(newProduct.getPrice());
                    product.setImgSrc(newProduct.getImgSrc());
                    product.setImgAlt(newProduct.getImgAlt());
                    return pRepo.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setProductId(productId);
                    return pRepo.save(newProduct);
                });
    }

    @DeleteMapping("/api/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        pRepo.deleteById(productId);
    }

    @GetMapping("/products/testnames")
    public List<String> getAllProductName() {
        List<String> productNameList = new ArrayList<>();
        productNameList.addAll(jdbcTemplate.queryForList("select name from product", String.class));
        return productNameList;
    }

    // After checking that user has bought the product
    @PostMapping("/products/review-add")
    public String addReview(Review review) {
        // if user already left review for the product, fail
        Review foundReview = rRepo.findByEmailAndProductId(review.getEmail(), review.getProductId());
        if (foundReview != null)
            return "fail";

        // check if user has bought the product

        // find all order that user made
        List<Order> order = oRepo.findByEmail(review.getEmail());
        for (Order o : order) {
            // find all ordered products in user's order
            OrderedProductPK opPK = new OrderedProductPK(o.getOrderId(), review.getProductId());
            Optional<OrderedProduct> op = opRepo.findById(opPK);
            System.out.println(op.isPresent());
            if (op.isPresent()) {
                // if user has bought the product, add review.
                rRepo.save(review);
                return "added";
            }
        }
        // if there is no history for the product by user, fail
        return "fail";
    }

    @GetMapping("/products/rates")
    public double getRateAvg(@RequestParam("productId") Long productId) {
        List<Review> list = rRepo.findByProductId(productId);
        double result = 0;
        for (Review r : list) {
            result += (double) r.getRate();
        }
        if (result == 0)
            return 0;
        else
            return Math.round(result / ((double) list.size()) * 10.0) / 10.0;
    }
}