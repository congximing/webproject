package servlet.AdministratorsServlet.CourseCRUD;

import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddClassroomServlet")
public class AddClassroomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String number = request.getParameter("number");
        String place = request.getParameter("place");
        UsersServiceImpl usersService = new UsersServiceImpl();
        //课表和地点关联  已有关联的不执行
        usersService.conNumAndPla(number,place);
        request.setAttribute("number",number);
        request.getRequestDispatcher("/courseAndPlace").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
