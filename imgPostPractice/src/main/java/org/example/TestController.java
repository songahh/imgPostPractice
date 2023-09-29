package org.example;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.MultipartResponse;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

@WebServlet("/practice")
public class TestController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("post");
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String path = "index.jsp";
        String action = request.getParameter("action");
        action = (action==null)? "" : action;
        System.out.println("action>>>>>>>>>>>>>>>>>>"+action);

        switch(action){
            case "show":
                path = show(request, response);
                //redirect(request, response, path);
                break;
            default:
                forward(request, response, path);
                break;
        }
    }

    String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            String DIR = "/Users/songahh/project/imgPostPractice/imgPostPractice/WebContent/WEB-INF/upload";

            MultipartRequest mr = new MultipartRequest(request, DIR);
            mr.getFile("myImg");



            //System.out.println(mr.getFile("myImg"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/img/show.jsp";
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException{
        System.out.println(request.getContextPath()+path);
        response.sendRedirect(request.getContextPath()+path);
    }
}