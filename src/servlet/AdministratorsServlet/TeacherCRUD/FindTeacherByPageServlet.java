package servlet.AdministratorsServlet.TeacherCRUD;

import domain.PageBean;
import domain.Users;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
//查看所有老师
@WebServlet("/FindTeacherByPageServlet")
public class FindTeacherByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String r_id = "2";
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if ("".equals(currentPage) || currentPage == null ){
            currentPage = "1";
        }
        if ("".equals(rows) || rows == null ){
            rows = "5";
        }

        Map<String, String[]> condition = request.getParameterMap();
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<Users> pb = usersService.findUsersByPage(currentPage, rows, condition,r_id);
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("Administrators/TeacherManger/TeacherList.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
