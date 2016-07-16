package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.*;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

@Path("users")
public class UsersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map userInfo,
                               @Context UriInfo uriInfo) {
        User user = new User(userInfo.get("name").toString());
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @Path("{userId}")
    public UserApi getUser(@PathParam("userId") String userId,
                           @Context UserRepository userRepository) {
        return userRepository.ofId(new UserId(userId))
                .map(UserApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
