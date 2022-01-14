package com.example.soplant.di

import com.example.soplant.application.network.services.ProductService
import com.example.soplant.application.repositories.LoginRepositoryImpl
import com.example.soplant.application.repositories.ProductRepositoryImpl
import com.example.soplant.domain.interactors.login.LoginWithCredentials
import com.example.soplant.domain.interactors.register.SignupUser
import com.example.soplant.domain.repositories.LoginRepository
import com.example.soplant.domain.utils.StringValidators
import com.example.soplant.domain.interactors.wall.GetOfflineWall
import com.example.soplant.domain.repositories.ProductsRepository
import com.example.soplant.redux.Middleware
import com.example.soplant.redux.Reducer
import com.example.soplant.redux.login.LoginAction
import com.example.soplant.redux.login.LoginDataMiddleware
import com.example.soplant.redux.login.LoginReducer
import com.example.soplant.redux.login.LoginViewState
import com.example.soplant.redux.register.*
import com.example.soplant.redux.wall.WallAction
import com.example.soplant.redux.wall.WallDataMiddleware
import com.example.soplant.redux.wall.WallReducer
import com.example.soplant.redux.wall.WallViewState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ApplicationModule.RepositoriesModule::class])
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    // QUALIFIERS

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LoginStore

    // SERVICES

    @Provides
    @Singleton
    fun provideCoinService(): ProductService {
        return Retrofit
            .Builder()
            .baseUrl("https://i40wosaqzh.execute-api.eu-west-3.amazonaws.com/v1/product")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }

    // MIDDLEWARES

    @Provides
    @Singleton
    fun provideLoginMiddlewares(loginWithCredentials: LoginWithCredentials): List<Middleware<LoginAction, LoginViewState>> {
        return listOf(LoginDataMiddleware(loginWithCredentials))
    }

    @Provides
    @Singleton
    fun provideRegisterMiddlewares(signupUser: SignupUser, stringValidators: StringValidators): List<Middleware<RegisterAction, RegisterViewState>> {
        return listOf(
            RegisterValidatorMiddleware(stringValidators),
            RegisterDataMiddleware(signupUser)
        )
    }

    @Provides
    @Singleton
    fun provideWallMiddlewares(getOfflineWall: GetOfflineWall): List<Middleware<WallAction, WallViewState>> {
        return listOf(WallDataMiddleware(getOfflineWall))
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoriesModule {

        // REPOSITORIES

        @Binds
        @Singleton
        fun bindLoginRepository(
            loginRepositoryImpl: LoginRepositoryImpl
        ): LoginRepository

        @Binds
        @Singleton
        fun bindWallRepository(
            productRepositoryImpl: ProductRepositoryImpl
        ): ProductsRepository

        // REDUCERS

        @Binds
        @Singleton
        fun bindLoginReducer(
            loginReducer: LoginReducer
        ): Reducer<LoginAction, LoginViewState>

        @Binds
        @Singleton
        fun bindRegisterReducer(
            registerReducer: RegisterReducer
        ): Reducer<RegisterAction, RegisterViewState>

        @Binds
        @Singleton
        fun bindWallReducer(
            wallReducer: WallReducer
        ): Reducer<WallAction, WallViewState>
    }
}