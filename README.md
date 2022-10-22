# Endeavour Simple App
Start a sample kotlin app to apply endeavour company
---

## Requirement
- Build a simple application support showing and toggle favourite products with **MVVM** architecture.

## Exercise
- The application has 3 screens
    - **HomePage** allows user can fetch the latest products from API and show this list to user.
        - User can toggle Favourite icon to enable/disable favourite this item.
        - User can click to the card item to view the details of products .
    
    - **Favourite** allows user can view the favourite product only.
        - User can toggle Favourite icon to enable/disable favourite of this item.
      
    - **ProductDetail** allows user to view the detail of product.
        - User can toggle Favourite icon to enable/disable favourite of this item.
      

## Technology stack details
|Technology|Motivation|
|------------------|-----------|
|Reactive Programming|[`Coroutines`](https://developer.android.com/kotlin/coroutines) is the official design pattern created by Google, [`Flow`](https://developer.android.com/kotlin/flow) lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services|
|Networking|[`Retrofit`](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java|
|Data Storage|[`Room`](https://developer.android.com/training/data-storage/room) is a persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite|
|Dependency Injector|[`Hilt`](https://developer.android.com/training/dependency-injection/hilt-android) A latest dependency injector for Kotlin.|
|Animation|[`lottie-android`](https://github.com/airbnb/lottie-android) An Android library to natively render After Effects vector animations|
|Coding Standard|default Coding of Kotlin[`Kotlin`](https://kotlinlang.org/docs/reference/coding-conventions.html)|
|Code Structure|[`Clean Architecture`](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) is a architecture that easy to maintain and upgrade in future|


## How to build the app multiple environments
1. dev: Product Flavor **dev**
3. Production: Product Flavor **production**

## How to run Unit test
1. call **testDevDebugUnitTestCoverage** in **Endeavour/Tasks/reporting**
2. check the result in **PROJECT_DIR/app\build\reports\jacoco\testDevDebugUnitTestCoverage\html\index.html**