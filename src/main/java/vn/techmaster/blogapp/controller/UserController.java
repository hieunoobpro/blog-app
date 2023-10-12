package vn.techmaster.blogapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blogapp.entity.Role;
import vn.techmaster.blogapp.entity.User;
import vn.techmaster.blogapp.repository.RoleRepository;
import vn.techmaster.blogapp.repository.UserRepository;
import vn.techmaster.blogapp.service.ImageService;
import vn.techmaster.blogapp.service.UserService;

import java.util.List;
@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, UserRepository userRepository, ImageService imageService, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.roleRepository = roleRepository;
    }
    @GetMapping
    public String getUserPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              Model model) {
        Page<User> pageData = userService.getAllUser(page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "admin/user/index";
    }
    //2. Tạo user
    @GetMapping("/create")
    public String getBlogCreatePage(Model model) {
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("roleList", roleList);
        return "admin/user/create";
    }


    //3. Chi tiết user
    @GetMapping("/{id}/detail")
    public String getBlogDetailPage(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("userList", roleList);
        model.addAttribute("imageList", imageService.getFilesOfCurrentUser());
        return "admin/user/detail";
    }
}


