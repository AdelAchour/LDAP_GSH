package com.prod.adelachour.ldap_gsh;

import android.os.Binder;
import android.os.IBinder;

import com.unboundid.ldap.sdk.LDAPConnection;

public class ObjectWrapperForBinder extends Binder {

    private final LDAPConnection mData;

    public ObjectWrapperForBinder(LDAPConnection data) {
        mData = data;
    }

    public LDAPConnection getData() {
        return mData;
    }
}