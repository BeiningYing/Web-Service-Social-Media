package com.x.politicianinfo.resource;

import java.net.URI;
import java.util.Collection;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.x.politicianinfo.data.Party;
import com.x.politicianinfo.data.Politician;
import com.x.politicianinfo.service.PoliticianService;
//import com.x.politicianinfo.resource.TwitterAccess;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import java.io.StringReader;

@Path("")
public class PoliticianResource {

    private static final String POLITICIAN_REQUIRED_FIELDS = "Politician name, post, and party ID are required.";
    private static final String UNABLE_TO_ADD_POLITICIAN = "Unable to add the politician.";
    private static final String POLITICIAN_NOT_FOUND = "Politician with the given ID not found.";
    private static final String NO_POLITICIANS_FOUND = "No politicians found with the given name.";
    private static final String POLITICIAN_NOT_DELETED = "Politician with the given ID not found or could not be deleted.";
    private static final String INVALID_PARTY_DATA = "Invalid party data.";

    private PoliticianService service = new PoliticianService();

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPolitician(Politician politician) {
        // Validate the incoming politician data
        if (politician.getName() == null || politician.getPost() == null || politician.getPartyId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(POLITICIAN_REQUIRED_FIELDS).build();
        }

        // Attempt to add the politician using the service
        Politician createdPolitician = service.addPolitician(politician);
        if (createdPolitician == null) {
            // Log the error or handle the exception
            return Response.status(Response.Status.BAD_REQUEST).entity(UNABLE_TO_ADD_POLITICIAN).build();
        }

        // Create the URI for the newly added politician and return the response
        URI uri = uriInfo.getAbsolutePathBuilder().path(createdPolitician.getId()).build();
        return Response.created(uri).entity(createdPolitician).build();
    }

    @GET
    @Path("/politicians/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoliticianById(@PathParam("id") String id) {
        Politician politician = service.getPoliticianById(id);
        if (politician == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(POLITICIAN_NOT_FOUND).build();
        }
        return Response.ok(politician).build();
    }
    
    @GET
    @Path("/politicians")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPoliticians() {
        Collection<Politician> politicians = service.getAllPoliticians();
        return politicians.isEmpty() ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.ok(politicians).build();
    }

//    @GET
//    @Path("/{name}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getPoliticianByName(@PathParam("name") String name) {
//        Collection<Politician> politicians = service.getPoliticianByName(name);
//        return politicians.isEmpty() ? Response.status(Response.Status.NOT_FOUND).entity(NO_POLITICIANS_FOUND).build()
//                : Response.ok(politicians).build();
//    }

    @GET
    @Path("/politicians/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("name") String username) {
        String userJson = TwitterAccess.getUserByUsername(username);

        JsonReader reader = Json.createReader(new StringReader(userJson));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        if (jsonObject.containsKey("data")) {
            JsonObject userData = jsonObject.getJsonObject("data");
            return Response.ok(userData).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
    }

    @GET
    @Path("/politicians/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweetsByUser(@PathParam("id") String userId, @QueryParam("count") @DefaultValue("2") int count) {
        String tweetsJson = TwitterAccess.getTweetsByUser(userId, count);

        JsonReader reader = Json.createReader(new StringReader(tweetsJson));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        if (jsonObject.containsKey("data")) {
            JsonArray tweets = jsonObject.getJsonArray("data");
            return Response.ok(tweets).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Tweets not found").build();
        }
    }
    
    @DELETE
    @Path("/politicians/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePoliticianById(@PathParam("id") String id) {
        boolean deleted = service.deletePoliticianById(id);
        return deleted ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).entity(POLITICIAN_NOT_DELETED).build();
    }
    
    @GET
    @Path("/politicians/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweetsByQuery(@PathParam("name") String query, @QueryParam("count") @DefaultValue("2") int count) {
        String tweetsJson = TwitterAccess.searchTweets(query, count);
        JsonReader reader = Json.createReader(new StringReader(tweetsJson));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        JsonArray data = jsonObject.getJsonArray("data");
        return Response.ok(data).build();
    }
    
    @Path("/parties")
    public class PartyResource {

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response addParty(Party party) {
            Party newParty = service.addParty(party);
            if (newParty == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(INVALID_PARTY_DATA).build();
            }
            URI newPartyUri = uriInfo.getAbsolutePathBuilder().path(newParty.getId()).build();
            return Response.created(newPartyUri).entity(newParty).build();
        }
//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public Response getAllParties() {
//            Collection<Party> parties = service.getAllParties();
//            return parties.isEmpty() ? Response.status(Response.Status.NO_CONTENT).build()
//                    : Response.ok(parties).build();
//        }

        @PUT
        @Path("/{partyId}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response updateParty(@PathParam("partyId") String partyId, Party updatedParty) {
            Party party = service.updateParty(partyId, updatedParty);
            if (party == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Party not found or could not be updated").build();
            }
            return Response.ok(party).build();
        }
        @DELETE
        @Path("/{partyId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteParty(@PathParam("partyId") String partyId) {
            boolean deleted = service.deleteParty(partyId);
            if (deleted) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Party not found or could not be deleted").build();
            }
        }
    }
}