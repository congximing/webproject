package servlet.AdministratorsServlet.ClassCRUD;

import domain.Room;
import domain.UsersCourse;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

@WebServlet("/FindClassroomServlet")
public class FindClassroomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String rows = request.getParameter("rows");
        String currentPage = request.getParameter("currentPage");
        String week = request.getParameter("week");
        String time = request.getParameter("time");
        if(week == "" || week == null){
            week = "周三";
        }
        if(time == "" || time == null){
            time = "下午一二节";
        }
        String s= week + time;
//        if ("".equals(currentPage) || currentPage==null){
//            currentPage = "1";
//        }
//        if ("".equals(rows) || rows==null){
//            rows = "5";
//        }
        UsersServiceImpl usersService = new UsersServiceImpl();
        List<Room> room = usersService.findRoom();
        List<UsersCourse> used = usersService.findCourseAndRoom(s);//某一时间占用的教室
        for (Room r: room) {
            String place = r.getPlace();
            for (UsersCourse u : used) {
                String place1 = u.getPlace();
                if (place.equals(place1)){
                    r.setState(1);
                }
            }
        }
        int count = usersService.courseAndRoomCount(s);
        request.setAttribute("count",count);
        request.setAttribute("room",room);
        request.getRequestDispatcher("/Administrators/ClassManager/roomstate.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
