
package api1;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.*;


/**
 *
 * @author deltagare
 */

@Path("user")
public class UserApi {
    
   // @Inject
    private Repository repo=new Repository();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get()
    {
        
        return Response.ok(
                repo.users
        ).build();
        
        
        
        //Check if the user authorized....
        
        /*return Response.status(
                Response.Status.UNAUTHORIZED//Status.OK.getStatusCode()
        ).build();*/
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id){
        
        User _user=repo.findUser(id);
        return _user==null
                ?Response.status(Response.Status.BAD_REQUEST).build()
                :Response.ok(_user).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response post(User user)
    {
        if(user==null)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else
        {
            repo.users.add(user);// or save it to the database
            return Response.status(Response.Status.CREATED).entity(user).build(); //.ok(user).build();
        }
    }
        
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id,User user)
    {
        if(user==null)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else
        {
            if(user.getId()!=id)
                return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
            User _user=repo.findUser(user.getId());
            if(_user==null)
            {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            else
            {
                _user.setAddress(user.getAddress());
                _user.setName(user.getName());
                return Response.ok(_user).build();
            }
        }
        
    }
    
    

}
