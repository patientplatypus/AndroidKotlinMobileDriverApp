
import io.reactivex.subjects.PublishSubject
import io.reactivex.Observable



enum class MainEventBus {

    INSTANCE;

    companion object {
        data class KeyValue(var key: String, var value: String)
    }

    private val bus = PublishSubject.create<KeyValue>().toSerialized() // the actual publisher handling all of the events

    fun send(keyValue: KeyValue) {
        println("INSIDE EVENTBUS")
        println("VALUE OF KEYVALUE")
        println(keyValue)
        bus.onNext(keyValue)
        println("DOES THE BUS HAVE OBSERVERS?")
        println(bus.hasObservers())
//        bus = PublishSubject.create<KeyValue>().toSerialized()
//        bus.onNext({key, message}) // the message being sent to all subscribers
    }

    fun toObserverable(): Observable<KeyValue> {
        return bus // return the publisher itself as an observable to subscribe to
    }

}
