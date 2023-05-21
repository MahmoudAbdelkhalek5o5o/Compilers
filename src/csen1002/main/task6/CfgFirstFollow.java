package csen1002.main.task6;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Write your info here
 * 
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */

public class CfgFirstFollow {
	String [] variables;
	String [] terminals;
	String [] rules;
	HashMap<String, Set<String>> firstTable;
	HashMap<String, Set<String>> followTable;
	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		String [] data = cfg.split("#");
		variables = data[0].split(";");
		terminals = data[1].split(";");
		rules = data[2].split(";");
		firstTable = new HashMap<String, Set<String>>();
		for(int i =0;i<terminals.length;i++){
			Set<String> set = new HashSet<String>();
			set.add(terminals[i]);
			firstTable.put(terminals[i],set);
		}
		for(int i =0;i<variables.length;i++){
			firstTable.put(variables[i], new HashSet<String>());
		}
		Set<String> set = new HashSet<String>();
		set.add("e");
		firstTable.put("e",set);
		followTable = new HashMap<String, Set<String>>();
		Set<String> startSet = new HashSet<>();
		startSet.add("$");
		followTable.put(variables[0], startSet);
		for(int i =1;i<variables.length;i++){
			followTable.put(variables[i], new HashSet<String>());
		}
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		String result = "";
		boolean change = true;
		while(change){
			change = false;
			for(int i =0;i< rules.length;i++){
				String leftSide = rules[i].split("/")[0];
				String [] rightSide = rules[i].split("/")[1].split(",");
				Set <String> set= firstTable.get(leftSide);
				for(int j =0;j<rightSide.length;j++){
					int oldLength = set.size();
					if(isTerminalOrEpsilon(rightSide[j].charAt(0)+"")){
						set.add(rightSide[j].charAt(0)+"");
					}
					else{
						if(epsilonIntersection(rightSide[j])) {
							set.add("e");
						}
						for(int k = 0;k<rightSide[j].length();k++){
							String subset = rightSide[j].substring(0,k);
							if(epsilonIntersection(subset)){
								Set<String> bSet= new HashSet<>(firstTable.get(rightSide[j].charAt(k)+""));
								bSet.remove("e");
								set.addAll(bSet);
							}
							else{
								break;
							}
						}
					}
					int newLength = set.size();
					if(oldLength<newLength)
						change = true;
				}
			}
		}
		for(int i =0;i<variables.length;i++){
			Set<String> set= firstTable.get(variables[i]);
			List<String> list = new ArrayList<String>(set);
			Collections.sort(list);
			result = result + variables[i]+"/";
			for(String val:list)
				result=result+val;
			result+=";";
		}
		return result.substring(0,result.length()-1);
	}
	public boolean epsilonIntersection(String term){
		for(int i =0;i<term.length();i++){
			Set<String> set = firstTable.get(term.charAt(i)+"");
			if(!set.contains("e")) return false;
		}
		return true;
	}
	public boolean isTerminalOrEpsilon(String value){
		if(value.equals("e")) return true;
		for(int i =0;i<terminals.length;i++){
			if(terminals[i].equals(value)) return true;
		}
		return false;
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		first();
		// TODO Auto-generated method stub
		String result = "";
		boolean change = true;
		while(change){
			change = false;
			for(int i =0;i<rules.length;i++){
				String leftSide = rules[i].split("/")[0];
				String [] rightSide = rules[i].split("/")[1].split(",");
				Set <String> set= followTable.get(leftSide);
				for(int j =0;j<rightSide.length;j++){
					for(int k =0;k<rightSide[j].length();k++){
						String beta = "e";
						if(k<rightSide[j].length()-1) beta = rightSide[j].substring(k+1)+"";
						if(isVariable(rightSide[j].charAt(k)+"")){
							Set<String> firstBeta = getFirstOfBeta(beta);
							firstBeta.remove("e");
							Set<String> followB = followTable.get(rightSide[j].charAt(k)+"");
							int oldLength = followB.size();
							followB.addAll(firstBeta);
							int newLength = followB.size();
							if(oldLength<newLength) change = true;
						}
						if(isVariable(rightSide[j].charAt(k)+"") && getFirstOfBeta(beta).contains("e")){
							Set<String> followB = followTable.get(rightSide[j].charAt(k)+"");
							int oldLength = followB.size();
							followB.addAll(set);
							int newLength = followB.size();
							if(oldLength<newLength) change = true;
						}
					}
				}
			}
		}
		for(int i =0;i<variables.length;i++){
			Set<String> set= followTable.get(variables[i]);
			List<String> list = new ArrayList<String>(set);
			Collections.sort(list);
			result = result + variables[i]+"/";
			for(String val:list)
				result=result+val;
			result+=";";
		}
		return result.substring(0,result.length()-1);
	}

	public Set<String> getFirstOfBeta(String beta){
		Set<String> firstBeta = new HashSet<String>();
		for(int i =0;i<beta.length();i++){
			Set<String> x = new HashSet<>(firstTable.get(beta.charAt(i)+""));
			firstBeta.addAll(x);
			if(!x.contains("e")) {
				firstBeta.remove("e");
				break;
			}
		}
		return firstBeta;
	}
	public boolean isVariable(String letter){
		for (int j =0;j<variables.length;j++){
			if(variables[j].equals(letter)) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;W;G;A;D;C;P#f;h;l;o;q;s;t#S/A,DPS,DqDDq,qAlS,qDPPo;W/e,lSD,tCShP;G/C,G,S,sDC,sS;A/fPlDf,o;D/PS,WAPs,e,oW,qD;C/G,PDP,PW,W;P/o,q");
		assertEquals("S/$fhloqst;W/floqst;G/floqt;A/$fhloqst;D/floqst;C/floqt;P/floqst", cfgFirstFollow.follow());
	}

}