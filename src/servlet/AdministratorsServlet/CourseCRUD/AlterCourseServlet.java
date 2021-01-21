package servlet.AdministratorsServlet.CourseCRUD;

import dao.impl.UsersDaoImpl;
import domain.UsersCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//修改课程信息的提交
@WebServlet("/AlterCourseServlet")
public class AlterCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String number = request.getParameter("number");

        UsersServiceImpl usersService = new UsersServiceImpl();
        UsersCourse course = usersService.findCourseByNu(number);

        request.setAttribute("course",course);
        request.getRequestDispatcher("/Administrators/CourseCRUD/updateCourse.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
