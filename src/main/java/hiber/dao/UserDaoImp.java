package hiber.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.List;

import hiber.model.User;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByModelAndSeriesOfCar(String model, int series) {
      try {
         Session session = sessionFactory.getCurrentSession();
         return session.createQuery("from User u where u.car.model=:model and u.car.series=:series", User.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .setFirstResult(0)
                 .setMaxResults(1)
                 .getSingleResult();
      } catch (NoResultException ex) {
         System.out.println("User with such a car not found");
         return null;
      }
   }
}