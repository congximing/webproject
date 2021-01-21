package servlet.AdministratorsServlet.StudentCRUD;

import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//管理员删除教师的课程
@WebServlet("/DeleteStudentCourseInAdministrators")
public class DeleteStudentCourseInAdministrators extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //在方法的参数上加上单引号，让他变成字符串类型
        String number = request.getParameter("number");
        String username = request.getParameter("username");
        UsersServiceImpl usersService = new UsersServiceImpl();
        usersService.deleteCourse(username,number);//管理员删除教师的课程

        response.sendRedirect("/FindStudentCourseByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
