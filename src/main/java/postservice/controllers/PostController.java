package postservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postservice.entities.dto.PostDto;
import postservice.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto){
        try {
            PostDto savedPost = postService.save(postDto);
            return ResponseEntity.ok(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId){
        try {
            List<PostDto> PostsByUser = postService.getPostsByUserId(userId);
            return ResponseEntity.ok(PostsByUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<PostDto>> getPostsByRegion(@PathVariable String region){
        try {
            List<PostDto> PostsByRegion = postService.getPostsByRegion(region);
            return ResponseEntity.ok(PostsByRegion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
