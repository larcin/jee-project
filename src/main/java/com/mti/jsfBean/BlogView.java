package com.mti.jsfBean;

import com.mti.entities.Post;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Sabrine.Elbahri on 11/07/2017.
 */

@ManagedBean
@ViewScoped
public class BlogView {
    private ArrayList<Post> posts;

    @PostConstruct
    public void test() {
        try {
            Client client = ClientBuilder.newClient();

            WebTarget resource = client.target("http://localhost:8080/jee-project/api/posts");

            Invocation.Builder request = resource.request();
            request.accept(MediaType.APPLICATION_JSON);

            Response response = request.get();

            ArrayList<Post> jsonResponse = response.readEntity(ArrayList.class);
            System.out.println(jsonResponse);

            if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
                posts = jsonResponse;
            } else {
                System.out.println("ERROR! " + response.getStatus());
                System.out.println(response);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }


}