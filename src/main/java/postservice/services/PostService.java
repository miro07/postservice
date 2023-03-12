package postservice.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import postservice.dao.PostDao;
import postservice.entities.Address;
import postservice.entities.Post;
import postservice.entities.dto.InstrumentDto;
import postservice.entities.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

import static postservice.entities.dto.builder.PostDtoBuilder.responseBuildPostDto;


@Service
public class PostService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PostDao postDao;

    public PostDto save(PostDto postDto){
        postDto.setProduct(addProduct(postDto.getProduct()));
        Address address = new Address(postDto.getAddress(), postDto.getAdditionalAddress()
                , postDto.getCity(), postDto.getZip(), postDto.getRegion(), postDto.getCountry());
        Post post = postDao.save(new Post(postDto.getDescription(),address,postDto.getUserID(),postDto.getProduct().getProductId()));
        postDto.setPostId(post.getId());
        return postDto;
    }
    public List<PostDto> getPostsByUserId(Long userId){
        List<PostDto> postsDtoByUserId = new ArrayList<>();
        List<Post> postsByUserId= postDao.findPostsByUserId(userId);
        for (Post post : postsByUserId){
            postsDtoByUserId.add(responseBuildPostDto(post, getProduct(post.getProductId())));
        }
        return postsDtoByUserId;
    }
    public List<PostDto> getPostsByRegion(String region){
        List<PostDto> postsDtoByRegion = new ArrayList<>();
        List<Post> postsByRegion= postDao.findPostsByRegion(region);
        for (Post post : postsByRegion){
            postsDtoByRegion.add(responseBuildPostDto(post, getProduct(post.getProductId())));
        }
        return postsDtoByRegion;
    }

    public InstrumentDto addProduct(InstrumentDto instrumentDto) {
        HttpEntity<InstrumentDto> requestEntity = new HttpEntity<>(instrumentDto);
        ResponseEntity<InstrumentDto> responseEntity = restTemplate.exchange(
                "http://localhost:8083/instruments",
                HttpMethod.POST,
                requestEntity,
                InstrumentDto.class
        );

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to add product: " + statusCode);
        }
    }
    public InstrumentDto getProduct(Long productId) {
        ResponseEntity<InstrumentDto> responseEntity = restTemplate.exchange(
                "http://localhost:8083/instruments/{productId}",
                HttpMethod.GET,
                null,
                InstrumentDto.class,
                productId
        );

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to get product: " + statusCode);
        }
    }

}
