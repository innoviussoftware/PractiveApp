package com.practicletaskapp

import android.app.Application
import com.practicletaskapp.data.repository.*
import com.practicletaskapp.network.MyApi
import com.practicletaskapp.network.NetworkConnectionInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        //Comman used
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }

        //Login screen Injection
       /* bind() from singleton { LoginRepository(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }*/


    }

}