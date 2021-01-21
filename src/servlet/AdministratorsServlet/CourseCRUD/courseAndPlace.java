package servlet.AdministratorsServlet.CourseCRUD;

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

//查看某个课表与教室的关联情况
@WebServlet("/courseAndPlace")
public class courseAndPlace extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String number = request.getParameter("number");
        UsersServiceImpl usersService = new UsersServiceImpl();
        UsersCourse one = usersService.findOneCourseAndRoon(number);
        String time = one.getTime();
        List<UsersCourse> used = usersService.findCourseAndRoom(time);
        List<Room> room = usersService.findRoom();//查看所有的教室
        for (Room r: room) {
            String place = r.getPlace();
            for (UsersCourse use: used) {
                if (place.equals(use.getPlace())){
                    r.setState(1);
                }
            }
        }
        request.setAttribute("one",one);
        request.setAttribute("room",room);
        request.getRequestDispatcher("/Administrators/CourseCRUD/courseAndPlaceList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
