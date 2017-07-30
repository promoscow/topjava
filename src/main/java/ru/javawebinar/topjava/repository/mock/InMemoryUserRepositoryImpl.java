package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private static Map<Integer, User> usersRepo = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        usersRepo.remove(id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        usersRepo.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return usersRepo.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return new ArrayList<>(usersRepo.values());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        User user1;
        try {
            user1 = usersRepo.values().stream().filter(user -> email.equals(user.getEmail())).findFirst().get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new User();
        }
        return user1;
    }
}
