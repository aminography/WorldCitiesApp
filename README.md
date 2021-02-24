# World Cities App

This example project demonstrates a clean way to architect an Android application. The explanation will be written in a
[Medium Post](https://medium.com/@aminography/android-app-architecture-in-a-clean-way-91e8b86e4b6f).

This app consists of tree screens with a simple flow of UI. The first screen shows a filterable list of world cities.
The content is retrieved from a json-structured file.

In terms of presentation layer architecture, common `MVVM` pattern is highly adaptable with the Android development environment
and tools, such as `Jetpack` architectural components, so I have chosen `MVVM` for this aim.

<br/>

Table of Contents
-----------------
- [Main Characteristics](#main-characteristics)
- [Architecture Overview](#architecture-overview)
  - [Gradle Modules and their organization](#gradle-modules-and-their-organization)
  - [Architecture Layers](#architecture-layers)
  - [Module Dependencies](#module-dependencies)
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

Architecture Overview
---------------------
The architecture of this application follows the well-known `Clean Architecture` guidelines to make the business rules separated as much as possible.  
It consists of 3 main layers: `Data`, `Domain`, and `Presentation` that will be explained in the following.

<br/>

##  Gradle Modules and their organization
The overall structure of the codebase is organized into 3 categories containing gradle modules.

- `architecture`: modules that establish the `Clean Architecture` layers.
- `common`: modules that provide common components and foundations, to be used by the `architecture` modules.
- `features`: modules that implement separate features of the application.

Regardless of two test-related modules (*i.e.* `sharedTest` and `sharedAndroidTest`), the structure consists of 11 gradle modules. (4 pure `Kotlin` and 7 `Android` module)

In this way, each module has a single responsibility, which increases their cohesion.
In addition, they are decoupled as they know only each other's interface, which increases their maintainability and testability.

<br/>

![](/static/modules.png)

<br/>

##  Architecture Layers
As it is mentioned above, the overall architecture is based on the `Clean Architecture` and it is organized in 3 main layers.

<br/>

![](/static/layers.svg)

<br/>

### Presentation
The presentation layer contains the `app` and features, which are all `Android` application modules.
It presents user interface by navigating between different features provided by feature modules.
It is also worth mentioning that features are implemented in `Dynamic Feature Modules`, which are not `instant` for now and they will be delivered at install time.
It allows us to separate their code and resources from the base `app` module.
In fact, the `app` module aggregates architectural modules for the features, in addition to holding base and navigation-related classes.

### Domain
The domain layer contains business rules and entities, which are pure `Kotlin` modules.
The `domain` module provides the business logic via use-case classes and defines abstraction of repositories to be implemented by the `data` module.
In addition, each use-case object can act as a bridge between `app` and `data` modules.
So, this is the only possible way for the `app` and it descendant features to collaborate with the `data` module.

### Data
As the aim of the data layer is to deal with local or remote sources of data, it needs to interact with the framework.
Therefor, it is an `Android` library module.
The concrete objects are created and delivered to the `domain` by the `dagger`.
It is worth noting that all of the concrete classes in this module are `internal`, so they cannot be exposed to dependant modules, like the `app`.
The only reason for having a dependency between `app` and `data` is accessing to dagger modules and components located in the `data`, which is required for building the dependency graph by the dagger.

<br/>

## Module Dependencies
Descriptions...

<br/>

![](/static/modules.svg)

<br/>

Dependency Injection
--------------------
In order to establish `IoC` in architecture design of the project, `dagger2` is used.
Using scopes, we can manage better the lifetime of provided objects by dagger components.
So, I have defined 3 level scopes for this aim.

**App-level** scope (*i.e.* `AppScope`) is used for the objects whose lifetime is equal to the app.
So, they are instantiated once and used through whole app, like the `applicationContext`.

**Foundation-level** scopes define the lifetime of the foundational objects, like the `Retrofit` object.
They can live as long as the app lives or shorter than that.

**Feature-level** scope (*i.e.* `FeatureScope`) is used for specifying the lifetime of object that should live as long as a feature lives.

<br/>

![](/static/scopes.svg)

<br/>
