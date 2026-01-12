//Harshitha Mahesh
//dal267662

#ifndef NODE_H
#define NODE_H

#include <iostream>

template<typename T>
class Node {
public:
    T object;
    Node* left;
    Node* right;

    Node(const T& obj) : object(obj), left(nullptr), right(nullptr) {}

    friend std::ostream& operator<<(std::ostream& out, const Node<T>& n) {
        out << n.object;
        return out;
    }

    bool operator<(const Node<T>& other) const { return object < other.object; }
    bool operator>(const Node<T>& other) const { return object > other.object; }
};

#endif
