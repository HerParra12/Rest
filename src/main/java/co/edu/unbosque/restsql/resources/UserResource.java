package co.edu.unbosque.restsql.resources;
import co.edu.unbosque.restsql.model.*;
import co.edu.unbosque.restsql.services.UserServices;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/users")
public class UserResource {

    private UserServices services;

    public UserResource(){
        services = new UserServices();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Person person) {
       Person response = services.addElement(person.getUsername(), person.getEmail(), person.getPassword(), person.getRole());
       return Response.status(201).entity(response).build();
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Person person){
        List<Person> list = services.getList(person.getUsername(), person.getPassword());
        return Response.ok()
                       .entity(list.stream()
                       .peek(y -> System.out.println(y))
                       .filter(x -> x.getUsername().equals(person.getUsername()) && x.getPassword().equals(person.getPassword()))
                               .peek(x -> System.out.println("La persona es = " + x))
                       .findFirst()
                       .orElse(new Person()))
                       .build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFcoins(Person person){
        return Response.ok()
                       .entity(services.updateFcoins(person.getUsername(), person.getFcoins()))
                       .build();
    }
}

