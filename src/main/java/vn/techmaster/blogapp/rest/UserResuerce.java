package vn.techmaster.blogapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blogapp.model.request.UpsertBlogRequest;
import vn.techmaster.blogapp.model.request.UpsertUserRequest;
import vn.techmaster.blogapp.service.UserService;

@RestController
@RequestMapping("/api/v1/admin/user")
public class UserResuerce {
    private final UserService userService;

    public UserResuerce(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<?> getAllUser(@RequestParam(required = false, defaultValue = "1") Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(userService.getAllUser(page, limit));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getBlogById(id));
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UpsertUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpsertUserRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.updateBlog(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
