package model.Service;
import model.Entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private String jdbcURL = "jdbc:mysql://localhost:3306/htmshoe1";
    private String getJdbcUsername = "root";
    private String getJdbcPassword = "Tulinh0308@a";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (productid,productname,quantity,price,discount,categoryid) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * from product where productid=?";
    private static final String SELECT_ALL_PRODUCT = "SELECT * from product";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE product set productname=?,quantity=?,price=?,discount=?,categoryid=? where productid=?;";
    private static final String DELETE_PRODUCT_SQL = "DELETE from product where productid=?";
    private static final String SEARCH_ALL_PRODUCT_BY_NAME="SELECT * FROM product where productname like ?";
    private static final String SORT_BY_NAME="SELECT * FROM product order by productname";

    public ProductServiceImpl() {
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
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setInt(1,product.getProductid());
            preparedStatement.setString(2, product.getProductname());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setDouble(5, product.getDiscount());
            preparedStatement.setInt(6, product.getCategoryid());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public boolean deleteProduct(int productId) throws SQLException {
        boolean productDeleted;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, productId);
            productDeleted = preparedStatement.executeUpdate() > 0;
        }
        return productDeleted;
    }
    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean productUpdated;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {

            preparedStatement.setInt(6, product.getProductid());
            preparedStatement.setString(1, product.getProductname());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getDiscount());
            preparedStatement.setInt(5, product.getCategoryid());
            productUpdated = preparedStatement.executeUpdate() > 0;
        }
        return productUpdated;
    }
    @Override
    public List<Product> searchProduct(String productname) throws SQLException {
        List<Product> productList=new ArrayList<>();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SEARCH_ALL_PRODUCT_BY_NAME);){
            productname = "%"+productname+"%";
                preparedStatement.setString(1,productname);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    int id=rs.getInt("productid");
                    String name=rs.getString("productname");
                    int quantity=rs.getInt("quantity");
                    double price=rs.getDouble("price");
                    double discount=rs.getDouble("discount");
                    int categoryid=rs.getInt("categoryid");
                    Product product=new Product();
                    product.setProductid(id);
                    product.setProductname(name);
                    product.setQuantity(quantity);
                    product.setPrice(price);
                    product.setDiscount(discount);
                    product.setCategoryid(categoryid);
                    productList.add(product);
                }
        }
        return productList;
    }
    @Override
    public List<Product> showAllProduct() {
        List<Product> productList=new ArrayList<>();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_PRODUCT)){
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                Product product=new Product();
                int id=rs.getInt("productid");
                String name=rs.getString("productname");
                int quantity=rs.getInt("quantity");
                double price=rs.getDouble("price");
                double discount=rs.getDouble("discount");
                int categoryId=rs.getInt("categoryid");
                product.setProductid(id);
                product.setProductname(name);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setDiscount(discount);
                product.setCategoryid(categoryId);
                productList.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return productList;
    }
    @Override
    public Product getProductById(int productid) throws SQLException {
        Product product = null;
//        String query = "{CALL get_product_by_id(?)}";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_PRODUCT_BY_ID);){
//                CallableStatement callableStatement = connection.prepareCall(query);) {
                preparedStatement.setInt(1,productid);
                ResultSet rs=preparedStatement.executeQuery();
//            callableStatement.setInt(1, productId);
//            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("productname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double discount=rs.getDouble("discount");
                int categoryid = rs.getInt("categoryid");
                product.setProductname(name);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setDiscount(discount);
                product.setCategoryid(categoryid);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> sortedProductByName() throws SQLException {
        List<Product> sortedProductList=new ArrayList<>();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SORT_BY_NAME);){

                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    Product product=new Product();
                    int id=rs.getInt("productid");
                    String name=rs.getString("productName");
                    int quantity=rs.getInt("quantity");
                    double price=rs.getDouble("price");
                    double discount=rs.getDouble("discount");
                    int categoryid=rs.getInt("categoryid");
                    product.setProductid(id);
                    product.setProductname(name);
                    product.setQuantity(quantity);
                    product.setPrice(price);
                    product.setDiscount(discount);
                    product.setCategoryid(categoryid);
                    sortedProductList.add(product);
                }
        }
    return sortedProductList;
    }

    public Product selectProduct(int productid){
        Product product=new Product();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_PRODUCT_BY_ID)){
                preparedStatement.setInt(1,productid);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String name=rs.getString("productname");
                int quantity=rs.getInt("quantity");
                double price=rs.getDouble("price");
                double discount=rs.getDouble("discount");
                int categoryid=rs.getInt("categoryid");
                product.setProductname(name);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setDiscount(discount);
                product.setCategoryid(categoryid);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
       return product;
    }
    private void printSQLException(SQLException ex){
        for(Throwable e:ex){
            if(e instanceof SQLException){
                e.printStackTrace();
                System.err.println("SQLState: "+((SQLException)e).getSQLState());
                System.err.println("ErrorCode: "+((SQLException)e).getErrorCode());
                System.err.println("Message: "+e.getMessage());
                Throwable t=ex.getCause();
                while (t!=null){
                    System.out.println("Cause: "+t);
                    t=t.getCause();
                }
            }
        }
    }
}
