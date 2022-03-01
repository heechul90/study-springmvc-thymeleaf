package study.springmvc.thymeleaf.basic;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {

    @GetMapping(value = "/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!!");
        return "basic/text-basic";
    }

    @GetMapping(value = "/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping(value = "/variable")
    public String variable(Model model) {
        User userA = new User("userA", 33);
        User userB = new User("userB", 22);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";

    }

    @GetMapping(value = "/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "hello " + data;
        }
    }

    @GetMapping(value = "/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/basic-date";
    }

    @GetMapping(value = "/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping(value = "/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Stpring!");
        return "basic/literal";
    }

    @GetMapping(value = "/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping(value = "/attribute")
    public String attribute(Model model) {
        return "basic/attribute";
    }

    @GetMapping(value = "/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    @GetMapping(value = "/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 25));
        list.add(new User("userB", 20));
        list.add(new User("userC", 15));

        model.addAttribute("users", list);
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }


}
