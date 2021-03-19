//package controller;
//
////import model.Service.ICategoryService;
//
//import model.Entity.Category;
//import model.Service.CategoryServiceImpl;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet(name = "CategoryController", value = "/category")
//public class CategoryController<ICategoryService> extends HttpServlet {
//
//    private ICategoryService categoryService;
//    public void init(){
//        categoryService=new CategoryServiceImpl();
//    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action=request.getParameter("action");
//        if(action==null){
//            action="";
//        }
//        try{
//            switch (action){
//                case "create":
//                    showNewFormCategory(request,response);
//                    break;
//                case "edit":
//                    showEditFormCategory(request,response);
//                    break;
//                case "delete":
//                    deleteCategory(request,response);
//                    break;
//                default:
//                    listCategory(request,response);
//                    break;
//            }
//        }catch (SQLException ex){
//            throw new ServletException(ex);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }
//    private void listCategory(HttpServletRequest request,HttpServletResponse response)
//        throws ServletException,IOException,ServletException{
//        List<Category> categoryList=cat
//    }
//}
