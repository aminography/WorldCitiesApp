# World Cities App

This example project demonstrates a clean way to architect an Android application. The explanation will be written in a
[Medium Post](https://medium.com/@aminography/android-app-architecture-in-a-clean-way-91e8b86e4b6f).

<br/>

Table of Contents
-----------------
- [Main Characteristics](#main-characteristics)
- [App Architecture](#app-architecture)
  - [Gradle Modules and their dependencies](#gradle-modules-and-their-dependencies)
- [Dependency Injection](#dependency-injection)

<br/>

Main Characteristics
--------------------
- Separation of concerns using gradle modularization
- Concurrent programming using Coroutines
- Dependency Injection using Dagger2
- Single Activity pattern using Jetpack Navigation Component
- Feature independence using Instant Dynamic Feature Modules
- Reactive programming using Flow and LiveData
- Layered architecture using Clean Architecture
- Lifecycle-aware presentation architecture using MVVM
- Indexing large amount of records using RadixTree

<br/>

App Architecture
----------------
The architecture of this application is based on well-known `Clean Architecture` consists of 3 main layers, to make
business rules separated as much as possible.  
In terms of presentation layer architecture, common `MVVM` pattern is highly adaptable with the Android development environment
and tools, such as `Jetpack` architectural components, so I have chosen `MVVM` for this aim.

###  Layers
Descriptions...

<br/>

![](/static/layers.svg)

<br/>

###  Gradle Modules and their dependencies
As it was mentioned before, the structure of codebase is consisted of 11 gradle modules, 4 pure `Kotlin` library and 7
`Android` module (plus two module containing shared classes for unit testing).

###  Module categories:

<br/>

![](/static/modules.png)

<br/>

### Module dependencies:
<br/>

![](/static/modules.svg)

<br/>

### 1. common

#### 1.1. scope
`scope` is a pure `Kotlin` module and holds only scope `annotation` classes.

#### 1.2. coroutine

#### 1.3. appCore

#### 1.4. navigation

<br/>

### 2. architecture

#### 2.1. App
`app` is an `Android` application module. It presents user interface and builds a dependency graph to flow objects through different
layers of architecture.

#### 2.2. Domain
`domain` is a pure `Kotlin` module. It provides business logic via use-case classes and defines abstraction of repositories to be
implemented in `data` module. In addition, use-case objects work as a bridge between `app` and `data` modules which is the only
possible way for the `app` to access provided data by the `data` module.

#### 2.3. Model
`model` is a pure `Kotlin` module and consists of business entities which should be accessible in whole codebase.

#### 2.4. Data
`data` is an `Android` library module. It collaborates with the `Android` framework to provide data. All of the concrete classes
are `internal`, so they cannot be exposed to the `app` module. The concrete objects are created by `dagger` and delivered to the
`domain` via the `app` dependency graph which is the reason for dependency between `app` and `data.`

<br/>

### 3. feature

#### 3.1. cityList

#### 3.2. mapViewer

#### 3.3. userList

<br/>

Dependency Injection
--------------------
In order to establish `IoC` in architecture design of the project, `dagger2` is used.

<br/>

![](/static/scopes.svg)

<br/>
