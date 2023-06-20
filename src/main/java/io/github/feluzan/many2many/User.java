package io.github.feluzan.many2many;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<User> friends;

    @JsonIgnore
    private List<User> friendOf;

    @JsonFilter("customFriendshipFilter")
    private List<User> friendship;

    public void addFriendship(User friend){
        this.friendship.add(friend);
    }

}
