/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.api;

import com.syntech.mavenproject4.StoryRepository;
import com.syntech.mavenproject4.Story;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author hrishi
 */
@Path("/story")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoryRestApi {

    @Inject
    private StoryRepository storyRepository;

    @GET
    public Response getAllStories() throws JsonProcessingException {
        List<Story> storyList = storyRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(storyList);

        return RestResponse.responseBuilder("true", "200", "List of Stories", str);
    }

    @GET
    @Path("{id}")
    public Response getStoryById(@PathParam("id") Long id) throws JsonProcessingException {
        Story story = storyRepository.findById(id);
        if (story == null) {
            return RestResponse.responseBuilder("false", "404", " Story with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(story);
        return RestResponse.responseBuilder("true", "200", "Employee with id " + id + " found", str);
    }

    @POST
    public Response createStory(Story story) throws JsonProcessingException {
        storyRepository.edit(story);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(story);

        return RestResponse.responseBuilder("true", "201", "story created successfully", str);
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteStory(@PathParam("id") Long id) throws JsonProcessingException {
        Story story = storyRepository.findById(id);
        if (story == null) {
            return RestResponse.responseBuilder("false", "404", " Story with id " + id + " not found", null);
        }
        storyRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "Employee with id " + id + " deleted successfully", null);
    }
    
    @PUT
    @Path("{id}")
    public Response updateStory(@PathParam("id") Long id, Story story) throws JsonProcessingException {
        Story s = storyRepository.findById(id);
        if (s == null) {
            return RestResponse.responseBuilder("false", "404", " Story with id " + id + " not found", null);
        }
        if (!story.getId().equals(s.getId())) {
            return RestResponse.responseBuilder("false", "404", " Employee id mismatch", null);
        }
        storyRepository.edit(story);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(story);

        return RestResponse.responseBuilder("true", "200", "employee updated successfully", str);
    }
}
