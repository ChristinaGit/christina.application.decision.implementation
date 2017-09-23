package christina.application.decision.objects_list.domain.usecase

import io.reactivex.Observable
import christina.library.android.common.rx.RxSchedulers
import christina.application.decision.objects_list.domain.model.Object
import christina.library.android.architecture.mvp.interactor.Interactor
import java.io.IOException
import java.util.Random
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetObjectsInteractor
@Inject
constructor() : Interactor<Unit, Observable<List<Object>>> {
    private val random = Random(2)

    override fun execute(argument: Unit): Observable<List<Object>> {
        return Observable
            .range(random.nextInt(100), 20)
            .delay(2, TimeUnit.SECONDS)
            .map { Object("Object #$it") }
            .toList()
            .toObservable()
            .doOnNext({
                if (random.nextInt(100) > 65) {
                    throw IOException("Error test.")
                }
            })
            .subscribeOn(RxSchedulers.io())
    }
}