package model.Service;

import model.Entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    public void insertProduct(Product product)throws SQLException;
    public boolean deleteProduct(int productId) throws SQLException;
    public boolean updateProduct(Product product) throws SQLException;
    public List<Product> searchProduct(String productname) throws SQLException;
    public List<Product> showAllProduct();
    public Product getProductById(int productId) throws SQLException;
    public List<Product> sortedProductByName()throws SQLException;
}
