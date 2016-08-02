package com.once2go.domain.interactors;

import com.once2go.domain.NetworkCManger;
import com.once2go.model.network.ServerApi;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by once2go on 01.08.16.
 */
public abstract class UseCase<T> {

    private Subscription subscription = Subscriptions.empty();
    private Scheduler threadExecutor = Schedulers.io();
    private Scheduler postExecutionThread = AndroidSchedulers.mainThread();

    protected abstract Observable<T> buildUseCaseObservable();

    protected ServerApi serverApi;

    public UseCase() {
        serverApi = NetworkCManger.getInstance().getTApiComponent().getServerApi();
    }

    public void execute(Subscriber<T> useCaseSubscriber) {
        subscription = setupObservable()
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public Observable<T> setupObservable() {
        return buildUseCaseObservable()
                .subscribeOn(threadExecutor)
                .observeOn(postExecutionThread);
    }
}
