# CoinCap

Best practice (Modularization) - built entirely with Jetpack Compose and cutting-edge libraries like
Coroutines, Flow, Hilt, Coil, etc.

This project is using Material 3 components, as well as the Compose navigation library. It also has
an integrated Static Analysis tool (Detekt) and CI/CD pipeline (Github Actions) + web scraping using a python script.

### Libraries and Tools

- Jetpack Compose
- Hilt
- Retrofit (with a deserializer for another API)
- Coroutines
- Coil
- Detekt
- GitHub Actions
- Material 3
- Compose Navigation

#### CI/CD

- I've used GitHub Actions, and Bitrise just to see different results from different platforms, and
  so far, IMO, GitHub Actions is nicer, Bitrise already has everything but I'd like to have some
  flexibility and write some scripts :)

- There was an issue with the previous local rates API, from bonbast.com which I replaced that with
  a new strategy. I've used a new script to scrap the data with Github Actions and store it in a
  file called currencies.json. The script is available in the project.

### Showcase

https://user-images.githubusercontent.com/15968667/210910446-79249585-3c54-451f-a310-b32088a35313.mp4

<p>
<img src="https://i.imgur.com/4hQXpR2.png" height="420" />
<img src="https://i.imgur.com/kZJbtow.png" height="420" />
<img src="https://i.imgur.com/5cGryi7.png" height="420" />
<img src="https://i.imgur.com/wMx9kOL.png" height="420" />
</p>

### Architecture

I've used the best practices from Google's official
repository [NowInAndroid](https://github.com/android/nowinandroid), and used Modularization to make
the app more scalable and maintainable.

### Modules

<img src="https://i.imgur.com/21DcyNY.png" width="400">

### Features

1. The logic behind searching is to get the data only after the last character is entered (using
   `flatMapLatest()` flow operator).
2. For the home screen, I am planning to get some data from the server for showing on the LineChart,
   but for now, I am using dummy data.
3. Unfortunately, the API doesn't provide the data for the `LineChart`, so I am using dummy data for
   now.
4. Iran (my home country) has been facing inflation for the last decade, and surprisingly, because
   of protests and the lack of management, the inflation rate is increasing day by day. So, I took
   the chance and put a logic for showing the rates in different currencies as **Local Currency**,
   with a refresh policy between Saturday to Thursday, and the rates will be updated every 5
   minutes. For Friday, the rates will be retrieved from the previous day.

### Implementation

This section is for those who are interested in the implementation details.

#### Home Screen

I had to combine two flows into one to avoid unnecessary network calls and extra logics inside
views. So here is how I did it:

<img src="https://i.imgur.com/c4rrVzx.jpg" width="520">

The implementation is pretty simple. I am using the `combine` operator to combine the two flows into
one. The `combine` operator will emit a new [Rate] model after multiplying the two values. The logic
and code is available in
the [GetRatesUseCaseImpl](https://github.com/LinX64/CoinCap/blob/master/core/data/src/main/java/com/client/data/repository/foreignRates/RatesRepositoryImpl.kt)
class.

#### API calls and deserialization

I am using Retrofit for making API calls, and I am using a custom deserializer for the local
currency API. The problem that I faced at first was that the original API was not free so I had to
use an already made API instead. Another problem was that the API was returning the data in a
different format, something like this format:

````
{
  "usd": {
    "sell": 43000,
    "buy": 42900
  },
  "eur": {
    "sell": 45870,
    "buy": 45720
  }
}
````

It was pretty hard to deserialize the data, so I had to write a custom deserializer for it. The
deserializer is basically converting all those three objects into a list of [LocalRate] objects.
Here you can find the
deserializer: [CurrencyDeserializer](https://github.com/LinX64/CoinCap/blob/master/core/data/src/main/java/com/client/data/util/CurrencyDeserializer.kt)

I am sure this is one of the major problems that all Android developers might face while
using `GSON` and `Retrofit`, so I hope this will help you.

### API

For this project, I am
using [CoinCap API](https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a) for rates, as
well as
[This API](https://bonbast-api.deta.dev/) for live Iranian exchange rates.

Also, I am using https://coinicons-api.vercel.app/api/icon/ for getting the icons of the coins.

### Continuous Integration

The project is using GitHub Actions to build and test the app on every push and pull request on two
devices. Also, it is using Renovate to keep the dependencies up to date.

### Static Code Analysis

The project is using Detekt for static code analysis, and it is using GitHub Actions to run the
analysis on every push and pull request.

I also used Detekt formatter to format the code automatically which is somehow a wrapper for ktLint
as well.

Please see: https://detekt.dev/docs/rules/formatting

### TODO

- Add Offline first support
- Unit Tests
- Get data from Server for LineChart
- Add more features
