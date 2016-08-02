package com.once2go.ttv.views;

import android.content.Context;

/**
 * Created by once2go on 02.08.16.
 */
public interface LoadDataView {

    void indicateProgress();

    void disableIndication();

    void showError(String message);

    Context getContext();
}
