package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopic
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowladge
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class GetTopicsOfKnowledgeUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository
    private lateinit var interactor: IGetTopicsOfKnowledgeInteractor
    private val FAKE_ID = 1

    @Before
    fun setup() {
        interactor = GetTopicsOfKnowledgeUseCase(userRepository)

    }

    @Test
    fun should_get_topics_of_knowledge() {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        var topicsList : ArrayList<DomainTopic> = ArrayList()
        topicsList.add(DomainTopic(1, "",""))


        // When
        Mockito.`when`(userRepository.getTopicsOfKnowladge(FAKE_ID))
                .thenReturn(Observable.just(DomainTopicsOfKnowladge(topicsList)))

        val testObserver = interactor.execute().test()

        testObserver.awaitTerminalEvent()

        val onNextEvents = testObserver.values()

        val topics = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        // Then
        Assert.assertTrue(topics.topicsOfKnowladge.size == 1)
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() {
        // GIVEN
        interactor.execute().test()
    }
}