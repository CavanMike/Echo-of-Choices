package com.andriod.echoofchoices.screen.game

/**
 * Represents a single scene in the visual novel.
 * Each scene has a background, optional character, dialog, and navigation options.
 */
data class GameScene(
    val id: String,
    val backgroundType: BackgroundType,
    val characterName: String? = null,          // null = no character box shown
    val characterImageRes: Int? = null,         // e.g. R.drawable.char_doctor
    val characterSide: CharacterSide = CharacterSide.LEFT,
    val dialogText: String,
    val speakerLabel: String? = null,           // label above dialog box
    val navigation: SceneNavigation
)

enum class BackgroundType {
    OFFICE,
    HOSPITAL,
    BLACK_SCREEN,
    NEIGHBOURHOOD,
    DASHBOARD_RETURN,
    PARK_PATH,
    HOUSE
}

enum class CharacterSide { LEFT, RIGHT }

/**
 * Describes how the player advances from this scene.
 */
sealed class SceneNavigation {
    /** Tap "Next" to go to a single next scene */
    data class Next(val nextId: String) : SceneNavigation()
    /** Player picks between two choices */
    data class Choice(val option1: ChoiceOption, val option2: ChoiceOption) : SceneNavigation()
    /** Game over / return to dashboard */
    object End : SceneNavigation()
}

data class ChoiceOption(
    val label: String,
    val nextId: String,
    val timeCost: Int = 0          // days subtracted from MC's time-left counter
)
