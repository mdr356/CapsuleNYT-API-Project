# CapsureNYT-API-Project

## Introduction

This project makes an API call to New Times Movie Critics api, and get all Critics and display in a
GridView Layout. Upon a user clicking on an item in the GridView, they navigate to the next screen,
proviing more details about a particular critic.

The Architecture for this project is base on MVVM
- Every view, fragment, has a viewmodel assigned to it.
- Repository is used to make API call
- Using Retrofit libary for out REST api
- Using Gson for our data deserialization
- Using Coroutine for Asynchronous processes, like making http calls which should be done in the background,
uses coroutine API.
- This application is using dependency injection, this helps promotes reusability, testability and maintainability.
- for logging purposes we are using a library called Timber.
- We have a few test to verify our viewmodel and repository.
- Loading of Images are done with Picasso.

