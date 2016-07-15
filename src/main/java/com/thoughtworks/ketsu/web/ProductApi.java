package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.web.beans.ProductResponseData;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

@Path("products")
public class ProductApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map productInfo,
                           @Context UriInfo uriInfo,
                           @Context ProductRepository productRepository) {
        productRepository.save(new Product(productInfo.get("name").toString(),
                productInfo.get("description").toString(),
                (double)productInfo.get(("price"))));
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @GET
    @Path("{prodId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductResponseData getOne(@Param("prodId") String prodId,
                                      @Context UriInfo uriInfo) {
        return new ProductResponseData(new Product( "Imran", "teacher", 1000.1), uriInfo);
    }
}