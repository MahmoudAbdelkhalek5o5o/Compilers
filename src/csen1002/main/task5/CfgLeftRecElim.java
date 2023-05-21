package csen1002.main.task5;

import java.util.ArrayList;
/**
 * Write your info here
 *
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */
public class CfgLeftRecElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	String [] variables;
	String [] terminals;
	String [] transitions;
	ArrayList<String> newTransitions;
	ArrayList<String> newVariables;

	public CfgLeftRecElim(String cfg) {
		// TODO Auto-generated constructor stub
		String [] data = cfg.split("#");
		variables = data[0].split(";");
		terminals = data[1].split(";");
		transitions = data[2].split(";");
		newTransitions = new ArrayList<String>();
		newVariables = new ArrayList<String>();
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "";
		for(int i =0;i<variables.length;i++) result = result + variables[i]+";";
		for(String var:newVariables)result = result+var+";";
		result = result.substring(0,result.length()-1);
		result+="#";
		for(int i =0;i<terminals.length;i++) result = result + terminals[i]+";";
		result = result.substring(0,result.length()-1);
		result+="#";
		for(int i =0;i<transitions.length;i++) result = result + transitions[i]+";";
		for(String transition:newTransitions) result = result + transition + ";";
		result = result.substring(0,result.length()-1);
		return result;
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		// TODO Auto-generated method stub
		for(int i =0;i<transitions.length;i++){
			if(isLeftRecursion(transitions[i])){
				String [] trans = transitions[i].split("/");
				String leftSide = trans[0];
				String [] rightSide = trans[1].split(",");
				ArrayList<String> alphas = new ArrayList<String>();
				ArrayList<String> betas = new ArrayList<String>();
				for (String value: rightSide) {
					if(leftSide.equals(value.charAt(0)+"")) alphas.add(value.substring(1,value.length()));
					else betas.add(value);
				}
				String leftSidePrime = leftSide+"'";
				String firstTransition = leftSide+"/";
				String secondTransition = leftSidePrime+"/";
				for(String beta: betas) firstTransition = firstTransition + beta +leftSidePrime+",";
				firstTransition = firstTransition.substring(0,firstTransition.length()-1);
				for(String alpha: alphas) secondTransition = secondTransition + alpha +leftSidePrime+",";
				secondTransition = secondTransition+"e";
				transitions[i] = firstTransition;
				newTransitions.add(secondTransition);
				newVariables.add(leftSidePrime);
			}
			substitute(transitions[i], i);
		}
	}
	private void substitute(String transition, int index){
		String leftSide = transition.split("/")[0];
		String rightSide = transition.split("/")[1];
		String [] values = rightSide.split(",");
		for(int i =index+1 ;i<transitions.length;i++){
			String [] places = transitions[i].split("/")[1].split(",");
			String newRightSide = "";
			for(int j = 0;j< places.length; j++){
				if(leftSide.equals(places[j].charAt(0)+"")){
					String original = places[j];
					places[j] = "";
					for(int k =0;k<values.length;k++){
						places[j] = places[j] + values[k] + original.substring(1) + ",";
					}
					places[j] = places[j].substring(0,places[j].length()-1);
				}
			}
			for(int j = 0;j< places.length;j++){
				newRightSide = newRightSide+places[j]+",";
			}
			transitions[i] = transitions[i].split("/")[0] + "/" + newRightSide.substring(0,newRightSide.length()-1);
		}
	}
	private static boolean isLeftRecursion(String rule){
		String [] trans = rule.split("/");
		String leftSide = trans[0];
		String [] rightSide = trans[1].split(",");
		for(int i =0;i<rightSide.length;i++){
			if(leftSide.equals(rightSide[i].charAt(0)+"")) return true;
		}
		return false;
	}
}