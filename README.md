# Quick summary

### Approach

The app contains two juicy packages:

 - repo
 - ui

Repo provides the data to be displayed by the UI. There are two abstractions to interact with: `AdsRepository` and `FavouritesRepository`. Since there is only a single endpoint to be hit, I've made a bold decision not to use `Retrofit`, nor any of the standard data binding JSON libraries. I have ensured, however, that if we were to switch, the transition would be rather smooth, as the underlying implementations reside behind a more generic interface (e.g. `JsonParser`).

`AdsViewModel` is the glue between the UI and repositories. It utilises `LiveData` to handle the lifecycle events.

The UI consists of a single activity and two Fragments: `MainFragment` and `FavouritesFragment`. You may navigate between the two using the navigation drawer.

Favourites are persisted using `Room`.

### Good parts

I think the app is reasonably modular, meaning it would cope with changes.

### Not so good parts

The UI as a whole could & should be a bit more polished. Image loading in particular should be smoother. I planned for pagination, but did not implement it. While keeping it simple, I feel using the third party libs (e.g. `RxJava`) would make some things easier & less boilerplate heavy. I could also do a bit more in terms of testing. Error handling is also neglected. There is no accessibility and internationalisation (LTR) features. Stock icons/thumbnails are bad, and I should feel bad.

### Conclusion

For a few hours' work, I'm reasonably happy with the outcome, although there is a lot more it could be done & improved.
