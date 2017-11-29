package by.teplouhova.chemist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/chemist")
public class Controller extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCharacterEncoding());
        User user=new User();
        String name=req.getParameter("name");
       // user.setName(req.getParameter("name"));
//        user.setSurname(req.getParameter("surname"));
//        user.setRole(RoleEnum.CLIENT);
//        user.setPhone(req.getParameter("phone"));
//        user.setAccount(BigDecimal.valueOf(Float.valueOf(req.getParameter("account"))));
//        user.setPassword(req.getParameter("pwd"));
//        user.setLogin(req.getParameter("login"));
        req.setAttribute("name",name);
        req.getRequestDispatcher("res.jsp").forward(req,resp);
       // System.out.println(user);
    }
}
