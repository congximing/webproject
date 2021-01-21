package servlet.StudentServlet;

import domain.UsersCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FindTermTable")
public class FindTermTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = null;
        String r_id = "1";
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)){
                    username = cookie.getValue();
                    break;
                }
            }
        }
        UsersServiceImpl usersService = new UsersServiceImpl();
        String t = request.getParameter("term");
        int term = Integer.parseInt(t);
//        int term = usersService.findTerm(username);
        List<UsersCourse> termCourse = usersService.findTermCourse(username, r_id, term);
        UsersCourse u1 = termCourse.get(0);
        int term1 = u1.getTerm();
        request.setAttribute("t",term1);
        request.setAttribute("termCourse",termCourse);
        request.getRequestDispatcher("/Student/CourseCRUD/StudentTermTable.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
