package spelling;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		if(word.equals(""))
		{
			return false;
		}
		
	    //TODO: Implement this method.
		TrieNode temp = root;
		String wordAdd = word.toLowerCase();
		
		for(int x = 0; x < wordAdd.length(); x++)
		{
			TrieNode insert = temp.insert(wordAdd.charAt(x));
			if(insert == null) // character already exists
			{
				temp = temp.getChild(wordAdd.charAt(x));
			}
			else
			{
				temp = insert;
			}
		}
		if(temp.endsWord())
		{
			return false;
		}
		
		temp.setEndsWord(true);
		size++;
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method		
	    return this.size;
	}
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String word = s.toLowerCase();
		TrieNode temp = root;
		
		for(int x = 0; x < word.length(); x++)
		{
			temp = temp.getChild(word.charAt(x));
			if(temp == null)
			{
				return false;
			}
		}
		
		return temp.endsWord();
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List<String> completions = new ArrayList<String>();
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 
    	 String word = prefix.toLowerCase();
    	 TrieNode stem = root;
  		
  		for(int x = 0; x < word.length(); x++)
  		{
  			stem = stem.getChild(word.charAt(x));
  			if(stem == null)
  			{
  				return completions;
  			}
  		}
    	 
    	 queue.addLast(stem);
    	 
    	 int cnt = 0;
    	 while(queue.size() != 0 && cnt != numCompletions)
    	 {
    		 TrieNode curr = queue.getFirst();
    		 if(curr.endsWord())
    		 {
    			 System.out.println("Found match: " + curr.getText());
    			 completions.add(curr.getText());
    			 cnt++;
    		 }
    		 for(Character ch: curr.getValidNextCharacters())
    		 {
    			 queue.addLast(curr.getChild(ch));
    		 }
    		 queue.removeFirst();
    	 }
    	 
		return completions;
     }
     
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		if(curr.endsWord())
 		{
 			System.out.print("New word: ");
 		}
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}