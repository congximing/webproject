package servlet.StudentServlet;

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
import java.util.Map;

//展示当前学生所有的课程 不含有复杂条件
//用来刷新学生已选课表页面
@WebServlet("/FindCourseServlet")
public class FindCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        String username = (String) request.getSession().getAttribute("username");
        //String username =(String) request.getAttribute("username");//20min 获取Attribute
        String r_id = "1";
        if ("".equals(currentPage) || currentPage==null){
            currentPage = "1";
        }
        if ("".equals(rows) || rows==null){
            rows = "5";
        }
        System.out.println(username);
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<SandTCourse> pb = usersService.findSandTByPage(currentPage, rows,r_id,username);
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/Student/CourseCRUD/studentCourseList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
