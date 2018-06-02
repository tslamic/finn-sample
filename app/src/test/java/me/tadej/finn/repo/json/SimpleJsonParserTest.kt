package me.tadej.finn.repo.json

import com.google.common.truth.Truth.assertThat
import me.tadej.finn.JsonResources
import me.tadej.finn.repo.AdFactory
import me.tadej.finn.repo.factory.SimpleAdFactory
import org.junit.Test

class SimpleJsonParserTest {
  private val factory: AdFactory = SimpleAdFactory
  private val parser = SimpleJsonParser(factory)

  @Test
  fun parse_withNull_assertEmptyList() {
    val ads = parser.parse(null)
    assertThat(ads).isEmpty()
  }

  @Test
  fun parse_withEmptyString_assertEmptyList() {
    val ads = parser.parse("")
    assertThat(ads).isEmpty()
  }

  @Test
  fun parse_withPartialJsonFile_assertSize() {
    val json = JsonResources.PARTIAL.asJson()
    val ads = parser.parse(json)
    assertThat(ads).hasSize(2)
  }

  @Test
  fun parse_withFullJsonFile_assertSize() {
    val json = JsonResources.FULL.asJson()
    val ads = parser.parse(json)
    assertThat(ads).hasSize(1000)
  }

  @Test
  fun parse_withCorruptedJson_assertSize() {
    val json = JsonResources.CORRUPTED.asJson()
    val ads = parser.parse(json)
    assertThat(ads).hasSize(1)
  }

  @Test
  fun parse_withPartialJsonFile_assertAd() {
    val json = JsonResources.PARTIAL.asJson()
    val ads = parser.parse(json)
    val ad = ads[0]

    assertThat(ad.id()).isEqualTo("105424045")
    assertThat(ad.imageUrl()).isEqualTo("2017/9/vertical-5/30/5/105/424/_1263219766.jpg")
    assertThat(ad.price()).isEqualTo(300)
    assertThat(ad.location()).isEqualTo("Laksevåg")
    assertThat(ad.description()).isEqualTo("Legemidler og bruken av dem")
  }
}
