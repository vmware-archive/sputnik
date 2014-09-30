package sputnik.messages;

import com.sputnik.messages.MessagesController;
import com.sputnik.persistence.Message;
import com.sputnik.persistence.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testListMessages() throws Exception {
        List<Message> allMessages = asList(new Message("hello", "world"));

        doReturn(allMessages).when(messageRepository).findAll();

        mockMvc.perform(get("/messages"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"title\":\"hello\",\"content\":\"world\"}]"));

    }

    @Test
    public void testCreateMessage() throws Exception {
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);

        Message message = new Message("hello", "world");

        doReturn(message).when(messageRepository).save(messageCaptor.capture());

        mockMvc.perform(post("/messages").header("Content-Type", "application/json")
                .content("{ \"title\" : \"hello\", \"content\" : \"world\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":0,\"title\":\"hello\",\"content\":\"world\"}"));

        verify(messageRepository).save(messageCaptor.capture());
    }

    @Test
    public void testDeleteMessage() throws Exception {
        doNothing().when(messageRepository).delete(3L);

        mockMvc.perform(delete("/messages/3"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));

        verify(messageRepository).delete(3L);
    }

    @Test
    public void testListMessagesByTitle() throws Exception {
        List<Message> someMessages = asList(new Message("hello", "world"));

        doReturn(someMessages).when(messageRepository).findByTitle("someTitle");

        mockMvc.perform(get("/messages/someTitle").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"title\":\"hello\",\"content\":\"world\"}]"));

    }
}
