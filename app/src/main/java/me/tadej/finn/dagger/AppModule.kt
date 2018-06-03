package me.tadej.finn.dagger

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import me.tadej.finn.misc.getCacheSize
import me.tadej.finn.repo.FavouritesRepository
import me.tadej.finn.repo.favs.FavouritesDatabase
import me.tadej.finn.repo.favs.SimpleFavouritesRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
  @Provides @Singleton fun providesExecutor(): Executor {
    val cores = Runtime.getRuntime().availableProcessors()
    return Executors.newFixedThreadPool(cores)
  }

  @Provides @Singleton fun providesOkHttpClient(): OkHttpClient {
    val size = getCacheSize(context)
    val cache = Cache(context.cacheDir, size)
    return OkHttpClient.Builder()
        .cache(cache)
        .build()
  }

  @Provides @Singleton fun providesFavouritesRepository(executor: Executor): FavouritesRepository {
    val database = Room.databaseBuilder(context,
        FavouritesDatabase::class.java,
        "favourites"
    ).build()
    return SimpleFavouritesRepository(executor, database)
  }
}
