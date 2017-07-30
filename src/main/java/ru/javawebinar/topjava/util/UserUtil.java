package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.user.AdminRestController;

/**
 * Created by promoscow on 29.07.17.
 */
public class UserUtil {

    public static void fillUsers(AdminRestController controller) {
        controller.create(new User(null, "petya", "slkjdhfjkds@list.ru", "112233", Role.ROLE_ADMIN));
        controller.create(new User(null, "vasya", "298743@mail.ru", "12345", Role.ROLE_USER));
        controller.create(new User(null, "pojo", "098jhgfd@list.ru", "09876", Role.ROLE_USER));
        controller.create(new User(null, "mojo", "000999888@yandex.ru", "101010", Role.ROLE_USER));
        controller.create(new User(null, "qwerty", "miha@bk.ru", "00009999", Role.ROLE_USER));
        controller.create(new User(null, "hell_keeper", "kent111@list.ru", "qwerty111222", Role.ROLE_USER));
        controller.create(new User(null, "mockup_chel", "gosha@google.com", "pop0987", Role.ROLE_USER));
    }
}
