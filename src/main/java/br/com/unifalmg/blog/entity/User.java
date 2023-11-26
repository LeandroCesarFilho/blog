package br.com.unifalmg.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "db2022108011", name = "user")
public class User implements Serializable { // Importante colocar "implements Serializable" para não dar erro

    @Id
    private Integer id;

    private String name;

    private String username;

    private String phone;

    private String email;

    private String website;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
