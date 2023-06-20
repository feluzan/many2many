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

    @JsonFilter("friendsCustomFilter")
    private List<User> friends;

    @JsonFilter("friendOfCustomFilter")
    private List<User> friendOf;

    public void addFriends(User friend){
        this.friends.add(friend);
    }

}
