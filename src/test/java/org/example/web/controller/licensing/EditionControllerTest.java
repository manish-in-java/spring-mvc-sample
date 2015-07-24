package org.example.web.controller.licensing;

import org.example.model.licensing.Edition;
import org.example.model.licensing.TaxonomyProperty;
import org.example.model.licensing.TermProperty;
import org.example.web.controller.RESTControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link EditionController}.
 */
@ContextConfiguration(locations = { "classpath:springDataContext.xml", "classpath:springServiceContext.xml", "classpath:springWebContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EditionControllerTest extends RESTControllerTest
{
  @Autowired
  private EditionController controller;

  /**
   * Tests that Java objects can be serialized and deserialized correctly over a REST API.
   */
  @Test
  public void testCheckWithProperties() throws Exception
  {
    // Attempt to exchange an edition that does not have any properties.
    mock.perform(post("/generics")
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(serialize(new Edition("VST2013"
                         , "VST"
                         , new TaxonomyProperty("IDE", "Integrated Development Environment")
                         , new TermProperty("DR", "Duration", 1)))))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * Tests that Java objects can be serialized and deserialized correctly over a REST API.
   */
  @Test
  public void testCheckWithoutProperties() throws Exception
  {
    // Attempt to exchange an edition that does not have any properties.
    mock.perform(post("/generics")
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(serialize(new Edition("VST2013", "VST"))))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * Tests that the informational page can be loaded successfully.
   */
  @Test
  public void testPage() throws Exception
  {
    mock.perform(get("/generics.html"))
        .andExpect(status().isOk())
        .andExpect(view().name("thymeleaf/generics"));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
