package com.sputnik.strava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StravaConverter<S, T> {
    public abstract T convert(S s);

    public List<T> convertList(List<S> sList) {
        if(sList == null) {
            return new ArrayList<>();
        }
        return sList.stream().map(this::convert).collect(Collectors.toList());
    }
}
