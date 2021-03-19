package controller;

import model.Entity.Product;
import model.Service.IProductService;
import model.Service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public ProductServiceImpl productService;
    public void init(){
        productService=new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "insert":
                    showNewForm(request,response);
                    break;
                case "delete":
                    deleteProduct(request,response);
                    break;
                case "update":
                    showUpdateForm(request,response);
                    break;

                case "showAll":
                    showAllProduct(request,response);
                    break;
                case "show":
                    break;
                case "sort":
                    sortProductByName(request,response);
                    break;
                default:
                    listProduct(request,response);
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "search":
                    searchProduct(request,response);
                    break;
                case "insert":
                    insertProduct(request,response);
                    break;
                case "delete":
                    deleteProduct(request,response);
                    break;
                case "update":
                    updateProduct(request,response);
                    break;
                case "sort":
                    sortProductByName(request,response);
                    break;
                default:
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException,IOException,ServletException{

        String productname=request.getParameter("productname");
        List<Product> productList= (List<Product>) productService.searchProduct(productname);
        request.setAttribute("searching",productList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("searchProduct.jsp");
        dispatcher.forward(request,response);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException,IOException,ServletException{
        List<Product> productList=productService.showAllProduct();
        request.setAttribute("productList",productList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("listProduct.jsp");
        dispatcher.forward(request,response);
    }
    private void listProduct(HttpServletRequest request,HttpServletResponse response)
        throws SQLException,IOException,ServletException{
        List<Product> productList=productService.showAllProduct();
        request.setAttribute("productList",productList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("listProduct.jsp");
        dispatcher.forward(request,response);
    }
    private void insertProduct(HttpServletRequest request,HttpServletResponse response)
        throws SQLException,IOException,ServletException{
        int id=Integer.parseInt(request.getParameter("productid"));
        String name=request.getParameter("productname");
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        double price= Double.parseDouble(request.getParameter("price"));
        double discount= Double.parseDouble(request.getParameter("discount"));
        int categoryId= Integer.parseInt(request.getParameter("categoryid"));
        Product product=new Product(id,name,quantity,price,discount,categoryId);
        productService.insertProduct(product);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/insertProduct.jsp");
        dispatcher.forward(request,response);
    }
    public void updateProduct(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,SQLException,IOException{

        int id=Integer.parseInt(request.getParameter("productid"));
        String name=request.getParameter("productname");
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        double price=Double.parseDouble(request.getParameter("price"));
        double discount=Double.parseDouble(request.getParameter("discount"));
        int categoryid=Integer.parseInt(request.getParameter("categoryid"));

        Product product=new Product(id,name,quantity,price,discount,categoryid);
        productService.updateProduct(product);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/updateProduct.jsp");
        dispatcher.forward(request,response);
   }
   public void deleteProduct(HttpServletRequest request,HttpServletResponse response)
        throws SQLException,IOException,ServletException{
        int id=Integer.parseInt(request.getParameter("productid"));
        productService.deleteProduct(id);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/deleteProduct.jsp");
        dispatcher.forward(request,response);

        List<Product> listProduct=productService.showAllProduct();
        request.setAttribute("listProduct",listProduct);
        RequestDispatcher dispatcher1=request.getRequestDispatcher("/listProduct.jsp");
        dispatcher1.forward(request,response);
    }
    private void showNewForm(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException,SQLException{
        RequestDispatcher dispatcher=request.getRequestDispatcher("/insertProduct.jsp");
        dispatcher.forward(request,response);
    }


    public void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        int id=Integer.parseInt(request.getParameter("productid"));
        Product existingProduct=productService.selectProduct(id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("updateProduct.jsp");
        request.setAttribute("product",existingProduct);
        dispatcher.forward(request,response);
    }

    public void sortProductByName(HttpServletRequest request,HttpServletResponse response)
        throws SQLException,ServletException,IOException{
        List<Product> sortedProduct=productService.sortedProductByName();
        RequestDispatcher dispatcher=request.getRequestDispatcher("listProduct.jsp");
        request.setAttribute("productList",sortedProduct);
        dispatcher.forward(request,response);
    }
}
