package com.app.myapplicationwithdagger.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by harsh on 22/3/18.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApiServiceScope {
}
