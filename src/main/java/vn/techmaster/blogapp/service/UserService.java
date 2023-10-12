package vn.techmaster.blogapp.service;
import com.github.slugify.Slugify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.techmaster.blogapp.entity.Blog;
import vn.techmaster.blogapp.entity.Category;
import vn.techmaster.blogapp.entity.Role;
import vn.techmaster.blogapp.entity.User;
import vn.techmaster.blogapp.exception.NotFoundException;
import vn.techmaster.blogapp.model.request.UpsertBlogRequest;
import vn.techmaster.blogapp.model.request.UpsertUserRequest;
import vn.techmaster.blogapp.repository.RoleRepository;
import vn.techmaster.blogapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found blog with id = " + id));
    }
    public Page<User> getAllUser(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").descending());
        return userRepository.findAll(pageable);
    }
    public User createUser(UpsertUserRequest request) {
        // TODO: Validate thông tin (nếu cần thiết) - validation

        // Tìm kiếm roles
        List<Role> roles = roleRepository.findByIdIn(request.getRoleIds());
        // Tao User
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User getBlogById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id = " + id));
    }

    public User updateBlog(Integer id, UpsertUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id = " + id));

        // TODO: Validate thông tin (nếu cần thiết) - validation
        // Tìm kiếm roles
        List<Role> roles = roleRepository.findByIdIn(request.getRoleIds());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void deleteBlog(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id = " + id));
        userRepository.delete(user);
    }
}