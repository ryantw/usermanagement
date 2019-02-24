package io.lker.webstore.bootstrap;

import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.common.model.product.ProductDescription;
import io.lker.webstore.common.model.product.ProductSize;
import io.lker.webstore.common.model.user.Role;
import io.lker.webstore.common.model.user.User;
import io.lker.webstore.usermanagement.services.UserService;
import io.lker.webstore.usermanagement.services.springjpa.ProductJPAService;
import io.lker.webstore.usermanagement.services.springjpa.RoleJPAService;
import io.lker.webstore.usermanagement.services.springjpa.SizeJPAService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class StartUpData implements CommandLineRunner {

    private final UserService userService;
    private final RoleJPAService roleService;
    private final ProductJPAService productJPAService;
    private final SizeJPAService sizeJPAService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public StartUpData(UserService userService, RoleJPAService roleService, BCryptPasswordEncoder bCryptPasswordEncoder,
                       ProductJPAService productJPAService, SizeJPAService sizeJPAService) {
        this.userService = userService;
        this.roleService = roleService;
        this.sizeJPAService = sizeJPAService;
        this.productJPAService = productJPAService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData(){
        Role adminRole = roleService.findByRoleName("ROLE_ADMIN");
        if(adminRole == null){
            roleService.save(Role.builder().roleName("ROLE_ADMIN").build());
        }
        adminRole = roleService.findByRoleName("ROLE_ADMIN");
        User user1 = User.builder().id(1L).firstName("Ryan").lastName("Walker")
                .emailAddress("ryanwalker@example.com")
                .password(bCryptPasswordEncoder.encode("test"))
                .roles(Arrays.asList(adminRole))
                .build();
        User user2 = User.builder().id(2L).firstName("Alicia").lastName("Walker")
                .emailAddress("aliciawalker@example.com").build();
        User user3 = User.builder().id(3L).firstName("Landon").lastName("Gavin")
                .emailAddress("landongavin@example.com")
                .roles(Arrays.asList(adminRole))
                .password(bCryptPasswordEncoder.encode("test")).build();
        User user4 = User.builder().id(4L).firstName("Nick").lastName("Schlenk")
                .emailAddress("nickschlenk@example.com").build();
        User user5 = User.builder().id(5L).firstName("Luke").lastName("Walker")
                .emailAddress("lukewalker@example.com")
                .password(bCryptPasswordEncoder.encode("test"))
                .build();
        User user6 = User.builder().id(6L).firstName("Barbara").lastName("Gavin")
                .emailAddress("barbaragavin@example.com").build();
        User user7 = User.builder().id(7L).firstName("Vince").lastName("Stratful")
                .emailAddress("vstrat@gmail.com").build();

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);


        ProductSize productSize = ProductSize.builder().id(1L).name("0-6 Months").build();
        ProductSize productSize1 = ProductSize.builder().id(2L).name("14/16 Months").build();
        Set<ProductSize> productSizes = new HashSet<>();
        productSizes.add(productSize);
        productSizes.add(productSize1);

        Set<ProductDescription> productDescriptions = new HashSet<>();
        ProductDescription productDescription = ProductDescription.builder().id(1L).description("Sup G").build();
        ProductDescription productDescription1 = ProductDescription.builder().id(2L).description("Sup W").build();
        productDescriptions.add(productDescription);
        productDescriptions.add(productDescription1);


        Product product = Product.builder().id(1L).groupedProduct(25L).name("Furry Beasts")
                .productSizes(productSizes).descriptions(productDescriptions).build();

        productDescription1.setProduct(product);
        productDescription.setProduct(product);
        productSize.setProduct(product);
        productSize1.setProduct(product);
        productJPAService.save(product);
        sizeJPAService.save(productSize);
        sizeJPAService.save(productSize1);

    }
}
