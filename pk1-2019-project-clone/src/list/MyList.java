package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyList<E> implements List<E> {

	Node head;
	Node last;
	int size = 0;

	private class Node {
		Node next;
		E elem;

		public Node(E e) {
			elem = e;
		}

		boolean hasNext() {
			return next != null;
		}

		@Override
		public String toString() {
			return " (Node " + elem + ")";
		}
	}

	private class NodeIterator implements Iterator<E> {
		Node current;

		public NodeIterator(MyList<E>.Node head) {
			current = new Node(null);
			current.next = head;
		}

		@Override
		public boolean hasNext() {
			return current.hasNext();
		}

		@Override
		public E next() {
			if (!current.hasNext()) {
				throw new NoSuchElementException();
			}
			current = current.next;
			return current.elem;
		}

	}

	@Override
	public boolean add(E e) {
		Node newNode = new Node(e);
		if (head == null) {
			head = newNode;
			last = head;
		} else {
			if (last == null) {
				throw new IllegalStateException("last was null!");
			}
			last.next = newNode;
			last = newNode;
		}
		size++;
		return true;

	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E e : c) {
			add(e);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void clear() {
		this.head = this.last = null;
		this.size = 0;
	}

	@Override
	public boolean contains(Object o) {
		Node current = head;
		while (current != null) {
			if (current.elem.equals(o))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		Node current = head;
		for (int i = 0; i < index; i++) {
			if (current == null) {
				throw new IndexOutOfBoundsException();
			}
			current = current.next;
		}
		return current.elem;
	}

	@Override
	public int indexOf(Object o) {

		int i = 0;
		Node current = head;
		while (current != null) {
			if (current.elem.equals(o)) {
				return i;
			}
			current = current.next;
			i++;
		}

		return -1;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new NodeIterator(head);
	}

	@Override
	public int lastIndexOf(Object o) {
		int result = -1;
		int i = 0;
		Node current = head;
		while (current != null) {
			if (current.elem.equals(o)) {
				result = i;
			}
			current = current.next;
			i++;
		}
		return result;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		if (this.isEmpty())
			return false;
		if (head.elem.equals(o))
			head = head.next;

		Node current = head;
		while (current.hasNext()) {
			if (current.next.elem.equals(o)) {
				current.next = current.next.next;
			}
			current = current.next;
		}
		return true;
	}

	@Override
	public E remove(int index) {
		if (index >= this.size)
			throw new IndexOutOfBoundsException();
		if (this.isEmpty())
			return null;

		if (index == 0) {
			E obj = head.elem;
			head = head.next;
			return obj;
		}
		Node current = head;
		for (int i = 1; i < index && current.hasNext(); i++) {
			current = current.next;
		}
		E obj = current.next.elem;
		current.next = current.next.next;
		return obj;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Object[] toArray() {
		return toArray(new Object[this.size()]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a == null)
			throw new IllegalStateException("a was null");
		if (a.length < this.size()) {
			a = (T[]) new Object[this.size()];
			System.out.println("Had to create new array");
		}
		Node current = head;
		int i = 0;
		while (current != null) {
			a[i] = (T) current.elem;
			i++;
			current = current.next;
		}
		return a;
	}

}
