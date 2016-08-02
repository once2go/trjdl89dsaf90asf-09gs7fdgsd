package com.once2go.ttv.presenters;

import android.support.annotation.NonNull;

/**
 * Created by once2go on 02.08.16.
 */
public interface Presenter<T> {

    void setView(@NonNull T view);

    void resume();

    void pause();

    void destroy();
}
