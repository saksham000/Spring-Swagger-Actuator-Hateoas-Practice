package com.rest.webservices.rest_web_services.users;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.rest_web_services.jpa.PostRepo;
import com.rest.webservices.rest_web_services.jpa.UserRepo;

import jakarta.validation.Valid;

// import org.springframework.hateoas.EntityModel;
// import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//@RestController
public class UserJpaResource {
    private UserRepo repo;
    private PostRepo postRepo;

    public UserJpaResource(UserRepo repo, PostRepo postRepo) {
        this.repo = repo;
        this.postRepo = postRepo;
    }

    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers() {
        return repo.findAll();
    }

    // @GetMapping("/jpa/users/{id}")
    // public EntityModel<User> retriveUser(@PathVariable int id) {
    //     Optional<User> user = repo.findById(id);
    //     if (user.isEmpty())
    //         throw new UserNotFoundException("id:" + id);

    //     EntityModel<User> entityModel = EntityModel.of(user.get());
    //     WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
    //     entityModel.add(link.withRel("all-users"));
    //     return entityModel;
    // }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repo.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrivePostsForUser(@PathVariable int id) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        return user.get().getPosts();
    }

    @PostMapping("jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repo.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        post.setUser(user.get());
        Post savedPost = postRepo.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}/posts/{pid}")
    public void deleteUserPost(@PathVariable int id,@PathVariable int pid) {
        postRepo.findById(pid).ifPresent(post ->{
            if(post.getUser().getId().equals(id)){
                postRepo.delete(post);
            }else{
                throw new IllegalArgumentException("Post Does Not belong to user");
            }
        });;
    }

}
