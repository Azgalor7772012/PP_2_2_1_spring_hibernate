package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//      User user1 = new User("User1", "Lastname1", "@gmail.com");
//      user1.setCar(new Car("Nissan", 2));
//
//      User user2 = new User("User2", "Lastname2", "@yandex.ru");
//      user2.setCar(new Car("Volvo", 5));
//
//      User user3  = new User("User3", "Lastname3", "user3@mail.ru");
//      user3.setCar(new Car("BMW", 3));
//
//      userService.add(user1);
//      userService.add(user2);
//      userService.add(user3);

      List<User> users = new ArrayList<>();
      User firstUser = userService.getUserByModel("BMW");
      User secondUser = userService.getUserByModel("Volvo");
      User thirdUser = userService.getUserByModel("Nissan");
      users.add(firstUser);
      users.add(secondUser);
      users.add(thirdUser);

      for (User user : users) {
         System.out.println(user);
      }


//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }


      context.close();
   }
}
