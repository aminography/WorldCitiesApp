# World Cities App

This is a sample project which shows how to deal with prefix search for large amount od records, such as cities of the world.
To achieve this, I have implemented the [Radix Tree](https://en.wikipedia.org/wiki/Radix_tree) data structure and used it in
an application with a Clean Architecture organization. In this document I will explain the points about the data structure
and architecture.

Table of Contents
-----------------

- [Data Structure and Algorithm](#data-structure-and-algorithm)
  - [Why Radix Tree?](#why-radix-tree)
  - [Improvements](#improvements)
- [App Architecture](#app-architecture)
  - [Modules and their dependencies](#modules-and-their-dependencies)

Data Structure and Algorithm
----------------------------
description

###  Why Radix Tree?
description

###  Improvements
description

App Architecture
----------------
The architecture of this application is based on well-known `Clean Architecture` consisted of 5 gradle modules, to separate
business rules as much as possible.  
In terms of of presentation architecture, common `MVVM` pattern is highly adaptable with
the Android development environment, such as `Jetpack` architectural components, so I have chosen `MVVM` for this aim.

###  Modules and their dependencies
As I have mentioned, the structure of codebase is consisted of 5 gradle modules, 3 `Java` library and 2 `Android` module.

![](/static/modules.png)
