package com.example.danielhorowitz.bitcoin

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.assertj.core.api.Assertions.assertThat

/**
 * Creates an Observer that records events and allows making assertions about them.
 */
fun <A> LiveData<A>.test(): TestObserver<A> {
    val observer = TestObserver<A>()
    observeForever(observer)
    return observer
}

/**
 * Observer that records events and allows making assertions about them.
 *
 * Note: We can add more methods over time. Naming should be consistent with RxJava's TestObserver
 * http://reactivex.io/RxJava/javadoc/io/reactivex/observers/TestObserver.html
 */
class TestObserver<A> : Observer<A> {

    private val _items = mutableListOf<A>()

    private val items: List<A>
        get() = _items

    override fun onChanged(t: A) {
        _items.add(t)
    }

    fun assertEmpty(): TestObserver<A> = also {
        assertThat(items).isEmpty()
    }

    fun assertValueCount(expected: Int): TestObserver<A> = also {
        assertThat(items).hasSize(expected)
    }

    fun assertValueAt(index: Int, value: A): TestObserver<A> = also {
        assertThat(items).element(index).isEqualTo(value)
    }

    fun assertValueAt(index: Int, predicate: (A) -> Boolean): TestObserver<A> = also {
        assertThat(items).isNotEmpty
        assertThat(predicate(items[index])).isTrue()
    }

    fun assertValues(vararg values: A): TestObserver<A> = also {
        assertThat(items).isEqualTo(values.toList())
    }

    fun assertValue(value: A): TestObserver<A> = also {
        assertThat(items).isEqualTo(listOf(value))
    }

    fun assertValue(predicate: (A) -> Boolean) = also {
        assertThat(items).isNotEmpty
        assertThat(predicate(items.first())).isTrue()
    }
}
