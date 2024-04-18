package com.rizwan.cinehub.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rizwan.cinehub.domain.Result
import com.rizwan.cinehub.domain.entities.MovieContent
import com.rizwan.cinehub.domain.usecases.GetMoviesListUseCase
import com.rizwan.cinehub.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * MoviesViewModel helps to get the list of movies and list of search result.
 *
 * @param getMoviesListUseCase - will pass get movies list request to repository using @see[GetMoviesListUseCase]
 * @param searchMoviesUseCase - this use-case will help to return the list of search result @see[SearchMoviesUseCase]
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ContainerHost<MoviesState, MoviesListSideEffects>, ViewModel() {

    override val container = container<MoviesState, MoviesListSideEffects>(
        MoviesState()
    )

    private val _mutableList: MutableList<MovieContent> = mutableListOf()

    init {
        loadNextPage()
    }

    fun retry() {
        loadNextPage()
    }

    fun loadNextPage() = intent {
        Log.d("Rizwan", "loadNextPage called Page: ${state.page + 1}")

        getMoviesListUseCase.getMoviesList(page = state.page + 1).collect {
            when (it) {
                is Result.Success -> {

                    _mutableList.addAll(it.data.contentList)
                    delay(200)
                    reduce {
                        state.copy(
                            status = UiStatus.LOADED,
                            page = it.data.pageNum.toInt(),
                            title = it.data.title,
                            contentList = _mutableList
                        )
                    }
                }

                is Result.Error -> {
                    // not handle error case for now
                }
                else -> {
                    //do nothing
                }
            }
        }
    }

    fun searchIconClicked(isEnabled: Boolean) = intent {
        reduce {
            state.copy(
                isSearchActivated = isEnabled,
                title = "",
                page = 0,
                contentList = _mutableList
            )
        }
        _mutableList.clear()
        if (isEnabled.not()) {
            loadNextPage()
        }
    }

    fun performSearch(searchedText: String) = intent {
        Log.d("search", " viewModel")

        MutableStateFlow(searchedText).debounce(300).filter {
            if (it.isNotEmpty()) {
                return@filter true
            } else {
                _mutableList.addAll(listOf<MovieContent>().toMutableList())
                reduce {
                    state.copy(contentList = listOf<MovieContent>().toMutableList())
                }
                return@filter false
            }
        }.distinctUntilChanged().flatMapLatest {
            searchMoviesUseCase.performSearch(searchedText = it)
        }.flowOn(Dispatchers.Default).collect {
            when (it) {
                is Result.Success -> {
                    _mutableList.addAll(it.data.toMutableList())
                    reduce {
                        state.copy(contentList = it.data.toMutableList())
                    }
                }
                is Result.Error -> {
                    Log.d("search", " search error")
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

}