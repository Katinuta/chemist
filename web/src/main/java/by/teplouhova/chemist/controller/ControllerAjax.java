package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandFactory;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

@WebServlet("/controllerAjax")
public class ControllerAjax extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String commandName = request.getParameter("command");
//        Command command = CommandFactory.defineCommand(commandName);
        String id = request.getParameter("medicineForAdd");
        HashMap<Medicine,Integer> basket= (HashMap<Medicine, Integer>) request.getSession().getAttribute("basket");
        TransactionManager manager=new TransactionManager();
        MedicineDAO dao= DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(dao);
        Medicine medicine= null;
        try {
            medicine = dao.findById(Integer.valueOf(id));
            LOGGER.log(Level.DEBUG, medicine);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        basket.put(medicine,1);
        request.getSession().setAttribute("basket",basket);
        //LOGGER.log(Level.DEBUG, request.getSession().getAttribute("basket"));
        manager.endTransaction();
        response.getWriter().write(basket.size());
//        SessionRequestContent requestContent = new SessionRequestContent();
//        requestContent.extractValues(request);

//        CommandResult page = command.execute(requestContent);
//        requestContent.insertAttributes(request);
//        if (CommandResult.ResponseType.FORWARD.equals(page.getResponseType())) {
//            request.getRequestDispatcher(page.getPage()).forward(request, response);
//        } else {
//            response.sendRedirect(request.getContextPath() + page.getPage());
//        }


    }
}
