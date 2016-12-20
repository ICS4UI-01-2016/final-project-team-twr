/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import java.util.Stack;

/**
 *
 * @author tatad6701
 */
public class StateManager {

    private Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
    }
}
