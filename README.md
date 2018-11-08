Bright’s Kotlin Clean Architecture MVVM/RXAJava2/Dagger2 Tutorial
------------------------------------------------------------

Personal CV Sample app
----------------------

This is a simple application that accesses an API to display:

1) A User's CV with summary information.
2) A list of previous work experiences.
3) When a previous experience has been clicked on, the detail view is shown.

Architecture
------------------
* The Presentation Layer is an MVVM implementation.

* The Application architecture is 'Clean Architecture'
I have decided to go with modules to enforce a separation of concerns using the Clean Architecture approach to enforce
'The Dependency Rule' where dependencies should point inwards. PresentationLayer -> DomainLayer <- Datalayer

By utilizing modules, each module is its own independent unit, and each module has its own:

Unit tests (Runs on local machine)
Unit Instrumented tests (Headless test to test the repository layer with a mockserver)
UI Tests (Instrumented UI tests)



Presentation Layer
------------------

View -  The View does nothing but simply render the view to the user and is made as dumb as possible with little logic

ViewModel - is responsible for fetching data as per the user's request, formatting the data and maintaining state for configuration changes.


Domain Layer
------------------

Is a pure Java implementation, and its responsibility is to perform business logic. Its repository interfaces are implemented by the data layer.
Once business logic has been performed, the response is sent back up the chain to the Presentation layer

Model is an interactor interface that implements the UseCase. This is the interaction that interacts with the business logic of the app and the Data layer of the app.
This is where the data is returned as a stream using Observables.

Data Layer
------------------
This is simply used to obtain the data from a resource via a repository and the implementation could be, Preferences i.e. local storage , it could be from a local DB, cache, Network.
In our case it is network based.


Tests Utils
------------------
Shared module, that is shared by other modules under instrumentation tests. Other modules have included this as a "androidTestCompile project(':testutils')"
so they may refer to this whilst testing.


BuildTypes for testing
------------------
I have a build type 'mockserver' which should be using to run instrumentation tests. This allows the app under test to use the mock server.
The mock server response is a hardcoded json response, which is a copy and paste from the real server response.

The normal debug buildType will hit the server.

I have not configured the release build type, as it's outside the scope of this tutorial ;)


How to Test (Command line or Jenkins)
------------------
1) This is runs all 'Unit Instrumented' (i.e. a headless test) tests within the Data Layer.
./gradlew data:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv -PtestBuildType=mockserver

2) This runs all 'Unit' tests within the Domain Layer.
./gradlew :domain:test

3) This runs 'UI Instrumented' tests within the Presentation Layer against a MOCK server.
./gradlew presentation:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj -PtestBuildType=mockserver

4) This runs ALL 3 of the above in sequence
./gradlew presentation:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj -PtestBuildType=mockserver :domain:test data:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv -PtestBuildType=mockserver

(to test in Android studio, be sure to select the mockserver variant for all modules)

Libraries used
------------------
* Circular progress bar
* Dagger 2 - for dependency injection
* Picasso - for image fetching from network
* Mockito - used for mocking during tests
* Gson - to map Json data to domain objects
* OkHttp3 - used for networking and mock server
* Retrofit - to make networking calls easier, which is a wrapper around okHttp3
* RxJava2 - So the app is reactive/asynchronous  and can make use of streams


![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_1.png)
![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_2.png)
![alt tag](https://github.com/brightsgithub/brights-kotlin-mvvm-tutorial/blob/master/screen_shot_3.png)