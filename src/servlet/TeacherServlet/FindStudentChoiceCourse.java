package servlet.TeacherServlet;

import domain.PageBean;
import domain.SandTCourse;
import domain.Users;
import service.UserService;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
//教师查看学生选课
@WebServlet("/FindStudentChoiceCourse")
public class FindStudentChoiceCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Users login = (Users) session.getAttribute("login");
        String username = login.getUsername();
//        String username = null;
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
        int term = usersService.findTerm(username);
        PageBean<SandTCourse> pb = usersService.findSandTByPage(currentPage, rows, condition,r_id,username,term);


        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件存入request
        request.getRequestDispatcher("Teacher/CourseCRUD/StudentChoiceCourseList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
