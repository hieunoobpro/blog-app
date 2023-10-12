package vn.techmaster.blogapp.model.request;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertUserRequest {
        private String name;
        private String email;
        private String password = "123";
        private List<Integer> roleIds; // Danh sách id của các category áp dụng
}
