package org.example.web.controller.business;

import org.example.web.controller.ControllerTest;
import org.example.web.controller.InfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for {@link InfoController}.
 */
@ContextConfiguration(locations = { "classpath:springDataContext.xml", "classpath:springServiceContext.xml", "classpath:springWebContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyControllerTest extends ControllerTest
{
  @Autowired
  private CompanyController controller;

  /**
   * Tests that companies can be retrieved as JSON.
   */
  @Test
  public void testGraph() throws Exception
  {
    mock.perform(get("/graph")
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  /**
   * Tests that the informational page can be loaded successfully.
   */
  @Test
  public void testPage() throws Exception
  {
    mock.perform(get("/graph.html"))
        .andExpect(status().isOk())
        .andExpect(view().name("graph"));
  }

  /**
   * {@inheritDoc}
   */
  protected Object controller()
  {
    return controller;
  }
}
