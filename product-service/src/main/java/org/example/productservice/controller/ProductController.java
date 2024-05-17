package org.example.productservice.controller;

import org.example.productservice.dto.ProductCreateDto;
import org.example.productservice.dto.ProductResponseDto;
import org.example.productservice.model.Product;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Test api
    @GetMapping("/all")
    public String done() {
        return "Done";
    }

    // Get all products
    @GetMapping("/get")
    public List<ProductResponseDto> getAll() {
        return productService.getAll();
    }


    // Get specific product by id
    @GetMapping("/get/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.get(id).orElse(null);
    }

    // Search product by input key
    @GetMapping("/search")
    public List<ProductResponseDto> search(@RequestParam String key) {
        return productService.search(key);
    }

    // Create new product
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean create(@RequestBody ProductCreateDto product) {
        // Check Unsupported Media Type (415) error when sending a POST request with JSON data
        // System.out.println(product);
        // Link problem: https://www.reddit.com/r/javahelp/comments/1383yqv/why_is_consumes_not_required_in_the_postmapping/
        // And: https://stackoverflow.com/questions/50563593/content-type-application-jsoncharset-utf-8-not-supported-in-spring-rest-app
        return productService.create(product);
    }

    // Update product
    @PutMapping("/update")
    public Boolean update(@RequestBody ProductCreateDto product) {
        return productService.update(product);
    }

    // Delete product
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/delete")
    public Boolean delete(@RequestBody Product product) {
        return productService.delete(product);
    }

    // Allow requests from specific origin
    @PostMapping(value = "image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Add CORS headers to allow requests from the specified origin
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Methods", "POST");
        headers.add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

        if (file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .body("Please select a file to upload");
        }

        try {
            // Save the uploaded file to the resources/static/images directory
            String fileName = file.getOriginalFilename();
            Resource resource = new ClassPathResource("static/images");
            System.out.println(resource.getURI());
            Path path = Files.write(Path.of(Path.of(resource.getURI()) + "/" + fileName), file.getBytes(), StandardOpenOption.CREATE_NEW);
            System.out.println(path);
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("File uploaded successfully: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .headers(headers)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        // Load image from the resources directory
        Resource resource = new ClassPathResource("static/images/" + imageName);

        // Check if the image exists
        if (resource.exists()) {
            // Read the image data
            byte[] imageBytes = Files.readAllBytes(Path.of(resource.getURI()));
            System.out.println(Path.of(resource.getURI()));
            System.out.println("Image found");
            // Return the image data with appropriate headers
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } else {
            // Image not found
            System.out.println("Image not found");
            return ResponseEntity.notFound().build();
        }
    }

}
