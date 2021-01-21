package servlet.AdministratorsServlet.StudentCRUD;

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

@WebServlet("/findOneForm")
public class FindOneForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
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
        String username = request.getParameter("username");
        UsersServiceImpl usersService = new UsersServiceImpl();
        PageBean<SandTCourse> form = usersService.findSandTByPage(currentPage, rows, r_id, username);
        request.setAttribute("pb",form);
        request.setAttribute("user",username);
        request.getRequestDispatcher("/Administrators/StudentManger/studentclassform.jsp").forward(request,resp);
    }
}
