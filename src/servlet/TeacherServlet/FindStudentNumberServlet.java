package servlet.TeacherServlet;

import domain.PageBean;
import domain.SandTCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//查询 某一课程学生人数 并显示学生信息
@WebServlet("/FindStudentNumberServlet")
public class FindStudentNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String r_id = "1";
        String number = request.getParameter("number");//获取课程代号
//        String number = "000 bv vb1";
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if ( "".equals(currentPage) || currentPage == null ){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        //查询 某一课程学生人数 并显示学生信息
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<SandTCourse> pb = usersService.findCourseByPage(currentPage, rows,r_id, number);
        System.out.println(pb.getList().size());
        request.setAttribute("pb",pb);
        System.err.println(number+"==========================");
        request.setAttribute("number",number);
        //跳转到这个页面
        request.getRequestDispatcher("Teacher/CourseCRUD/DisplayedStudentChoice.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
