package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */

public class CfgEpsUnitElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	String [] variables;
	String [] terminals;
	String [] transitions;

	boolean [] hadEpsilons;

	public CfgEpsUnitElim(String cfg) {
		// TODO Auto-generated constructor stub
		String [] data = cfg.split("#");
		variables = data[0].split(";");
		terminals = data[1].split(";");
		transitions = data[2].split(";");
		hadEpsilons = new boolean[variables.length];
		for(int i=0;i< transitions.length;i++){
			String dests = transitions[i].split("/")[1];
			String [] posses = dests.split(",");
			if(arrContains(posses,"e")) hadEpsilons[i] = true;
		}
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "";
		for(int i =0;i< variables.length;i++){
			result = result + variables[i] + ";";
		}
		result = result.substring(0,result.length()-1);
		result+="#";
		for(int i =0;i< terminals.length;i++){
			result = result + terminals[i] + ";";
		}
		result = result.substring(0,result.length()-1);
		result+="#";
		for(int i =0;i< transitions.length;i++){
			result = result + transitions[i] + ";";
		}
		result = result.substring(0,result.length()-1);
		return result;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		// TODO Auto-generated method stub
		while(haveEpsilons(transitions)){
			for(int i =transitions.length-1;i>-1;i--){
				String []transition = transitions[i].split("/");
				String leftSide = transition[0];
				String rightSide = transition[1];
				String [] possibilities = rightSide.split(",");
				if(arrContains(possibilities,"e")){
					transitions[i]=removeEpsilon(transitions[i]);
					for(int j=transitions.length-1;j>-1;j--){
						transitions[j] = eliminateEpsilon(transitions[j],leftSide, j);
					}
					transitions[i]=removeEpsilon(transitions[i]);
				}
			}
		}
	}
	public static boolean haveEpsilons(String [] transitions){
		for(int i =0;i<transitions.length;i++){
			if(transitions[i].contains("e") && transitions[i].charAt(0)!='S') return true;
		}
		return false;
	}
	public static String removeEpsilon(String s){
		for(int i =0;i<s.length();i++){
			if(s.charAt(i)=='e'){
				if(i<s.length()-1){
					s = s.substring(0,i)+s.substring(i+2,s.length());
				}
				else{
					s = s.substring(0,s.length()-1);
				}
				break;
			}
		}
		return s;
	}
	public String eliminateEpsilon(String trans, String variable, int index){
		String []transition = trans.split("/");
		String leftSide = transition[0];
		String rightSide = transition[1];
		String [] possibilities = rightSide.split(",");
		HashSet<String> result = new HashSet<String>();
		for(int i =0;i<possibilities.length;i++){
			HashSet<String> subResult = generateFreeEpsilon(possibilities[i],variable);
			result.addAll(subResult);
		}
		if(hadEpsilons[index] && !trans.contains("e")) result.remove("e");
		else if (result.contains("e")) hadEpsilons[index] = true;
		List<String> list  = new ArrayList<String>(result);
		Collections.sort(list);
		String finalResult ="";
		for(String s:list) finalResult+=s+",";
		finalResult = finalResult.substring(0,finalResult.length()-1);
		return leftSide+"/"+finalResult;
	}

	public static HashSet<String> generateFreeEpsilon(String oldValue, String variable){
		HashSet<String> newValues = new HashSet<String>();
		helperGenerateFreeEpsilon(oldValue,variable,newValues,0);
		return newValues;
	}
	private static void helperGenerateFreeEpsilon(String oldValue, String variable, HashSet<String> values, int index){
		if(oldValue.equals("")) return;
		values.add(oldValue);
		if(index>=oldValue.length())return;
		if((oldValue.charAt(index)+"").equals(variable)) {
			values.add(oldValue);
			helperGenerateFreeEpsilon(oldValue, variable, values, index + 1);
			String newValue = oldValue.substring(0, index) + oldValue.substring(index + 1);
			if(newValue.equals("")) values.add("e");
			else values.add(newValue);
			helperGenerateFreeEpsilon(newValue, variable, values, index);
		}
		else{
			helperGenerateFreeEpsilon(oldValue, variable, values, index + 1);
		}
	}

	public static boolean arrContains(String [] array, String val){
		for(int i =0;i<array.length;i++){
			if(array[i].equals(val)) return true;
		}
		return false;
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
		for(int i = 0;i<transitions.length;i++){
			String [] transition = transitions[i].split("/");
			String leftSide = transition[0];
			String rightSide = transition[1];
			String [] possibilities = rightSide.split(",");
			transitions[i] = findUnion(leftSide, possibilities);
		}
	}
	public String findUnion(String leftSide, String [] possibilities){
		Set<String> union = new HashSet<String>();
		ArrayList<String> usedVars = new ArrayList<String>();
		findUnionHelper(leftSide, possibilities, union, usedVars);
		List<String> list = new ArrayList<String>(union);
		Collections.sort(list);
		String finalResult ="";
		for(String s:list) finalResult+=s+",";
		finalResult = finalResult.substring(0,finalResult.length()-1);
		return leftSide+"/"+finalResult;
	}
	public void findUnionHelper(String leftSide, String [] possibilities, Set<String> union, ArrayList<String> usedVars){
		usedVars.add(leftSide);
		for(int i= 0;i<possibilities.length;i++){
			if(possibilities[i].equals(leftSide))continue;
			if(possibilities[i].length() == 1 && possibilities[i].charAt(0)>='A' && possibilities[i].charAt(0)<='Z'){
				if(!usedVars.contains(possibilities[i])){
					int j =0;
					while(j<transitions.length){
						if(possibilities[i].equals(transitions[j].charAt(0)+"")){
							break;
						}
						j++;
					}
					String [] newPossibilities = (transitions[j].split("/"))[1].split(",");
					findUnionHelper(possibilities[i], newPossibilities, union, usedVars);
				}
			}
			else{
				union.add(possibilities[i]);
			}
		}
	}
}
