package org.corka.housholdkeepingbook.category;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;
import com.gargoylesoftware.htmlunit.WebClient;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    CategoryService categoryService;

    WebClient webClient;

    @Before
    public void setup() {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context)
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
        assertThat(tableDataElements.size()).isEqualTo(3);
        long count = tableDataElements.stream().filter(domEle -> domEle.getTextContent().equalsIgnoreCase("car")).count();
        assertThat(count).isEqualTo(1);
    }
}
