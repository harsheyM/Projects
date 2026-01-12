//Harshitha Mahesh
//dal267662

#ifndef NODE_H
#define NODE_H
#include <string>
#include <sstream>
#include <sstream>
#include <format>
#include <iomanip>

using namespace std;
template <typename T>

class Node {
private:
    T object;

public:
    Node<T>* left;
    Node<T>* right;
    
    //Constructors 
    Node() { object = nullptr; left = nullptr; right = nullptr; }
	Node(T obj) { object = obj; left = nullptr; right = nullptr; }

    //Accessor
    T getObject() { return object; } 
    
    //Mutators
    void setObject(T x) {object = x;}
    void setLeft(Node<T>* n) { left=n; }
    void setRight(Node<T>* n) { right=n; }
    
    //Operators
    bool operator<(const T& other) const { return object < other.object; }
    bool operator>(const T& other) const { return object > other.object; }
    friend ostream& operator<<(ostream& out, const Node<T>& other) { out << other.object; return out; }


    
#endif
};