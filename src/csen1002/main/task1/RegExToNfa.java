package csen1002.main.task1;;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */

public class RegExToNfa {
	String alphabet;
	String postfixRegExp;
	Stack<String> stack;
	Stack<int []> stateStack;
	ArrayList<String []> transitions;
	int stateCount;
	String output;
	String operators = ".|*";
	ArrayList<Integer> states;

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
	public RegExToNfa(String input) {
		String [] data = input.split("#");
		alphabet = data[0];
		alphabet = alphabet.replaceAll(";","");
		postfixRegExp = data[1];
		stack = new Stack<String>();
		stateStack = new Stack<int []>();
		stateCount = -1;
		transitions= new ArrayList<String []>();
		output ="";
		states = new ArrayList<Integer>();
		for(int i =0;i<postfixRegExp.length();i++){
			String current = postfixRegExp.charAt(i)+"";
			if(stack.isEmpty()) stack.push(current);
			if(operators.contains(current)){
				if(current.equals("*")){
					String operand = stack.pop();
					star(operand);
				}
				else {
					String operand1 = stack.pop();
					String operand2 = stack.pop();
					if (current.equals("."))
						concat(operand1, operand2);
					if (current.equals("|"))
						union(operand1, operand2);
				}
			}
			else{
				stack.push(current);
				int start = stateCount+1;
				states.add(stateCount+1);
				int end = stateCount+2;
				states.add(stateCount+2);
				int [] startEnd = {start,end};
				stateStack.push(startEnd);
				String [] transition = {start+"",current,end+""};
				transitions.add(transition);
				stateCount+=2;
			}
		}
	}

	public void star(String operand){
		int [] startEnd = stateStack.pop();
		int start = startEnd[0];
		int end = startEnd[1];
		String [] t1 = {end+"","e",start+""};
		String [] t2 = {(stateCount+1)+"","e",start+""};
		String [] t3 = {end+"","e",(stateCount+2)+""};
		String [] t4 = {(stateCount+1)+"","e",(stateCount+2)+""};
		transitions.add(t1);transitions.add(t2);transitions.add(t3);transitions.add(t4);
		int [] newState = {stateCount+1,stateCount+2};
		stateStack.push(newState);
		stack.push(operand+"*");
		states.add(stateCount+1);
		states.add(stateCount+2);
		stateCount += 2;
	}
	public void union(String operand1, String operand2){
		int [] startEnd1 = stateStack.pop();
		int [] startEnd2 = stateStack.pop();
		int start1 = startEnd1[0];
		int end1 = startEnd1[1];
		int start2 = startEnd2[0];
		int end2 = startEnd2[1];
		String [] t1 = {(stateCount+1)+"","e",start1+""};
		String [] t2 = {(stateCount+1)+"","e",start2+""};
		String [] t3 = {end1+"","e",(stateCount+2)+""};
		String [] t4 = {end2+"","e",(stateCount+2)+""};
		transitions.add(t1);transitions.add(t2);transitions.add(t3);transitions.add(t4);
		int [] newState = {stateCount+1,stateCount+2};
		stateStack.push(newState);
		stack.push(operand2+"|"+operand1);
		states.add(stateCount+1);
		states.add(stateCount+2);
		stateCount += 2;
	}
	public void concat(String operand1, String operand2){
		int [] startEnd1 = stateStack.pop();
		int [] startEnd2 = stateStack.pop();
		int start2 = startEnd1[0];
		int end2 = startEnd1[1];
		int start1 = startEnd2[0];
		int end1 = startEnd2[1];
		for(int i = 0;i< transitions.size();i++){
			String [] transition = transitions.get(i);
			if(transition[0].equals(start2+""))transition[0]=end1+"";
		}
		states.remove(Integer.valueOf(start2));
		int [] newState = {start1,end2};
		stateStack.push(newState);
		stack.push(operand2+"."+operand1);
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		String result = "";
		for(int i =0;i<states.size();i++){
			result = result + states.get(i) +";";
		}
		result = result.substring(0,result.length()-1);
		result +="#";
		for(int i =0;i<alphabet.length();i++){
			result = result + (alphabet.charAt(i) + "") +";";
		}
		result = result.substring(0,result.length()-1);
		result +="#";
		Collections.sort(transitions, new Comparator<String[]>() {
			public int compare(String[] frst, String[] scnd) {
				int start1 = Integer.parseInt(frst[0]);
				int end1 = Integer.parseInt(frst[2]);
				int start2 = Integer.parseInt(scnd[0]);
				int end2 = Integer.parseInt(scnd[2]);
				if(start1 > start2) {
					return 1;
				}
				else if(start1 == start2){
					if(end1>end2)
						return 1;
					else return -1;
				}
				else return -1;
			}
		});
		for(int i =0;i<transitions.size();i++){
			String [] transition = transitions.get(i);
			result = result + transition[0] + "," + transition[1] + "," + transition[2] + ";";
		}
		result = result.substring(0,result.length()-1);
		result +="#";
		int [] wholeStartEnd = stateStack.pop();
		result = result + wholeStartEnd[0] + "#" + wholeStartEnd[1];
		return result;
	}

	public static void main(String [] args){
		RegExToNfa x = new RegExToNfa("o;s#os|*");
		System.out.println(x);
	}

}
