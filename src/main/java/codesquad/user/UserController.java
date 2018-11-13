package codesquad.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    @PostMapping("/user/create")
    public String create(User user) {
        user.setIndex(UserRepository.getUsers().size() + 1);
        UserRepository.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", UserRepository.getUsers());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String profile(Model model, @PathVariable String userId) {
        model.addAttribute("user",
                UserRepository.getUsers().stream()
                .filter(u -> u.isMatchUserId(userId))
                .findFirst()
                .orElse(null));
        return "/user/profile";
    }

    ////        todo: 회원정보수정
    @GetMapping("/users/{userId}/form")
    public String updateProfile(Model model, @PathVariable String userId) {
        model.addAttribute("user",
                UserRepository.getUsers().stream()
                        .filter(u -> u.isMatchUserId(userId))
                        .findFirst()
                        .orElse(null));
        return "/user/updateForm";
    }

    @PostMapping("/users/update")
    public String updateUser(User user) {
        return "redirect:/users";
    }
}
//todo: stream에서 map한 값은 object type인지 확인해보기

