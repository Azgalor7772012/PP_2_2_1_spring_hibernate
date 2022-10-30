package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByModel(String model) {
      User user = null;
      try(Session session = sessionFactory.openSession()) {
         TypedQuery<Car> query =
                 session.createQuery("Select u From Car u WHERE u.model=:model", Car.class);
         query.setParameter("model", model);
         long id = query.getSingleResult().getId();
         Query<User> query1 = session.createNativeQuery("SELECT * FROM users WHERE cars_id=:id",
                 User.class);
         query1.setParameter("id", id);
         user = query1.getSingleResult();
//         TypedQuery<User> query2 = session.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class);
//         query2.setParameter("id", id);
//         user = query2.getSingleResult();

      } catch(Exception e) {
         e.printStackTrace();
      }
      return user;

   }



}
