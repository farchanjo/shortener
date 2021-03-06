package br.eti.archanjo.velociraptor.entities.mysql;

import br.eti.archanjo.velociraptor.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "URLS", indexes = {
        @Index(name = "shortValueIdx", columnList = "shortValue"),
        @Index(name = "shortAndStatusIdx", columnList = "shortValue,status")
})
@Entity(name = "URLS")
public class UrlEntity implements Serializable {

    private static final long serialVersionUID = 3949423743473962511L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "shortValue")
    private String shortValue;

    @Column(name = "destination")
    private String destination;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "maxRequests")
    private Long maxRequests;

    @Column(name = "created")
    private Date created;

    @Column(name = "modified")
    private Date modified;

    @ManyToOne
    private DomainEntity domain;

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
