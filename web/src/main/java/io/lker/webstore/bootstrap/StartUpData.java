package io.lker.webstore.bootstrap;

import io.lker.webstore.common.model.catalogue.Catalogue;
import io.lker.webstore.common.model.catalogue.Category;
import io.lker.webstore.common.model.preorder.PreOrder;
import io.lker.webstore.common.model.preorder.PreOrderStatus;
import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.common.model.product.ProductDescription;
import io.lker.webstore.common.model.product.ProductOption;
import io.lker.webstore.common.model.product.ProductSize;
import io.lker.webstore.common.model.user.Role;
import io.lker.webstore.common.model.user.User;
import io.lker.webstore.usermanagement.services.UserService;
import io.lker.webstore.usermanagement.services.springjpa.*;
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
    private final CatalogueJPAService catalogueJPAService;
    private final CategoryJPAService categoryJPAService;
    private final PreOrderJPAService preOrderJPAService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public StartUpData(UserService userService, RoleJPAService roleService, ProductJPAService productJPAService,
                       SizeJPAService sizeJPAService, CatalogueJPAService catalogueJPAService,
                       CategoryJPAService categoryJPAService, PreOrderJPAService preOrderJPAService,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.productJPAService = productJPAService;
        this.sizeJPAService = sizeJPAService;
        this.catalogueJPAService = catalogueJPAService;
        this.categoryJPAService = categoryJPAService;
        this.preOrderJPAService = preOrderJPAService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData(){
        loadUserData();
        loadStoreData();
    }

    private void loadStoreData(){

        Catalogue catalogue = new Catalogue();
        Category categoryPants = new Category();
        Category categoryShirts = new Category();
        Category onSale = new Category();

        categoryPants.setCatalogue(catalogue);
        categoryPants.setName("Pants");

        categoryShirts.setCatalogue(catalogue);
        categoryShirts.setName("Shirts");

        onSale.setCatalogue(catalogue);
        onSale.setName("On Sale");

        catalogue.addCategory(categoryPants);
        catalogue.addCategory(categoryShirts);
        catalogue.addCategory(onSale);
        catalogueJPAService.save(catalogue);
        categoryJPAService.save(categoryPants);
        categoryJPAService.save(categoryShirts);
        categoryJPAService.save(onSale);

        ProductSize productSize = ProductSize.builder().id(1L).name("0-6 Months").build();
        sizeJPAService.save(productSize);
        /*
        Set<ProductSize> productSizes = new HashSet<>();
        productSizes.add(productSize);
        productSizes.add(productSize1);
        */

        ProductDescription productDescription = ProductDescription.builder().id(1L).title("Striped").description("Long sleeves and stuff.").rteDescription("Test").build();
        ProductDescription productDescription1 = ProductDescription.builder().id(2L).title("Comfy").description("Made with cawtin").rteDescription("Really?").build();

        Product product = Product.builder().id(1L).name("Long Sleeve, V-Neck").build();

        productJPAService.save(product);

        ProductOption productOption = ProductOption.builder().productSize(productSize)
                .color("red").quantity(10).build();

        productSize.setProductOption(productOption);
        productJPAService.save(product);

        product.addProductOption(productOption);

        productDescription1.setProduct(product);
        productDescription.setProduct(product);

        product.addDescription(productDescription);
        product.addDescription(productDescription1);

        product.addCategory(onSale);
        product.addCategory(categoryShirts);

        productJPAService.save(product);
        categoryShirts.addProduct(product);
        onSale.addProduct(product);

        categoryJPAService.save(categoryShirts);
        categoryJPAService.save(onSale);

        PreOrder preOrder = PreOrder.builder().preOrderStatus(PreOrderStatus.COLLECT_ORDERS)
                .product(product).build();
        User user = User.builder().firstName("Pre").lastName("Order").emailAddress("pre@order.com").build();
        preOrder.addPreOrderUser(user);
        user.addPreOrder(preOrder);

        userService.save(user);
        preOrderJPAService.save(preOrder);


        /********************************

        ProductSize productSize2 = ProductSize.builder().id(3L).name("XXL").build();
        sizeJPAService.save(productSize2);
        ProductSize productSize3 = ProductSize.builder().id(4L).name("XL").build();
        sizeJPAService.save(productSize3);
        Set<ProductSize> productSizes2 = new HashSet<>();
        productSizes2.add(productSize2);
        productSizes2.add(productSize3);

        Set<ProductDescription> productDescriptions2 = new HashSet<>();
        ProductDescription productDescription2 = ProductDescription.builder().id(3L).title("Striped").description("Long sleeves and stuff.").rteDescription("Test").build();

        ProductDescription productDescription3 = ProductDescription.builder().id(4L).title("Comfy").description("Made with cawtin").rteDescription("Really?").build();
        productDescriptions2.add(productDescription2);
        productDescriptions2.add(productDescription3);


        Product product2 = Product.builder().id(2L).groupedProduct(25L).name("Long Sleeve, V-Neck, Female")
                .productSizes(productSizes2).build();

        productJPAService.save(product2);

        productDescription2.setProduct(product2);
        productDescription3.setProduct(product2);

        product2.addDescription(productDescription2);
        product2.addDescription(productDescription3);


        productSize2.setProduct(product2);
        productSize3.setProduct(product2);
        sizeJPAService.save(productSize2);
        sizeJPAService.save(productSize3);

        product2.addCategory(onSale);
        product2.addCategory(categoryShirts);

        productJPAService.save(product2);
        categoryShirts.addProduct(product2);
        onSale.addProduct(product2);

        categoryJPAService.save(categoryShirts);
        categoryJPAService.save(onSale);
         */
    }

    private void loadUserData(){

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

        User user8 = User.builder().id(8L).firstName("Ava").lastName("Walker")
                .emailAddress("avawalker@test.com").build();

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);
    }
}
