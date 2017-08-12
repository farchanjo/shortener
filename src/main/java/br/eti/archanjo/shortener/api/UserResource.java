package br.eti.archanjo.shortener.api;

import br.eti.archanjo.shortener.constants.PathConstants;
import br.eti.archanjo.shortener.dtos.UserDTO;
import br.eti.archanjo.shortener.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PathConstants.USERS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserResource extends GenericResource {
    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final UserFacade userFacade;

    @Autowired
    public UserResource(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO createUser() throws Exception {
        return userFacade.create(getClient());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserDTO> listUsers(@RequestParam("limit") Integer limit,
                                   @RequestParam("size") Integer size) throws Exception {
        return userFacade.listUsers(limit, size, getClient());
    }

    @RequestMapping(path = PathConstants.ME, method = RequestMethod.GET)
    public UserDTO me() throws Exception {
        return userFacade.me(getClient());
    }
}
