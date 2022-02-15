package ch.dentsu.aluethi.gradecalulator.service;

import ch.dentsu.aluethi.gradecalulator.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped

public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    public User getSingleUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void delete(Long id) {
        User userToDelete = getSingleUser(id);
        entityManager.remove(userToDelete);
    }
    @Transactional
    public User update(User user){
        entityManager.merge(user);
        return user;
    }
}
