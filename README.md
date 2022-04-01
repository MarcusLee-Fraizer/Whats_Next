# What's Next?

## A movie tracking and recommendation app.

This application will allow a user to *log movies*
that they have already seen and *rate them*. 
They can also get *recommendations* for new movies 
to watch, based on *genres* they prefer and movies 
that they have watched in the past. They will also 
be able to find out which streaming services the 
movie is on, if any.

This app may be of interest to people that can not 
decide what movie to watch next, or previously 
enjoyed a movie and want to watch something similar. 
This project is of interest to me because I have 
spent far too much time deciding what to watch 
instead of actually watching something. I have also 
started watching movies that I had previously seen, 
forgetting my previous viewing. Having a list of 
movies that I have already watched may help with that
problem.

# User Stories

**A list of user stories**:
- As a user, I want to be able to add multiple 
movies to my "Watched Movies" list on my profile.
- As a user, I want to be able to give a movie,
in my "Watched Movies" list, a rating out of 5 
stars. 
- As a user, I want search for films that I have 
watched based on title or genre.
- As a user, I want to get movie recommendations
based on a genre or previously watched movie.
- As a user, I want to save movie additions to
my recommendation and watched lists.
- As a user, I want to be able to load previous 
recommendation and watched lists to my current 
profile.

## Phase 4: Task 2

**Examples of logged events:**
- Mon Mar 28 15:00:14 PDT 2022 
PIRATES OF THE CARRIBEAN was added to the Watched List.
- Mon Mar 28 15:00:23 PDT 2022
DEADPOOL was added to the Recommended List.

## Phase 4: Task 3
- Refactor and pull common functionalities from
displayMovies out of individual tabs and into 
abstract Tab class.
- Remove WhatsNextApp class completely.
- Remove List of Movie fields from WatchedTab and
RecommendedTab and access them through the given
WhatsNextUI Profile.
- Refactor and move JSON_STORE field and saveProfile()
and loadProfile() methods into HomeTab Class, to
keep save and load functionality in the same class.


  