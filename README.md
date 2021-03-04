# Android app architecture in a clean way

This example project demonstrates a clean way to architect an Android application.
Further details will be explained in a [Medium Post](https://medium.com/@aminography/android-app-architecture-in-a-clean-way-91e8b86e4b6f).

This app consists of three screens with a simple flow of UI:
- A filterable list of world cities
- Viewing a city on map
- Exploring github users of the city

<br/>

#### :warning: **DISCLAIMER:**

In case of large-scale project, for example containing dozens of features, the exact architecture of this project might not be efficient.
It would be better to apply `Clean Architecture` to each feature independently, which leads to higher levels of cohesion and shorter compile-time.

<br/>

Table of Contents
-----------------
- [Main Characteristics](#main-characteristics)
- [Architecture Overview](#architecture-overview)
  - [Gradle Modules](#gradle-modules)
  - [Architecture Layers](#architecture-layers)
  - [Module Dependencies](#module-dependencies)
- [Dependency Injection](#dependency-injection)
- [Third-Party Libraries](#third-party-libraries)
- [License](#license)

<br/>

Main Characteristics
--------------------
- Layered architecture => `Clean Architecture`
- Separation of concerns (`SoC`) => `Gradle` modularization
- Lifecycle-aware presentation architecture => `MVVM` pattern
- Single `Activity` pattern => `Jetpack Navigation Component`
- Feature independence => `Dynamic Feature Module`
- Dependency injection => `Dagger2`
- Concurrent programming => `Coroutines`
- Reactive programming => `Flow` and `LiveData`
- Indexing large amount of records (~210k) for retrieving in few milliseconds => `RadixTree`
- Pagination => `Paging3`

<br/>

Architecture Overview
---------------------
The architecture of the app follows the well-known `Clean Architecture` guidelines to make the business rules as separated as possible.
It consists of 3 main layers: `Data`, `Domain`, and `Presentation` that will be explained in the following.

<br/>

##  Gradle Modules
The overall structure of the codebase is organized into 3 categories of gradle modules.

- `architecture`: modules that establish the `Clean Architecture` layers.
- `common`: modules that provide common components and foundations for `architecture` modules.
- `features`: modules that implement separate features of the application.

Regardless of two test-related modules (*i.e.* `sharedTest` and `sharedAndroidTest`), there are 11 gradle modules. (4 pure `Kotlin` and 7 `Android` module)

Each module follows single responsibility principle, results in higher cohesion.
In addition, modules are decoupled, so they know only each other's interface, which increases their maintainability and testability.

<br/>

![](/static/modules.png)

<br/>

##  Architecture Layers
The overall architecture is organized into 3 main layers:

<br/>

![](/static/layers.svg)

<br/>

### 1. Presentation
The presentation layer contains the `app` and features, which are all `Android` application modules.
It presents user interface by navigating between different features provided by feature modules.
Features are implemented in `Dynamic Feature Modules` and they will be delivered at install time.
It allows us to separate their code and resources from the base `app` module.
In fact, the `app` module aggregates architectural modules for the features, in addition holds base and navigation-related classes.

### 2. Domain
The domain layer contains business rules and entities, which are pure `Kotlin` modules.
The `domain` module provides the business logic via use-case classes and defines abstraction of repositories to be implemented by the `data` module.
In addition, each use-case object can act as a bridge between `app` and `data` modules.
So, this is the only possible way for the `app` and it descendant features to collaborate with the `data` module.

### 3. Data
As the aim of the data layer is to deal with local or remote sources of data, it needs to interact with the framework.
Therefor, it is an `Android` library module.
The concrete objects are created and delivered to the `domain` by the `dagger`.
It is worth noting that all of the concrete classes in this module are `internal`, so they cannot be exposed to dependant modules, like the `app`.

- The only reason for having a dependency between `app` and `data` is accessing to dagger modules and components located in the `data`, which is required for building the dependency graph by the dagger.

<br/>

## Module Dependencies
The diagram below shows the dependency graph of gradle modules.

<br/>

![](/static/modules.svg)

<br/>

Dependency Injection
--------------------
`dagger2` is a powerful tool to establish Inversion of control (`IoC`) in the architecture design.
Using scopes, we can manage the lifetime of objects provided by dagger components.
In this project, scopes are categorized into 3 levels:

- **App-level** scope (*i.e.* `AppScope`) is used for the objects whose lifetime is equal to the app.
So, they are instantiated once and used through whole app, like the `applicationContext`.

- **Foundation-level** scopes define the lifetime of the foundational objects, like the `Retrofit` object.
They can live as long as the app lives or shorter than that.

- **Feature-level** scope (*i.e.* `FeatureScope`) is used for specifying the lifetime of object that should live as long as a feature lives.

<br/>

![](/static/scopes.svg)

<br/>

Third-Party Libraries
-------------------
- [Dagger2](https://dagger.dev)
- [Navigation Component](https://developer.android.com/guide/navigation)
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [Retrofit](https://square.github.io/retrofit)
- [Gson](https://github.com/google/gson)
- [RadixTree](https://github.com/aminography/RadixTree)
- [Glide](https://bumptech.github.io/glide)
- [Google Maps](https://developers.google.com/maps/documentation/android-sdk/overview)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide)
- [MockK](https://mockk.io)

License
--------
```
Copyright 2021 Amin Hassani.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```