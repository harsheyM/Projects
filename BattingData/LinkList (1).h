//Harshitha Mahesh
//dal267662

#ifndef LINKLIST_H
#define LINKLIST_H
#include "Node.h"
#include <string>
#include <sstream>
#include <sstream>
#include <format>
#include <iomanip>
#include <vector>

using namespace std;
template <typename T>


class LinkList {
private:
	Node<T>* head;

public:
	LinkList() {
		head = nullptr;
	}

    LinkList(Node<T>* h) {
        head = h;
    }

	//Insert method
	void insert(T obj) {

		Node<T>* newNode = new Node<T>(obj);

//If there's nothing in list, the new node is at the head
		if (head == nullptr) {
			head = newNode;
			return;
		}
		Node<T>* current = head;
		//otherwise the new node is added at the end
		while (current->next != nullptr) {
			current = current->next;
		}
		current->next = newNode;
	}


//Delete method
	T remove(T obj) {
		Node<T>* current = head;

		//If list is empty
		if (head == nullptr) {
			return head->object;
			//If node to be deleted is at the beginning of the list
		} else if (obj == head->object) {
			head = head->next;
			current->next = nullptr;
			T data = current->object;
			delete current;
			return data;
			//If node is anywhere else in the list
		} else {
			while (current->next->object != obj) {
				current = current->next;
			}
			
			Node<T>* hold = current->next;
			current->next  = current->next->next;
			T data = hold->object;
			delete hold;
			return data;

		}
		return head->object;
	}
//search method
	bool search(T obj) {
		Node<T>* current = head;
		//goes through list
		while (current != nullptr) {
			//if the node is what we're looking for
			if (current->object == obj) {
				return true;
			}
			current = current->next;
		}
		return false;
	}
//sort method
	void bubbleSort() {
		//The list is empty or has one node, so no need to sort!
		if (!head || !head->next) {
			return;
		}

		bool swapped;
		Node<T>* firstNode;
		Node<T>* lastNode = nullptr; // Last sorted element

		do {
			//checks if the linkedlist is sorted
			swapped = false;
			firstNode = head;

			while (firstNode->next != lastNode) {
				if (firstNode->object > firstNode->next->object) {
					// Swap the <T> object
					T temp = firstNode->object;
					firstNode->object = firstNode->next->object;
					firstNode->next->object = temp;
					swapped = true;
				}
				firstNode = firstNode->next;
			}
			//Mark the last element as sorted
			lastNode = firstNode;
		} while (swapped);
	}
//Convert linked list to vector
vector<T> listToArray() {
    vector<T> arr;
    Node<T>* curr = head;

    while (curr != nullptr) {
        arr.push_back(curr->object);
        curr = curr->next;
    }

    return arr;
}
//toString method
	string toString() {
		ostringstream out;
		Node<T>* curr = head;
		if (head == nullptr) {
			out << "";
			return out.str();
		}
		else {
			return toStringHelper(curr, "");
		}
	}
//Recursive method
	string toStringHelper(Node<T>* curr, string out) {
		if (curr == nullptr) {
			return out;
		}
		else {
			out += curr->object.toString() + "\n";
			return toStringHelper(curr->next, out);
		}
	}

#endif
};