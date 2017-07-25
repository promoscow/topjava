package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = new InMemoryUserRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        System.out.println(id);
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String userData = String.format("%s, %s, %s, %d", id, String.valueOf(localDateTime),
                name, age);

        log.info("user data: {}", userData);

        User user = new User(id.isEmpty() ? null : Integer.parseInt(id), localDateTime, name, age);

        log.info(user.isNew() ? "Create {}" : "Update {}", user);
        userRepository.save(user);
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                userRepository.delete(id);
                response.sendRedirect("users");
                break;
            case "create":
            case "update":
                final User user = "create".equals(action) ?
                        new User(LocalDateTime.now(), "", 18) :
                        userRepository.get(getId(request));
                log.info("Preparing to create user {}", user);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("users", userRepository.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
