package edu.galileo.android.facebookrecipes.lib.base;

/**
 * Created by praxis on 14/06/16.
 */
public interface EventBus {
    void register(Object suscribe);
    void unregister(Object suscribe);
    void post(Object event);
}
