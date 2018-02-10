package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandFactory;
import by.teplouhova.chemist.command.CommandResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Class DoctorController.
 */
@WebServlet("/doctor")
public class DoctorController extends HttpServlet {

    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.toString());
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("command");
        Command command = CommandFactory.defineCommand(commandName);
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        CommandResult page = command.execute(requestContent);
        requestContent.insertAttributes(request);

        if (page.getResponseType()==CommandResult.ResponseType.FORWARD) {
            request.getRequestDispatcher(page.getPage()).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + page.getPage());
        }

    }
}
