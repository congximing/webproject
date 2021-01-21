package servlet.AdministratorsServlet.TeacherCRUD;

import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//管理员删除教师的课程
@WebServlet("/DeleteTeacherCourseInAdministrators")
public class DeleteTeacherCourseInAdministrators extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //在方法的参数上加上单引号，让他变成字符串类型
        String number = request.getParameter("number");
        String username = request.getParameter("username");
        System.out.println(number);
//        String username = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null || cookies.length>0 ) {
//            for (Cookie c: cookies) {
//                String name = c.getName();
//                if ("username".equals(name)){
//                    username = c.getValue();
//                    break;
//                }
//            }
//        }
//        request.setAttribute("username",username);
        UsersServiceImpl usersService = new UsersServiceImpl();
        usersService.deleteCourse(username,number);//管理员删除教师的课程

        response.sendRedirect("/FindTeacherCourseByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
