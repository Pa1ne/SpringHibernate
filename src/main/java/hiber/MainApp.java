package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User alexey = new User("Алексей", "Попович", "alex1861@lexa.ru");
      User mikhail = new User("Михаил", "Круг", "mixa1962@mix.com");
      User alina = new User("Алина", "Пай", "alina1998@mail.com");

      Car alinaCar = new Car("Porsche Panamera", 777, alina);
      Car alexeyCar = new Car("Toyota Yaris", 1, alexey);

      alina.setCar(alinaCar);
      alexey.setCar(alexeyCar);

      userService.add(mikhail);
      userService.add(alexey);
      userService.add(alina);

      System.out.println();

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car: " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByModelAndSeriesOfCar("Toyota Yaris", 1));
      System.out.println(userService.getUserByModelAndSeriesOfCar("Yaris", 0));

      context.close();
   }
}
