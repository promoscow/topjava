package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.UserUtil;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    ConfigurableApplicationContext appCtx;
    AdminRestController adminUserController;
    UserRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        adminUserController = appCtx.getBean(AdminRestController.class);

        UserUtil.fillUsers(adminUserController);
        repository = new InMemoryUserRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                log.info(show(action,
                        request.getParameter("name"),
                        request.getParameter("password"),
                        ""));
                response.sendRedirect("meals");
                break;
            case "signup":
                log.info(show(action,
                        request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("password")));
                adminUserController.create(new User(null,
                        request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("password"),
                        Role.ROLE_USER));
                response.sendRedirect("meals");
                break;
            case "update":
                User user = adminUserController.get(getId(request));
                user.setName(request.getParameter("name"));
                user.setPassword(request.getParameter("password"));
                user.setEmail(request.getParameter("email"));
                user.setEnabled(request.getParameter("enabled") != null && request.getParameter("enabled").equals("true"));
                user.setCaloriesPerDay(Integer.parseInt(request.getParameter("caloriesPerDay")));
                response.sendRedirect("users");
                break;
            case "search-by-email":
                User user1 = adminUserController.getByMail(request.getParameter("email"));
                if (user1.getId() == null) response.sendRedirect("users");
                else {
                    request.setAttribute("users", Arrays.asList(user1));
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                }
                break;
        }

//        response.sendRedirect("/index.html");
    }

    private String show(String action, String name, String password, String email) {
        return String.format("%s — name={%s}, password={%s}%s%n", action, name, password,
                email.length() == 0 ? "" : ", email=" + email);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "signup":
            case "login":
                request.setAttribute("action", action);
                request.setAttribute("user", new User(true, new Date(), Role.ROLE_USER));
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
                break;
            case "update":
                final User user = adminUserController.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user.jsp").forward(request, response);
                break;
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                adminUserController.delete(id);
                response.sendRedirect("users");
                break;
            case "all":
            default:
                request.setAttribute("users", adminUserController.getAll());
//                adminUserController.getAll().forEach(System.out::println);
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
