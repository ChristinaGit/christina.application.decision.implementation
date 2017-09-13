package moe.christina.decision.objects_list.domain.usecase

import io.reactivex.Observable
import moe.christina.common.core.RxSchedulers
import moe.christina.decision.objects_viewer.domain.model.CreatedObject
import moe.christina.mvp.interactor.TaskInteractor
import java.io.IOException
import java.util.Random
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetObjectsInteractor
@Inject
constructor()
    : TaskInteractor<List<CreatedObject>> {
    private val random = Random(2)

    override operator fun invoke(): Observable<List<CreatedObject>> {
        return Observable
            .range(random.nextInt(100), 20)
            .delay(2, TimeUnit.SECONDS)
            .map { CreatedObject("Decision #$it") }
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