package com.hunter.springbootpostgresql.business.concretes;

import com.hunter.springbootpostgresql.business.abstracts.ProductService;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessResult;
import com.hunter.springbootpostgresql.dataAccess.abstracts.ProductDataAccessObject;
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

    private ProductDataAccessObject productDataAccessObject;

    @Autowired
    public ProductManager(ProductDataAccessObject productDataAccessObject) {
        this.productDataAccessObject = productDataAccessObject;
    }

    @Override
    public DataResult<List<Product>> getAllProducts() {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.findAll(), "get request is successful");
    }

    @Override
    public Result addProduct(Product product) {
        this.productDataAccessObject.save(product);
        return new SuccessResult("the product is added successfully");
    }

    @Override
    public DataResult<Product> getProductByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDataAccessObject.getProductByProductName(productName), "get request by productname is successful");
    }

    @Override
    public DataResult<Product> getProductByProductNameAndCategory_CategoryId(String productName, int categoryId) {
        return new SuccessDataResult<Product>(this.productDataAccessObject.getProductByProductNameAndCategory_Id(productName, categoryId), "get request by product name or category id is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameOrCategory_CategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductsByProductNameOrCategory_Id(productName, categoryId), "get request by product name or category id is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByUnitPrice(double unitPrice) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductByUnitPrice(unitPrice), "get request by unit price is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductByProductNameStartsWith(productName), "get request by product name starts with is successful");
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameEndsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductsByProductNameEndsWith(productName));
    }

    @Override
    public DataResult<List<Product>> getProductsByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductsByProductNameContains(productName));
    }

    @Override
    public DataResult<List<Product>> getProductsByUnitPriceGreaterThanEqual(double unitPrice) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductsByUnitPriceGreaterThanEqual(unitPrice));
    }

    @Override
    public DataResult<List<Product>> getProductsByUnitPriceLessThanEqual(double unitPrice) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductByUnitPriceLessThanEqual(unitPrice));
    }

    @Override
    public DataResult<List<Product>> getProductsByCategoryIdIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.getProductsByCategory_IdIn(categories));
    }

    @Override
    public DataResult<List<Product>> getAllProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize); //we made pageNo into pageNo - 1, because Pageable interface works like an array. it starts at 0 index.
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.findAll(pageable).getContent());
    }

    @Override
    public DataResult<List<Product>> getAllProductsSortedByUnitPrice() {
        Sort sort = Sort.by(Sort.Direction.ASC, "unitPrice");
        return new SuccessDataResult<List<Product>>(this.productDataAccessObject.findAll(sort));
    }

    @Override
    public DataResult<List<ProductWithCategoryDTO>> getProductsWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDTO>>(this.productDataAccessObject.getProductsWithCategoryDetails(), "product with category get request is successful");
    }
}
