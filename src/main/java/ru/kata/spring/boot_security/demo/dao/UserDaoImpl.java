//package ru.kata.spring.boot_security.demo.dao;
//
//
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.model.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//
//class UserDaoImpl implements UserDao {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//
//
//    @Override
//
//    public void saveUser(User user) {
////        entityManager.getCurrentSession().saveOrUpdate(user);
//        entityManager.persist(user);
//        entityManager.flush();
//    }
//
//    @Override
//
//    public void updateUser(User user) {
//        entityManager.merge(user);
//        entityManager.flush();
//    }
//
//    @Override
//
//    public void delete(Long id) {
////        TypedQuery<User> query = entityManager.getCurrentSession().createQuery("delete from User where id =:userID");
////        query.setParameter("userID", id);
////        query.executeUpdate();
//        entityManager.createQuery("delete from User where id=: id")
//                .setParameter("id", id)
//                .executeUpdate();
//        entityManager.flush();
//
//    }
//
//    @Override
//
//    public User getById(Long id) {
////        return entityManager.getCurrentSession().get(User.class, id);
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//
//    public List<User> getAllUsers() {
////        TypedQuery<User> query = entityManager.getCurrentSession().createQuery("from User");
////        return query.getResultList();
//        return entityManager.createQuery("from User").getResultList();
//    }
//
//    @Override
//
//    public User findByUsername(String username) {
//        return entityManager.createQuery("select distinct a from User a left join fetch a.roles where a.username = :username", User.class)
//                .setParameter("username", username).getSingleResult();
//    }
//
//}