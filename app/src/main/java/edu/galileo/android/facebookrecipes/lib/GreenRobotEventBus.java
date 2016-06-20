package edu.galileo.android.facebookrecipes.lib;

import edu.galileo.android.facebookrecipes.lib.base.EventBus;

/**
 * Created by praxis on 14/06/16.
 */
public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object suscribe) {

    }

    @Override
    public void unregister(Object suscribe) {

    }

    @Override
    public void post(Object event) {

    }
}
