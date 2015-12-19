package com.roundon.model;

import java.util.ArrayList;

/**
 * Created by liqy on 15/12/17.
 */
public class ErrorMessage {
    public ArrayList<String> errors;

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "errors=" + errors +
                '}';
    }
}
