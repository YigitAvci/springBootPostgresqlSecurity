package com.hunter.springbootpostgresql.business.abstracts;

import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import com.hunter.springbootpostgresql.entities.concretes.Product;
import com.hunter.springbootpostgresql.entities.dataTransferObjects.ProductWithCategoryDTO;

import java.util.List;

public interface ProductService {
    Result addProduct(Product product);
    DataResult<List<Product>> getAllProducts();
    DataResult<Product> getProductByProductName(String productName);
    DataResult<Product> getProductByProductNameAndCategory_CategoryId(String productName, int categoryId);
    DataResult<List<Product>> getProductsByProductNameOrCategory_CategoryId(String productName, int categoryId);
    DataResult<List<Product>> getProductsByUnitPrice(double unitPrice);
    DataResult<List<Product>> getProductsByProductNameStartsWith(String productName);
    DataResult<List<Product>> getProductsByProductNameEndsWith(String productName);
    DataResult<List<Product>> getProductsByProductNameContains(String productName);
    DataResult<List<Product>> getProductsByUnitPriceGreaterThanEqual(double unitPrice);
    DataResult<List<Product>> getProductsByUnitPriceLessThanEqual(double unitPrice);
    DataResult<List<Product>> getProductsByCategoryIdIn(List<Integer> categories);
    DataResult<List<Product>> getAllProducts(int pageNo, int pageSize);
    DataResult<List<Product>> getAllProductsSortedByUnitPrice();
    DataResult<List<ProductWithCategoryDTO>> getProductsWithCategoryDetails();
}
