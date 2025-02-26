package dev.babananick.userstask

import app.cash.turbine.test
import dev.babananick.userstask.data.FakeUserRepository
import dev.babananick.userstask.feature.userlist.UserListViewModel
import dev.babananick.userstask.feature.userlist.model.UserListUiEvent
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserListVMTest {
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var userListVM: UserListViewModel

    @Before
    fun setUp(){
        fakeUserRepository = FakeUserRepository()
        userListVM = UserListViewModel(fakeUserRepository)
    }

    @Test
    fun `check initial state and update list`() = runTest {
        userListVM.stateFlow.test {
            val initialState = awaitItem()
            assert(initialState.isLoading)
            assert(initialState.userList.isEmpty())
        }
        userListVM.dispatch(UserListUiEvent.UpdateList)
        userListVM.stateFlow.test {
            val finalState = awaitItem()
            assert(!finalState.isLoading)
            assert(finalState.userList.isNotEmpty())
        }
    }

    @Test
    fun `check if loading on retry`() = runTest {
        userListVM.dispatch(UserListUiEvent.Retry)
        userListVM.stateFlow.test {
            assertEquals(true, awaitItem().isLoading)
        }
    }

    @Test
    fun `check data similarity`() = runTest {
        val repositoryData = fakeUserRepository.getSimplifiedUsers().getOrNull()?.data
        userListVM.stateFlow.test {
            skipItems(1)
            assertEquals(repositoryData, awaitItem().userList)
        }
    }

    @Test
    fun `check doubling data in fake repository`() = runTest {
        val repositoryData = fakeUserRepository.getSimplifiedUsers().getOrNull()?.data
        val doubleList = buildList {
            if (repositoryData != null) {
                addAll(repositoryData)
                addAll(repositoryData)
            }
        }
        userListVM.dispatch(UserListUiEvent.UpdateList)
        userListVM.stateFlow.test {
            skipItems(1)
            assertEquals(doubleList, awaitItem().userList)
        }
    }
}

