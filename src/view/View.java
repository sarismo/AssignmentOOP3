package view;

import Utils.Callbacks.MessageCallback;

public abstract class View {
    public abstract void display(String message);
    public MessageCallback getCallback(){
        return this :: display;
    }
}
