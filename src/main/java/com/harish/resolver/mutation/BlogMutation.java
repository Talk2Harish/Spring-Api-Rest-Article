package com.harish.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.harish.model.Blog;
import com.harish.model.mutation.CreateBlog;
import com.harish.repository.BlogRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class BlogMutation implements GraphQLMutationResolver {

    private BlogRepository blogRepository;

    @Transactional
    public Blog createBlog(CreateBlog body){
        String title = body.getTitle();
        String boby = body.getBody();
        String author = body.getAuthor();
        return blogRepository.save(new Blog(title, boby, author));
    }


}
