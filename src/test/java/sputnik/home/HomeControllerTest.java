package sputnik.home;

import com.sputnik.home.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class HomeControllerTest {
    MockMvc mockMvc;
    HomeController controller;

    @Mock
    HttpServletRequest request;

    @Mock
    CsrfToken token;

    @Before
    public void setup() {
        initMocks(this);
        controller = new HomeController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        doReturn(token).when(request).getAttribute(anyString());
    }

    @Test
    public void testIndex() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();

        doReturn("token header").when(token).getHeaderName();
        doReturn("token parameter").when(token).getParameterName();
        doReturn("token").when(token).getToken();

        String result = controller.loadIndex(request, response);

        assertEquals("index", result);
        assertEquals("token header", response.getHeader("X-CSRF-HEADER"));
        assertEquals("token parameter", response.getHeader("X-CSRF-PARAM"));
        assertEquals("token", response.getHeader("X-CSRF-TOKEN"));
    }

    @Test
    public void testSignIn() throws Exception {
        Model model = mock(Model.class);

        String result = controller.signIn(model, request);

        assertEquals("signin", result);
        verify(model).addAttribute("_csrf", token);
    }
}
