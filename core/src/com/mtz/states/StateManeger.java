package com.mtz.states;

import java.util.Stack;

public class StateManeger {
	
	public Stack<State> states;
	
	public StateManeger() {
		
		states = new Stack<State>();
		
	}
	
	public void pushState(State state){
		
		states.push(state);
		
	}
	
	public void popState(){
		
		states.pop();
		states.peek().setInputProssesor();
		
	}
	
	public State peekState(){
		
		return states.peek();
		
	}
}
