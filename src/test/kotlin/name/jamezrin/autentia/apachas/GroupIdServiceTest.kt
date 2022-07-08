package name.jamezrin.autentia.apachas

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.service.GroupIdService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest

@MicronautTest
class GroupIdServiceTest {
    @Inject
    lateinit var groupIdService: GroupIdService

    @RepeatedTest(200)
    fun testGenerate() {
        val randomId = groupIdService.generateNew()
        val failureMessage = "random id is $randomId";

        assertNotNull(randomId)
        assertTrue(randomId.isNotEmpty())

        val parts = randomId.split("-")

        assertEquals(3, parts.size, failureMessage)

        assertTrue(GroupIdService.ADJECTIVES.contains(parts[0]), failureMessage)
        assertTrue(GroupIdService.NOUNS.contains(parts[1]), failureMessage)

        for (character in parts[2]) {
            assertTrue(GroupIdService.CHARACTERS.contains(character), failureMessage)
        }
    }
}
