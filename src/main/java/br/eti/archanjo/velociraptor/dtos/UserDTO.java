package br.eti.archanjo.velociraptor.dtos;

import br.eti.archanjo.velociraptor.enums.Roles;
import br.eti.archanjo.velociraptor.enums.Status;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 3159108320293992291L;
    private Long id;
    private String name;
    private String password;
    private String email;
    private String username;
    private Roles roles;
    private Status status;
    private Date created;
    private Date modified;
}
