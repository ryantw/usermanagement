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
        ProductDescription productDescription = ProductDescription.builder().id(1L).title("YES").description("Sup G").rteDescription("Test").build();
        ProductDescription productDescription1 = ProductDescription.builder().id(2L).title("YES").description("Sup W").rteDescription("Test Again").build();
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


        ProductSize productSize2 = ProductSize.builder().id(3L).name("XXL").build();
        ProductSize productSize3 = ProductSize.builder().id(4L).name("L").build();
        Set<ProductSize> productSizes2 = new HashSet<>();
        productSizes2.add(productSize2);
        productSizes2.add(productSize3);

        Set<ProductDescription> productDescriptions1 = new HashSet<>();
        ProductDescription productDescription2 = ProductDescription.builder().id(3L).title("NO").description("Goat").rteDescription("Is this the difference?").build();
        ProductDescription productDescription3 = ProductDescription.builder().id(4L).title("NO").description("Of All Time son!").rteDescription("Is this really all?").build();
        productDescriptions1.add(productDescription2);
        productDescriptions1.add(productDescription3);


        Product product1 = Product.builder().id(2L).groupedProduct(25L).name("Fire Beasts")
                .productSizes(productSizes2).descriptions(productDescriptions1).build();

        productDescription2.setProduct(product1);
        productDescription3.setProduct(product1);
        productSize2.setProduct(product1);
        productSize3.setProduct(product1);
        productJPAService.save(product1);
        sizeJPAService.save(productSize2);
        sizeJPAService.save(productSize3);

    }
}
