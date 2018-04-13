package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.books.*;

import javax.inject.Inject;
import java.util.List;

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
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        if (bookForm.hasErrors()) {
            flash("danger", "Please correct the form below");
            return badRequest(create.render(bookForm));
        }
        Book book = bookForm.get();
        book.save();
        flash("success", "Book created");
        return redirect(routes.BookController.index());
    }

    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        if (bookForm.hasErrors()) {
            flash("danger", "Please correct the form below");
            return badRequest("BLAH");
        }

        Book newVersion = bookForm.get();
        Book oldVersion = Book.find.byId(newVersion.id);
        if (oldVersion == null) {
            flash("danger", "Book not found");
            return notFound();
        }
        oldVersion.title = newVersion.title;
        oldVersion.price = newVersion.price;
        oldVersion.author = newVersion.author;
        oldVersion.update();
        flash("success", "book updated");
        return ok();
    }

    public Result destroy(Integer id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            flash("danger", "Book not found");
            return notFound();
        }
        book.delete();
        flash("success", "Book deleted");
        return ok();
    }
}
