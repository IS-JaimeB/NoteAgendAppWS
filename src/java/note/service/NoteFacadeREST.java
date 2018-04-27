/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package note.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import note.Note;


@Stateless
@Path("note.note")
public class NoteFacadeREST extends AbstractFacade<Note> {

    @PersistenceContext(unitName = "NoteAgendAppWSPU")
    private EntityManager em;

    public NoteFacadeREST() {
        super(Note.class);
    }

    @POST
    @Path("addNote/{id}/{email}/{content}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNote(@PathParam("id") String id,@PathParam("email") String email,@PathParam("content") String content) {
        Note entity = new Note();
        entity.setId(Integer.parseInt(id));
        entity.setEmail(email);
        entity.setContent(content);
        super.addNote(entity);
    }
    

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Note entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Note find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Note> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Note> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("viewNotes/{email}")
    @Produces(MediaType.APPLICATION_XML)
    public String viewNotes(@PathParam("email") String email) {
        return super.viewNotes(email);
    }
    
    @GET
    @Path("searchNote/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String searchNote(@PathParam("id") int id) {
        return super.searchNote(id);
    }
        
    @GET
    @Path("existsNote/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Note existsNote(@PathParam("id") int id) {
        return super.existsNote(id);
    }
    
    @GET
    @Path("countNotes/{email}")
    @Produces(MediaType.APPLICATION_XML)
    public String countNotes(@PathParam("email") String email) {
        return super.countNotes(email);
    }
    
    @GET
    @Path("deleteNote/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteNote(@PathParam("id") int id) {
        return super.deleteNote(id);
    }    
    
    @GET
    @Path("modifyNote/{id}/{content}")
    @Produces(MediaType.APPLICATION_XML)
    public String modifyNote(@PathParam("id") int id, @PathParam("content") String content) {
        return super.modifyNote(id, content);
    }  

    @GET
    @Path("checkNoteOwner/{id}/{email}")
    @Produces(MediaType.APPLICATION_XML)
    public String checkNoteOwner(@PathParam("id") int id, @PathParam("email") String email) {
        return super.checkNoteOwner(id, email);
    }        
}