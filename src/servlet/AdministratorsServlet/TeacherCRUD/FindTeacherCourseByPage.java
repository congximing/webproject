package servlet.AdministratorsServlet.TeacherCRUD;

import domain.PageBean;
import domain.SandTCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
//查看所有老师的已选课程
@WebServlet( "/FindTeacherCourseByPageServlet")
public class FindTeacherCourseByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String r_id = "2";
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage==null || "".equals(currentPage)){
        currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        Map<String, String[]> condition = request.getParameterMap();//将所有参数存放到condition
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<SandTCourse> pb = null;
        try {
            pb = usersService.findSandTByPage(currentPage, rows, condition,r_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件存入request  有什么影响？
        request.getRequestDispatcher("/Administrators/TeacherManger/TeacherCourseList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
