package com.bayuokta.core.data.source.remote

import android.util.Log
import com.bayuokta.core.data.source.remote.network.ApiResponse
import com.bayuokta.core.data.source.remote.network.ApiService
import com.bayuokta.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<ResultsItem>>> {
       // get data from remote api
        return flow {
            try {
                val response = apiService.getMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.d("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

