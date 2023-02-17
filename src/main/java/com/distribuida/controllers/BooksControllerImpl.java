package com.distribuida.controllers;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.json.Json;



import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
@Path("/books")
@ApplicationScoped

public class BooksControllerImpl implements BooksController{
private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

@Inject
private BookRepository booksService;

private final GreetingProvider greetingProvider;

@Inject
public BooksControllerImpl(GreetingProvider greetingConfig) {
	this.greetingProvider = greetingConfig;
}

@Override
@GET
@Operation(summary = "Returns a generic greeting",
				description = "Greets the user generically")
@APIResponse(description = "Simple JSON containing the greeting",
				content = @Content(mediaType = "application/json",
								schema = @Schema(implementation = Book.class)))
@Produces(MediaType.APPLICATION_JSON)
public List<Book> findAll() {
	return booksService.findAll();
}

@Override
@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Book findOne(@PathParam("id") int id) {
	return booksService.findOne(id);
}

@Override
@PUT
@Path("/{id}")
@Operation(summary = "Returns a generic greeting",
				description = "Greets the user generically")
@APIResponse(description = "Simple JSON containing the greeting",
				content = @Content(mediaType = "application/json",
								schema = @Schema(implementation = Book.class)))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Book update(@PathParam("id") int id, Book book) {
	Book singObj = booksService.findOne(id);
	if (singObj != null) {
		
		return booksService.update(singObj);
	}
	return null;
}

@Override
@POST
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Book save(Book book) {
	return booksService.save(book);
}

@Override
@DELETE
@Path("/{id}")
public void delete(@PathParam("id") int id) {
	booksService.delete(id);
}
@Path("/greeting")
@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestBody(name = "greeting",
				required = true,
				content = @Content(mediaType = "application/json",
								schema = @Schema(type = SchemaType.STRING, example = "{\"greeting\" : \"Hola\"}")))
@APIResponses({
				@APIResponse(name = "normal", responseCode = "204", description = "Greeting updated"),
				@APIResponse(name = "missing 'greeting'", responseCode = "400",
								description = "JSON did not contain setting for 'greeting'")})
public Response updateGreeting(JsonObject jsonObject) {
	
	if (!jsonObject.containsKey("greeting")) {
		JsonObject entity = JSON.createObjectBuilder()
						                    .add("error", "No greeting provided")
						                    .build();
		return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
	}
	
	String newGreeting = jsonObject.getString("greeting");
	
	greetingProvider.setMessage(newGreeting);
	return Response.status(Response.Status.NO_CONTENT).build();
}

}
