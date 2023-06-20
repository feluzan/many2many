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
    @JoinTable(name = "follows", joinColumns = @JoinColumn(name = "id_follower"), inverseJoinColumns = @JoinColumn(name = "id_followed"))
    private List<UserEntity> following;


//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "following")
//    private List<UserEntity> followedBy;
}

