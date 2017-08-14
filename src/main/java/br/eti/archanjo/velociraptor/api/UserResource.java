package br.eti.archanjo.velociraptor.api;

import br.eti.archanjo.velociraptor.constants.PathConstants;
import br.eti.archanjo.velociraptor.dtos.UserDTO;
import br.eti.archanjo.velociraptor.enums.Status;
import br.eti.archanjo.velociraptor.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = PathConstants.API + PathConstants.USERS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final UserFacade userFacade;

    @Autowired
    public UserResource(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody UserDTO user) throws Exception {
        return userFacade.create(user, getClient());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserDTO> listUsers(@RequestParam("limit") Integer limit,
                                   @RequestParam("page") Integer page,
                                   @RequestParam(value = "status", required = false,
                                           defaultValue = "ENABLED") Status status) throws Exception {
        return userFacade.listUsers(page, limit, status, getClient());
    }

    @RequestMapping(path = PathConstants.ME, method = RequestMethod.GET)
    public UserDTO me() throws Exception {
        return userFacade.me(getClient());
    }
}
