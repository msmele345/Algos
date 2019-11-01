package com.algos.mitch.redis

import com.algos.mitch.algo_store.AlgorithmResponse
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CacheRepository : CrudRepository<AlgorithmResponse, String>
