package csen1002.main.task3;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Write your info here
 * 
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */

public class FallbackDfa {
	String [] states;
	String alphabet;
	String [] transitions;
	String initialState;
	String [] acceptStates;

	String result;
	Stack<String> stackStates;
	int L;
	ArrayList<String> subStrings;
	/**
	 * Constructs a Fallback DFA
	 * 
	 * @param fdfa A formatted string representation of the Fallback DFA. The string
	 *             representation follows the one in the task description
	 */
	public FallbackDfa(String fdfa) {
		String [] data = fdfa.split("#");
		states = data[0].split(";");
		alphabet = data[1].replaceAll(";","");
		transitions = data[2].split(";");
		initialState = data[3];
		acceptStates = data[4].split(";");
		result = "";
		stackStates = new Stack<String>();
		subStrings = new ArrayList<String>();
	}

	/**
	 * @param input The string to simulate by the FDFA.
	 * 
	 * @return Returns a formatted string representation of the list of tokens. The
	 *         string representation follows the one in the task description
	 */
	public String run(String input) {
		stackStates.push(initialState);
		for(int i=0;i<input.length();i++){
			stackStates.push(findNextState(stackStates.peek(),input.charAt(i)+""));
		}
		String lastState = initialState;
		L = input.length()-1;
		while(!stackStates.empty() && !input.equals("")){
			String currentState = stackStates.pop();
			if(arrayContains(acceptStates,currentState)){
				subStrings.add((input.substring(0,L+1)+","+currentState));
				input = input.substring(L+1,input.length());
				stackStates.clear();
				stackStates.push(initialState);
				for(int i=0;i<input.length();i++){
					stackStates.push(findNextState(stackStates.peek(),input.charAt(i)+""));
				}
				lastState = stackStates.peek();
				L = input.length()-1;
			}
			else {
				L--;
			}
		}
		if(!input.equals("")) subStrings.add((input+","+lastState));
		return formatResult();
	}
	public String formatResult(){
		for(int i =0;i<subStrings.size();i++){
			result+=subStrings.get(i)+";";
		}
		return result.substring(0,result.length()-1);
	}
	public String findNextState(String state, String letter){
		for(int i =0;i<transitions.length;i++){
			String [] transition = transitions[i].split(",");
			if(state.equals(transition[0]) && letter.equals(transition[1]))
				return transition[2];
		}
		return "";
	}

	public static boolean arrayContains(String [] array, String element){
		for(int i =0;i<array.length;i++){
			if(array[i].equals(element)) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#r;t;x#0,r,3;0,t,10;0,x,1;1,r,8;1,t,12;1,x,6;2,r,0;2,t,4;2,x,9;3,r,2;3,t,2;3,x,4;4,r,6;4,t,11;4,x,11;5,r,4;5,t,6;5,x,5;6,r,5;6,t,13;6,x,5;7,r,11;7,t,9;7,x,3;8,r,6;8,t,0;8,x,8;9,r,4;9,t,9;9,x,4;10,r,13;10,t,0;10,x,6;11,r,8;11,t,6;11,x,2;12,r,7;12,t,6;12,x,0;13,r,8;13,t,6;13,x,2#12#3;9");
		assertEquals("rxtrr,3;rt,9;rt,9;rtt,9;rrrx,8", fallbackDfa.run("rxtrrrtrtrttrrrx"));
	}
	
}
