package model.Service;

import model.Entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService{

    private String jdbcURL = "jdbc:mysql://locallhost:3306/htmshoe1";
    private String getJdbcUsername = "root";
    private String getJdbcPassword = "Tulinh0308@a";

    private static final String INSERT_CATEGORY_SQL = "INSERT INTO category\" + \"(categoryId,categoryNameId)VALUES\" + \"(?,?);";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT categoryId,categoryName from category where categoryId=?";
    private static final String SELECT_ALL_CATEGORY = "SELECT categoryId,categoryName from category";
    private static final String UPDATE_CATEGORY_SQL = "UPDATE category set categoryId=?,categoryName=? where id=?;";
    private static final String DELETE_CATEGORY_SQL = "DELETE from category where categoryId=?";
    private static final String SELECT_CATEGORY_BY_NAME="SELECT categoryId,categoryName from category where categoryName=?";

    public CategoryServiceImpl() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, getJdbcUsername, getJdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertCategory(Category category) throws SQLException {
        System.out.println(INSERT_CATEGORY_SQL);
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(INSERT_CATEGORY_SQL)){
                preparedStatement.setInt(1,category.getCategoryId());
                preparedStatement.setString(2,category.getCategoryName());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }


    }

    private void printSQLException(SQLException e) {
        for(Throwable ex:e){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: "+((SQLException)e).getSQLState());
                System.err.println("Error Code: "+((SQLException)e).getErrorCode());
                System.err.println("Message: "+e.getMessage());
                Throwable t=ex.getCause();
                while (t!=null){
                    System.out.println("Cause: "+t);
                    t=t.getCause();
                }
            }
        }
    }

    @Override
    public boolean deleteCategory(int categoryId) throws SQLException {
        boolean categoryDeleted;
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(DELETE_CATEGORY_SQL)){
                preparedStatement.setInt(1,categoryId);
                categoryDeleted=preparedStatement.executeUpdate()>0;
        }
        return categoryDeleted;

    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {
        boolean categoryUpdated;
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_CATEGORY_SQL)){
                preparedStatement.setInt(1,category.getCategoryId());
                preparedStatement.setString(2,category.getCategoryName());
                categoryUpdated=preparedStatement.executeUpdate()>0;
        }
             return categoryUpdated;
    }

    @Override
    public Category searchCategory(String categoryName) throws SQLException {
        Category category=null;
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_CATEGORY_BY_NAME)){
                preparedStatement.setString(2,categoryName);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    int id=rs.getInt("categoryId");
                    category=new Category(id,categoryName);
                }
        }catch (SQLException e){
            printSQLException(e);
        }
        return category;
    }

    @Override
    public List<Category> showAllCategory() {
        List<Category> categoryList=new ArrayList<>();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_CATEGORY)){
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("categoryId");
                String name=rs.getString("categoryName");
                categoryList.add(new Category(id,name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    return categoryList;
    }

    @Override
    public Category getCategoryById(int categoryId) throws SQLException {
        Category category = null;
        String query = "{CALL get_category_by_id(?)}";
        try (
                Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, categoryId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("categoryName");
                category = new Category(categoryId, categoryName);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }
}
