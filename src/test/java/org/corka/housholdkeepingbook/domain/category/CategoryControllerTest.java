package org.corka.housholdkeepingbook.domain.category;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import lombok.val;
import org.corka.housholdkeepingbook.domain.user.User;
import org.corka.housholdkeepingbook.domain.user.UserRepository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@WithMockUser(username = "bob", password = "secret")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    CategoryService categoryService;

    private WebClient webClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        if (this.userRepository.findByNameContainingIgnoreCase("bob") == null) {
            val user = new User();
            user.setName("Bob");
            user.setPassword(this.passwordEncoder.encode("secret"));
            this.userRepository.save(user);
        }

        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context, springSecurity())
                .build();
    }

    @Test
    public void testAddCategory() throws Exception {
        HtmlPage viewPage = webClient.getPage("http://localhost/category");
        HtmlForm form = viewPage.getHtmlElementById("categoryForm");
        HtmlTextInput nameInput = viewPage.getHtmlElementById("inputCategoryName");
        nameInput.setValueAttribute("car");
        HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
        viewPage = submit.click();

        List<Category> allActiveCategories = categoryService.getAllActiveCategories();
        assertThat(allActiveCategories.size()).isEqualTo(1);

        DomNodeList<DomElement> tableDataElements = viewPage.getElementsByTagName("td");
        assertThat(tableDataElements.size()).isEqualTo(2);
        long count = tableDataElements.stream().filter(domEle -> domEle.getTextContent().equalsIgnoreCase("car")).count();
        assertThat(count).isEqualTo(1);
    }
}
