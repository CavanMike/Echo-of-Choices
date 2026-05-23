package com.andriod.echoofchoices.screen.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.app.CustomApp
import com.andriod.echoofchoices.screen.dashboard.DashboardActivity

class GameActivity : AppCompatActivity() {

    // ── State ─────────────────────────────────────────────────────────────────
    private lateinit var scenes: Map<String, GameScene>
    private lateinit var currentScene: GameScene
    private var timeLeft: Int = 365
    private lateinit var username: String

    // ── Views ─────────────────────────────────────────────────────────────────
    private lateinit var backgroundView: View
    private lateinit var tvTimeLeft: TextView

    // Character — now a free-floating ImageView, no card wrapper
    private lateinit var ivCharacter: ImageView

    // Dialog box
    private lateinit var cardDialog: CardView
    private lateinit var tvSpeaker: TextView
    private lateinit var tvDialog: TextView

    // Navigation
    private lateinit var btnNext: Button
    private lateinit var layoutChoices: LinearLayout
    private lateinit var btnChoice1: Button
    private lateinit var btnChoice2: Button

    // ── Lifecycle ─────────────────────────────────────────────────────────────
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        username = (application as CustomApp).getUser().username
        scenes   = SceneRepository.buildScenes(username)

        bindViews()
        showScene(SceneRepository.FIRST_SCENE_ID)
    }

    // ── View binding ──────────────────────────────────────────────────────────
    private fun bindViews() {
        backgroundView = findViewById(R.id.view_background)
        tvTimeLeft     = findViewById(R.id.tv_time_left)

        // Character sprite — directly on screen, no card
        ivCharacter    = findViewById(R.id.iv_character)

        // Dialog
        cardDialog     = findViewById(R.id.card_dialog)
        tvSpeaker      = findViewById(R.id.tv_speaker)
        tvDialog       = findViewById(R.id.tv_dialog)

        // Navigation
        btnNext        = findViewById(R.id.btn_next)
        layoutChoices  = findViewById(R.id.layout_choices)
        btnChoice1     = findViewById(R.id.btn_choice1)
        btnChoice2     = findViewById(R.id.btn_choice2)
    }

    // ── Scene rendering ───────────────────────────────────────────────────────
    private fun showScene(sceneId: String) {
        val scene = scenes[sceneId] ?: return
        currentScene = scene

        applyBackground(scene.backgroundType)
        applyCharacter(scene)
        applyDialog(scene)
        applyNavigation(scene.navigation)
        updateTimeHud()
    }

    private fun applyBackground(type: BackgroundType) {
        backgroundView.setBackgroundResource(
            when (type) {
                BackgroundType.OFFICE           -> R.drawable.bg_office
                BackgroundType.HOSPITAL         -> R.drawable.bg_hospital
                BackgroundType.BLACK_SCREEN     -> R.drawable.bg_black
                BackgroundType.NEIGHBOURHOOD    -> R.drawable.bg_neighbourhood
                BackgroundType.DASHBOARD_RETURN -> R.drawable.bg_black
                BackgroundType.PARK_PATH        ->R.drawable.bg_park_path
                BackgroundType.HOUSE            ->R.drawable.house
            }
        )
    }

    private fun applyCharacter(scene: GameScene) {
        if (scene.characterImageRes != null) {
            // Show the sprite directly on screen — no card box
            ivCharacter.visibility = View.VISIBLE
            ivCharacter.setImageResource(scene.characterImageRes)

            // Dim when character is present but not the active speaker
            if (scene.speakerLabel == scene.characterName) {
                ivCharacter.clearColorFilter()              // full brightness
            } else {
                ivCharacter.setColorFilter(                 // 50 % dark tint
                    android.graphics.Color.parseColor("#80000000"),
                    android.graphics.PorterDuff.Mode.SRC_ATOP
                )
            }
        } else {
            ivCharacter.visibility = View.GONE
        }
    }

    private fun applyDialog(scene: GameScene) {
        if (scene.speakerLabel != null) {
            tvSpeaker.visibility = View.VISIBLE
            tvSpeaker.text = scene.speakerLabel
        } else {
            tvSpeaker.visibility = View.GONE
        }
        tvDialog.text = scene.dialogText
    }

    private fun applyNavigation(nav: SceneNavigation) {
        when (nav) {
            is SceneNavigation.Next -> {
                btnNext.visibility       = View.VISIBLE
                layoutChoices.visibility = View.GONE
                btnNext.text             = "Next ▶"
                btnNext.setOnClickListener { showScene(nav.nextId) }
            }
            is SceneNavigation.Choice -> {
                btnNext.visibility       = View.GONE
                layoutChoices.visibility = View.VISIBLE
                btnChoice1.text          = nav.option1.label
                btnChoice2.text          = nav.option2.label
                btnChoice1.setOnClickListener {
                    applyTimeCost(nav.option1.timeCost)
                    showScene(nav.option1.nextId)
                }
                btnChoice2.setOnClickListener {
                    applyTimeCost(nav.option2.timeCost)
                    showScene(nav.option2.nextId)
                }
            }
            is SceneNavigation.End -> {
                btnNext.visibility       = View.VISIBLE
                layoutChoices.visibility = View.GONE
                btnNext.text             = "Return to Town"
                btnNext.setOnClickListener { returnToDashboard() }
            }
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private fun applyTimeCost(days: Int) {
        timeLeft = (timeLeft - days).coerceAtLeast(0)
        updateTimeHud()
    }

    private fun updateTimeHud() {
        tvTimeLeft.text = "Time Left: $timeLeft days"
    }

    private fun returnToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}
