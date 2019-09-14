package cn.honeyjam.dorm.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String url=request.getRequestURI();
        if(request.getSession().getAttribute("user")==null)
        {
            if(url!=null && !url.equals("") && ( url.indexOf("Login")<0 && url.indexOf("login")<0 ))
            {
                session.setAttribute("error_msg","请先登录");
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<script>");
                out.println("window.open ('/login.jsp','_top')");
                out.println("</script>");
                out.println("</html>");
                out.flush();
                out.close();
            }
        }
//        else
//        {
//            if(url!=null && !url.equals("") && ( url.indexOf("Login")<0 && url.indexOf("login")<0 ))
//            {
//                response.sendRedirect(request.getContextPath() + "/index.jsp");
//            }
//        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
