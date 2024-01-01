package com.jun.tripguide_v2.core.domain.usecase.route

class GetPath(
    private val n: Int,
    private val graph: Array<IntArray>
) {

    private val inf = 987654321
    private val prev = Array(16) { IntArray(1 shl 16) { -1 } }
    private val dp = Array(16) { IntArray(1 shl 16) { -1 } }

    init {
        dfs(0, 1, (1 shl n) - 1)
    }

    private fun dfs(node: Int, state: Int, endState: Int): Int {
        if (state == endState) {
            return if (graph[node][0] != 0) {
                graph[node][0]
            } else {
                inf
            }
        }
        if (dp[node][state] != -1) return dp[node][state]

        dp[node][state] = inf
        for (i in 0 until n) {
            if (state and (1 shl i) != 0 || graph[node][i] == 0) continue
            val cost = graph[node][i] + dfs(i, state or (1 shl i), endState)
            if (cost < dp[node][state]) {
                dp[node][state] = cost
                prev[node][state] = i
            }
        }
        return dp[node][state]
    }

    fun getPath(node: Int, state: Int): List<Int> {
        if (state == (1 shl n) - 1) {
            return listOf(node, 0)
        }
        val nextNode = prev[node][state]
        return if (nextNode != -1) {
            listOf(node) + getPath(nextNode, state or (1 shl nextNode))
        } else {
            listOf(node)
        }
    }
}
