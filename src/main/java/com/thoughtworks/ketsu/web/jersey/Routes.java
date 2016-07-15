package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.user.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI relativePath(UriInfo uriInfo) {
        return URI.create(uriInfo.getPath());
    }

    public String productUrlString(String prodId) {
        return "/products/" + prodId;
    }
}
