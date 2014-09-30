package sputnik.messages;

import com.sputnik.messages.MessagesController;
import com.sputnik.persistence.Message;
import com.sputnik.persistence.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MessagesControllerTest {
    @Mock
    MessageRepository messageRepository;

    MockMvc mockMvc;
    MessagesController controller;

    @Before
    public void setup() {
        initMocks(this);
        controller = new MessagesController(messageRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListArticles() throws Exception {
        List<Message> allMessages = asList(new Message("hello", "world"));

        doReturn(allMessages).when(messageRepository).findAll();

        mockMvc.perform(get("/messages").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"title\":\"hello\",\"content\":\"world\"}]"));

    }
}
