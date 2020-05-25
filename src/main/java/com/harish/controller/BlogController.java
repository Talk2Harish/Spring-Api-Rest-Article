package com.harish.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.harish.model.Blog;
import com.harish.repository.BlogRepository;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "articles", description = "Blog controller")
public class BlogController {

	final private BlogRepository blogRepository;

	public BlogController(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Operation(summary = "Find all blogs", description = "All blogs", tags = { "blog" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Blog.class)))) })
	@GetMapping("/articles")
	public List<Blog> index() {
		return blogRepository.findAll();
	}
	
	@Operation(summary = "Create a new blog", description = "Create a new blog", tags = { "blog" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Blog.class))) })
	@PostMapping("/articles")
	public Blog create(@RequestBody Map<String, String> craeteAuthor) {
		String title = craeteAuthor.get("title");
		String body = craeteAuthor.get("body");
		String author = craeteAuthor.get("author");
		return blogRepository.save(new Blog(title, body, author));
	}

	@Operation(summary = "Find blog by id", description = "Find blog by id", tags = { "blog" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Blog.class))) })
	@GetMapping("/articles/{id}")
	public Blog show(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		return blogRepository.findById(blogId).orElse(new Blog());
	}

	@Operation(summary = "Update a new blog", description = "Update a new blog", tags = { "blog" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Blog.class))) })
	@PutMapping("/articles/{id}")
	public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
		int blogId = Integer.parseInt(id);
		// getting blog
		Blog blog = blogRepository.findById(blogId).orElse(new Blog());
		blog.setTitle(body.get("title"));
		blog.setbody(body.get("boby"));
		blog.setAuthor(body.get("author"));
		return blogRepository.save(blog);
	}

	@Operation(summary = "Delete a  blog", description = "Delete a blog", tags = { "blog" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))) })
	@DeleteMapping("/articles/{id}")
	public boolean delete(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		blogRepository.deleteById(blogId);
		return true;
	}

}
