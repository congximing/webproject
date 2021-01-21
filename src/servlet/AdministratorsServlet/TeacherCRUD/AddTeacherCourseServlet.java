package servlet.AdministratorsServlet.TeacherCRUD;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.NetState;
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

//将学号信息展示到 添加页面上
@WebServlet("/AddTeacherCourseServlet")
public class AddTeacherCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String r_id = "2";
        String number = request.getParameter("number");
        String username = request.getParameter("username");
        response.setCharacterEncoding("utf-8");

        if (number.equals("请选择")|| number.equals("")){
            //提示前端页面
            number = "0";
//            response.getWriter().write("<script>alert('你太帅了，一添加就添加失败了！');</script>");
            request.getRequestDispatcher("/FindTeacher").forward(request,response);
        }else {
            UsersServiceImpl usersService = new UsersServiceImpl();
            usersService.addCourse(username,number,r_id);//添加的课程不能大于1
            request.getRequestDispatcher("/FindTeacher").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
