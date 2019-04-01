package com.example.java8.controller;


import com.example.java8.entity.Product;
import com.example.java8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/findAll")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> collect = productService.findAll().stream().collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(collect);
  }

  @GetMapping("/findByName")
  public ResponseEntity<List<Product>> getProductsByName(String name) {
        /*Old version
        List<Product> all = productService.findAll();
        List<Product> products = new ArrayList <>();
        for (Product product : all) {
            if (product.getName().equals(name)){
                 products.add(product);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
        **/

    List<Product> byName = productService.getByName(name);
    return ResponseEntity.status(HttpStatus.OK).body(byName);
  }

  @GetMapping("/findById")
  public ResponseEntity<Product> getProductById(int id) {
    Product productById = productService.getProductById(id);
    if (productById == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(productById);
  }

  @PostMapping("/saveProduct")
  public ResponseEntity<Void> saveProduct(Product product) {
    productService.saveProduct(product);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/deleteById")
  public ResponseEntity<Void> deleteById(int id) {
    if (productService.deleteProduct(id)) {
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping("/getAllByUserId")
  public ResponseEntity<List<Product>> getAllByUserId(int id) {
    List<Product> byUserId = productService.findByUserId(id);
    return ResponseEntity.status(HttpStatus.OK).body(byUserId);
  }

}