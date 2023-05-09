/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			shortList.remove(10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		String r1 = shortList.remove(0);
		assertEquals("Check remove: removing from beginning", "A", r1);
		int r2 = longerList.remove(longerList.size()-1);
		assertEquals("Check remove: removing from end", (Integer)9, (Integer)r2);
		int r3 = longerList.remove(4);
		assertEquals("Check remove: removing from middle", (Integer)4, (Integer)r3);
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		shortList.add("C");
		assertEquals("Add end: Check shortlist new size", 3, shortList.size());
		assertEquals("Add end: Check shortlist new element", "C", shortList.get(2));
		
		longerList.add(10);
		assertEquals("Add end: Check longerlist new size", 11, longerList.size());
		assertEquals("Add end: check longerlist new element", (Integer)10, longerList.get(10));
		
		list1.add(30);
		assertEquals("Add end: Check list1 new size", 4, list1.size());
		assertEquals("Add end: Check list1 new element", (Integer)30, list1.get(3));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		int s1 = shortList.size();
		int s2 = emptyList.size();
		int s3 = longerList.size();
		int s4 = list1.size();
		
		assertEquals("Size: check shortList size", 2, s1);
		assertEquals("Size: check emptyList size", 0, s2);
		assertEquals("Size: check longerList size", 10, s3);
		assertEquals("Size: check list1 size", 3, s4);
	}
	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test	
		try {
			shortList.add(0, null);
			fail("Null element not allowed!");
		}
		catch (NullPointerException e) {
		}
		try {
			shortList.add(-1, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			shortList.add(7, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		shortList.add(0, "1");
		assertEquals("Add at index: check head shortlist element", "1", shortList.get(0));
		shortList.add(3, "D");
		assertEquals("Add at index: check tail shortlist element", "D", shortList.get(3));
		shortList.add(1, "Z");
		assertEquals("Add at index: check middle shortlist element", "Z", shortList.get(1));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			shortList.set(-1, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			shortList.set(20, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			longerList.set(0, null);
			fail("Null element");
		}
		catch (NullPointerException e) {
		}
		try {
			emptyList.set(0, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		int r1 = list1.set(0, 30);
		assertEquals("Set at index: setting beginning", (Integer)list1.get(0), (Integer)30);
		assertEquals("Set at index: checking original beginning", (Integer)65, (Integer)r1);
	
		int r2 = list1.set(2, 50);
		assertEquals("Set at index: setting end", (Integer)list1.get(2), (Integer)50);
		assertEquals("Set at index: checking original end", (Integer)42, (Integer)r2);
	}
	
	
	// TODO: Optionally add more test methods.
	
}
