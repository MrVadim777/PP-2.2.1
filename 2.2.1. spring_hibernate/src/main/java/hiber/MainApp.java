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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Haval", 7)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Citroen", 4)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("GAC", 3)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Ravon", 4)));

        List<User> users = new ArrayList<>();
        users.add(userService.getUserByCar("Ravon", 4));
        users.add(userService.getUserByCar("GAC", 3));
        users.add(userService.getUserByCar("Citroen", 4));
        users.add(userService.getUserByCar("Haval", 7));

        System.out.println("--------------------------------------------------");
        for (User user : users) {
            System.out.println(user.toString() + '\n');
        }

        context.close();
    }
}
