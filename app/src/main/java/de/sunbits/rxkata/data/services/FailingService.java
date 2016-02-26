package de.sunbits.rxkata.data.services;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by matkoch on 26/02/16.
 */
public class FailingService {

    public Observable<String> getNames() {

        return Observable.interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(final Long aLong) {

                        if (Math.random() < .5) {
                            throw new RuntimeException();
                        }
                        return "Success " + aLong;
                    }
                });
    }
}
