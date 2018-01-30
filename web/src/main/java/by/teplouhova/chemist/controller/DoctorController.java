package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandFactory;
import by.teplouhova.chemist.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor")
public class DoctorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.toString());
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("command");
        Command command = CommandFactory.defineCommand(commandName);
        request.getSession().setAttribute("refererUrl", request.getHeader("referer"));
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        CommandResult page = command.execute(requestContent);
        requestContent.insertAttributes(request);

        if (CommandResult.ResponseType.FORWARD==(page.getResponseType())) {
            request.getRequestDispatcher(page.getPage()).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + page.getPage());
        }

    }
}
