/**
 * @author kjell
 */

package com.redheads.patterns.be;

public class Message {

    private int id;
    private String message;

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
