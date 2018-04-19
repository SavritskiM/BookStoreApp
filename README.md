## BookStoreApp Tutorial
Follow this wonderful [Play Framework Tutorial](https://www.youtube.com/playlist?list=PLYPFxrXyK0Bx9SBkNhJr1e2-NlIq4E7ED)

# Tutorial notes
## Video 12
We need to explicitly define getters and setters for the Book class or else future form submission (video 14) will not work.

## [Video 14](https://www.youtube.com/watch?v=4DB2De3qv0U&list=PLYPFxrXyK0Bx9SBkNhJr1e2-NlIq4E7ED&index=14)
This video explains how to set up the debugger.

## Video 15
After following the video, you most likely will get a message:
```
Unauthorized
You must be authenticated to access this page.
```
To fix this, modify `create.scala.html` and `edit.scala.html` (video 22) where it has `@helper.form()` to the below:
```
@helper.form(CSRF(routes.BooksController.save())) {
```

## Video 21
**@ 1:24** - The EBean plugin version should be 4.1.0, or check [releases](https://github.com/playframework/play-ebean#releases) for other versions.
- Add the following to `build.sbt`:
    ```
    libraryDependencies += "com.h2database" % "h2" % "1.4.192"
    libraryDependencies += javaJdbc
    ```
- Click "Import Changes" in the bottom right.
- Restart the SBT shell, ctrl-c, or ctrl-d. until you get to the Linux terminal or windows cmd.

This should fix the issue the YouTuber experiences @ 5:15.

**@ 5:00** we will use adifferent import statement for Model:   
```
import io.ebean.*;
```
**@ 5:15 to 7:00** IGNORE

*Note: If you get "io.ebean could not be found" try restarting the sbt console.*

## Video 23 - mySQL/PostgreSQL Database
You can skip the video's mySQL tutorial and follow my PostgreSQL instructions. This will be the database type used if we ever deploy our app to [Heroku](https://www.heroku.com). My instructions will be based on the article *[Play-example-postgresql](http://ics-software-engineering.github.io/play-example-postgresql)*.

### PostgreSQL (unofficially recommended)
1. [Download and Install PostgreSql](https://www.postgresql.org/download/)
    - Heroku currently is using version `10.3` (as of Apr 9, 2018).
    - More detailed [PostgreSQL install instructions](https://www.tutorialspoint.com/postgresql/postgresql_environment.htm)(if needed).
    - I didn't install the "Stack Builder" optional software at the end of the psql install.
2. Verify the PostgreSQL install.
    - **Linux**: Run `psql` from the command line (`cmd` or `terminal`).
      - In teh psql terminal, enter `create database bookstorepg ;`. You should see the new database in a list.
    - **Windows**: Search for and open `pgAdmin 4`
      - In the file menu, select `Objects -> Create -> Database`. Call the database `bookstorepg`, or whatever, then hit `save`.
3. Place into your `build.sbt` file:
    ```
    libraryDependencies += "org.postgresql" % "postgresql" % "42.2.2"
    ```
    Check for the [latest jdbc PostgreSQL driver](https://mvnrepository.com/artifact/org.postgresql/postgresql).

4. Place the following code into your `conf/application.conf` file.
    - *Notice: there are three things that *may* be diferent for your setup. **1. database name:** mine is `bookstorepg`. **2. username:** mine defaults to `postgres`. **3. password:** when I installed PostgreSQL, I set my password to `root`. If any of these are different on your system, you have to change the second line of code below accordingly.*
    ```
    default.driver=org.postgresql.Driver
    default.url="jdbc:postgresql://localhost:5432/bookstorepg?user=postgres&password=root"
    default.url=${?DATABASE_URL}
    ```
5. Update and run the Play application.
    - In the trminal for your Play app, run `sbt`. 
    - Then, within the `sbt` terminal, enter `update` to download the ostgreSQL dependencies.
    - Enter `run` to run your play application. Visit it in the browser at `localhost:9000`.
    - Everything should be working as it did prior to changing the database configuration. You should be able to add, edit, delete books.


### mySQL
Install [Visual C++ Redistributable for Visual Studio 2015](https://www.microsoft.com/en-za/download/details.aspx?id=48145) before installing [WAMP](http://www.wampserver.com/en/).

**@ 2:52** Copy the following to `build.sbt`.
```java
// build.sbt
...
libraryDependencies += jdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
...
```

Copy the following into your `conf/application.conf` file. The name of the database will be as you named it in phpMyAdmin using WampServer.
```java
db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://localhost/NAME_OF_DATABASE"
db.default.username=root
db.default.password=""
```

## Video 24 - Bootstrap and jQuery
My PlayTemplate already includes Bootstrap and jQuery. You can **skip this video**.

## [Video 25 - Update views 1](https://www.youtube.com/watch?v=6Zzmrf_p9Xg&list=PLYPFxrXyK0Bx9SBkNhJr1e2-NlIq4E7ED&index=25)
**You should skip this video and follow my instructions here.** There are slight differences in approach. Follow mine for better or worse.

Modify your `app/controllers/HomeController.java` file to remove the "Welcome to Play" string, leving the `render()` method call empty.
```java
public Result index() {
    return ok(views.html.index.render());
}
```

Paste this in to your `app/views/index.scala.html` file.
```
@layout("All Books") {
  <div class="jumbotron">
    <h1>Welcome to BookStore App</h1>
    <h3>Built using Play Framework 2.6</h3>
  </div>
}
```

Update your `app/views/books/layout.scala.html` file to look exactly like the below. What you should do from here is activate the two links for "All Books" and "Create Book" using `@routes.BooksController... `. The video does this at **6:32 to 7:40**
```html
<!-- file: app/views/books/layout.scala.html -->

@(title: String)(content: Html)

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        @* Here's where we render the page title `String`. *@
        <title>@title</title>
        <link rel="shortcut icon" type="image/png" href="@routes.Assets.versioned("images/favicon.png")">
        <link rel="stylesheet" type="text/css" href="@routes.Assets.versioned("stylesheets/bootstrap.css")">
        <link rel="stylesheet" media="screen" href="@routes.Assets.versioned("stylesheets/fa-svg-with-js.css")">
        <script defer src="@routes.Assets.versioned("javascripts/fontawesome-all.js")"></script>

    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-light navbar-light">
                <!-- Brand/logo -->
            <a class="navbar-brand" href="#">BookStore App</a>

                <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">All Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Create Book</a>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            @content
        </div>

        <script type='text/javascript' src='@routes.Assets.versioned("javascripts/jquery-3.3.1.js")'></script>
        <script type='text/javascript' src='@routes.Assets.versioned("javascripts/bootstrap.js")'></script>
    </body>
</html>
```

You may now delete the `app/views/main.scala.java` file, since it is pretty much a copy of our `layout.scala.html` file.

## [Video 26 - Update views 2](https://www.youtube.com/watch?v=LBuxYAAYXRM&index=26&list=PLYPFxrXyK0Bx9SBkNhJr1e2-NlIq4E7ED)
Important: We will be using [font-awesome](https://fontawesome.com/how-to-use/svg-with-js#additional-styling) for our icons. Replace all `glyphicon` with `fas`. So, instead of `class="glyphicon glyphicon-plus"`, use `class="fas fa-plus"`.
```
Glyphicon:                        FontAwesome:
glyphicon glyphicon-plus          fas fa-plus
glyphicon glyphicon-book          fas fa-book
```
### Modify index
```
// app/views/books/index.scala.html
...
<a class="btn btn-success" href="@routes.BookController.create()">
    <i class="fas fa-plus"></i>
    Create Book
</a>
```

### Modify layout nav
```
// app/views/layout.scala.html
...
    <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="@routes.BookController.index()">
                        <i class="fas fa-book"></i>
                        All Books
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="@routes.BookController.create()">
                        <i class="fas fa-plus"></i>
                        Create Book
                    </a>
                </li>
            </ul>
...
```

### Modify create

```scala
@* file: app/views/books/create.scala.html *@

@(bookForm: Form[Book])

@import helper._
@layout("Create Book") {
    <h1>Create Book</h1>
    @helper.form(CSRF(routes.BookController.save())) {
        @helper.inputText(bookForm("id"), 'class -> "form-control")
        @helper.inputText(bookForm("title"), 'class -> "form-control")
        @helper.inputText(bookForm("price"), 'class -> "form-control")
        @helper.inputText(bookForm("author"), 'class -> "form-control")
        <button class="btn btn-success" type="submit">
            <i class="fas fa-plus"></i>
            Create Book
        </button>
    }
}
```
## Video 27 - Form validations
**@ 5:58** The alert close button should be:
```html
<a href="#" class="close" data-dismiss="alert" aria-label="close"><i class="fas fa-times"></i></a>
```

## Video 28 - Delete request
Due to security reasons, the explanation in the video will not work fully. You can still follow along and then modify as explained below. We need to accomodate for CSRF in our `show` page's delete button and add a function to the `custom.js` file.


### Modify show's delete button
File `app/views/show.scala.html`. Update the delete button to look like this:
```html
...
    <button id="delete-book"
            data-url=" @helper.CSRF(routes.BooksController.destroy(book.id))"
            data-r-url="@routes.BooksController.index()"
            class="btn btn-danger">
        Delete
    </button>
...
```

### Modify custom js file
File: `public/javascripts/custom.js`. Should be as below.
```JavaScript
// file: public/javascripts/custom.js

$(function() {
    $("button#delete-book").click(function () {
        var url = $(this).data("url");
        var rUrl = $(this).data("r-url");
        sendDeleteRequest(url, rUrl);
    });
});

function sendDeleteRequest(url, rUrl) {
    $.ajax({
        url: url,
        method: "DELETE",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function () {
            window.location = rUrl;
        },
        error: function () {
            window.location.reload();
        }
    });
}

```

### Add `custom.js` to layout
**If it is not already there**
In the `app/views/layout.scala.html` file, add the `<script ...>` line just above the `</body>` tag.
```html
    ...
        <script type='text/javascript' src='@routes.Assets.versioned("javascripts/custom.js")'></script>
    </body>
</html>
```


## Video 29 - `PUT` Request
Follow the video through. There will be errors, so check the modifications below.

File: `app/views/books/edit.scala.html`. The button should be as follows.
```html
    <button id="edit-book"
            class="btn btn-primary"
            data-form-id="bookUpdateForm"
            data-r-url="@routes.BookController.edit(bookForm.get().id)">
        Edit Book
    </button>
```

File: `public/javascripts/custom.js`. Add the `edit-button` click function.
```JavaScript
$(function() {
    $("button#delete-book").click(function () {
        var url = $(this).data("url");
        var rUrl = $(this).data("r-url");
        sendDeleteRequest(url, rUrl);
    });
    
    // add this one
    $("button#edit-book").click(function () {
        var formId = $(this).data("form-id");
        var rUrl = $(this).data("r-url");
        sendPutRequest(formId, rUrl);
    });
});
```

If you are having difficulties with your `BookController.java` file, make sure the `update()` function is as below.
```java
// file: app/controllers/BooksController.java

    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        if (bookForm.hasErrors()) {
            flash("danger", "Please correct the form below");
            return badRequest();
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
```
