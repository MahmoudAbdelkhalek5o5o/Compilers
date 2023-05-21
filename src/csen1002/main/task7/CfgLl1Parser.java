package csen1002.main.task7;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Write your info here
 * 
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */

public class CfgLl1Parser {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param //cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	String [] variables;
	String [] terminals;
	HashMap<String, ArrayList<String>> rules;
	HashMap<String, ArrayList<String>> firstTable;
	HashMap<String, ArrayList<String>> followTable;

	ArrayList<String> [] [] parsingTable;
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		String [] data = input.split("#");
		variables = data[0].split(";");
		terminals = data[1].split(";");
		rules = new HashMap<String, ArrayList<String>>();
		firstTable = new HashMap<String, ArrayList<String>>();
		followTable = new HashMap<String, ArrayList<String>>();
		String [] rulesData = data[2].split(";");
		String [] firstData = data[3].split(";");
		String [] followData = data[4].split(";");
		for(int i =0;i<rulesData.length;i++){
			String var = rulesData[i].split("/")[0];
			ArrayList<String> ruless = new ArrayList<String>(Arrays.asList(rulesData[i].split("/")[1].split(",")));
			rules.put(var,ruless);
		}
		for(int i =0;i<firstData.length;i++){
			String var = firstData[i].split("/")[0];
			ArrayList<String> firsts = new ArrayList<String>(Arrays.asList(firstData[i].split("/")[1].split(",")));
			for(int j =0;j<firsts.size();j++){
				if(firsts.get(j).length()>1){
					String s = firsts.get(j);
					firsts.remove(j--);
					for(int k =0;k<s.length();k++){
						firsts.add((s.charAt(k)+""));
					}
				}
			}
			firstTable.put(var,firsts);
		}
		for(int i =0;i<followData.length;i++){
			String var = followData[i].split("/")[0];
			ArrayList<String> follows = new ArrayList<String>(Arrays.asList(followData[i].split("/")[1].split(",")));
			for(int j =0;j<follows.size();j++){
				if(follows.get(j).length()>1){
					String s = follows.get(j);
					follows.remove(j--);
					for(int k =0;k<s.length();k++){
						follows.add((s.charAt(k)+""));
					}
				}
			}
			followTable.put(var,follows);
		}
		parsingTable = new ArrayList[variables.length][terminals.length+1];
		for(int i =0;i< parsingTable.length;i++){
			for(int j = 0;j< parsingTable[0].length;j++){
				ArrayList<String> rules;
				if(j == terminals.length)
					rules = findRules(variables[i],"$");
				else
					rules = findRules(variables[i],terminals[j]);
				parsingTable[i][j] = rules;
			}
		}
	}

	private ArrayList<String> findRules(String var, String terminal){
		ArrayList<String> findRules = new ArrayList<String>();
		ArrayList<String> theRules = rules.get(var);
		for(int i =0;i<theRules.size();i++){
			String rightSide = theRules.get(i);
			if(rightSide.equals("e")){
				if(followTable.get(var).contains(terminal)){
					findRules.add("e");
				}
			}
			else{
				if(condition1(rightSide,terminal)){
					findRules.add(rightSide);
				} else if (condition2(rightSide, terminal, var)) {
					findRules.add(rightSide);
				}
			}
		}
		return findRules;
	}

	private Set<String> findFirst(String term){
		Set<String> firsts = new HashSet<String>();
		for(int j = 0;j<term.length();j++){
			firsts.remove("e");
			if(isTerminal(term.charAt(j)+"")){
				firsts.add((term.charAt(j)+""));
				break;
			}
			firsts.addAll(firstTable.get(term.charAt(j)+""));
			if(!firsts.contains("e"))break;
		}
		return firsts;
	}

	private boolean condition1(String rightSide, String terminal){
		ArrayList<String> firsts = new ArrayList<String>(findFirst(rightSide));
		for(int j =0;j<firsts.size();j++){
			if(firsts.get(j).contains(terminal)){
				return true;
			}
		}
		return false;
	}
	private boolean condition2(String rightSide, String terminal, String var){
		ArrayList<String> firsts = new ArrayList<String>(findFirst(rightSide));
		if(firsts.contains("e") || rightSide.equals("e")){
			if(followTable.get(var).contains(terminal)){
				return true;
			}
		}
		return false;
	}

	private boolean isTerminal(String value){
		for(int i = 0;i< terminals.length;i++){
			if(terminals[i].equals(value)) return true;
		}
		return false;
	}
	private boolean isVariable(String value){
		for(int i = 0;i< variables.length;i++){
			if(variables[i].equals(value)) return true;
		}
		return false;
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		// TODO Auto-generated method stub
		Stack<String> stack = new Stack<>();
		stack.push("S");
		int index = 0;
		String derivation = "S;";
		String stringSoFar = "S";
		while(!stack.isEmpty()){
			if(isTerminal(stack.peek())){
				if(stack.peek().equals(input.charAt(index)+"")) {
					stack.pop();
					index++;
					continue;
				}
				else{
					derivation = derivation + "ERROR;";
					break;
				}
			}
			else{
				String var = stack.pop();
				if(index>=input.length()){
					if(rules.get(var).contains("e")){
						stringSoFar = replaceFirst(stringSoFar,var,"");
						derivation = derivation + stringSoFar+";";
						continue;
					}
					else{
						derivation = derivation + "ERROR;";
						break;
					}
				}
				ArrayList<String> cellRules = parsingTable[findVarIndex(var)][findTerminalIndex(input.charAt(index)+"")];
				if(cellRules.isEmpty()){
//					if(rules.get(var).contains("e")){
//						stringSoFar = replaceFirst(stringSoFar,var,"");
//						derivation = derivation + stringSoFar+";";
//						continue;
//					}
//					else {
						derivation = derivation + "ERROR;";
						break;
//					}
				}
				else{
					String rule = cellRules.get(0);
					if(rule.equals("e")) rule = "";
					for(int j = rule.length()-1;j>-1;j--){
						stack.push(rule.charAt(j)+"");
					}
					stringSoFar = replaceFirst(stringSoFar, var, rule);
				}
			}
			derivation = derivation+stringSoFar+";";
		}
		if(index==input.length())
			return derivation.substring(0,derivation.length()-1);
		else
			if(stack.isEmpty()){
				if(derivation.substring(derivation.length()-6,derivation.length()-1).equals("ERROR"))
					return derivation.substring(0,derivation.length()-1);
				return derivation+"ERROR";
			}
			else
				return derivation.substring(0,derivation.length()-1);

	}

	private String replaceFirst(String s, String var, String rule){
		boolean find = false;
		String tmp = "";
		for(int k=0;k<s.length();k++){
			if(var.equals(s.charAt(k)+"") && !find){
				tmp = tmp + rule;
				find = true;
			}
			else
				tmp = tmp + s.charAt(k);
		}
		return tmp;
	}
	private int findVarIndex(String var){
		for (int i =0;i<variables.length;i++){
			if(var.equals(variables[i])) return i;
		}
		return 0;
	}
	private int findTerminalIndex(String terminal){
		for (int i =0;i<terminals.length;i++){
			if(terminal.equals(terminals[i])) return i;
		}
		return 0;
	}

	public static void main(String[] args) {
		CfgLl1Parser cfgLl1Parser = new CfgLl1Parser("S;F;G;T;X#g;l;o;p;u;y;z#S/FFTz,yS;F/lG,gF;G/yX,uS,e;T/oXTz,g,pSpG,e;X/pSlS,zS#S/gl,y;F/l,g;G/y,u,e;T/o,g,p,e;X/p,z#S/$glopz;F/gl;G/glz;T/z;X/glopz");
		assertEquals("S;yS;yyS;yyFFTz;yylGFTz;yylFTz;yyllGTz;yyllTz;yyllz;ERROR", cfgLl1Parser.parse("yyllzozl"));
	}

}
