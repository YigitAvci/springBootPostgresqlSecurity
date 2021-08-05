package com.hunter.springbootpostgresql.dataAccess.abstracts;

import com.hunter.springbootpostgresql.entities.concretes.Product;
import com.hunter.springbootpostgresql.entities.dataTransferObjects.ProductWithCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDataAccessLayer extends JpaRepository<Product, Integer> {

    Product getProductByProductName(String productName);

    Product getProductByProductNameAndCategory_Id(String productName, int categoryId);

    List<Product> getProductsByProductNameOrCategory_Id(String productName, int categoryId);

    @Query("from Product where unitPrice=:unitPrice")
    List<Product> getProductByUnitPrice(double unitPrice);

    List<Product> getProductByProductNameStartsWith(String productName);

    List<Product> getProductsByProductNameEndsWith(String productName);

    List<Product> getProductsByProductNameContains(String productName);

    List<Product> getProductsByUnitPriceGreaterThanEqual(double unitPrice);

    @Query("from Product where unitPrice<=:unitPrice")
    List<Product> getProductByUnitPriceLessThanEqual(double unitPrice);

    List<Product> getProductsByCategory_IdIn(List<Integer> categories);

    @Query("select new com.hunter.springbootpostgresql.entities.dataTransferObjects.ProductWithCategoryDTO(p.id, p.productName, c.name) from Category c inner join c.products p")
    List<ProductWithCategoryDTO> getProductsWithCategoryDetails();
}