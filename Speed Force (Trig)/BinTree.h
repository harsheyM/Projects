//Harshitha Mahesh
//dal267662

#ifndef BINTREE_H
#define BINTREE_H

#include "Node.h"
#include <vector>

template<typename T>
class BinTree {
public:
	Node<T>* root;

	BinTree() : root(nullptr) {}

	~BinTree() {
		clear(root);
	}

	void insert(const T& val) {
		root = insertRec(root, val);
	}

	void inorderCollect(std::vector<T>& v) const {
		inorder(root, v);
	}

	bool search(T x) {
		return searchHelper(root, x);
	}


private:

	Node<T>* insertRec(Node<T>* node, const T& data);

	void inorder(Node<T>* node, std::vector<T>& v) const;

	void clear(Node<T>* node);
	
	bool searchHelper(Node<T>* ptr, T x);


};

#endif

template <typename T>
Node<T>* BinTree<T>::insertRec(Node<T>* node, const T& data) {
	if (!node) {
		return new Node<T>(data);
	}
	if (node->object.sameExponent(data)) {
		node->object.coeff = node->object.coeff + data.coeff;
		return node;
	}
	if (data < node->object) {
		node->left = insertRec(node->left, data);
	}
	else {
		node->right = insertRec(node->right, data);
	}
	return node;
}

template <typename T>
void BinTree<T>::inorder(Node<T>* node, std::vector<T>& v) const {
	if (!node) {
		return;
	}
	inorder(node->right, v);
	v.push_back(node->object);
	inorder(node->left, v);
}

template <typename T>
void BinTree<T>::clear(Node<T>* node) {
	if (!node) {
		return;
	}
	clear(node->left);
	clear(node->right);
	delete node;
}

template <typename T>
bool BinTree<T>::searchHelper(Node<T>* ptr, T x) {
	//If tree is empty or value not found
	if (ptr == nullptr) {
		return nullptr;
	}

	//Value found at the current node
	if (ptr->object == x) {
		return ptr;
	}

	//Traverse/search in the right or left subtree
	if (x < ptr->object) {
		//Left
		return search(ptr->left, x);
	} else {
		//Right
		return search(ptr->right, x);
	}
}
