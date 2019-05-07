# Zemoga
An app that lists all messages and their details from JSONPlaceholder.

<div id="content">
   <img src="https://github.com/carlosgvl/Zemoga/blob/master/screenshot/home.png" width="180" alt="  " />
   <img src="https://github.com/carlosgvl/Zemoga/blob/master/screenshot/detail.png" width="180" alt="  " />
   <img src="https://github.com/carlosgvl/Zemoga/blob/master/screenshot/favorite.png" width="180" alt="  " />
   <img src="https://github.com/carlosgvl/Zemoga/blob/master/screenshot/post_read.png" width="180" alt="  " />
</div>


##   Programming Language / Project Structure

### Programming Language
- Kotlin 

### Project Strucuture
This project have implemented MVP and Dependency Injection, structured into 5 packages:

- di: Where dagger2 resides in dependency injection.
- models: Data models.
- network: Where Retrofit resides in.
- ui: Activities with presenter and contract.
- util: Some helps.

## Requirements:

- Load the posts from the JSON API and populate the sidebar.
- The first 20 posts should have a blue dot indicator.
- Remove the blue dot indicator once the related post is read.
- Once a post is touched, its related content is shown in the main content area.
- The related content also displays the user information.
- Add a button in the navigation. It adds the current post to favorites.
- Each cell should have the functionality to swipe and delete the post.
- * Add a button to the footer that removes all posts.
- Add a button to navigation that reloads all posts.
- Add a segmented control to filter posts (All / Favorites)
- * Favorite posts should have a star indicator.







## Autor

* **Carlos Vivas** 
