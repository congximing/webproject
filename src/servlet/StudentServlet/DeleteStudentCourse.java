package servlet.StudentServlet;

import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteStudentCourse")
public class DeleteStudentCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //在方法的参数上加上单引号，让他变成字符串类型
        String number = request.getParameter("number");
        System.out.println(number);
        String username = null;
        username = (String) request.getSession().getAttribute("username");
        UsersServiceImpl usersService = new UsersServiceImpl();
        usersService.deleteCourse(username,number);//学生删除课程

        request.getRequestDispatcher("/FindCourseServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
