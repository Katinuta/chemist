package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.RoleEnum;
import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.exception.DAOException;
import by.teplouhova.chemist.impl.User;
import by.teplouhova.chemist.impl.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/controller")
public class Controller extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        Command command;
    }
}
