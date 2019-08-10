Bright’s Kotlin Clean Architecture MVVM/Coroutines/Dagger2 Tutorial
------------------------------------------------------------

Libraries used
------------------
* **Kotlin Coroutines** - So the app is can perform background tasks, using async/await pattern
* **Kotlin Broadcast Channels** - To broadcast data to receiving coroutines
* **Room** - used for offline mode implementation.
* Circular progress bar
* Dagger 2 - for dependency injection
* Picasso - for image fetching from network
* Mockito - used for mocking during tests
* Gson - to map Json data to domain objects
* OkHttp3 - used for networking and mock server
* Retrofit - to make networking calls easier, which is a wrapper around okHttp3


Personal CV Sample app
----------------------

This is a simple application that accesses an API to display:

1) A User's CV with summary information.
2) A list of previous work experiences.
3) When a previous experience has been clicked on, the detail view is shown.
4) **Offline mode supported**

Architecture
------------------
* The Presentation Layer is an MVVM implementation.

* The Application architecture is 'Clean Architecture'
I have decided to go with modules to enforce a separation of concerns using the Clean Architecture approach to enforce
'The Dependency Rule' where dependencies should point inwards. **PresentationLayer -> DomainLayer <- Datalayer**

By utilizing modules, each module is its own independent unit, and each module has its own:

Unit tests (Runs on local machine)
Unit Instrumented tests (Headless test to test the repository layer with a mockserver)
UI Tests (Instrumented UI tests)



Presentation Layer
------------------

**View** -  The View does nothing but simply render the view to the user and is made as dumb as possible with little logic

**ViewModel** - is responsible for fetching data as per the user's request, formatting the data and maintaining state for configuration changes.


Domain Layer
------------------

Is a pure Kotlin implementation, and its responsibility is to perform business logic. Its repository interfaces are implemented by the data layer.
Once business logic has been performed, the response is sent back up the chain to the Presentation layer

Model is an interactor interface that implements the **UseCase**. This is the interaction that interacts with the business logic of the app.
This is where the data is returned as a
```python
// Either we have a success or failure within this pair
Pair<DomainObj, Exception>
```
using **Kotlin Coroutines**.


Data Layer
------------------
This is simply used to obtain the data from a resource via a **repository** and the implementation is an API server request and a local Room DB to support offline mode
which caches requests made to the server.


Tests Utils
------------------
Shared module, that is shared by other modules under instrumentation tests. Other modules have included this as a "androidTestCompile project(':testutils')"
so they may refer to this whilst testing.


---


## Running the app (variants & flavors)

* **prodDebug** - variant will run against the production environment (which hits the real server)
* **mockServerDebug** - variant will run against the local OkHttpMockServer (which intercepts requests and returns hardcoded Json responses)

* ***release** I have not configured the release build type, as it's outside the scope of this tutorial ;)



How to Test (Command line or Jenkins)
------------------
**1) Data Layer tests:**

```sh
# This runs all 'Unit Instrumented' (i.e. a headless test on the device) against MockServer  **(Recommended)**
./gradlew data:connectedMockServerDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj.data
```

```sh
# Run against Prod (Same as above but hits the real server)
./gradlew data:connectedProdDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj.data
```

**2) Domain Layer tests:**

```sh
# Run local unit tests
./gradlew :domain:test
```

**3) Presentation Layer tests:**

```sh
#  'UI Instrumented' tests against MockServer **(Recommended)**:
./gradlew presentation:connectedMockServerDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj
```

```sh
# Run against Prod (Same as above but hits the real server)
./gradlew presentation:connectedProdDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj
```

```sh
# Run local JUnit tests
./gradlew :presentation:test
```

(to test in Android studio, be sure to select the **mockServerDebug** variant for all modules)




![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_1.png)
![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_2.png)
![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_3.png)
![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_4.png)
![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_5.png)