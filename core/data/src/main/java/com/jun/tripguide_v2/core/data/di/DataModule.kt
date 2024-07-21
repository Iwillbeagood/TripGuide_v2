package com.jun.tripguide_v2.core.data.di

import com.jun.tripguide_v2.core.data.repository.airplaneapi.AirplaneScheduleRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoKeywordRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.kakao.KakaoRouteRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.room.RouteRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.room.TravelRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.AreaCodeRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.CommonInfoRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.DetailIntroRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.FestivalRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.KeywordRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.LocationTouristRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.StayRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.tourapi.TouristsRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.trainapi.TrainInfoRepositoryImpl
import com.jun.tripguide_v2.core.data.repository.trainapi.TrainStationRepositoryImpl
import com.jun.tripguide_v2.core.data_api.repository.airplaneapi.AirplaneScheduleRepository
import com.jun.tripguide_v2.core.data_api.repository.kakao.KakaoKeywordRepository
import com.jun.tripguide_v2.core.data_api.repository.kakao.KakaoRouteRepository
import com.jun.tripguide_v2.core.data_api.repository.room.RouteRepository
import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.AreaCodeRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.CommonInfoRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.DetailIntroRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.FestivalRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.KeywordRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.LocationTouristRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.StayRepository
import com.jun.tripguide_v2.core.data_api.repository.tourapi.TouristsRepository
import com.jun.tripguide_v2.core.data_api.repository.trainapi.TrainInfoRepository
import com.jun.tripguide_v2.core.data_api.repository.trainapi.TrainStationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsAreaCodeRepository(
        repository: AreaCodeRepositoryImpl
    ): AreaCodeRepository

    @Binds
    abstract fun bindsAreaBaseListRepository(
        repository: TouristsRepositoryImpl
    ): TouristsRepository

    @Binds
    abstract fun bindsKeywordRepository(
        repository: KeywordRepositoryImpl
    ): KeywordRepository

    @Binds
    abstract fun bindsCommonInfoRepository(
        repository: CommonInfoRepositoryImpl
    ): CommonInfoRepository

    @Binds
    abstract fun bindsDetailIntroRepository(
        repository: DetailIntroRepositoryImpl
    ): DetailIntroRepository

    @Binds
    abstract fun bindsLocationTouristRepository(
        repository: LocationTouristRepositoryImpl
    ): LocationTouristRepository

    @Binds
    abstract fun bindsTravelRepository(
        travelRepository: TravelRepositoryImpl
    ): TravelRepository

    @Binds
    abstract fun bindsRouteRepository(
        routeRepository: RouteRepositoryImpl
    ): RouteRepository

    @Binds
    abstract fun bindsFestivalRepository(
        festivalRepository: FestivalRepositoryImpl
    ): FestivalRepository

    @Binds
    abstract fun bindsStayRepository(
        stayRepository: StayRepositoryImpl
    ): StayRepository

    @Binds
    abstract fun bindsKakaoLocalKeywordRepository(
        kakaoLocalKeywordRepository: KakaoKeywordRepositoryImpl
    ): KakaoKeywordRepository

    @Binds
    abstract fun bindsKakaoRouteRepository(
        kakaoRouteRepository: KakaoRouteRepositoryImpl
    ): KakaoRouteRepository

    @Binds
    abstract fun bindsAirplaneScheduleRepository(
        airplaneScheduleRepository: AirplaneScheduleRepositoryImpl
    ): AirplaneScheduleRepository

    @Binds
    abstract fun bindsTrainStationRepository(
        trainStationRepository: TrainStationRepositoryImpl
    ): TrainStationRepository

    @Binds
    abstract fun bindsTrainInfoRepository(
        trainInfoRepository: TrainInfoRepositoryImpl
    ): TrainInfoRepository
}