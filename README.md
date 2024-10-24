<h1 align="center">TripGuide_v2</h1>

<p align="center">  
🗡️ TripGuide는 여행의 출발부터 다시 집에 돌아오는 것까지 계획을 세워주는 여행을 계획 쉽고 빠르게 만들 수 있는 애플리케이션 입니다
</p>

<p align="center">
<img src="previews/screenshot.png"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - Navigation: Facilitates screen navigation, complemented by [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.
  - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Constructs REST APIs and facilitates paging network data retrieval.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format reflectionless serialization.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Landscapist Glide](https://github.com/skydoves/landscapist#glide), [animation](https://github.com/skydoves/landscapist#animation), [placeholder](https://github.com/skydoves/landscapist#placeholder): A pluggable, highly optimized Jetpack Compose and Kotlin Multiplatform image loading library that fetches and displays network images with Glide, Coil, and Fresco.

## Technical Contents

If you're interested in learning the tech stacks used to build Pokedex Compose, you can find detailed information in the articles linked below:

- [Shared Element Transition In Jetpack Compose: Provide Enriched User Experiences](https://medium.com/@skydoves/shared-element-transition-in-jetpack-compose-provide-enriched-user-experiences-163d4e435869)


## Architecture
**TripGuide**는 MVVM architecture 와 [Google's official architecture guidance](https://developer.android.com/topic/architecture) 를 따릅니다.

![image](https://github.com/user-attachments/assets/2319b1bc-70be-4f4f-969c-49c540d2fcd5)

해당 아키텍처에서 Domain layer는 Optional로 2개 이상의 Repository를 Combine 해야하거나, 별도의 유효성 검사를 실시해야하는 경우에 사용합니다.

### UI Layer

**TripGuide**는 MVVM architecture에 React적 개념을 적용하여 Data layer로부터 받은 데이터를 ViewModel이 저장하고 있는 `State`로 UI에 내려주고, UI에서 이벤트를 ViewModel에 전달하는 UDF로 상태를 관리합니다.

![mad-arch-ui-udf](https://github.com/user-attachments/assets/7013c714-2c13-4299-9450-5589552b27d1)

### Data Layer

**TripGuide**는 data layer에 Repository pattern을 사용하여 데이터 저장소(로컬 데이터베이스, API)에 대한 접근을 추상화합니다. data layer에 집중된 관심사를 분리하기 위해, 로컬 데이터베이스(Room)와 Repository Interface를 별도의 모듈로 분리하여 관리합니다.


## Open API

**TripGuide**는 한국관광공사의 Open API와 주소 검색을 위한 카카오 주소 검색 API를 사용하고 있습니다.
