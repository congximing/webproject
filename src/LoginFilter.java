import domain.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*@WebFilter("/*")*/
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Users login = (Users) session.getAttribute("login");

        String servletPath = req.getServletPath();
        System.out.println(servletPath);//  /login.jsp
        String[] urls = {"/loginServlet","/json",".js",".css",".ico",".jpg",".png","/checkCodeServlet"};
        boolean flag = true;
        for (String str : urls) {
            if (servletPath.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }
        if (flag) {
            if (login != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
                System.out.println("req.getContextPath()"+req.getContextPath());
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
