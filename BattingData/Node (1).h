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

public:
    T object;
    Node<T>* next;
    
    //Overloaded constructor
	Node(T obj)
	{
		object = obj;
		next = nullptr;
	}

    string toString() const{ return object.toString(); }

    bool operator<(const T& other) const { return object < other.object; }
    
#endif
};