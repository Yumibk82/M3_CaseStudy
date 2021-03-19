package model.Service;

import model.Entity.Category;
import model.Entity.Product;

import java.sql.SQLException;
import java.util.List;

interface ICategoryService {
    public void insertCategory(Category category)throws SQLException;
    public boolean deleteCategory(int categoryId) throws SQLException;
    public boolean updateCategory(Category category) throws SQLException;
    public Category searchCategory(String categoryName) throws SQLException;
    public List<Category> showAllCategory();
    public Category getCategoryById(int categoryId) throws SQLException;
}
