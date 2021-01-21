package servlet.AdministratorsServlet.CourseCRUD;

import domain.UsersCourse;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

//学生选课表的添加功能
@WebServlet("/addCourse")
public class AddCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        Map<String, String[]> condition = request.getParameterMap();
        UsersCourse usersCourse = new UsersCourse();
//        try {
//            BeanUtils.populate(usersCourse,condition);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        UsersServiceImpl usersService = new UsersServiceImpl();

        String week = request.getParameter("week");
        String day = request.getParameter("day");
        String time=week+day;
        String coursename = request.getParameter("coursename");
        String credit = request.getParameter("credit");
        String type = request.getParameter("type");
        usersCourse.setCoursename(coursename);//名称
        usersCourse.setCredit(credit);//学分
        usersCourse.setType(type);//类型
        usersCourse.setTime(time);//上课时间
        usersCourse.setWeeks("1-16周");//学时
        usersService.addCourse(usersCourse);
        request.getRequestDispatcher("/Administrators/CourseCRUD/addCourse.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
