package servlet.AdministratorsServlet.TeacherCRUD;

import domain.PageBean;
import domain.SandTCourse;
import domain.UsersCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//查看所有老师的已选课程
@WebServlet( "/FindTeacher")
public class FindTeacher extends HttpServlet {
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
        String username = request.getParameter("username");
//        Map<String, String[]> condition = request.getParameterMap();//将所有参数存放到condition
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<SandTCourse> pb = usersService.findSandTByPage(currentPage, rows,r_id, username);
        List<UsersCourse> course = usersService.findCourseByPage();
        request.setAttribute("username",username);
        request.setAttribute("course",course);//授课用的公共域
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/Administrators/TeacherManger/CourseArrangementList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
