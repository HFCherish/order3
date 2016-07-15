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
import java.util.ArrayList;
import java.util.List;
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
    public ProductResponseData getOne(@PathParam("prodId") String prodId,
                                      @Context UriInfo uriInfo,
                                      @Context ProductRepository productRepository) {
        return new ProductResponseData(productRepository.findById(prodId).map(product -> product).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)), uriInfo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductResponseData> getAll() {
        return new ArrayList<>();
    }
}
