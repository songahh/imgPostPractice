package org.example;

import com.oreilly.servlet.MultipartRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet("/practice")
public class TestController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DIR = "/Users/songahh/project/imgPostPractice/imgPostPractice/WebContent/WEB-INF/upload/";


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
            case "save":
                path = save(request, response);
                redirect(request, response, path);
                break;
            case "show":
                path = show(request, response);
                forward(request, response, path);
                break;
            default:
                forward(request, response, path);
                break;
        }
    }

    String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //Path path = Paths.get(DIR);
        //byte[] data = Files.readAllBytes(path);
        //String base64Image = Base64.getEncoder().encodeToString(data);

        //request.setAttribute("myImg", base64Image);
        return "img/show.jsp";
    }

    String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            MultipartRequest mr = new MultipartRequest(request, DIR);
            int imgSize = Integer.parseInt(mr.getParameter("imgSize"));
            System.out.println(imgSize);

            String tc = mr.getParameter("text");
            System.out.println(tc);


            for(int i=0; i<imgSize; ++i){
                String param = "myImg"+i;
                File file = mr.getFile(param);

                String orgName = file.getName();
                String newName = "img" + UUID.randomUUID() + ".jpg";

                Files.move(
                        Paths.get(DIR+orgName),
                        Paths.get(DIR+newName),
                        StandardCopyOption.REPLACE_EXISTING
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //return "index.jsp";
        return "/practice?action=show";
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        System.out.println(path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException{
        System.out.println(request.getContextPath()+path);
        response.sendRedirect(request.getContextPath()+path);
    }
}