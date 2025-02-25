package dev.babananick.userstask

import app.cash.turbine.test
import dev.babananick.userstask.data.FakeUserRepository
import dev.babananick.userstask.feature.userlist.UserListViewModel
import dev.babananick.userstask.feature.userlist.model.UserListUiEvent
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserListVMTest {
    private val fakeUserRepository = FakeUserRepository()
    private val userListVM = UserListViewModel(fakeUserRepository)

    @Test
    fun `check initial state and update list`() = runTest {
        userListVM.stateFlow.test {
            val initialState = awaitItem()
            assert(initialState.isLoading)
            assert(initialState.userList.isEmpty())
            userListVM.dispatch(UserListUiEvent.UpdateList)
            val finalState = awaitItem()
            assert(!finalState.isLoading)
            assert(finalState.userList.isNotEmpty())
        }
    }

    @Test
    fun `check data similarity`() = runTest {
        userListVM.stateFlow.test {
            skipItems(1)
            val repositoryData = fakeUserRepository.getSimplifiedUsers().getOrNull()?.data
            assertEquals(repositoryData, awaitItem().userList)
        }
    }

    @Test
    fun `check doubling data in fake repository`() = runTest {
        userListVM.stateFlow.test {
            skipItems(1)
            userListVM.dispatch(UserListUiEvent.UpdateList)
            val repositoryData = fakeUserRepository.getSimplifiedUsers().getOrNull()?.data
            val doubleList = buildList {
                if (repositoryData != null) {
                    addAll(repositoryData)
                    addAll(repositoryData)
                }
            }
            assertEquals(doubleList, awaitItem().userList)
        }
    }
}

