package servlet.AdministratorsServlet.StudentCRUD;

import domain.PageBean;
import domain.Users;
import service.UserService;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
//查看所有学生
@WebServlet( "/FindStudentByPageServlet")
public class FindStudentByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        String r_id = "1";
        //获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //空指针的判断 避免异常
        if(currentPage==null || "".equals(currentPage)){
        currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        //获取条件查询
        Map<String, String[]> condition = request.getParameterMap();//将所有参数存放到condition

        //调用service查询
        UserService usersService = new UsersServiceImpl();
        PageBean<Users> pb = usersService.findUsersByPage(currentPage, rows, condition,r_id);

        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件存入request  有什么影响？
        request.getRequestDispatcher("/Administrators/StudentManger/studentList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
