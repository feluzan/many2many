package io.github.feluzan.many2many;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friendship", joinColumns = @JoinColumn(name = "id_friend"), inverseJoinColumns = @JoinColumn(name = "id_friend_of"))
    private List<UserEntity> friends;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "friends")
    private List<UserEntity> friendOf;

    @Transient
    private List<UserEntity> friendship;


//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "following")
//    private List<UserEntity> followedBy;
}

