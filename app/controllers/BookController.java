package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.books.*;

import javax.inject.Inject;
import java.util.List;

/***
 * Setup debugger
 * https://www.youtube.com/watch?v=4DB2De3qv0U&list=PLYPFxrXyK0Bx9SBkNhJr1e2-NlIq4E7ED&index=14
 *
 * 12. We need to explicitly define getters and setters for future form submission (video 14)
 *
 * 21. @ 1:24 - The EBean plugin version should be 4.1.0, or check releases for other versions.
 * https://github.com/playframework/play-ebean#releases
 *
 *      Add the following to build.sbt:
 *      libraryDependencies += "com.h2database" % "h2" % "1.4.192"
 *      libraryDependencies += javaJdbc
 *
 *      Click "Import Changes" in the bottom right.
 *      Restart the SBT shell, ctrl-c, or ctrl-d. until you get to the Linux terminal or windows cmd.
 *      This should fix the issue the YouTuber experiences @ 5:15.
 *
 *     @ 5:00 different  import statement for Model ->     import io.ebean.*;
 *
 *     @ 5:15 to 7:00 IGNORE
 *
 *     If you get "io.ebean could not be found" try restarting the sbt console.
 *
 * 22. After following the video, you most likely will get a message:
 *      Unauthorized
 *      You must be authenticated to access this page.
 *
 *     To fix this, modify edit.scala.html and edit.scala.html @helper.form() to the below:
 *     @helper.form(CSRF(routes.BooksController.save())) {
 *
 *
 */

public class BookController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        List<Book> books = Book.find.all();
        return ok(index.render(books));
    }

    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
    }

    public Result show(Integer id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            return notFound("Book not found.");
        }

        return ok(show.render(book));
    }

    public Result edit(Integer id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            return notFound("Book not found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(edit.render(bookForm));
    }

    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class);
        Book book = bookForm.bindFromRequest().get();
        book.save();
        return redirect(routes.BookController.index());
    }

    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class);
        Book newVersion = bookForm.bindFromRequest().get();
        Book oldVersion = Book.find.byId(newVersion.id);
        if (oldVersion == null) {
            return notFound("Book not found");
        }
        oldVersion.title = newVersion.title;
        oldVersion.price = newVersion.price;
        oldVersion.author = newVersion.author;
        oldVersion.update();

        return redirect(routes.BookController.index());
    }

    public Result destroy(Integer id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            return notFound("Book not found");
        }
        book.delete();
        return redirect(routes.BookController.index());

    }
}
