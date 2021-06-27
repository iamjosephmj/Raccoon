![Raccoon Logo](https://github.com/iamjosephmj/Raccoon/blob/main/media/racoon-logo.png)


# Raccoon



<div align="center">
 
 <a href = "https://github.com/iamjosephmj/Raccoon/actions/workflows/android.yml">
      <img src = "https://github.com/iamjosephmj/Raccoon/actions/workflows/android.yml/badge.svg" />
 </a>
   
 <a href = "https://github.com/iamjosephmj/Raccoon/network/">
    <img src = "https://img.shields.io/github/forks/iamjosephmj/Raccoon" />
  </a>
 
  <a href = "https://github.com/iamjosephmj/Raccoon">
    <img src = "https://img.shields.io/github/stars/iamjosephmj/Raccoon" />
 </a>

 <a href = "https://github.com/iamjosephmj/Raccoon/issues">
     <img src = "https://img.shields.io/github/issues/iamjosephmj/Raccoon" />
 </a>  
 
 <a href = "https://github.com/iamjosephmj/Raccoon/blob/master/LICENSE">
     <img src = "https://img.shields.io/github/license/iamjosephmj/Raccoon" />
 </a> 

  <a href = "https://jitpack.io/#iamjosephmj/Raccoon">
     <img src = "https://jitpack.io/v/iamjosephmj/Raccoon.svg" />
  </a>
 
 <a href = "https://twitter.com/iamjosephmj">
     <img src = "https://img.shields.io/twitter/url?label=follow&style=social&url=https%3A%2F%2Ftwitter.com" />
  </a>

</div>

## Medium Articles

Checkout these article to get more insights about this library:

- [How to integrate this in your Android Test](https://iamjosephmj.medium.com/efficient-android-testing-using-raccoon-and-espresso-c890770276a)

## Why Raccoon?

There has been different ways to mock the response from an API call, We do have a lot of good frameworks
for this purpose. But, what if we need to give back a mock response according to the request and its
parameters. That is what this library is all about. Raccoon is an interceptor plugin that will help the
developer to mock a response back according to the request. The library uses Reflection apis under the hood
to do the whole process.

## Insights

The integration of this library is soo much compact in such a way that the developer
doesn't need to take much time for the process. We basically need to make main 3 additions:

- Add the Interceptor Plugin
- Add Service class in the AndroidTest directory
- Add Controller class and the endpoint definition in the AndroidTest directory.

##  Gradle

Add the following to your project's root build.gradle.kts file


```kotlin

allprojects {
  repositories {
	maven ("https://jitpack.io")
  }
}

```

Add the following to your project's build.gradle.kts file

```kotlin

dependencies {
  
  // Other dependencies
  implementation("com.github.iamjosephmj:Raccoon:1.0.5")
     
}

```

## Basic usage

### `Add interceptor plugin`
#### `Retrofit`

Retrofit users need to add the `RaccoonOkHttpPlugin` as the interceptor

```kotlin

  val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RaccoonOkHttpPlugin())
            .build()

  val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .build()

```

Adding this plugin to the real project doesn't hurt until the `RaccoonStub` class is initialized.

### `Add Controller Class`

This is one of the core classes that Raccoon library is looking into. Controller class is the place
were the `Endpoint` definitions are made. Developers can keep the logically related `Endpoints` in
the same `Controller`

```kotlin

@ControllerModule
class MockController : RaccoonController() {

    override fun setup() {
        // Do the DI related stuff
    }

    @RaccoonEndpoint(
        endpoint = "your endpoint",
        responseTime = 100, /* delay in api response delivery */
        RaccoonRequestType.GET /* user can define their request types here */
    )
    fun fetchToDoList(@Params headers: Parameters): RaccoonResponse {
        return </* Your response object */>
        .buildRaccoonResponse(statusCode = 200 /* STATUS code of the api response */)
    }


    override fun tearDown() {
        // clean up memory.
    }
}

```

#### `setUp()`

If user wishes to import some objects via DI, this function will be entry point to do the same.
This function is called before the endpoint endpoint definition is called.

#### `tearDown()`

This function is the best candidate to free up the memory after the endpoint point definition
execution. This function is called after the endpoint endpoint definition is called.

#### `Endpoint Definition function`

The user can define any names to the endpoint definition function. The only requirement that the
library has is that the user should give an `@RaccoonEndpoint` for the same. Take the example above
example to see the annotation in action.

The Endpoint Definition function has a return type `RaccoonResponse`. The developer can either
directly create `RaccoonResponse` object with

```kotlin

    RaccoonResponse(
        statusCode=200,
        body =/* json string */ "{ ... }"
    )

```

or they can use the Moshi/Gson supported pojo objects. Raccoon provides a helper extension
function `buildRaccoonResponse` to do the same

```kotlin

 return </* Your response object */>
        .buildRaccoonResponse(statusCode = 200 /* STATUS code of the api response */)

```

You can also include multiple controllers in single controller class to maintain modularity in
your project.


```kotlin

@ControllerModule(includeControllers = [MockController2::class /* you can add any number of controllers here */])
class MockController : RaccoonController() {

    // your implementation ...
}
```


Take a look at the `androidTest` implementation to get more insights on the same.


### `Add Service Class`

Service class helps the library to create an overall understanding about how to efficiently parse
through controllers to fetch the endpoint.

```kotlin

@RaccoonService
class MockService : RaccoonServiceImpl() {

    @RaccoonController
    fun providesMockController() = MockController::class
}

```

This developer should:

- Extend the `RaccoonServiceImpl` class
- Add `@RaccoonService` as the annotation for the Service class.
- Add `@RaccoonController` as the annotation for mock controller class provider function.

#### `Test class implementation`

```kotlin

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setup() {
       // setup
        RaccoonStub.setUp(
                  RaccoonConfig.Builder()
                      .addService(MockService::class)
                      .addService(MockService2::class)
                      .addService(MockService3::class)
                      .setParserType(GsonPlugin())
                      .build()
              )
        /*
         * The developer can also use {@see MoshiPlugin} as the parserType as per the project
         * requirements
         */
    }

    /**
     * You should not launch the activity before the RaccoonStub initialization.
     * There can be scenarios where the app calls the API on launch. In such cases only launch the
     * Activity inside the test function
     */
    @get:Rule
    val rule = ActivityTestRule(
        MainActivity::class.java, false, false
    )

    @Test
    fun useAppContext() {
        // run your test
    }

    @After
    fun tearDown() {
       // cleans up the memory.
        RaccoonStub.teatDown()
    }

}

```


#### `Raccoon TestRule`

You can define the testRule in this way:
```kotlin
 
// Seup raccoon stub
val raccoonTestRule = RaccoonTestRule {
     RaccoonStub.setUp(
     	RaccoonConfig.Builder()
        	.addService(ServiceClass::class)
                .build()
	)
}
 
// Setup activity testRule 
private val activityTestRule = ActivityTestRule(MainActivity::class.java)

@get:Rule
val chain: RuleChain = RuleChain
	/*
	 * Always give raccoon testrule before the activity testRule, because this can be helpfull
	 * if you app does an API call at the launch of an activity.
         */
	.outerRule(raccoonTestRule)
        .around(activityTestRule)
    
```



## Contribution, Issues or Future Ideas

If part of Raccoon is not working correctly be sure to file a Github issue. In the issue provide as
many details as possible. This could include example code or the exact steps that you did so that
everyone can reproduce the issue. Sample projects are always the best way :). This makes it easy
for our developers or someone from the open-source community to start working!

If you have a feature idea submit an issue with a feature request or submit a pull request and we
will work with you to merge it in!

## Contribution guidelines

Contributions are more than welcome!

- You should make sure that all the tests are working properly.
- You should raise a PR to develop branch
- Before you raise a PR please make sure your code had no issue from Android studio lint analyzer.

## Please Share & Star the repository to keep me motivated.
  <a href = "https://github.com/iamjosephmj/Raccoon">
     <img src = "https://img.shields.io/github/stars/iamjosephmj/Raccoon" />
  </a>
  <a href = "https://twitter.com/iamjosephmj">
     <img src = "https://img.shields.io/twitter/url?label=follow&style=social&url=https%3A%2F%2Ftwitter.com" />
  </a>
