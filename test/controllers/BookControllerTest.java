package controllers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

import play.mvc.Result;
import play.twirl.api.Content;

public class BookControllerTest {

    @Test
    public void testIndex() {
        Result result = new BookController().index();
        assertEquals("Should return status OK", OK, result.status());
        assertEquals("Should be text/html content", "text/html", result.contentType().get());
        assertEquals("Should be utf-8","utf-8", result.charset().get());
        assertTrue("Should contain heading", contentAsString(result).contains("<h1>Book List</h1>"));
    }

}