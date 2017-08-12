package br.eti.archanjo.shortener.entities.mysql;

import br.eti.archanjo.shortener.enums.Roles;
import br.eti.archanjo.shortener.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "USERS", indexes = {
        @Index(name = "userPassStatsIdx", columnList = "username,password,status"),
        @Index(name = "statusIdx", columnList = "status")
})
@Entity(name = "USERS")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6046209000285746209L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private Roles roles;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "created")
    private Date created;

    @Column(name = "modified")
    private Date modified;


    @PrePersist
    private void prePersist() {
        created = new Date();
        modified = new Date();
    }

    @PostUpdate
    private void postUpdated() {
        modified = new Date();
    }
}
