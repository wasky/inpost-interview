package pl.waskysoft.inpost.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.waskysoft.inpost.db.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//@InstallIn(ViewModelComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    //@ViewModelScoped
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

}