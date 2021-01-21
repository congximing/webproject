package servlet.AdministratorsServlet.NewCourse;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.UsersDaoImpl;
import domain.NetState;
import domain.Subject;
import domain.Type;
import domain.UsersCourse;
import netscape.javascript.JSObject;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findSubjectType")
public class FindSubjectType extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersServiceImpl usersService = new UsersServiceImpl();
        UsersDaoImpl usersDao = new UsersDaoImpl();
        String method = request.getParameter("method");
        ObjectMapper om = new ObjectMapper();
        response.setCharacterEncoding("utf-8");
        if (method.equals("findType")){
            List<Type> type = usersDao.findType();
            String json = om.writeValueAsString(type);
            System.out.println(json);
            response.getWriter().write(json);
        }else if (method.equals("findSubject")){
            String param = request.getParameter("param");
            int i = Integer.parseInt(param);
            List<Subject> subject = usersDao.findSubject(i);
            String s = om.writeValueAsString(subject);
            response.getWriter().write(s);
            System.out.println(s);
        }else if (method.equals("findCur")){
            String id = request.getParameter("param");
            int i = Integer.parseInt(id);
            List<UsersCourse> usersCourses = usersDao.arrCourse(i);
            String s = om.writeValueAsString(usersCourses);
            response.getWriter().write(s);
            System.out.println(s);
        }else if (method.equals("add")){
            String number = request.getParameter("number");
            NetState netState = new NetState();
            System.err.println(number);
            if (number.equals("0")){
                netState.setState(500);
            }else {
                netState.setState(200);
            }
            String s = om.writeValueAsString(netState);
            System.out.println("s  "+s);
            response.getWriter().write(s);
        }else if (method.equals("addCourse")){
            String coursename = request.getParameter("param");
            System.err.println(coursename);
            boolean flage = usersDao.findCourse(coursename);
            NetState netState = new NetState();
            if (!flage){
                netState.setState(200);
            }else {
                netState.setState(500);
            }
            String s = om.writeValueAsString(netState);
            System.err.println("======================="+s);
            response.getWriter().write(s);
        }
    }
//
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
