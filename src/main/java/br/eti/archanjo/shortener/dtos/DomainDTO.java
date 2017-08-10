package br.eti.archanjo.shortener.dtos;

import br.eti.archanjo.shortener.enums.Status;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class DomainDTO implements Serializable {
    private static final long serialVersionUID = 2434592629028454357L;
    private Long id;
    private String domain;
    private Status status;
    private Date created;
    private Date modified;
}
