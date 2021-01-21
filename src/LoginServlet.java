import dao.UserDao;
import dao.impl.UsersDaoImpl;
import domain.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码

        HttpSession session = req.getSession();//作用域再一次会话范围内
        String checkcode_session = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//避免验证码重复使用
        String contextPath = req.getContextPath();
        //为什么不能使用map集合
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");
        req.setAttribute("username",username);//什么作用？？？
        session.setAttribute("username",username);//使用session来存储用户的登录信息
//        Cookie cookie = new Cookie("username",username);//使用cookie   如何让每一个用户获取自己的username？？
//        cookie.setMaxAge(60 * 60 * 3);
//        resp.addCookie(cookie);

        Users loginusers = new Users();//为UserDao做铺垫 封装
        loginusers.setUsername(username);
        loginusers.setPassword(password);

        UserDao usersDao = new UsersDaoImpl();//创建对象
        if (checkcode_session!=null&&checkcode_session.equalsIgnoreCase(checkCode)) {//避免空指针
            try {
                Users login = usersDao.loginM(loginusers);//在数据库查询
                String page = login.getPage();
                session.setAttribute("login", login);// 存储数据（共享数据）在一次请求的范围内
//                req.getRequestDispatcher(page).forward(req, resp);不能用跳转 只能用转发
                resp.sendRedirect(page);//到主页
            } catch (Exception e) {
                req.setAttribute("login_error","密码错误");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("check_error","验证码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
