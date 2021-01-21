package servlet.AdministratorsServlet.CourseCRUD;

import domain.PageBean;
import domain.UsersCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//查询所有的课程与教室
@WebServlet("/FindCourseAndPlace")
public class FindCourseAndPlace extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        Map<String, String[]> condition = request.getParameterMap();//获取参数
        if ("".equals(currentPage) || currentPage==null){
            currentPage = "1";
        }
        if ("".equals(rows) || rows==null){
            rows = "5";
        }
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<UsersCourse> pb = usersService.findCourseHavePlace(condition,currentPage,rows);
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//在前端显示数据 保证换页正常
        request.getRequestDispatcher("/Administrators/CourseCRUD/AllCourseAndPlace.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
