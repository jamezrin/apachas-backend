package name.jamezrin.autentia.apachas.service

import jakarta.inject.Singleton
import kotlin.random.Random

@Singleton
class GroupIdService {
    /**
     * Generates a random identifier following this criteria:
     * {noun}-{verb}-{nums}{chars} where
     * - {adjective} is a random adjective in english
     * - {noun} is a random noun in english
     * - {nums} is 5 random ASCII alphanumeric characters
     */
    fun generateNew(): String {
        val adjective = ADJECTIVES[Random.nextInt(ADJECTIVES.size)]
        val noun = NOUNS[Random.nextInt(NOUNS.size)]
        return buildString {
            append(adjective)
            append('-')
            append(noun)
            append('-')
            repeat(5) {
                val char = CHARACTERS[Random.nextInt(CHARACTERS.size)]
                append(char)
            }
        }
    }

    companion object {
        private val stripDashes: (String) -> String = {it.replace("-", "")}

        val ADJECTIVES = arrayOf(
            "devilish",
            "mountainous",
            "drunk",
            "awesome",
            "vast",
            "cut",
            "optimal",
            "wide-eyed",
            "billowy",
            "last",
            "staking",
            "young",
            "tasteful",
            "auspicious",
            "jagged",
            "synonymous",
            "nonchalant",
            "thirsty",
            "dangerous",
            "tart",
            "spiffy",
            "pretty",
            "phobic",
            "wry",
            "addicted",
            "fancy",
            "humdrum",
            "incredible",
            "redundant",
            "grumpy",
            "fast",
            "physical",
            "unfair",
            "noisy",
            "equable",
            "abnormal",
            "horrible",
            "unadvised",
            "cool",
            "aboriginal",
            "modern",
            "tight",
            "gray",
            "eminent",
            "wholesale",
            "healthy",
            "severe",
            "aware",
            "far",
            "odd",
        ).map(stripDashes)

        val NOUNS = arrayOf(
            "goal",
            "apartment",
            "perception",
            "procedure",
            "assumption",
            "complaint",
            "elevator",
            "refrigerator",
            "coffee",
            "outcome",
            "bathroom",
            "pizza",
            "software",
            "friendship",
            "response",
            "guest",
            "instruction",
            "grocery",
            "office",
            "arrival",
            "indication",
            "television",
            "heart",
            "poet",
            "insurance",
            "law",
            "application",
            "security",
            "magazine",
            "entry",
            "extent",
            "airport",
            "advertising",
            "psychology",
            "system",
            "bonus",
            "development",
            "menu",
            "requirement",
            "literature",
            "contribution",
            "situation",
            "editor",
            "shirt",
            "perspective",
            "drawer",
            "property",
            "passenger",
            "potato",
            "reading",
        ).map(stripDashes)

        val CHARACTERS = arrayOf(
            *(48..57).toList().toTypedArray(), /* from 0 to 9 */
            *(65..90).toList().toTypedArray(), /* from a to z */
            *(97..122).toList().toTypedArray(), /* from A to Z */
        ).map(Int::toChar)
    }
}


