package servlet.AdministratorsServlet.CourseCRUD;

import domain.UsersCourse;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
//课表信息的修改
@WebServlet("/UpdataCourseServlet")
public class UpdataCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        UsersCourse course = new UsersCourse();
        try {
            BeanUtils.populate(course,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //修改信息
        UsersServiceImpl usersService = new UsersServiceImpl();
        //调用更新方法
        usersService.updateCourse(course);
        request.getRequestDispatcher("/FindSCourseByPageServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
