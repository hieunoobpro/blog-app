package vn.techmaster.blogapp.repository;

import org.springframework.data.jpa.repository.Query;
import vn.techmaster.blogapp.entity.Category;
import vn.techmaster.blogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.blogapp.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    List<Role> findByIdIn(List<Integer> roleIds);
}
