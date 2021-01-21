package servlet.AdministratorsServlet.StudentCRUD;

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
//查看所有学生的课表(含有没选的课)
@WebServlet( "/FindStudentCourseByPageServlet")
public class FindStudentCourseByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String r_id = "1";
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
        PageBean<SandTCourse> pb = null;//学生和老师都用此方法
        try {
            pb = usersService.findSandTByPage(currentPage, rows, condition,r_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件存入request  有什么影响？
        request.getRequestDispatcher("/Administrators/StudentManger/studentCourseList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
