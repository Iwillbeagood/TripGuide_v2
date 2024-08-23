package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject
import kotlin.math.sqrt

class InsertTravelByRouteUsecase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    suspend operator fun invoke(travel: Travel) {
        travelRepository.insertTravel(
            travel.copy(
                places = sortPlacesByRoute(travel.startPlace, travel.places)
            )
        )
    }

    private fun sortPlacesByRoute(startPlace: Tourist, places: List<Tourist>) =
        tsp(listOf(startPlace) + places)

    private fun tsp(tourists: List<Tourist>): List<Tourist> {
        val n = tourists.size
        val dist = Array(n) { DoubleArray(n) }

        // 모든 Tourist 객체 간의 거리 계산
        for (i in 0 until n) {
            for (j in 0 until n) {
                dist[i][j] = distance(tourists[i], tourists[j])
            }
        }

        // dp 배열 및 경로 추적을 위한 배열 초기화
        val dp = Array(1 shl n) { DoubleArray(n) { Double.POSITIVE_INFINITY } }
        val parent = Array(1 shl n) { IntArray(n) { -1 } } // 경로 추적용

        dp[1][0] = 0.0  // 시작점에서 시작하는 경로는 0 비용

        // 상태를 순회하며 DP 테이블 업데이트
        for (mask in 1 until (1 shl n)) {
            for (u in 0 until n) {
                if ((mask and (1 shl u)) == 0) continue // 방문하지 않은 좌표 건너뜀
                for (v in 0 until n) {
                    if ((mask and (1 shl v)) != 0) continue // 이미 방문한 좌표 건너뜀
                    val newDist = dp[mask][u] + dist[u][v]
                    if (newDist < dp[mask or (1 shl v)][v]) {
                        dp[mask or (1 shl v)][v] = newDist
                        parent[mask or (1 shl v)][v] = u // 경로 추적을 위한 부모 노드 기록
                    }
                }
            }
        }

        // 시작점으로 돌아오는 경로 계산
        var result = Double.POSITIVE_INFINITY
        var lastNode = -1
        for (i in 1 until n) {
            val cost = dp[(1 shl n) - 1][i] + dist[i][0]
            if (cost < result) {
                result = cost
                lastNode = i
            }
        }

        // 경로를 역추적
        val path = mutableListOf<Int>()
        var mask = (1 shl n) - 1
        var currentNode = lastNode
        while (currentNode != -1) {
            path.add(currentNode)
            val nextNode = parent[mask][currentNode]
            mask = mask and (1 shl currentNode).inv()
            currentNode = nextNode
        }

        path.add(0) // 시작점으로 돌아감
        path.reverse()

        return path.filter { it != 0 }.map { tourists[it] }
    }

    // 좌표 간의 유클리드 거리 계산 함수
    private fun distance(t1: Tourist, t2: Tourist): Double {
        return sqrt((t1.mapX - t2.mapX) * (t1.mapX - t2.mapX) + (t1.mapY - t2.mapY) * (t1.mapY - t2.mapY))
    }

}