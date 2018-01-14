package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.commandajax.Command;
import by.teplouhova.chemist.commandajax.CommandFactory;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

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

        String commandName = request.getParameter("command");
        Command command = CommandFactory.defineCommand(commandName);
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        JSONObject result = command.execute(requestContent);
        requestContent.insertAttributes(request);
        response.setContentType("application/json");
        response.getWriter().write(result.toString());



    }
}
