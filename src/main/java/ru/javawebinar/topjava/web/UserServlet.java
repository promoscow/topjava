package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("doPost");
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                show(action, request.getParameter("name"), request.getParameter("password"), "");
                break;
            case "signup":
                show(action, request.getParameter("name"), request.getParameter("password"),
                        request.getParameter("email"));
        }

        responce.sendRedirect("meals");
    }

    private void show(String action, String name, String password, String email) {
        String e = email.length() == 0 ? "" : ", email=" + email;
        System.out.printf("%s — name={%s}, password={%s}%s%n", action, name, password, e);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        request.setAttribute("action", action);
        request.setAttribute("user", new User(true, new Date(), Role.ROLE_USER));
        log.debug("User: {}", action);
        request.getRequestDispatcher("/authorization.jsp").forward(request, response);

//        log.debug("forward to users");
//        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
