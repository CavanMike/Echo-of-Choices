package com.andriod.echoofchoices.screen.game

import com.andriod.echoofchoices.R

/**
 * Builds and returns every scene in the game.
 * [username] is injected so dialog can address the MC by name.
 */
object SceneRepository {

    fun buildScenes(username: String): Map<String, GameScene> {
        val scenes = mutableListOf<GameScene>()

        // ── SCENE 1: Office – working at the desk ─────────────────────────────
        scenes += GameScene(
            id = "office_work",
            backgroundType = BackgroundType.OFFICE,
            characterName = null,
            dialogText = "*Deep sigh*... Man, when is this endless pile of crap actually gonna end? " +
                    "If I keep going at this pace, " +
                    "I’m literally gonna be stuck here until next week.$username.",
            speakerLabel = username,
            navigation = SceneNavigation.Next("office_work2")
        )

        scenes += GameScene(
            id = "office_work2",
            backgroundType = BackgroundType.OFFICE,
            characterName = null,
            dialogText = "*Deep breath*... I got this. It's nothing like I've done before,  $username.",
            speakerLabel = username,
            navigation = SceneNavigation.Next("office_collapse")
        )

        // ── SCENE 2: Office – MC collapses ────────────────────────────────────
        scenes += GameScene(
            id = "office_collapse",
            backgroundType = BackgroundType.OFFICE,
            characterName = null,
            dialogText = "Suddenly, the room just completely spins. Everything goes dark around the edges," +
                    " your knees buckle, and you just collapse straight onto the floor...\";",
            speakerLabel = "Narrator",
            navigation = SceneNavigation.Next("hospital_wake")
        )

        // ── SCENE 3: Hospital – waking up ─────────────────────────────────────
        scenes += GameScene(
            id = "hospital_wake",
            backgroundType = BackgroundType.HOSPITAL,
            characterName = null,
            dialogText = "You wake to the hum of machines and the sting of antiseptic. " +
                    "White ceiling tiles. The steady blip of a heart monitor.",
            speakerLabel = "Narrator",
            navigation = SceneNavigation.Next("hospital_doctor")
        )

        // ── SCENE 4: Hospital – doctor delivers news ──────────────────────────
        scenes += GameScene(
            id = "hospital_doctor",
            backgroundType = BackgroundType.HOSPITAL,
            characterName = "Doctor",
            characterImageRes = R.drawable.char_doctor,   // ← your doctor image
            characterSide = CharacterSide.LEFT,
            dialogText = "\"$username… Please, take a seat... I just got your pathology reports back." +
                    " I'm afraid the news isn't what we hoped for." +
                    " The biopsy confirmed that you have a tumor, specifically a very rare form of lung cancer. " +
                    "I know this is incredibly heavy to hear, but we are going to walk through the next steps together."+
                    "We can manage your comfort here, but… " +
                    "you don't have much time left.\"",
            speakerLabel = "Dr. Reyes",
            navigation = SceneNavigation.Choice(
                option1 = ChoiceOption(
                    label = "Stay in the Hospital",
                    nextId = "hospital_stay",
                    timeCost = 30
                ),
                option2 = ChoiceOption(
                    label = "Go Back Home",
                    nextId = "hospital_leave",
                    timeCost = 50
                )
            )
        )

        // ── BRANCH A: Stay in hospital ────────────────────────────────────────
        scenes += GameScene(
            id = "hospital_stay",
            backgroundType = BackgroundType.BLACK_SCREEN,
            characterName = null,
            dialogText = "… Months pass. The treatments are gruelling, " +
                    "but against all odds the scans clear.\n\n" +
                    "🎉  Hooray — you have beaten the cancer!\n\n" +
                    "Yet as you sit alone in the discharge room, " +
                    "an unexpected hollowness settles in your chest. " +
                    "You are alive… but something still feels empty.",
            speakerLabel = "Narrator",
            navigation = SceneNavigation.End   // → return to dashboard
        )

        // ── BRANCH B: Go home – arrive at neighbourhood ───────────────────────

        scenes += GameScene(
            id = "hospital_leave",
            backgroundType = BackgroundType.HOSPITAL,
            characterName = null,
            dialogText = "Thank you, doctor... truly. But if my time is running out anyway," +
                    " I don't want to spend what's left of it stuck in a hospital bed staring at machines.",
            speakerLabel = username,
            navigation = SceneNavigation.Next("neighbourhood_arrive")
        )
        scenes += GameScene(
            id = "neighbourhood_arrive",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            dialogText = "The old streets look smaller than you remember. " +
                    "The old oak tree at the corner is still there. " +
                    "The smell of the ocean drifts from a nearby window.",
            speakerLabel = "Narrator",
            navigation = SceneNavigation.Next("neighbourhood_charA_hello")
        )

        // ── Character A spots MC ──────────────────────────────────────────────
        scenes += GameScene(
            id = "neighbourhood_charA_hello",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = "Character A",
            characterImageRes = R.drawable.char_alex,     // ← your Alex image
            characterSide = CharacterSide.LEFT,
            dialogText = "\"Hey — is that you, $username?! " +
                    "It's been a while! Where have you been all this time?\"",
            speakerLabel = "Alex",
            navigation = SceneNavigation.Next("neighbourhood_mc_busy")
        )

        // ── MC explains they were helping father's business ───────────────────
        scenes += GameScene(
            id = "neighbourhood_mc_busy",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            dialogText = "\"Yeah, it's me. hows it been man how are you.\"",
            speakerLabel = username,
            navigation = SceneNavigation.Next("neighbourhood_chat")
        )

        scenes += GameScene(
            id = "neighbourhood_chat",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = "Character A",
            characterImageRes = R.drawable.char_alex,     // ← your Alex image
            characterSide = CharacterSide.LEFT,
            dialogText = "\"well you stuff happened , been busy with basketball we just won " +
                    "agings a neighbouring teams last week so how about you .\"",
            speakerLabel = username,
            navigation = SceneNavigation.Next("neighbourhood_chat2")
        )
        scenes += GameScene(
            id = "neighbourhood_chat2",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            dialogText = "\" you know boring stuff just kept on working busy with meeting and stuff like that .\"",
            speakerLabel = username,
            navigation = SceneNavigation.Next("neighbourhood_charA_why")
        )



        // ── Character A asks why they came back ───────────────────────────────
        scenes += GameScene(
            id = "neighbourhood_charA_why",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = "Character A",
            characterImageRes = R.drawable.char_alex,     // ← your Alex image
            characterSide = CharacterSide.LEFT,
            dialogText = "\"That makes sense… but what made you come back now? " +
                    "Something must have happened.\"",
            speakerLabel = "Alex",
            navigation = SceneNavigation.Choice(
                option1 = ChoiceOption(
                    label = "Tell the Truth",
                    nextId = "truth_cancer",
                    timeCost = 30
                ),
                option2 = ChoiceOption(
                    label = "Tell a Lie",
                    nextId = "lie_miss",
                    timeCost = 10
                )
            )
        )

        // ── Truth branch ──────────────────────────────────────────────────────
        scenes += GameScene(
            id = "truth_cancer",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            dialogText = "You take a slow breath.\n\n" +
                    "\"Alex… I have cancer. " +
                    "The doctors say I don't have long. " +
                    "I came back because I want to make amends — " +
                    "with you, with everyone — before it's too late.\"",
            speakerLabel = username,
            navigation = SceneNavigation.Next("truth_cancer2")
        )

        // ── Lie branch ────────────────────────────────────────────────────────
        scenes += GameScene(
            id = "lie_miss",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            dialogText = "You manage a small smile.\n\n" +
                    "\"Honestly? I just… missed you. " +
                    "Missed all of you. The city got too loud " +
                    "and I needed to hear this street again.\"",
            speakerLabel = username,
            navigation = SceneNavigation.Next("path_park")
        )

        //------ truth -------------------------
        scenes += GameScene(
            id = "truth_cancer2",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            characterImageRes = R.drawable.char_alex,     // ← your Alex image
            characterSide = CharacterSide.LEFT,
            dialogText = "may i ask what type of cancer it is  ",
            speakerLabel = "Alex",
            navigation = SceneNavigation.Next("truth_cancer3")

        )
        scenes += GameScene(
            id = "truth_cancer3",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = "Character A",
            characterImageRes = R.drawable.char_alex,     // ← your Alex image
            characterSide = CharacterSide.LEFT,
            dialogText = "may i ask what type of cancer it is  ",
            speakerLabel = "Alex",
            navigation =  SceneNavigation.Next("truth_cancer4")
        )
        scenes += GameScene(
            id = "truth_cancer4",
            backgroundType = BackgroundType.NEIGHBOURHOOD,
            characterName = null,
            dialogText = "well the doctors don't even know what type is it aside from its a lung cancer",
            speakerLabel = username,
            navigation =  SceneNavigation.Next("path_park")
        )

        scenes += GameScene(
            id = "path_park",
            backgroundType = BackgroundType.PARK_PATH,
            characterName = "Character A",
            characterImageRes = R.drawable.char_alex,
            characterSide = CharacterSide.LEFT,
            dialogText = "well anyways i think the others would love to welcome you back ",
            speakerLabel = "Alex",
            navigation =  SceneNavigation.Next("path_park2")
        )

        scenes += GameScene(
            id = "path_park2",
            backgroundType = BackgroundType.PARK_PATH,
            characterName = null,
            dialogText = "As you walk back to your old house with Alex wave of nostalgia hits you " +
                    "as countless memory floods back to you all the fun times you had with your friends ",
            speakerLabel = username,
            navigation =  SceneNavigation.Next("house")
        )


        scenes += GameScene(
            id = "house",
            backgroundType = BackgroundType.HOUSE,
            characterName ="Character A",
            characterImageRes = R.drawable.char_alex,
            characterSide = CharacterSide.LEFT,
            dialogText = "Well were here so see you tomorrow ",
            speakerLabel = "Alex",
            navigation =  SceneNavigation.Next("house2")
        )

        scenes += GameScene(
            id = "house2",
            backgroundType = BackgroundType.HOUSE,
            characterName =null,
            dialogText = "Yeah see you tomorrow Alex",
            speakerLabel = username,
            navigation = SceneNavigation.Next("house3")

        )

        scenes += GameScene(
            id = "house3",
            backgroundType = BackgroundType.HOUSE,
            characterName =null,
            dialogText = "Welp home sweet home , as you spend the night in you old house you wake up and head outside to take a walk " +
                    "but you suddenly see your old friends outside  ",
            speakerLabel = username,
            navigation =  SceneNavigation.Next("house4")
        )

        scenes += GameScene(
            id = "house4",
            backgroundType = BackgroundType.BLACK_SCREEN,
            characterName = null,
            dialogText = "FOR NOW THIS IS ALL I CAN MAKE SORRY IF THE GAME WAS SO SHORT " +
                    "I RAN OUT OF TIME TO DO THIS PROJECT PLS SIR JUST ACCEPT THIS AND THANK YOU " +
                    "THIS IS A DEMO NOT HE FULL GAME FOR THE FULL ACCESS PLS GIVE ME MORE TIME ",
            speakerLabel = "Narrator",
            navigation = SceneNavigation.End
        )



        return scenes.associateBy { it.id }
    }

    const val FIRST_SCENE_ID = "office_work"
}
