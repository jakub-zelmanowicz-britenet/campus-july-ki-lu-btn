package pl.britenet.campuslu.service;

import pl.britenet.campuslu.database.DatabaseService;
import pl.britenet.campuslu.database.ResultParser;
import pl.britenet.campuslu.object.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final DatabaseService databaseService;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Product> getProducts() {
        return this.databaseService.performSQL("SELECT * FROM product", resultSet -> {
            try {
                List<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    productList.add(product);
                }
                return productList;
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
        });
    }

    public Optional<Product> getProduct(int id) {
        Product retrievedProduct = this.databaseService.performSQL(String.format("SELECT * FROM product WHERE id = %d", id), resultSet -> {
            try {
                Product product = null;
                if (resultSet.next()) {
                    product = new Product(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                }
                return product;
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
        });
        return Optional.of(retrievedProduct);
    }
}
