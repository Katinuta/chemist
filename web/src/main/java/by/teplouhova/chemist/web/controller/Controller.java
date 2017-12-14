package by.teplouhova.chemist.web.controller;

import by.teplouhova.chemist.web.SessionRequestContent;
import by.teplouhova.chemist.web.command.ActionFactory;
import by.teplouhova.chemist.web.command.Command;
import by.teplouhova.chemist.web.command.CommandResult;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet{
    private final static Logger LOGGER= LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName=request.getParameter("command");
        Command command= ActionFactory.defineCommand(commandName);
        SessionRequestContent requestContent=new SessionRequestContent();
      //  requestContent.extractValues(request);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        CommandResult page=command.execute(request);
      //  requestContent.insertAttributes(request);

            request.getRequestDispatcher(page.getPage()).forward(request,response);


    }
}
