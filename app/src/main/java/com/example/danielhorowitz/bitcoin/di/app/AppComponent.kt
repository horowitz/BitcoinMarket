package com.example.danielhorowitz.bitcoin.di.app

import com.example.danielhorowitz.bitcoin.BitcoinApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by danielhorowitz on 8/9/17.
 */
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (NetworkModule::class),
    (BuildersModule::class)
])
interface AppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: BitcoinApplication) : Builder
        fun build() : AppComponent
    }

    fun inject(application: BitcoinApplication)
}
