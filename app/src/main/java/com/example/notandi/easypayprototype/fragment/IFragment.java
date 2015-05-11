package com.example.notandi.easypayprototype.fragment;

/**
 * Created by Notandi on 11/05/2015.
 */
public interface IFragment {

    /**
     * Method used to get fragment title
     *
     * @return
     */
    String getTitle();

    /**
     * Method used to indicate if the fragment can be displayed or not
     *
     * @return true or false
     */
    boolean isEnable();

}
