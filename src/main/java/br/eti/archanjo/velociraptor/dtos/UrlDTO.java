package br.eti.archanjo.velociraptor.dtos;

import br.eti.archanjo.velociraptor.enums.Status;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UrlDTO implements Serializable {
    private static final long serialVersionUID = 2434592629028454357L;
    private Long id;
    private String shortValue;
    private String destination;
    private Status status;
    private String completeUrl;
    private Long domainId;
    private Date expirationDate;
    private Long maxRequests;
    private Date created;
    private Date modified;

}
