package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
		{
			throw new NullPointerException("Can't add null element!");
		}
		
		// add to empty list
		if(head == null || tail == null)
		{
			LLNode<E> temp = new LLNode<E>(element);
			tail = temp;
			head = temp;
		}
		// element already exists
		else if(tail != null)
		{
			LLNode<E> temp = new LLNode<E>(element);
			tail.next = temp;
			temp.prev = tail;
			tail = temp;
		}
		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index > size-1 || index < 0)
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		
		LLNode<E> temp = head;
		for(int x = 0; x < index; x++)
		{
			temp = temp.next;
		}
		
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		if(element == null)
		{
			throw new NullPointerException("Can't add null element!");
		}
		
		LLNode<E> newElem = new LLNode<E>(element);
		
		// insert at beginning
		if(index == 0)
		{
			// no elements
			if(head == null || tail == null)
			{
				head = newElem;
				tail = newElem;
			}
			else
			{
				head.prev = newElem;
				newElem.next = head;
				head = newElem;
			}
			
		}
		// insert at end
		else if(index == size)
		{
			tail.next = newElem;
			newElem.prev = tail;
			tail = newElem;
		}
		else
		{
			LLNode<E> temp = head;
			for(int x = 0; x < index; x++)
			{
				temp = temp.next;
			}
			
			newElem.next = temp;
			newElem.prev = temp.prev;
			newElem.next.prev = newElem;
			newElem.prev.next = newElem;
		}		
		size++;
		
		return;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// removing first element
		if(index < 0 || index > size-1)
		{
			throw new IndexOutOfBoundsException();
		}
		// removing from empty list
		if(head == null || tail == null)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0)
		{
			E temp = head.data;
			
			if(size == 1)
			{
				head = null;
				tail = null;
			}
			else
			{
				head = head.next;
				head.prev = null;
			}	

			size--;
			
			return temp;
		}
		// removing from end of list
		else if(index == size-1)
		{
			E temp = tail.data;
			if(size == 1)
			{
				head = null;
				tail = null;
			}
			else
			{
				tail = tail.prev;
				tail.next = null;
			}	

			size--;
			return temp;
		}
		else {
			LLNode<E> temp = head;
			for(int x = 0; x < index; x++)
			{
				temp = temp.next;
			}
			E tempData = temp.data;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;

			size--;
			return tempData;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size-1)
		{
			throw new IndexOutOfBoundsException();
		}
		if(element == null)
		{
			throw new NullPointerException();
		}
		
		LLNode<E> temp = head;
		for(int x = 0; x < index; x++)
		{
			temp = temp.next;
		}
		
		E tempData = temp.data;
		temp.data = element;
		
		return tempData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
