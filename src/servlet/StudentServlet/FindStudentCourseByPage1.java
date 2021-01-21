package servlet.StudentServlet;

import domain.PageBean;
import domain.SandTCourse;
import domain.Users;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
//不能查看其它学生的课表 只能查看自己的课程表  已选课表
//有学期
@WebServlet( "/FindStudentCourseByPageServlet1")
public class FindStudentCourseByPage1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String r_id = "1";
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        Users login = (Users) session.getAttribute("login");
        String username = login.getUsername();
//        String username = request.getParameter("username");

        if(currentPage==null || "".equals(currentPage)){
        currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        Map<String, String[]> condition = request.getParameterMap();//将所有参数存放到condition
        UsersServiceImpl usersService = new UsersServiceImpl();
        String t = request.getParameter("term");
//        int term = 0;
//        if (t == null || "".equals(t)){
//            int term = Integer.parseInt(t);
//        term = usersService.findTerm(username);
//        }else {
//            term = Integer.parseInt(t);
//        }
        PageBean<SandTCourse> pb = usersService.findSandTByPage(currentPage, rows,condition,r_id,username,0);

        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件存入request
        request.getRequestDispatcher("/Student/CourseCRUD/studentCourseList.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
