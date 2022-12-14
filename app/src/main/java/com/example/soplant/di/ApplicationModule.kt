package com.example.soplant.di

import com.example.soplant.BuildConfig
import com.example.soplant.application.network.services.*
import com.example.soplant.application.repositories.*
import com.example.soplant.domain.interactors.confirm_reset.ConfirmReset
import com.example.soplant.domain.interactors.confirmation.CreateAccount
import com.example.soplant.domain.interactors.confirmation.CreateExploration
import com.example.soplant.domain.interactors.confirmation.ResendCode
import com.example.soplant.domain.interactors.confirmation.ValidateUser
import com.example.soplant.domain.interactors.login.LoginWithCredentials
import com.example.soplant.domain.interactors.register.GetCountries
import com.example.soplant.domain.interactors.register.SignupUser
import com.example.soplant.domain.interactors.reset_password.ResetPassword
import com.example.soplant.domain.interactors.social_signin.FederateSignIn
import com.example.soplant.domain.interactors.social_signin.SignOut
import com.example.soplant.domain.interactors.wall.*
import com.example.soplant.domain.interactors.wallet.GetTransactions
import com.example.soplant.domain.repositories.*
import com.example.soplant.domain.utils.StringValidators
import com.example.soplant.redux.Middleware
import com.example.soplant.redux.Reducer
import com.example.soplant.redux.confirm_reset.*
import com.example.soplant.redux.confirmation.ConfirmationAction
import com.example.soplant.redux.confirmation.ConfirmationDataMiddleware
import com.example.soplant.redux.confirmation.ConfirmationReducer
import com.example.soplant.redux.confirmation.ConfirmationViewState
import com.example.soplant.redux.create_post.CreatePostAction
import com.example.soplant.redux.create_post.CreatePostReducer
import com.example.soplant.redux.create_post.CreatePostViewState
import com.example.soplant.redux.login.LoginAction
import com.example.soplant.redux.login.LoginDataMiddleware
import com.example.soplant.redux.login.LoginReducer
import com.example.soplant.redux.login.LoginViewState
import com.example.soplant.redux.register.*
import com.example.soplant.redux.reset_password.*
import com.example.soplant.redux.social_signin.SocialSignInAction
import com.example.soplant.redux.social_signin.SocialSignInDataMiddleware
import com.example.soplant.redux.social_signin.SocialSignInReducer
import com.example.soplant.redux.social_signin.SocialSignInViewState
import com.example.soplant.redux.wall.WallAction
import com.example.soplant.redux.wall.WallDataMiddleware
import com.example.soplant.redux.wall.WallReducer
import com.example.soplant.redux.wall.WallViewState
import com.example.soplant.redux.wallet.WalletAction
import com.example.soplant.redux.wallet.WalletDataMiddleware
import com.example.soplant.redux.wallet.WalletReducer
import com.example.soplant.redux.wallet.WalletViewState
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
    fun provideProductService(): ProductService {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun provideResourceService(): ResourceService {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ResourceService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletService(): WalletService {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WalletService::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountService(): AccountService {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AccountService::class.java)
    }

    @Provides
    @Singleton
    fun provideExplorationService(): ExplorationService {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExplorationService::class.java)
    }

    // MIDDLEWARES

    @Provides
    @Singleton
    fun provideLoginMiddlewares(
        loginWithCredentials: LoginWithCredentials,
        resendCode: ResendCode
    ): List<Middleware<LoginAction, LoginViewState>> {
        return listOf(LoginDataMiddleware(loginWithCredentials, resendCode))
    }

    @Provides
    @Singleton
    fun provideConfirmationMiddlewares(
        validateUser: ValidateUser,
        resendCode: ResendCode,
        createAccount: CreateAccount,
        createExploration: CreateExploration,
        loginWithCredentials: LoginWithCredentials
    ): List<Middleware<ConfirmationAction, ConfirmationViewState>> {
        return listOf(
            ConfirmationDataMiddleware(
                validateUser,
                resendCode,
                loginWithCredentials,
                createAccount,
                createExploration
            )
        )
    }

    @Provides
    @Singleton
    fun provideSocialSignInMiddlewares(
        federateSignIn: FederateSignIn,
        signOut: SignOut,
        getCountries: GetCountries,
        createAccount: CreateAccount,
        createExploration: CreateExploration
    ): List<Middleware<SocialSignInAction, SocialSignInViewState>> {
        return listOf(
            SocialSignInDataMiddleware(
                federateSignIn,
                signOut,
                getCountries,
                createAccount,
                createExploration
            )
        )
    }

    @Provides
    @Singleton
    fun provideRegisterMiddlewares(
        signupUser: SignupUser,
        getCountries: GetCountries,
        stringValidators: StringValidators
    ): List<Middleware<RegisterAction, RegisterViewState>> {
        return listOf(
            RegisterValidatorMiddleware(stringValidators),
            RegisterDataMiddleware(signupUser, getCountries)
        )
    }

    @Provides
    @Singleton
    fun provideResetPasswordMiddlewares(
        resetPassword: ResetPassword,
        stringValidators: StringValidators
    ): List<Middleware<ResetPasswordAction, ResetPasswordViewState>> {
        return listOf(
            ResetPasswordValidatorMiddleware(stringValidators),
            ResetPasswordDataMiddleware(resetPassword)
        )
    }

    @Provides
    @Singleton
    fun provideConfirmResetMiddlewares(
        confirmReset: ConfirmReset,
        stringValidators: StringValidators
    ): List<Middleware<ConfirmResetAction, ConfirmResetViewState>> {
        return listOf(
            ConfirmResetValidatorMiddleware(stringValidators),
            ConfirmResetDataMiddleware(confirmReset)
        )
    }

    @Provides
    @Singleton
    fun provideWallMiddlewares(
        getOfflineWall: GetOfflineWall,
        getUserWall: GetUserWall,
        getUsers: GetUsers,
        getUser: GetUser
    ): List<Middleware<WallAction, WallViewState>> {
        return listOf(WallDataMiddleware(getOfflineWall, getUserWall, getUsers, getUser))
    }

    @Provides
    @Singleton
    fun provideWalletMiddlewares(
        getWallet: GetWallet,
        getTransactions: GetTransactions
    ): List<Middleware<WalletAction, WalletViewState>> {
        return listOf(WalletDataMiddleware(getWallet, getTransactions))
    }

    @Provides
    @Singleton
    fun provideCreatePostMiddlewares(
    ): List<Middleware<CreatePostAction, CreatePostViewState>> {
        return listOf()
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

        @Binds
        @Singleton
        fun bindResourceRepository(
            resourceRepositoryImpl: ResourceRepositoryImpl
        ): ResourceRepository

        @Binds
        @Singleton
        fun bindUserRepository(
            userRepositoryImpl: UserRepositoryImpl
        ): UserRepository

        @Binds
        @Singleton
        fun bindAccountRepository(
            accountRepositoryImpl: AccountRepositoryImpl
        ): AccountRepository

        @Binds
        @Singleton
        fun bindWalletRepository(
            walletRepositoryImpl: WalletRepositoryImpl
        ): WalletRepository

        @Binds
        @Singleton
        fun bindExplorationRepository(
            resourceExplorationImpl: ExplorationRepositoryImpl
        ): ExplorationRepository

        @Binds
        @Singleton
        fun bindTransactionsRepository(
            transactionsRepositoryImpl: TransactionRepositoryImpl
        ): TransactionRepository

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
        fun bindConfirmationReducer(
            confirmationReducer: ConfirmationReducer
        ): Reducer<ConfirmationAction, ConfirmationViewState>

        @Binds
        @Singleton
        fun bindSocialSignInReducer(
            socialSignInReducer: SocialSignInReducer
        ): Reducer<SocialSignInAction, SocialSignInViewState>

        @Binds
        @Singleton
        fun bindResetPasswordReducer(
            resetPasswordReducer: ResetPasswordReducer
        ): Reducer<ResetPasswordAction, ResetPasswordViewState>

        @Binds
        @Singleton
        fun bindConfirmResetReducer(
            confirmResetReducer: ConfirmResetReducer
        ): Reducer<ConfirmResetAction, ConfirmResetViewState>

        @Binds
        @Singleton
        fun bindWallReducer(
            wallReducer: WallReducer
        ): Reducer<WallAction, WallViewState>

        @Binds
        @Singleton
        fun bindWalletReducer(
            walletReducer: WalletReducer
        ): Reducer<WalletAction, WalletViewState>

        @Binds
        @Singleton
        fun createPostReducer(
            createPostReducer: CreatePostReducer
        ): Reducer<CreatePostAction, CreatePostViewState>
    }
}