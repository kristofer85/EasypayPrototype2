package com.example.notandi.easypayprototype;

/**
 * Created by Notandi on 08/05/2015.
 */

import com.github.devnied.emvnfccard.model.EmvCard;

public interface IContentActivity {

    /**
     * Method used to get log from root activity
     *
     * @return String buffer
     */
    StringBuffer getLog();

    /**
     * Method used to get read card data
     *
     * @return
     */
    EmvCard getCard();

    /**
     * Method to set refreshabe content
     *
     * @param pRefreshable
     */
    void setRefreshableContent(IRefreshable pRefreshable);

}

