package cn.honeyjam.dorm.controller;

import cn.honeyjam.dorm.common.Identify;
import cn.honeyjam.dorm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("checkLogin.do")
    public String queryStu(
            @RequestParam("userId")String userID,
            @RequestParam("password")String password,
            @RequestParam("sign")String sign,
            @RequestParam(name = "remember",defaultValue ="false")Boolean remember,
            HttpServletResponse response,
            HttpServletRequest request,
            Model model
    )
    {
        if(StringUtil.isEmpty(userID)||StringUtil.isEmpty(password)||StringUtil.isEmpty(sign))
        {
            model.addAttribute("error_msg","请填写完整的用户信息");
            return "redirect:index.jsp";
        }
        else
        {
            Object user = loginService.queryMember(userID,password,sign);
            HttpSession session = request.getSession();
            if(user!=null)
            {
                Cookie[] cookies = request.getCookies();
                if(remember)
                {
                    //保存,记住密码
//                    Cookie cookie = new Cookie("userId",userID);
//                    Cookie cookie1 = new Cookie("password",password);

//                    cookie.setMaxAge(60*60*24);
//                    cookie1.setMaxAge(60*60*24);

//                    response.addCookie(cookie);
//                    response.addCookie(cookie1);

                    //设置session的sessionID不过期
                    for(Cookie cookie:cookies)
                    {
                        if(cookie.equals("JSESSIONID"))
                        {
                            cookie.setMaxAge(60*60*24*7);
                        }
                    }
                    //当记住密码功能，包含对应的信息
                    session.setAttribute("user_Login",user);
                }
                else
                {
//                    for(Cookie cookie:cookies)
//                    {
//                        if(cookie.getName().equals("userId")||cookie.getName().equals("password")||cookie.getName().equals("remembea"))
//                        {
//                            cookie.setValue(null);
//                            cookie.setMaxAge(0);
//                            response.addCookie(cookie);
//                        }
//                    }
                    for(Cookie cookie:cookies)
                    {
                        if(cookie.equals("JSESSIONID"))
                        {
                            cookie.setMaxAge(60*24);
                        }
                    }
                    if(session.getAttribute("user_Login")!=null)
                    session.removeAttribute("user_Login");
                }
                Cookie cookie2= new Cookie("remember", remember.toString());
                cookie2.setMaxAge(60*60*24*7);
                response.addCookie(cookie2);
                session.setAttribute("user",user);
                model.addAttribute("userInfo",user);
                String s = Identify.queryIdentify(user);
                if(s.equals("学生"))
                {
                    return "redirect:student/index.jsp";
                }
                else if(s.equals("宿管"))
                {
                    return "redirect:manage/index.jsp";
                }
            }
            else
            {
                model.addAttribute("error_msg","用户名或者密码错误");
                return "redirect:index.jsp";
            }
        }
        return "redirect:index.jsp";
    }

    @GetMapping("logout.do")
    public ModelAndView logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("user",null);
        if(session.getAttribute("user_Login")==null)
        {
            session.invalidate();
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/index.jsp");
        return mv;
    }

    @GetMapping("test.do")
    public String test()
    {
        return null;
    }

}
