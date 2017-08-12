package br.eti.archanjo.velociraptor.entities.mysql;

import br.eti.archanjo.velociraptor.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "DOMAINS", indexes = {
        @Index(name = "statusIdx", columnList = "status"),
        @Index(name = "statusDomainIdx", columnList = "domain,status"),
        @Index(name = "tokenIdx", columnList = "token"),
})
@Entity(name = "DOMAINS")
public class DomainEntity implements Serializable {

    private static final long serialVersionUID = -8484034461271946183L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "isSSL")
    private boolean isSSL;

    @OneToMany
    private List<UrlEntity> urls;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;

    @ManyToMany
    private List<UserEntity> users;

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
