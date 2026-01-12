//Harshitha Mahesh
//dal267662

#ifndef BINTREE_H
#define BINTREE_H

#include "Node.h"
#include <string>
#include <sstream>
#include <vector>
#include <format>
#include <iomanip>

using namespace std;
template <typename T>

class BinTree {
private:
	Node<T>* copyHelper(const Node<T>* other);
	Node<T>* insertHelper(Node<T>* ptr, Node<T>* object);
	bool searchHelper(Node<T>* ptr, T x);
	void inOrderHelper(Node<T>* ptr, vector<T>& objectPointers);
	Node<T>* deleteNodeRecursive(Node<T>* ptr, T value);
	Node<T>* findMin(Node<T>* node);


public:

	Node<T>* root;

	//Constructors
	BinTree() {
		root = nullptr;
	}
	BinTree(Node<T>* r) {
		root = r;
	}

	//Copy Constructor
	BinTree(const BinTree<T>& other);

	void insert(T obj);

	bool search(T x);

	void remove(T x);

	vector<T> inOrderTraversal();
};
#endif

template <typename T>
BinTree<T>::BinTree(const BinTree<T>& other) {
	root = copyHelper(other.root);
}

template <typename T>
Node<T>* BinTree<T>::copyHelper(const Node<T>* other) {
	//End of the tree
	if (other == nullptr) {
		return nullptr;
	}

	//Create new node
	Node<T>* newNode = new Node<T>;
	//Copy the data
	newNode->setObject(other->getObject());

	//Copy left
	newNode->left = copyHelper(other->left);
	//Copy right
	newNode->right = copyHelper(other->right);

	return newNode;
}


template <typename T>
void BinTree<T>::insert(T object) {
	Node<T>* n = new Node<T>(object);
	//If there is nothing in the tree
	if (root == nullptr) {
		root = n;
	}
	else {
		insertHelper(root, n);
	}
}

template <typename T>
Node<T>* BinTree<T>::insertHelper(Node<T>* ptr, Node<T>* object) {
	if (ptr == nullptr) {
		return object;
	}
	//Check left node
	if (object->getObject() < ptr->getObject()) {
		ptr->left = insertHelper(ptr->left, object);
		//Check right node
	} else if (object->getObject() > ptr->getObject()) {
		ptr->right = insertHelper(ptr->right, object);
		//If they're the same, combine the nodes
	} else {
		ptr->setObject(ptr->getObject() + object->getObject());
	}

	return ptr;
}

template <typename T>
bool BinTree<T>::search(T x) {
	return searchHelper(root, x);
}

template <typename T>
bool BinTree<T>::searchHelper(Node<T>* ptr, T x) {
	//If tree is empty or value not found
	if (ptr == nullptr) {
		return nullptr;
	}

	//Value found at the current node
	if (ptr->getObject() == x) {
		return ptr;
	}

	//Traverse/search in the right or left subtree
	if (x < ptr->getObject()) {
		//Left
		return search(ptr->left, x);
	} else {
		//Right
		return search(ptr->right, x);
	}
}

template <typename T>
void BinTree<T>::remove(T x) {
	root = deleteNodeRecursive(root, x);
}

template <typename T>
//Helper function for finding in-order successor when there are two kids
Node<T>* BinTree<T>::findMin(Node<T>* node) {
	while (node->left != nullptr) {
		node = node->left;
	}
	return node;
}

template <typename T>
Node<T>* BinTree<T>::deleteNodeRecursive(Node<T>* ptr, T value) {
	//Empty tree
	if (ptr == nullptr) {
		return nullptr;
	}

	if (value < ptr->getObject()) {
		ptr->left = deleteNodeRecursive(ptr->left, value);
	} else if (value > ptr->getObject()) {
		ptr->right = deleteNodeRecursive(ptr->right, value);
	} else {
		// Node to be deleted found
		//If no children
		if (ptr->left == nullptr && ptr->right == nullptr) {
			delete ptr;
			ptr = nullptr;
		}
		//if one child
		else if (ptr->left == nullptr) {
			Node<T>* temp = ptr;
			ptr = ptr->right;
			delete temp;
		} else if (ptr->right == nullptr) {
			Node<T>* temp = ptr;
			ptr = ptr->left;
			delete temp;
		}
		//If two children
		else {
			Node<T>* successor = findMin(ptr->right);
			ptr->setObject(successor->getObject());
			ptr->right = deleteNodeRecursive(ptr->right, successor->getObject());
		}
	}
	return ptr;
}

template <typename T>
vector<T> BinTree<T>::inOrderTraversal() {
	Node<T>* ptr = root;

	vector<T> objectPointers;

	//If there is nothing in the tree
	if (root == nullptr) {
		return objectPointers;
	}

	inOrderHelper(ptr, objectPointers);

	return objectPointers;
}

template <typename T>
void BinTree<T>::inOrderHelper(Node<T>* ptr, vector<T>& objectPointers) {
	if (!ptr) return;

	//Traverse the left subtree.
	inOrderHelper(ptr->left, objectPointers);

	//Add the current node's data to the vector
	objectPointers.push_back(ptr->getObject());

	//Traverse the right subtree.
	inOrderHelper(ptr->right, objectPointers);
}