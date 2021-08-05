package com.hunter.springbootpostgresql.business.concretes;

import com.hunter.springbootpostgresql.business.abstracts.ProductService;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessResult;
import com.hunter.springbootpostgresql.dataAccess.abstracts.ProductDataAccessLayer;
import com.hunter.springbootpostgresql.entities.concretes.Product;
import com.hunter.springbootpostgresql.entities.dataTransferObjects.ProductWithCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductDataAccessLayer productDataAccessLayer;

    @Autowired
    public ProductManager(ProductDataAccessLayer productDataAccessLayer) {
        this.productDataAccessLayer = productDataAccessLayer;
    }

    @Override
    public DataResult<List<Product>> getAllProducts() {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.findAll(), "get request is successful");
    }

    @Override
    public Result addProduct(Product product) {
        this.productDataAccessLayer.save(product);
        return new SuccessResult("the product is added successfully");
    }

    @Override
    public DataResult<Product> getProductByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDataAccessLayer.getProductByProductName(productName), "get request by productname is successful");
    }

    @Override
    public DataResult<Product> getProductByProductNameAndCategory_CategoryId(String productName, int categoryId) {
        return new SuccessDataResult<Product>(this.productDataAccessLayer.getProductByProductNameAndCategory_Id(productName, categoryId), "get request by product name or category id is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameOrCategory_CategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductsByProductNameOrCategory_Id(productName, categoryId), "get request by product name or category id is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByUnitPrice(double unitPrice) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductByUnitPrice(unitPrice), "get request by unit price is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductByProductNameStartsWith(productName), "get request by product name starts with is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameEndsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductsByProductNameEndsWith(productName));
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductsByProductNameContains(productName));
    }

    @Override
    public DataResult<List<Product>> getProductsByUnitPriceGreaterThanEqual(double unitPrice) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductsByUnitPriceGreaterThanEqual(unitPrice));
    }

    @Override
    public DataResult<List<Product>> getProductsByUnitPriceLessThanEqual(double unitPrice) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductByUnitPriceLessThanEqual(unitPrice));
    }

    @Override
    public DataResult<List<Product>> getProductsByCategoryIdIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.getProductsByCategory_IdIn(categories));
    }

    @Override
    public DataResult<List<Product>> getAllProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize); //we made pageNo into pageNo - 1, because Pageable interface works like an array. it starts at 0 index.
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.findAll(pageable).getContent());
    }

    @Override
    public DataResult<List<Product>> getAllProductsSortedByUnitPrice() {
        Sort sort = Sort.by(Sort.Direction.ASC, "unitPrice");
        return new SuccessDataResult<List<Product>>(this.productDataAccessLayer.findAll(sort));
    }

    @Override
    public DataResult<List<ProductWithCategoryDTO>> getProductsWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDTO>>(this.productDataAccessLayer.getProductsWithCategoryDetails(), "product with category get request is successful");
    }
}
