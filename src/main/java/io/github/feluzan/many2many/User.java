package io.github.feluzan.many2many;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;

    @JsonFilter("followingCustomFilter")
    private List<User> following;

    @JsonFilter("followedByCustomFilter")
    private List<User> followedBy;

    public void addFollowing(User followed){
        this.following.add(followed);
    }

}
