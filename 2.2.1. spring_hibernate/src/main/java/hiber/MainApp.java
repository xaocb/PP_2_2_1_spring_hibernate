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

        User user_1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user_2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user_3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user_4 = new User("User4", "Lastname4", "user4@mail.ru");

        user_1.setCar(new Car("Rio", 1));
        user_2.setCar(new Car("Solaris", 2));
        user_3.setCar(new Car("Sportage", 3));
        user_4.setCar(new Car("Sportage", 3));

        userService.add(user_1);
        userService.add(user_2);
        userService.add(user_3);
        userService.add(user_4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.findUserByCar("Sportage", 3));

        context.close();
    }
}
