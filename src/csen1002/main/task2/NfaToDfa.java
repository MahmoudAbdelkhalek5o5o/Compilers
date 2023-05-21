package csen1002.main.task2;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mahmoud Ahmed Abdelkhaleq
 * @id 46-18954
 * @labNumber 17
 */


class StringComparator  implements Comparator<String> {
	public int compare(String o1, String o2) {
		String [] x = o1.split("/");
		String [] y = o2.split("/");
		for(int i =0;i<Math.min(x.length, y.length);i++){
			if(Integer.parseInt(x[i])<Integer.parseInt(y[i])) return -1;
			if(Integer.parseInt(x[i])>Integer.parseInt(y[i])) return 1;
		}
		if(x.length<y.length) return -1;
		if(x.length> y.length) return 1;
		return 0;
	}
}
public class NfaToDfa {

	String [] NFAStates;
	ArrayList<String> states;
	String alphabet;
	String [] NFATransitions;
	ArrayList<String []> DFATransitions;
	int stateCount;
	String [] acceptStates;
	ArrayList<String> DFAAcceptStates;
	String initialState;

	HashMap<String,ArrayList<String>> statesTable;
	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {
		String [] data = input.split("#");
		NFAStates = data[0].split(";");
		states = new ArrayList<String>();
		alphabet = data[1].replaceAll(";","");
		NFATransitions = data[2].split(";");
		DFATransitions = new ArrayList<String []>();
		initialState = data[3];
		acceptStates = data[4].split(";");
		DFAAcceptStates = new ArrayList<String>();
		statesTable = new HashMap<String, ArrayList<String>>();
		for(int i = 0;i<NFAStates.length;i++){
			ArrayList<String> closure = new ArrayList<String>();
			closure.add(NFAStates[i]);
			statesTable.put(NFAStates[i],closure);
		}
		// construct epsilon-closure table

		while(true){
			boolean noChange = true;
			for(int i =0;i<NFAStates.length;i++){
				ArrayList<String> reachable = statesTable.get(NFAStates[i]);
				for(int j =0;j<NFATransitions.length;j++){
					String [] transition = NFATransitions[j].split(",");
					if(transition[1].equals("e") && reachable.contains(transition[0]) && !reachable.contains(transition[2])){
						reachable.add(transition[2]);
						noChange = false;
					}
				}
			}
			if(noChange){
				break;
			}
		}
		for(int i =0;i<NFAStates.length;i++){
			ArrayList<String> reachable = statesTable.get(NFAStates[i]);
			Collections.sort(reachable, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					int x = Integer.parseInt(o1);
					int y = Integer.parseInt(o2);
					if(x>y) return 1;
					return -1;
				}
			});
		}

		// construct states

		Queue<String> DFAStates = new LinkedList<String>();
		ArrayList <String> initialStateReachability = statesTable.get(initialState);
		String firstState = "";
		for(int i =0;i<initialStateReachability.size();i++){
			firstState = firstState+initialStateReachability.get(i)+"/";
		}
		firstState = firstState.substring(0,firstState.length()-1);
		DFAStates.add(firstState);
		states.add(firstState);
		if(containsAcceptState(firstState,acceptStates)){
			DFAAcceptStates.add(firstState);
		}
		initialState = firstState;
		while(!DFAStates.isEmpty()){
			String stateString = DFAStates.poll();
			String [] state = stateString.split("/");
			for(int i =0;i<alphabet.length();i++){
				String newState = findStates(state,alphabet.charAt(i)+"", NFATransitions, statesTable);
				String [] transition = {stateString,alphabet.charAt(i)+"",newState};
				DFATransitions.add(transition);
				if(!stateAdded(states, newState)){
					DFAStates.add(newState);
					states.add(newState);
					if(containsAcceptState(newState,acceptStates)){
						DFAAcceptStates.add((newState));
					}
				}
			}
		}
		Collections.sort(DFATransitions, new Comparator<String[]>() {
			public int compare(String[] frst, String[] scnd) {
				int x = (new StringComparator()).compare(frst[0],scnd[0]);
				if(x!=0) return x;
				else{
					int y = frst[1].compareTo(scnd[1]);
					if(y!=0) return y;
					else{
						return (new StringComparator()).compare(frst[2],scnd[2]);
					}
				}
			}
		});
	}
	public static boolean stateAdded(ArrayList<String> DFAStates, String state){
		for (String item: DFAStates) {
			if(item.equals(state))return true;
		}
		return false;
	}
	public static String findStates(String [] states, String letter, String [] NFATransitions, HashMap<String, ArrayList<String>> statesTable){
		String result="";
		Set <String> newStates= new HashSet<String>();
		for(int i =0;i<NFATransitions.length;i++){
			String [] transition = NFATransitions[i].split(",");
			if(transition[1].equals(letter) && arrContains(states,transition[0])){
				ArrayList<String> reachableStates = statesTable.get(transition[2]);
				for(int j =0;j<reachableStates.size();j++)
					newStates.add(reachableStates.get(j));
			}
		}
		Object [] arrResult = newStates.toArray();
		Arrays.sort(arrResult, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				int x = Integer.parseInt((String)o1);
				int y = Integer.parseInt((String)o2);
				if(x>y) return 1;
				return -1;
			}
		});
		for(int i =0;i<arrResult.length;i++){
			result = result+arrResult[i]+"/";
		}
		if(result.equals("")) return "-1";
		return result.substring(0,result.length()-1);
	}
	public static boolean arrContains(String [] array, String val){
		for(int i =0;i<array.length;i++){
			if(array[i].equals(val)) return true;
		}
		return false;
	}
	public static boolean containsAcceptState(String newState, String [] acceptStates){
		String [] arrayState = newState.split("/");
		for(int i =0;i<acceptStates.length;i++){
			if(arrContains(arrayState,acceptStates[i])){
				return true;
			}
		}
		return false;
	}
	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Collections.sort(states, new StringComparator());
		String result = "";
		for(int i =0;i<states.size();i++){
			result = result+states.get(i)+";";
		}
		result = result.substring(0,result.length()-1);
		result+="#";
		for(int i =0;i<alphabet.length();i++){
			result=result+alphabet.charAt(i)+";";
		}
		result = result.substring(0,result.length()-1);
		result+="#";
		for(int i =0;i<DFATransitions.size();i++){
			String [] transition = DFATransitions.get(i);
			result = result + transition[0] + "," + transition[1] + "," + transition[2] + ";";
		}
		result = result.substring(0,result.length()-1);
		result+="#";
		result+=initialState;
		result+="#";
		Collections.sort(DFAAcceptStates, new StringComparator());
		for(int i =0;i<DFAAcceptStates.size();i++){
			result+=DFAAcceptStates.get(i);
			result+=";";
		}
		result = result.substring(0,result.length()-1);
		return result;
	}
	public static void main (String [] x){
		NfaToDfa nfaToDfa= new NfaToDfa("0;1;2;3;4;5;6;7;8;9;10;11#k;n;w#0,w,1;1,n,2;2,e,0;2,e,4;3,e,0;3,e,4;4,e,11;5,e,6;6,e,7;6,e,9;7,k,8;8,e,7;8,e,9;9,e,11;10,e,3;10,e,5#10#11");
		System.out.println(nfaToDfa);
	}
}
