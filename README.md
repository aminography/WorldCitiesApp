# World Cities App

This is a sample project which shows how to deal with prefix search for large amount of records, like cities of the world.
To achieve this, I have implemented the [RadixTree](https://en.wikipedia.org/wiki/Radix_tree) data structure with the ability
to retrieve data with `offset` and `limit` used for pagination. To gain more from separation of concerns, the whole app is
organized by Clean Architecture. In this document I will explain some points about the data structure as well as the architecture.

Table of Contents
-----------------

- [Data Structure and Algorithm](#data-structure-and-algorithm)
  - [Why Radix Tree?](#why-radix-tree)
  - [Implementation and Improvements](#implementation-and-improvements)
- [App Architecture](#app-architecture)
  - [Modules and their dependencies](#modules-and-their-dependencies)
  - [Dependency Injection](#dependency-injection)

Data Structure and Algorithm
----------------------------
According to the task description, the algorithm used for filtering items would be critical part of this project. Therefore,
in this section I will elaborate on what approach is taken and how it could be beneficial.

###  Why Radix Tree?
Based on research that I have done, `RadixTree` is the best data structure for indexing a large set of objects with string keys
in order to retrieve them by a prefix search. `RadixTree` or compressed trie is the compact and space-optimized form of prefix tree
which enables us to find all nodes starting with a prefix string by a `O(L + V)` complexity order, where `L` is the length of input
prefix, and `V` stands for number of nodes containing the desired value. In case of this project dataset, the length of keys for
cities are dramatically lower than number of cities, which means that the time complexity of this search is significantly better
than linear search. To know more, please refer to the comments inside the `MinimalRadixTree` class.

###  Implementation and Improvements
The `RadixTree`, as the heart of the algorithm, is minimally implemented in `MinimalRadixTree` class.
Thus, it is only involved basic functionality required for this project such as insert into and search in a tree.
Other operations like delete a node are not implemented.

#### Insert
According to the assignment description, initial loading time of dataset does not matter. Therefore, insertion algorithm is
implemented using a recursive approach.

#### Search
In case of searching the tree, I have used a combination of selective BFS and DFS traversals, which are both implemented
iteratively to reduce the searching time as much as possible. The complexity analysis is available in the code comments.

App Architecture
----------------
The architecture of this application is based on well-known `Clean Architecture` consists of 5 gradle modules, to separate
business rules as much as possible.  
In terms of presentation architecture, common `MVVM` pattern is highly adaptable with the Android development environment
and tools, such as `Jetpack` architectural components, so I have chosen `MVVM` for this aim.

###  Modules and their dependencies
As it was mentioned before, the structure of codebase is consisted of 5 gradle modules, 3 pure `Kotlin` library and 2
`Android` module (plus one module containing shared classes for the unit test).

<br/>

![](/static/modules.png)

#### 1. Scope
`scope` is a pure `Kotlin` module and holds only scope `annotation` classes.

#### 2. Model
`model` is a pure `Kotlin` module and consists of business entities which should be accessible in whole codebase.

#### 3. Domain
`domain` is a pure `Kotlin` module. It provides business logic via use-case classes and defines abstraction of repositories to be
implemented in `data` module. In addition, use-case objects work as a bridge between `app` and `data` modules which is the only
possible way for the `app` to access provided data by the `data` module.

#### 4. Data
`data` is an `Android` library module. It collaborates with the `Android` framework to provide data. All of the concrete classes
are `internal`, so they cannot be exposed to the `app` module. The concrete objects are created by `dagger` and delivered to the
`domain` via the `app` dependency graph which is the reason for dependency between `app` and `data.`

#### 5. App
`app` is an `Android` application module. It presents user interface and builds a dependency graph to flow objects through different
layers of architecture.

###  Dependency Injection
In order to establish `IoC` in architecture design of the project, `dagger2` is used.
