package servlet.StudentServlet;

import domain.Users;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
//学生选课表的添加功能
@WebServlet("/addSelectedStudentCourse")
public class AddSelectedStudentCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String r_id = "1";
//        String username = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies !=null && cookies.length>0){
//            for (Cookie cookie : cookies){
//                String name = cookie.getName();
//                if ("username".equals(name)){
//                    username = cookie.getValue();
//                }
//            }
//        }
        Users login = (Users) request.getSession().getAttribute("login");
        String username = login.getUsername();
        String number = request.getParameter("number");
        System.out.println(number);
        UsersServiceImpl usersService = new UsersServiceImpl();
        usersService.addCourseStudent(username,number);
//        response.sendRedirect("/StudentChoiceCourse");
        request.getRequestDispatcher("/FindCourseServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
