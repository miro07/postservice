package postservice.entities.dto.builder;


import postservice.entities.Post;
import postservice.entities.dto.InstrumentDto;
import postservice.entities.dto.PostDto;
import postservice.entities.dto.ProductDto;

public class PostDtoBuilder {

    PostDto postDto;

    public PostDtoBuilder(){
        postDto= new PostDto();
    }
    public PostDtoBuilder withPostId(Long postId){
        postDto.setPostId(postId);
        return this;
    }

    public PostDtoBuilder withAdditionalAddress(String additionalAddress){
        postDto.setAdditionalAddress(additionalAddress);
        return this;
    }
    public PostDtoBuilder withAddress(String address){
        postDto.setAddress(address);
        return this;
    }
    public PostDtoBuilder withCity(String city){
        postDto.setCity(city);
        return this;
    }
    public PostDtoBuilder withCountry(String country){
        postDto.setCountry(country);
        return this;
    }
    public PostDtoBuilder withRegion(String region){
        postDto.setRegion(region);
        return this;
    }
    public PostDtoBuilder withZip(Long zip){
        postDto.setZip(zip);
        return this;
    }
    public PostDtoBuilder withUserId(Long userId){
        postDto.setUserID(userId);
        return this;
    }

    public PostDtoBuilder withDescription(String description){
        postDto.setDescription(description);
        return this;
    }
    public PostDtoBuilder withProducts(InstrumentDto product){
        postDto.setProduct(product);
        return this;
    }


    public PostDto build() {
        return postDto;
    }

    public static PostDto responseBuildPostDto(Post post, InstrumentDto product) {
        return new PostDtoBuilder()
                .withPostId(post.getId())
                .withAddress(post.getAddress().getAddress())
                .withAdditionalAddress(post.getAddress().getAdditionalAddress())
                .withCity(post.getAddress().getCity())
                .withRegion(post.getAddress().getRegion())
                .withZip(post.getAddress().getZip())
                .withCountry(post.getAddress().getCountry())
                .withDescription(post.getDescription())
                .withUserId(post.getUserID())
                .withProducts(product)
                .build();
    }
    public static PostDto responseBuildPostDto(Post post) {
        return new PostDtoBuilder()
                .withPostId(post.getId())
                .withAddress(post.getAddress().getAddress())
                .withAdditionalAddress(post.getAddress().getAdditionalAddress())
                .withCity(post.getAddress().getCity())
                .withRegion(post.getAddress().getRegion())
                .withZip(post.getAddress().getZip())
                .withCountry(post.getAddress().getCountry())
                .withDescription(post.getDescription())
                .withUserId(post.getUserID())
                .build();
    }
}

