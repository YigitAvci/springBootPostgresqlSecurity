package com.hunter.springbootpostgresql.api.controllers;

import com.hunter.springbootpostgresql.business.abstracts.ProductService;
import com.hunter.springbootpostgresql.business.concretes.ProductManager;
import com.hunter.springbootpostgresql.core.entities.User;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import com.hunter.springbootpostgresql.entities.concretes.Product;
import com.hunter.springbootpostgresql.entities.dataTransferObjects.ProductWithCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductManager productService;

    @Autowired
    public ProductsController(ProductManager productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public DataResult<List<Product>> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping("/getProductByProductName")
    public DataResult<Product> getProductByProductName(@RequestParam String productName) {
        return this.productService.getProductByProductName(productName);
    }

    @GetMapping("/getProductByProductNameAndCategoryId")
    public DataResult<Product> getProductByProductNameAndCategory_CategoryId(@RequestParam String productName, @RequestParam int categoryId) {
        return this.productService.getProductByProductNameAndCategory_CategoryId(productName, categoryId);
    }

    @GetMapping("/getProductsByProductNameOrCategoryId")
    public DataResult<List<Product>> getProductsByProductNameOrCategoryId(@RequestParam String productName, @RequestParam int categoryId) {
        return this.productService.getProductsByProductNameOrCategory_CategoryId(productName, categoryId);
    }

    @GetMapping("/getProductsByUnitPrice")
    public DataResult<List<Product>> getProductsByUnitPrice(@RequestParam double unitPrice) {
        return this.productService.getProductsByUnitPrice(unitPrice);
    }

    @GetMapping("/getProductsByProductNameStartsWith")
    public DataResult<List<Product>> getProductsByProductNameStartsWith(@RequestParam String productName) {
        return this.productService.getProductsByProductNameStartsWith(productName);
    }

    @GetMapping("/getProductsByProductNameEndsWith")
    public DataResult<List<Product>> getProductsByProductNameEndsWith(@RequestParam String productName) {
        return this.productService.getProductsByProductNameEndsWith(productName);
    }

    @GetMapping("/getProductsByProductsNameContains")
    public DataResult<List<Product>> getProductsByProductNameContains(@RequestParam String productName) {
        return this.productService.getProductsByProductNameContains(productName);
    }

    @GetMapping("/getProductsByUnitPriceGreaterThanEqual")
    public DataResult<List<Product>> getProductsByUnitPriceGreaterThanEqual(@RequestParam double unitPrice) {
        return this.productService.getProductsByUnitPriceGreaterThanEqual(unitPrice);
    }

    @GetMapping("/getProductsByUnitPriceLessThanEqual")
    public DataResult<List<Product>> getProductsByUnitPriceLessThanEqual(@RequestParam double unitPrice) {
        return this.productService.getProductsByUnitPriceLessThanEqual(unitPrice);
    }

    @GetMapping("/getProductsByCategoryIdIn")
    public DataResult<List<Product>> getProductsByCategoryIdIn(@RequestParam List<Integer> categories) {
        return this.productService.getProductsByCategoryIdIn(categories);
    }

    @GetMapping("/getAllProductsByPage")
    public DataResult<List<Product>> getAllProductsByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        return this.productService.getAllProducts(pageNo, pageSize);
    }

    @GetMapping("/getAllProductsSortedByUnitPrice")
    public DataResult<List<Product>> getAllProductsSortedByUnitPrice() {
        return this.productService.getAllProductsSortedByUnitPrice();
    }

    @GetMapping("/getAllProductsWithCategoryDetails")
    public DataResult<List<ProductWithCategoryDTO>> getAllProductsWithCategoryDetails() {
        return this.productService.getProductsWithCategoryDetails();
    }
}
