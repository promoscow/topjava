package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by promoscow on 24.07.17.
 */
public class InMemoryUserRepositoryImpl implements UserRepository {

    /** Репозиторий — хранилище юзеров в памяти (для тестов), потом будет в БД. */
    private Map<Integer, User> userRepo = new ConcurrentHashMap<>();

    /** counter — счётчик id */
    private AtomicInteger counter = new AtomicInteger(0);

    /** Заносим всех юзеров из хардкод-страницы в репозиторий. */
    {
        UserUtil.USERS.forEach(this::save);
    }

    /**
     * Метод получает экземпляр класса, полученный через сервлет (?)
     * Если юзер новый (id == null), то создаётся новый id путём инкремента
     * и в репозиторий кладётся новый пользователь.
     *
     * @param user — передаётся из сервлета (?)
     * @return user из параметра.
     */
    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            userRepo.put(user.getId(), user);
        }
        return user;
    }

    /** Удаление экземпляра класса из карты репозитория по id */
    @Override
    public void delete(int id) {
        userRepo.remove(id);
    }

    /** Взятие экземпляра класса из карты репозитория по id */
    @Override
    public User get(int id) {
        return userRepo.get(id);
    }

    /** Взятие всех объектов user из репозитория в виде коллекции */
    @Override
    public Collection<User> getAll() {
        return userRepo.values();
    }
}
